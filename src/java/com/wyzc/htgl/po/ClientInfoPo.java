package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

/**
 * 客户资料实体类
 *
 * @author Administrator
 */
public class ClientInfoPo extends BasePo {
    private String cName; //客户姓名
    private String cTel; //联系电话
    private String cCounselor; //所属顾问
    private String cLeader; //上级领导
    private String cPart; //所属部门
    private String cDecider = ""; //谈单人
    private String cAddress = ""; //谈单地点
    private String cDate = ""; //到访日期
    private String cCondition;//具体情况
    private String cFrequency;//到访次数
    private String cResult; //谈单结果
    private String cDepart; //谈单人所属部门
    private String cIsonline; //是否网签


    public ClientInfoPo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ClientInfoPo(String cName, String cTel, String cCounselor, String cLeader, String cPart, String cDecider,
                        String cAddress, String cDate) {
        super();
        this.cName = cName;
        this.cTel = cTel;
        this.cCounselor = cCounselor;
        this.cLeader = cLeader;
        this.cPart = cPart;
        this.cDecider = cDecider;
        this.cAddress = cAddress;
        this.cDate = cDate;
    }

    @Override
    public String toString() {
        return "ClientInfoPo [cName=" + cName + ", cTel=" + cTel + ", cCounselor=" + cCounselor + ", cLeader=" + cLeader
                + ", cPart=" + cPart + ", cDecider=" + cDecider + ", cAddress=" + cAddress + ", cDate=" + cDate + "]";
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcTel() {
        return cTel;
    }

    public void setcTel(String cTel) {
        this.cTel = cTel;
    }

    public String getcCounselor() {
        return cCounselor;
    }

    public void setcCounselor(String cCounselor) {
        this.cCounselor = cCounselor;
    }

    public String getcLeader() {
        return cLeader;
    }

    public void setcLeader(String cLeader) {
        this.cLeader = cLeader;
    }

    public String getcPart() {
        return cPart;
    }

    public void setcPart(String cPart) {
        this.cPart = cPart;
    }

    public String getcDecider() {
        return cDecider;
    }

    public void setcDecider(String cDecider) {
        this.cDecider = cDecider;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getcDate() {
        return cDate;
    }

    public void setcDate(String cDate) {
        this.cDate = cDate;
    }


    public String getcFrequency() {
        return cFrequency;
    }

    public void setcFrequency(String cFrequency) {
        this.cFrequency = cFrequency;
    }

    public String getcCondition() {
        return cCondition;
    }

    public void setcCondition(String cCondition) {
        this.cCondition = cCondition;
    }

    public String getcResult() {
        return cResult;
    }

    public void setcResult(String cResult) {
        this.cResult = cResult;
    }

    public String getcDepart() {
        return cDepart;
    }

    public void setcDepart(String cDepart) {
        this.cDepart = cDepart;
    }

    public String getcIsonline() {
        return cIsonline;
    }

    public void setcIsonline(String cIsonline) {
        this.cIsonline = cIsonline;
    }


}
