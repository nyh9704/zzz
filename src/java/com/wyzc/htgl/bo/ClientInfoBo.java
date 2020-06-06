package com.wyzc.htgl.bo;

import com.wyzc.htgl.po.ClientInfoPo;

public class ClientInfoBo extends ClientInfoPo {
    private String keyWorld; //关键字查询
    private String wpId; //部门编号
    private String dutyClass; //职务等级
    private String userId; //当前登陆用户编号
    private String userName; //当前登陆用户姓名
    private String cId; //客户编号
    private String endTime;//结束时间
    private String startTime;//开始时间
    private String counselor;//顾问
    private String cTime = ""; //登记时间
    private String sort; //排序字段
    private String order; //排序方式
    private String todayVisit = "";//今日开拍
    private String yestodayVisit = "";//昨日开拍
    private String threeDaysVisit = "";//三日内到访
    private String oneWeekVisit = ""; //一周内到访
    private String oneMonthVisit = ""; //一月内到访
    private String nowDate; //当前系统时间
    private String visitData = "";

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getKeyWorld() {
        return keyWorld;
    }

    public void setKeyWorld(String keyWorld) {
        this.keyWorld = keyWorld;
    }

    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

    public String getDutyClass() {
        return dutyClass;
    }

    public void setDutyClass(String dutyClass) {
        this.dutyClass = dutyClass;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
    }

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTodayVisit() {
        return todayVisit;
    }

    public void setTodayVisit(String todayVisit) {
        this.todayVisit = todayVisit;
    }

    public String getYestodayVisit() {
        return yestodayVisit;
    }

    public void setYestodayVisit(String yestodayVisit) {
        this.yestodayVisit = yestodayVisit;
    }

    public String getThreeDaysVisit() {
        return threeDaysVisit;
    }

    public void setThreeDaysVisit(String threeDaysVisit) {
        this.threeDaysVisit = threeDaysVisit;
    }

    public String getOneWeekVisit() {
        return oneWeekVisit;
    }

    public void setOneWeekVisit(String oneWeekVisit) {
        this.oneWeekVisit = oneWeekVisit;
    }

    public String getOneMonthVisit() {
        return oneMonthVisit;
    }

    public void setOneMonthVisit(String oneMonthVisit) {
        this.oneMonthVisit = oneMonthVisit;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public String getVisitData() {
        return visitData;
    }

    public void setVisitData(String visitData) {
        this.visitData = visitData;
    }


}
