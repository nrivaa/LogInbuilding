package com.nqm.loginbuilding;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sirawang on 02/07/2015.
 */
public class GetSessionDetailAsync  extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressDialog ringProgressDialog;
    private String id;

    GetSessionDetailAsync(Context context, String id) {
        this.context = context;
        this.id = id;
        ringProgressDialog = new ProgressDialog(context);
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

        HttpPost httppost = new HttpPost(Config.url_getSessionDetail);

        HttpClient httpclient = new DefaultHttpClient();

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                        }
                    });


            // Adding file data to http body

            entity.addPart("Building_ID", new StringBody(id));

            httppost.setEntity(entity);

            // Making server call
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity r_entity = response.getEntity();

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                // Server response
                responseString = EntityUtils.toString(r_entity);
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
        boolean error = true;

        try {
            JSONObject responseJSON = new JSONObject(result);
            error = responseJSON.getBoolean("error");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        List<Session> list = new ArrayList<>();

        if (result != null && !error) {

            try {
                JSONArray jResult = (new JSONObject(result)).getJSONArray("message");
                Log.e("GetArray", "Success");
                int size = jResult.length();

                for (int i = 0; i < size; i++) {
                    Session item = new Session();

                    JSONObject obj = jResult.getJSONObject(i);
                    item.setPoint(obj.getString("Point"));
                    item.setFloor(obj.getString("Floor"));
                    item.setDescription(obj.getString("Description"));
                    item.setLocation(obj.getString("Location"));
                    item.setLoc_Specification(obj.getString("Loc_Specification"));
                    item.setStatus_AWN(obj.getString("AWN_Status"));
                    item.setStatus_DTAC(obj.getString("DTAC_Status"));
                    item.setStatus_TRUEH(obj.getString("TRUEH_Status"));
                    item.setStatus_3BB(obj.getString("3BB_Status"));
                    item.setRemark(obj.getString("Remark"));
                    item.setCreateDate(new Date());
                    item.setConfirmStatus(obj.getString("Confirm_Status"));
                    item.setConfirmDate(new Date());
                    item.setUser(obj.getString("User"));

                    list.add(item);
                }

                Project.getInstance().setSessions(list);
                Project.getInstance().ShowProjectDetailActivity();
                //((FeedActivity) context).setList(list);

            } catch (JSONException e) {
                Log.e("ConnectServer", "Error parsing data " + e.toString());
            }

        } else {
            Project.getInstance().setSessions(list);
            Project.getInstance().ShowProjectDetailActivity();
        }

        ringProgressDialog.dismiss();
    }

}