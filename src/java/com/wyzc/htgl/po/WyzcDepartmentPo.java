package com.wyzc.htgl.po;

/**
 * 部门实体类
 *
 * @author Administrator
 */
public class WyzcDepartmentPo {
    private String wpId = ""; //部门编号
    private String wpName; //部门名称
    private String parentId; //上级部门编号


    public WyzcDepartmentPo() {
        super();
        // TODO Auto-generated constructor stub
    }


    public WyzcDepartmentPo(String wpId, String wpName, String parentId) {
        super();
        this.wpId = wpId;
        this.wpName = wpName;
        this.parentId = parentId;
    }


    @Override
    public String toString() {
        return "WyzcDepartmentPo [wpId=" + wpId + ", wpName=" + wpName + ", parentId=" + parentId + "]";
    }


    public String getWpId() {
        return wpId;
    }


    public void setWpId(String wpId) {
        this.wpId = wpId;
    }


    public String getWpName() {
        return wpName;
    }


    public void setWpName(String wpName) {
        this.wpName = wpName;
    }


    public String getParentId() {
        return parentId;
    }


    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


}
