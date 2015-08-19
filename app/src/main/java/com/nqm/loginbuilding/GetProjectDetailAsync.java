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
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by sirawang on 02/07/2015.
 */
public class GetProjectDetailAsync extends AsyncTask<String, Integer, String> {


    private Context context;
    private ProgressDialog ringProgressDialog;
    private String projectName;

    // Constructor
    GetProjectDetailAsync(Context context, String name) {
        this.context = context;
        this.projectName = name;
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

        HttpPost httppost = new HttpPost(Config.url_getProjectDetail);

        HttpClient httpclient = new DefaultHttpClient();

        try {
            AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
                    new AndroidMultiPartEntity.ProgressListener() {

                        @Override
                        public void transferred(long num) {
                        }
                    });


            // Adding file data to http body

            entity.addPart("name", new StringBody(projectName));

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
            ringProgressDialog.dismiss();
        }

        if (result != null && !error) {

            try {
                JSONArray jResult = (new JSONObject(result)).getJSONArray("message");
                Log.e("GetArray", "Success");
                int size = jResult.length();

                if(size!=0){
                    JSONObject obj = jResult.getJSONObject(0);
                    Project.getInstance().setBuildingID(obj.getString("Building_ID"));
                    Project.getInstance().setName(obj.getString("Name"));
                    Project.getInstance().setService(obj.getString("Service"));
                    Project.getInstance().setAccessSite(obj.getString("Access_Site"));
                    Project.getInstance().setContactName(obj.getString("Contact_Name"));
                    Project.getInstance().setContactNumber(obj.getString("Contact_Number"));
                    Project.getInstance().setIMEI_AWN(obj.getString("IMEI_AWN"));
                    Project.getInstance().setIMEI_DTAC(obj.getString("IMEI_DTAC"));
                    Project.getInstance().setIMEI_TRUEH(obj.getString("IMEI_TRUEH"));
                    Project.getInstance().setIMEI_3BB(obj.getString("IMEI_3BB"));
                    Project.getInstance().setAddress(obj.getString("Address"));
                    Project.getInstance().setTambon(obj.getString("Tambon"));
                    Project.getInstance().setDistrict(obj.getString("District"));
                    Project.getInstance().setProvince(obj.getString("Province"));
                    Project.getInstance().setPostCode(obj.getString("Postcode"));
                    Project.getInstance().setBuildingDetail(obj.getString("Building_Detail"));
                    Project.getInstance().setCreateDate(new Date());
                    Project.getInstance().setLattitude(obj.getString("Latitude"));
                    Project.getInstance().setLongitude(obj.getString("Longitude"));
                    Project.getInstance().setUser(obj.getString("User"));
                    Project.getInstance().setLAC(obj.getString("LAC"));
                    Project.getInstance().setCID(obj.getString("CID"));
                    Project.getInstance().setEditDate(new Date());
                    Project.getInstance().setEditUser(obj.getString("User"));
                }

                Project.getInstance().GetSessionList();
                //Project.getInstance().ShowProjectDetailActivity();
            } catch (JSONException e) {
                Log.e("ConnectServer", "Error parsing data " + e.toString());
                ringProgressDialog.dismiss();
            }

        } else {
            ringProgressDialog.dismiss();
        }

		ringProgressDialog.dismiss();
    }

}