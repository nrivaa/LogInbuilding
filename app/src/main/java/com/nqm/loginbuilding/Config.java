package com.nqm.loginbuilding;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by sirawang on 03/07/2015.
 */
public class Config {
    public static String user = "";
    public static String url_login = "http://49.231.24.106/TranstagramInbuilding/login.php";
    public static String url_getProjectList = "http://49.231.24.106/TranstagramInbuilding/GetProjectList.php";
    public static String url_getProjectDetail = "http://49.231.24.106/TranstagramInbuilding/GetProjectDetail.php";
    public static String url_getSessionDetail = "http://49.231.24.106/TranstagramInbuilding/GetSessionDetail.php";
    public static String url_addProject = "http://49.231.24.106/TranstagramInbuilding/AddProject.php";
    public static String url_addSession = "http://49.231.24.106/TranstagramInbuilding/AddSession.php";
    public static String url_editSession = "http://49.231.24.106/TranstagramInbuilding/EditSession.php";

    // / RESIZE IMAGE QUALITY
    public static void resizeImageFile(File sourceFile) {
        try {
            Log.d("fileupload", "Size Before: " + sourceFile.length());
            InputStream is = new FileInputStream(sourceFile);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2; // subsampling
            int compress = 85; // 90
            BitmapFactory.decodeStream(is, null, options).compress(
                    Bitmap.CompressFormat.JPEG, compress,
                    new FileOutputStream(sourceFile));
            Log.d("fileupload", "Size After: " + sourceFile.length());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
