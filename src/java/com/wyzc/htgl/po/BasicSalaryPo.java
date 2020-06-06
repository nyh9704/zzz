package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

/**
 * 财务部：基本工资，补贴，提成，社保，共同实体类
 */
public class BasicSalaryPo extends BasePo {
    private Integer fbsNumber;
    private Integer fcNumber;
    private Integer fsNumber;
    private Integer fssNumber;
    private String fbsName;
    private String fcName;
    private String fsName;
    private String fssName;
    private String fbsValue;
    private String fcValue;
    private String fsValue;
    private String fssValue;
    private String searchMo;

    public BasicSalaryPo(Integer fbsNumber, Integer fcNumber, Integer fsNumber, Integer fssNumber, String fbsName, String fcName, String fsName, String fssName, String fbsValue, String fcValue, String fsValue, String fssValue, String searchMo) {
        this.fbsNumber = fbsNumber;
        this.fcNumber = fcNumber;
        this.fsNumber = fsNumber;
        this.fssNumber = fssNumber;
        this.fbsName = fbsName;
        this.fcName = fcName;
        this.fsName = fsName;
        this.fssName = fssName;
        this.fbsValue = fbsValue;
        this.fcValue = fcValue;
        this.fsValue = fsValue;
        this.fssValue = fssValue;
        this.searchMo = searchMo;
    }

    public BasicSalaryPo() {
        super();
    }

    @Override
    public String toString() {
        return "BasicSalaryPo{" +
                "fbsNumber=" + fbsNumber +
                ", fcNumber=" + fcNumber +
                ", fsNumber=" + fsNumber +
                ", fssNumber=" + fssNumber +
                ", fbsName='" + fbsName + '\'' +
                ", fcName='" + fcName + '\'' +
                ", fsName='" + fsName + '\'' +
                ", fssName='" + fssName + '\'' +
                ", fbsValue='" + fbsValue + '\'' +
                ", fcValue='" + fcValue + '\'' +
                ", fsValue='" + fsValue + '\'' +
                ", fssValue='" + fssValue + '\'' +
                ", searchMo='" + searchMo + '\'' +
                '}';
    }

    public Integer getFbsNumber() {
        return fbsNumber;
    }

    public void setFbsNumber(Integer fbsNumber) {
        this.fbsNumber = fbsNumber;
    }

    public Integer getFcNumber() {
        return fcNumber;
    }

    public void setFcNumber(Integer fcNumber) {
        this.fcNumber = fcNumber;
    }

    public Integer getFsNumber() {
        return fsNumber;
    }

    public void setFsNumber(Integer fsNumber) {
        this.fsNumber = fsNumber;
    }

    public Integer getFssNumber() {
        return fssNumber;
    }

    public void setFssNumber(Integer fssNumber) {
        this.fssNumber = fssNumber;
    }

    public String getFbsName() {
        return fbsName;
    }

    public void setFbsName(String fbsName) {
        this.fbsName = fbsName;
    }

    public String getFcName() {
        return fcName;
    }

    public void setFcName(String fcName) {
        this.fcName = fcName;
    }

    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    public String getFssName() {
        return fssName;
    }

    public void setFssName(String fssName) {
        this.fssName = fssName;
    }

    public String getFbsValue() {
        return fbsValue;
    }

    public void setFbsValue(String fbsValue) {
        this.fbsValue = fbsValue;
    }

    public String getFcValue() {
        return fcValue;
    }

    public void setFcValue(String fcValue) {
        this.fcValue = fcValue;
    }

    public String getFsValue() {
        return fsValue;
    }

    public void setFsValue(String fsValue) {
        this.fsValue = fsValue;
    }

    public String getFssValue() {
        return fssValue;
    }

    public void setFssValue(String fssValue) {
        this.fssValue = fssValue;
    }

    public String getSearchMo() {
        return searchMo;
    }

    public void setSearchMo(String searchMo) {
        this.searchMo = searchMo;
    }
}
