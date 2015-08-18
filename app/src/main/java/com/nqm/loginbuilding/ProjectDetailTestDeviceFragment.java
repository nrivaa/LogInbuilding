package com.nqm.loginbuilding;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProjectDetailTestDeviceFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View windows = inflater.inflate(R.layout.activity_project_detail_test_device_fragment, container, false);
        //((TextView)windows.findViewById(R.id.textView)).setText("Windows");
        SetProjectInfo(windows);

        return windows;
    }

    public void SetProjectInfo(View window){
        // Assign Control value
        ((TextView)window.findViewById(R.id.projectDetail_imei_awn)).setText(Project.getInstance().getIMEI_AWN());
        ((TextView)window.findViewById(R.id.projectDetail_imei_dtac)).setText(Project.getInstance().getIMEI_DTAC());
        ((TextView)window.findViewById(R.id.projectDetail_imei_trueh)).setText(Project.getInstance().getIMEI_TRUEH());
        ((TextView)window.findViewById(R.id.projectDetail_imei_3bb)).setText(Project.getInstance().getIMEI_3BB());



    }
}