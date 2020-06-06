package com.wyzc.htgl.po;

public class WyzcUserInfoPo {
    private String userIdInfo; //用户编号
    private String userNameInfo; //用户姓名
    private String userIdEntityInfo; //身份证

    public WyzcUserInfoPo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcUserInfoPo(String userIdInfo, String userNameInfo, String userIdEntityInfo) {
        super();
        this.userIdInfo = userIdInfo;
        this.userNameInfo = userNameInfo;
        this.userIdEntityInfo = userIdEntityInfo;
    }

    @Override
    public String toString() {
        return "WyzcUserInfoPo [userIdInfo=" + userIdInfo + ", userNameInfo=" + userNameInfo + ", userIdEntityInfo="
                + userIdEntityInfo + "]";
    }

    public String getUserIdInfo() {
        return userIdInfo;
    }

    public void setUserIdInfo(String userIdInfo) {
        this.userIdInfo = userIdInfo;
    }

    public String getUserNameInfo() {
        return userNameInfo;
    }

    public void setUserNameInfo(String userNameInfo) {
        this.userNameInfo = userNameInfo;
    }

    public String getUserIdEntityInfo() {
        return userIdEntityInfo;
    }

    public void setUserIdEntityInfo(String userIdEntityInfo) {
        this.userIdEntityInfo = userIdEntityInfo;
    }


}
