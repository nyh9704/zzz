package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

/**
 * 实体类
 *
 * @author Administrator
 */
public class DueDiligenceDisposePo extends BasePo {
    private String userName;
    private String userId;
    private String userChooseHouse;
    private String userPost;//状态
    private String userPostDate;
    private String userPs;//备注
    private String userCancel;//用户是否申请取消
    private String userEndDate;

    public DueDiligenceDisposePo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DueDiligenceDisposePo(String userName, String userId, String userChooseHouse, String userPost,
                                 String userPostDate, String userPs, String userCancel, String userEndDate) {
        super();
        this.userName = userName;
        this.userId = userId;
        this.userChooseHouse = userChooseHouse;
        this.userPost = userPost;
        this.userPostDate = userPostDate;
        this.userPs = userPs;
        this.userCancel = userCancel;
        this.userEndDate = userEndDate;
    }

    @Override
    public String toString() {
        return "DueDiligenceDisposePo [userName=" + userName + ", userId=" + userId + ", userChooseHouse="
                + userChooseHouse + ", userPost=" + userPost + ", userPostDate=" + userPostDate + ", userPs=" + userPs
                + ", userCancel=" + userCancel + ", userEndDate=" + userEndDate + "]";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserChooseHouse() {
        return userChooseHouse;
    }

    public void setUserChooseHouse(String userChooseHouse) {
        this.userChooseHouse = userChooseHouse;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public String getUserPostDate() {
        return userPostDate;
    }

    public void setUserPostDate(String userPostDate) {
        this.userPostDate = userPostDate;
    }

    public String getUserPs() {
        return userPs;
    }

    public void setUserPs(String userPs) {
        this.userPs = userPs;
    }

    public String getUserCancel() {
        return userCancel;
    }

    public void setUserCancel(String userCancel) {
        this.userCancel = userCancel;
    }

    public String getUserEndDate() {
        return userEndDate;
    }

    public void setUserEndDate(String userEndDate) {
        this.userEndDate = userEndDate;
    }


}
