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

import java.io.IOException;

/**
 * Created by sirawang on 02/07/2015.
 */
public class AddProjectAsync extends AsyncTask<String, Integer, String> {


private Context context;
private ProgressDialog ringProgressDialog;
private int curPage;

    AddProjectAsync(Context context, int page) {
        this.context = context;
        this.curPage = page;
        //ringProgressDialog = new ProgressDialog(context);
        }

protected void onPreExecute() {

        String loadtext = "Get information...";
        ringProgressDialog = ProgressDialog.show(context, "Please wait ...",
        loadtext, false);
        ringProgressDialog.setCancelable(false);

        }

@Override
protected String doInBackground(String... arg0) {

        return InvokeGetFeed();
        }

public String InvokeGetFeed() {
        String responseString = null;

        HttpPost httppost = new HttpPost("http://www.youtube.com");

        HttpClient httpclient = new DefaultHttpClient();

        try {
        AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
        new AndroidMultiPartEntity.ProgressListener() {

@Override
public void transferred(long num) {
        }
        });


        // Adding file data to http body

        entity.addPart("page", new StringBody(Integer.toString(curPage)));

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
        }