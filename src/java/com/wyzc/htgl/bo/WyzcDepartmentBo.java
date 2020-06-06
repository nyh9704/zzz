package com.wyzc.htgl.bo;

import java.util.List;

import com.wyzc.htgl.po.WyzcDepartmentPo;


public class WyzcDepartmentBo extends WyzcDepartmentPo {
    private List<WyzcDepartmentBo> children;//对应父级下对应子级集合 
    private String id;
    private String text;
    private String userName;
    private String userWpId;

    public List<WyzcDepartmentBo> getChildren() {
        return children;
    }

    public void setChildren(List<WyzcDepartmentBo> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserWpId() {
        return userWpId;
    }

    public void setUserWpId(String userWpId) {
        this.userWpId = userWpId;
    }

    @Override
    public String toString() {
        return "WyzcDepartmentBo [children=" + children + ", id=" + id + ", text=" + text + ", userName=" + userName
                + ", userWpId=" + userWpId + "]";
    }


}
