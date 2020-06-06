package com.wyzc.htgl.po;

/**
 * @author Administrator
 * 用户信息表
 */
public class WyzcUserPo {
    private String userId; //用户编号
    private String userName; //用户姓名
    private String userPwd; //用户密码
    private String identityCard; //身份证号码 
    private String wpId; //所属部门
    private String wpIdChild; //所属子部门
    private String wdId; //所属职务

    public WyzcUserPo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcUserPo(String userId, String userName, String userPwd, String identityCard, String wpId,
                      String wpIdChild, String wdId) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.identityCard = identityCard;
        this.wpId = wpId;
        this.wpIdChild = wpIdChild;
        this.wdId = wdId;
    }

    @Override
    public String toString() {
        return "WyzcUserPo [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", identityCard="
                + identityCard + ", wpId=" + wpId + ", wpIdChild=" + wpIdChild + ", wdId=" + wdId + "]";
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

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

    public String getWpIdChild() {
        return wpIdChild;
    }

    public void setWpIdChild(String wpIdChild) {
        this.wpIdChild = wpIdChild;
    }

    public String getWdId() {
        return wdId;
    }

    public void setWdId(String wdId) {
        this.wdId = wdId;
    }


}
