package com.nqm.loginbuilding;

import android.content.Context;

import java.util.Date;

/**
 * Created by sirawang on 01/07/2015.
 */
public class Session {
    private Context ctx;
    private String buildingID;
    private String point;
    private String floor;
    private String description;
    private String location;
    private String loc_Specification;
    private String status_AWN;
    private String status_DTAC;
    private String status_TRUEH;
    private String status_3BB;
    private String remark;
    private String createDate;
    private String confirmStatus;
    private String confirmDate;
    private String user;

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLoc_Specification() {
        return loc_Specification;
    }

    public void setLoc_Specification(String loc_Specification) {
        this.loc_Specification = loc_Specification;
    }

    public String getStatus_AWN() {
        return status_AWN;
    }

    public void setStatus_AWN(String status_AWN) {
        this.status_AWN = status_AWN;
    }

    public String getStatus_DTAC() {
        return status_DTAC;
    }

    public void setStatus_DTAC(String status_DTAC) {
        this.status_DTAC = status_DTAC;
    }

    public String getStatus_TRUEH() {
        return status_TRUEH;
    }

    public void setStatus_TRUEH(String status_TRUEH) {
        this.status_TRUEH = status_TRUEH;
    }

    public String getStatus_3BB() {
        return status_3BB;
    }

    public void setStatus_3BB(String status_3BB) {
        this.status_3BB = status_3BB;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public String getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(String buildingID) {
        this.buildingID = buildingID;
    }

    public Session() {
        //TODO: Implement code
    }

    public String GetSessionDetail() {
        //TODO: Implement code
        //GetSessionDetailAsync task = new GetSessionDetailAsync(Project.getInstance().getCtx(),);
        //task.execute();
        return "";
    }

    public void AddSession(){
        AddSessionAsync task = new AddSessionAsync(ctx,this,buildingID);
        task.execute();
    }

    public void EditSession(){
        //TODO: Implement code
    }

    public void DeleteSession(){
        //TODO: Implement code
    }

    public void ConfirmSession(){
        //TODO: Implement code
    }
}
