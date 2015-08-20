package com.nqm.loginbuilding;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sirawang on 03/07/2015.
 */
public class LoginAsync   extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressDialog ringProgressDialog;
    String fileConfigName = "EazyTest.txt";
    String user;
    String password;
    LoginAsync(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {

        String loadtext = "Get information...";
        ringProgressDialog = ProgressDialog.show(context, "Please wait ...",
                loadtext, false);
        ringProgressDialog.setCancelable(false);

    }

    @Override
    protected String doInBackground(String... arg0) {

        String responseString = null;

        HttpPost httppost = new HttpPost(Config.url_login);

        HttpClient httpclient = new DefaultHttpClient();

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                        }
                    });


            // Adding file data to http body
            user = arg0[0];
            password = arg0[1];
            entity.addPart("username", new StringBody(arg0[0]));
            entity.addPart("password", new StringBody(arg0[1]));

            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
                Config.user=arg0[0];
            } else {
                responseString = "Error occurred! Http Status Code: "
                        + statusCode;
            }

        } catch (ClientProtocolException e) {
            responseString = e.toString();
        } catch (IOException e) {
            responseString = e.toString();
        }

        return responseString;
    }

    protected void onPostExecute(String result) {

        Log.e("Result", "Result:" + result);

        if (result.contains("Success")){
            Toast.makeText(context,"Login Success!!",Toast.LENGTH_SHORT).show();

            // Write File Config
                String md5string = user + "," + password;
                writeFileInternalStorage(md5string, context, fileConfigName);

            Intent i = new Intent(context,ProjectListActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ((LoginActivity)context).startActivity(i);
        }
        else
        {
            Toast.makeText(context,"Login Fail!!",Toast.LENGTH_SHORT).show();
        }


        //boolean error = true;

//        try {
//            JSONObject responseJSON = new JSONObject(result);
//            error = responseJSON.getBoolean("error");
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            ((FeedActivity)context).dismissLoadingTopBottom();
//        }
//
//        ArrayList<FeedItem> list = new ArrayList<FeedItem>();
//
//        if (result != null && !error) {
//
//            try {
//                JSONArray jResult = (new JSONObject(result)).getJSONArray("message");
//                Log.e("GetArray", "Success");
//                int size = jResult.length();
//
//                for (int i = 0; i < size; i++) {
//                    FeedItem item = new FeedItem();
//
//                    JSONObject obj = jResult.getJSONObject(i);
//                    item.setId(obj.getString("id"));
//                    item.setOperator(obj.getString("operator"));
//                    item.setLat(obj.getString("lat"));
//                    item.setLng(obj.getString("lng"));
//                    item.setAddress(obj.getString("address"));
//                    item.setStatus(obj.getString("status"));
//                    item.setPicPath(obj.getString("picPath"));
//                    item.setRemark(obj.getString("remark"));
//                    item.setTranDate(obj.getString("tranDate"));
//                    item.setUser(obj.getString("user"));
//
//                    list.add(item);
//
//                }
//
//
//                ((FeedActivity) context).setList(list);
//
//            } catch (JSONException e) {
//                Log.e("ConnectServer", "Error parsing data " + e.toString());
//                ((FeedActivity) context).dismissLoadingTopBottom();
//            }
//
//        } else {
//            ((FeedActivity) context).dismissLoadingTopBottom();
//        }

        ringProgressDialog.dismiss();
    }

    public static void writeFileInternalStorage(String strWrite,
                                                Context context, String fileName) {
        try {
            // Check if Storage is Readable
            if (isSdReadable()) // isSdReadable()e method is define at bottom of
            // the post
            {
                String smsfilename = fileName;
                FileOutputStream fos = context.openFileOutput(smsfilename,
                        Context.MODE_PRIVATE);
                fos.write(strWrite.getBytes());
                fos.flush();
                fos.close();
                Log.e("Writefile", "WRITE " + fileName + " : "  + strWrite);
            }
        } catch (Exception e) {
            // Your Code
        }
    }

    public static boolean isSdReadable() {

        boolean mExternalStorageAvailable = false;
        try {
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                mExternalStorageAvailable = true;
                Log.i("isSdReadable", "External storage card is readable.");
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                Log.i("isSdReadable", "External storage card is readable.");
                mExternalStorageAvailable = true;
            } else {
                // Something else is wrong. It may be one of many other
                // states, but all we need to know is we can neither read nor
                // write
                mExternalStorageAvailable = false;
            }
        } catch (Exception ex) {

        }
        return mExternalStorageAvailable;
    }

}