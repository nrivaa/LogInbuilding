//package com.nqm.loginbuilding;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
///**
// * Created by sirawang on 8/10/2015 AD.
// */
//public class Camera extends Activity {
//
//    Context context;
//
//    // Activity request codes
//    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
//    public static final int MEDIA_TYPE_IMAGE = 1;
//
//    // directory name to store captured images and videos
//    private static final String IMAGE_DIRECTORY_NAME = "Inbuilding Camera";
//
//    private Uri fileUri; // file URL to store image/video
//
//    /**
//     * Checking device has camera hardware or not
//     * */
//    private boolean isDeviceSupportCamera() {
//        if (getApplicationContext().getPackageManager().hasSystemFeature(
//                PackageManager.FEATURE_CAMERA)) {
////        if (context.getPackageManager().hasSystemFeature(
////                PackageManager.FEATURE_CAMERA)) {
//            // this device has a camera
//            return true;
//        } else {
//            // no camera on this device
//            return false;
//        }
//    }
//
//    /*
//     * Here we store the file url as it will be null after returning from camera
//     * app
//     */
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        // save file url in bundle as it will be null on scren orientation
//        // changes
//        outState.putParcelable("file_uri", fileUri);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//
//        // get the file url
//        fileUri = savedInstanceState.getParcelable("file_uri");
//    }
//
//    /*
//     * Capturing Camera Image will launch camera app-request image capture
//     */
//    private void captureImage() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        // start the image capture Intent
//        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//    }
//
//    /**
//     * Receiving activity result method will be called after closing the camera
//     * */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // if the result is capturing Image
//        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // successfully captured the image
//                // display it in image view
//                previewCapturedImage();
//            } else if (resultCode == RESULT_CANCELED) {
//                // user cancelled Image capture
//                Toast.makeText(getApplicationContext(),
//                        "User cancelled image capture", Toast.LENGTH_SHORT)
//                        .show();
//            } else {
//                // failed to capture image
//                Toast.makeText(getApplicationContext(),
//                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        }
//    }
//
//    /*
//     * Display image from a path to ImageView
//     */
//    private void previewCapturedImage() {
//        try {
//
//            // bimatp factory
//            BitmapFactory.Options options = new BitmapFactory.Options();
//
//            // downsizing image as it throws OutOfMemory Exception for larger
//            // images
//            options.inSampleSize = 8;
//
//            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
//                    options);
//
//
//
//            btnCapture.setImageBitmap(bitmap);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    /**
//     * ------------ Helper Methods ----------------------
//     * */
//
//	/*
//	 * Creating file uri to store image/video
//	 */
//    public Uri getOutputMediaFileUri(int type) {
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
//
//    /*
//     * returning image / video
//     */
//    private static File getOutputMediaFile(int type) {
//
//        // External sdcard location
//        File mediaStorageDir = new File(
//                Environment
//                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
//                IMAGE_DIRECTORY_NAME);
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
//                        + IMAGE_DIRECTORY_NAME + " directory");
//                return null;
//            }
//        }
//
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
//                Locale.getDefault()).format(new Date());
//        File mediaFile;
//        if (type == MEDIA_TYPE_IMAGE) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator
//                    + "IMG_" + timeStamp + ".jpg");
//
//        } else {
//            return null;
//        }
//
//        return mediaFile;
//    }
//
//    public void UpdatePass() {
//        Toast.makeText(getApplicationContext(), "Update Success",
//                Toast.LENGTH_SHORT).show();
//
//        AppController.setProblemType("%");
//        AppController.setProblemStatus("%");
//        AppController.setProblemZone("%");
//
//        Intent i = new Intent(pm_SubmitOnsiteActivity.this, FeedActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(i);
//
//    }
//
//    public void UpdateFail() {
//        Toast.makeText(getApplicationContext(), "Update Fail",
//                Toast.LENGTH_SHORT).show();
//
//    }
//
//    public Location requestUpdatesFromProvider(final String provider,
//                                               String error) {
//        Location location = null;
//        if (lm.isProviderEnabled(provider)) {
//            lm.requestLocationUpdates(provider, 1000, 10, listener);
//            location = lm.getLastKnownLocation(provider);
//        } else {
//            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
//        }
//        return location;
//    }
//
//    public final LocationListener listener = new LocationListener() {
//        public void onLocationChanged(Location location) {
//            Log.e("listen", String.format("%.7f", location.getLatitude()) + "/"
//                    + String.format("%.7f", location.getLongitude()));
//
//        }
//
//        public void onProviderDisabled(String provider) {
//        }
//
//        public void onProviderEnabled(String provider) {
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//    };
//
//    /**
//     * Uploading the file to server
//     * */
//    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
//
//        private Context ctx;
//        public ProgressDialog ringProgressDialog;
//
//        UploadFileToServer(Context ctx) {
//            this.ctx = ctx;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            // setting progress bar to zero
//            // progressBar.setProgress(0);
//            super.onPreExecute();
//            ringProgressDialog = ProgressDialog.show(ctx, "Please wait ...",
//                    "Update Status...", true);
//            ringProgressDialog.setCancelable(false);
//
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            return uploadFile();
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... progress) {
//            // // Making progress bar visible
//            // progressBar.setVisibility(View.VISIBLE);
//            //
//            // // updating progress bar value
//            // progressBar.setProgress(progress[0]);
//            //
//            // // updating percentage value
//            // txtPercentage.setText(String.valueOf(progress[0]) + "%");
//        }
//
//        @SuppressWarnings("deprecation")
//        private String uploadFile() {
//            String responseString = null;
//
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httppost = new HttpPost(ConfigClass.URL_PM_ONSITE_UP);
//            try {
//                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(
//                        new ProgressListener() {
//
//                            @Override
//                            public void transferred(long num) {
//                                publishProgress((int) ((num / (float) totalSize) * 100));
//                            }
//                        });
//
//                Charset chars = Charset.forName("UTF-8");
//                File sourceFile = new File(fileUri.getPath());
//                ConfigClass.resizeImageFile(sourceFile);
//
//                // Adding file data to http body
//                entity.addPart("image", new FileBody(sourceFile));
//
//                // Extra parameters if you want to pass to server
//                entity.addPart("onsiteUser", new StringBody(AppController.getUser()));
//                entity.addPart("lat", new StringBody(latitude));
//                entity.addPart("lng", new StringBody(longitude));
//                entity.addPart("id", new StringBody(eID));
//                entity.addPart("actionType", new StringBody(type));
//
//                Log.d("type",type);
//
//                if(type.contains("Before")){
//                    entity.addPart("problem", new StringBody(main,chars));
//                    if(onsite_id != null){
//                        entity.addPart("onsite_id", new StringBody(onsite_id,chars));
//                    }
//                }
//                else{
//                    entity.addPart("onsite_id", new StringBody(onsite_id,chars));
//                    entity.addPart("action", new StringBody(main,chars));
//                    entity.addPart("nextAct", new StringBody(nextAct,chars));
//                    entity.addPart("summary", new StringBody(summary,chars));
//                }
//
//
//                totalSize = entity.getContentLength();
//                httppost.setEntity(entity);
//
//                // Making server call
//                HttpResponse response = httpclient.execute(httppost);
//                HttpEntity r_entity = response.getEntity();
//
//                int statusCode = response.getStatusLine().getStatusCode();
//                if (statusCode == 200) {
//                    // Server response
//                    responseString = EntityUtils.toString(r_entity);
//                } else {
//                    responseString = "Error occurred! Http Status Code: "
//                            + statusCode;
//                }
//
//            } catch (ClientProtocolException e) {
//                responseString = e.toString();
//            } catch (IOException e) {
//                responseString = e.toString();
//            }
//
//            Log.i("Response", responseString);
//
//            ringProgressDialog.dismiss();
//            new ShowIssueDetail(ctx,eID,"PM").execute();
//            finish();
//
//            return responseString;
//
//        }
//    }
//}
