package com.nqm.loginbuilding;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.List;

/**
 * Created by sirawang on 02/07/2015.
 */
public class GetProjectListAsync  extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressDialog ringProgressDialog;

    // Constructor
    GetProjectListAsync(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
        // Show loading dialog
        String loadtext = "Get information...";
        ringProgressDialog = ProgressDialog.show(context, "Please wait ...",
                loadtext, false);
        ringProgressDialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... arg0) {

        String responseString = null;

        HttpPost httppost = new HttpPost(Config.url_getProjectList);

        HttpClient httpclient = new DefaultHttpClient();

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                        }
                    });

            // Adding file data to http body
            // Nothing
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
            //((ProjectListActivity)context).dismissLoadingTopBottom();
        }

        //ArrayList<FeedItem> list = new ArrayList<FeedItem>();

        if (result != null && !error) {

            try {
                JSONArray jResult = (new JSONObject(result)).getJSONArray("message");
                Log.e("GetArray", "Success");
                int size = jResult.length();

                List<String> projectList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    JSONObject obj = jResult.getJSONObject(i);
                    projectList.add(obj.getString("Name"));
                }

                ((ProjectListActivity) context).UpdateProjectList(projectList);

            } catch (JSONException e) {
                Log.e("ConnectServer", "Error parsing data " + e.toString());
                ringProgressDialog.dismiss();
            }

        } else {
            ringProgressDialog.dismiss();
        }

        ringProgressDialog.dismiss();

//        Intent i = new Intent(context,ProjectDetailActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ((ProjectListActivity)context).startActivity(i);

    }

}