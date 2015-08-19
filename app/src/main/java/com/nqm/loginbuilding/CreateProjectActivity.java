package com.nqm.loginbuilding;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.net.Uri;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.Toast;


public class CreateProjectActivity extends ActionBarActivity {

    private String[] arraySpinner;
    private TextView tvBuildingID,tvName,tvContact,tvNumber,tvImsiA,tvImsiD,tvImsiT,tvImsi3,tvAddress,tvTambon,tvDistrict,tvPostCode,tvDetail;
    private ImageButton btnCapture;
    private RadioGroup rgService, rgAccess;

    // Location loc ;
    LocationManager lm;
    String latitude;
    String longitude;
    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Transtagram Camera";
    private Uri fileUri; // file URL to store image/video
    public List<String> listdata = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        this.arraySpinner = new String[] {
                "Krabi",
                "Bangkok",
                "Kanchanaburi",
                "Kalasin",
                "Kamphaeng Phet",
                "Khon Kaen",
                "Chanthaburi",
                "Chachoengsao",
                "Chiang Mai",
                "Chiang Rai",
                "Chon Buri",
                "Chai Nat",
                "Chaiyaphum",
                "Chumphon",
                "Trang",
                "Trat",
                "Tak",
                "Nakhon Nayok",
                "Nakhon Pathom",
                "Nakhon Phanom",
                "Nakhon Ratchasima",
                "Nakhon Si Thammarat",
                "Nakhon Sawan",
                "Nonthaburi",
                "Narathiwat",
                "Nan",
                "Buriram",
                "Pathum Thani",
                "Prachuap Khiri Khan",
                "Prachin Buri",
                "Pattani",
                "Phetchaburi",
                "Phetchabun",
                "Phrae",
                "Phayao",
                "Phangnga",
                "Phatthalung",
                "Phichit",
                "Phitsanulok",
                "Phuket",
                "Mae Hong Son",
                "Maha Sarakham",
                "Mukdahan",
                "Yasothon",
                "Yala",
                "Roi Et",
                "Ranong",
                "Rayong",
                "Ratchaburi",
                "Lop Buri",
                "Lampang",
                "Loei",
                "Lamphun",
                "Sisaket",
                "Sakon Nakhon",
                "Songkhla",
                "Satun",
                "Samut Prakan",
                "Samut Songkhram",
                "Samut Sakhon",
                "Sa Kaeo",
                "Sara Buri",
                "Sing Buri",
                "Sukhothai",
                "Suphan Buri",
                "Surat Thani",
                "Surin",
                "Nong Khai",
                "Nong Bua Lamphu",
                "Ang Thong",
                "Amnat Charoen",
                "Udon Thani",
                "Uttaradit",
                "Uthai Thani",
                "Ubon Ratchathani",
                "Ayutthaya",
                "Thonburi"
        };
        Spinner s = (Spinner) findViewById(R.id.spnProvince);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        final Context ctx = getApplicationContext();

        tvBuildingID = (TextView)findViewById(R.id.createProject_BuildingID);
        tvName = (TextView)findViewById(R.id.createProject_BuildingName);
        tvContact = (TextView)findViewById(R.id.createProject_contactName);
        tvNumber = (TextView)findViewById(R.id.createProject_contactNumber);
        tvImsiA = (TextView)findViewById(R.id.createProject_imei_awn);
        tvImsiD = (TextView)findViewById(R.id.createProject_imei_dtac);
        tvImsiT = (TextView)findViewById(R.id.createProject_imei_trueh);
        tvImsi3 = (TextView)findViewById(R.id.createProject_imei_3bb);
        tvAddress = (TextView)findViewById(R.id.createProject_address);
        tvTambon = (TextView)findViewById(R.id.createProject_tambon);
        tvDistrict = (TextView)findViewById(R.id.createProject_district);
        tvPostCode = (TextView)findViewById(R.id.createProject_postcode);
        tvDetail = (TextView)findViewById(R.id.createProject_buildingDetail);
        btnCapture = (ImageButton)findViewById(R.id.createProject_imgbtn);
        rgService = (RadioGroup)findViewById(R.id.rbService);
        rgAccess = (RadioGroup)findViewById(R.id.rbAccess);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        btnCapture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        Button btn_submit = (Button)findViewById(R.id.projectCreate_btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx,ProjectDetailActivity.class);
                startActivity(i);
            }
        });

        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    // SubmitProject
    private void SubmitProject(boolean submitFlag) {


        //Upload to Async

    }

    /*
	 * Capturing Camera Image will launch camera app-request image capture
	 */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

        //Get lat,long
        GPSTracker  gps = new GPSTracker(CreateProjectActivity.this);
        // check if GPS enabled
        if(gps.canGetLocation()){
            latitude = String.valueOf(gps.getLatitude());
            longitude = String.valueOf(gps.getLongitude());
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        Log.e("gps :","Lat="+latitude+",Lng="+longitude);


    }
    /*
	 * Creating file uri to store image/video
	 */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    /*
	 * returning image / video
	 */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    /*
	 * Display image from a path to ImageView
	 */
    private void previewCapturedImage() {
        try {

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            btnCapture.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
