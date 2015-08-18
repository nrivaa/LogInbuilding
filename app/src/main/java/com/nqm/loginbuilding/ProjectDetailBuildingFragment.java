package com.nqm.loginbuilding;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProjectDetailBuildingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View windows = inflater.inflate(R.layout.activity_project_detail_building_fragment, container, false);
        //((TextView)windows.findViewById(R.id.textView)).setText("Windows");
        SetProjectInfo(windows);


        return windows;
    }

    public void SetProjectInfo(View window){
        // Assign Control value
        ((TextView)window.findViewById(R.id.projectDetail_BuildingID)).setText(Project.getInstance().getBuildingID());
        ((TextView)window.findViewById(R.id.projectDetail_Name)).setText(Project.getInstance().getName());
        ((TextView)window.findViewById(R.id.projectDetail_Service)).setText(Project.getInstance().getService());
//        ((TextView)window.findViewById(R.id.projectDetail_accessSite)).setText(Project.getInstance().getAccessSite());
        ((TextView)window.findViewById(R.id.projectDetail_contactName)).setText(Project.getInstance().getContactName());
        ((TextView)window.findViewById(R.id.projectDetail_contactNumber)).setText(Project.getInstance().getContactNumber());
//        ((TextView)window.findViewById(R.id.projectDetail_imei_awn)).setText(Project.getInstance().getIMEI_AWN());
//        ((TextView)window.findViewById(R.id.projectDetail_imei_dtac)).setText(Project.getInstance().getIMEI_DTAC());
//        ((TextView)window.findViewById(R.id.projectDetail_imei_trueh)).setText(Project.getInstance().getIMEI_TRUEH());
//        ((TextView)window.findViewById(R.id.projectDetail_imei_3bb)).setText(Project.getInstance().getIMEI_3BB());
        ((TextView)window.findViewById(R.id.projectDetail_address)).setText(Project.getInstance().getAddress());
        ((TextView)window.findViewById(R.id.projectDetail_tambon)).setText(Project.getInstance().getTambon());
        ((TextView)window.findViewById(R.id.projectDetail_district)).setText(Project.getInstance().getDistrict());
        ((TextView)window.findViewById(R.id.projectDetail_province)).setText(Project.getInstance().getProvince());
        ((TextView)window.findViewById(R.id.projectDetail_postcode)).setText(Project.getInstance().getPostCode());
        ((TextView)window.findViewById(R.id.projectDetail_BuildingDetail)).setText(Project.getInstance().getBuildingDetail());
        ((TextView)window.findViewById(R.id.projectDetail_createDate)).setText(Project.getInstance().getCreateDate().toString());
//        ((TextView)window.findViewById(R.id.projectDetail_editDate)).setText(Project.getInstance().getEditDate().toString());
//        ((TextView)window.findViewById(R.id.projectDetail_latitude)).setText(String.valueOf(Project.getInstance().getLattitude()));
//        ((TextView)window.findViewById(R.id.projectDetail_longitude)).setText(String.valueOf(Project.getInstance().getLongitude()));
//        ((TextView)window.findViewById(R.id.projectDetail_user)).setText(Project.getInstance().getUser());
//        ((TextView)window.findViewById(R.id.projectDetail_lac)).setText(Project.getInstance().getLAC());
//        ((TextView)window.findViewById(R.id.projectDetail_cid)).setText(Project.getInstance().getCID());

        // Update Listview
//        (Listview)window.findViewById(R.id.)sessions.clear();
//
//        for (int i =0;i<Project.getInstance().getSessions().size();i++){
//            sessions.add((Project.getInstance().getSessions().get(i)).getPoint());
//        }
//
//        itemsAdapter.notifyDataSetChanged();


    }
}
