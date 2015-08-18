package com.nqm.loginbuilding;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sirawang on 01/07/2015.
 */
public class Project {
    //Object singelton
    private static Project mProject=null;

    private Context ctx;
    private String buildingID;
    private String name;
    private String service;
    //private String photo;
    private int accessSite;
    private String contactName;
    private String contactNumber;
    private String IMEI_AWN;
    private String IMEI_DTAC;
    private String IMEI_TRUEH;
    private String IMEI_3BB;
    private String address;
    private String tambon;
    private String district;
    private String province;
    private String postCode;
    private String buildingDetail;
    private Date createDate;
    private double Lattitude;
    private double Longitude;
    private String user;
    private String LAC;
    private String CID;
    private Date editDate;
    private String editUser;
    private List<Session> sessions = null;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session item){
        if(sessions==null){
            sessions = new ArrayList<>();
        }
        sessions.add(item);
    }

    public void clearSessions(){
        if(sessions==null){
            sessions=new ArrayList<>();
        }
        else{
            sessions.clear();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getAccessSite() {
        return accessSite;
    }

    public void setAccessSite(int accessSite) {
        this.accessSite = accessSite;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getIMEI_AWN() {
        return IMEI_AWN;
    }

    public void setIMEI_AWN(String IMEI_AWN) {
        this.IMEI_AWN = IMEI_AWN;
    }

    public String getIMEI_DTAC() {
        return IMEI_DTAC;
    }

    public void setIMEI_DTAC(String IMEI_DTAC) {
        this.IMEI_DTAC = IMEI_DTAC;
    }

    public String getIMEI_TRUEH() {
        return IMEI_TRUEH;
    }

    public void setIMEI_TRUEH(String IMEI_TRUEH) {
        this.IMEI_TRUEH = IMEI_TRUEH;
    }

    public String getIMEI_3BB() {
        return IMEI_3BB;
    }

    public void setIMEI_3BB(String IMEI_3BB) {
        this.IMEI_3BB = IMEI_3BB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTambon() {
        return tambon;
    }

    public void setTambon(String tambon) {
        this.tambon = tambon;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getBuildingDetail() {
        return buildingDetail;
    }

    public void setBuildingDetail(String buildingDetail) {
        this.buildingDetail = buildingDetail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getLattitude() {
        return Lattitude;
    }

    public void setLattitude(double lattitude) {
        Lattitude = lattitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLAC() {
        return LAC;
    }

    public void setLAC(String LAC) {
        this.LAC = LAC;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public Project(Context context) {
        if (mProject==null){
            mProject=new Project();
            this.ctx=context;
        }
    }

    public Project(){

    }

    public static Project getInstance(){
        if (mProject==null)
            mProject=new Project();
        return mProject;
    }

    public String GetProjectDetail(String name) {
        //TODO: Implement code
        GetProjectDetailAsync task = new GetProjectDetailAsync(ctx,name);
        task.execute();

    return "";
    }

    public void GetSessionList()
    {
        GetSessionDetailAsync taskGetSession =new GetSessionDetailAsync(ctx,this.buildingID);
        taskGetSession.execute();
    }

    public void ShowProjectDetailActivity(){
        Log.e("ShowProjectDetailActivity", "Calling....");
        Intent i = new Intent(ctx,ProjectDetailActivity.class);
        //Intent i = new Intent(ctx,ProjectDetailActivityFragment.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ((ProjectListActivity)ctx).startActivity(i);
    }

    public void AddProject(){
        //TODO: Implement code

    }

    public void EditProject(){
        //TODO: Implement code
    }

    public void DeleteProject(){
        //TODO: Implement code

    }
}
