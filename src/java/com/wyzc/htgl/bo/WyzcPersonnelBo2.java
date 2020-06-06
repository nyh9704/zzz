package com.wyzc.htgl.bo;

import java.io.Serializable;

import com.wyzc.htgl.po.WyzcPersonnelPo;

public class WyzcPersonnelBo2 extends WyzcPersonnelPo implements Serializable {
    private String searchMo;
    private String postponeTime1;
    private String postponeTime2;
    private String startWorkTime1;
    private String startWorkTime2;
    private String workAge1;
    private String workAge2;
    private String age1;
    private String age2;
    private String registered1;
    private String education1;
    private String userName1;
    private String wpName1;
    private String postName1;
    private String wdName1;
    private String isOfficial1;
    private String contractType1;
    private String userTel;
    private String fileNumber;
    private String order;
    private String sort;
    private String contractTime;
    private String contractEndTime;
    private String contractDedline;
    private String turnOfficialTime1;
    private String turnOfficialTime2;
    private String contractTime1;
    private String contractTime2;
    private String contractEndTime1;
    private String contractEndTime2;

    public WyzcPersonnelBo2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcPersonnelBo2(String userId, String userName, String userPwd, String identityCard, String wpId,
                            String wpName, String wdId, String wdName, String postName, String userSex, String practiceStart,
                            String practiceNumber, String practiceEnd, String isPostpone, String postponeNumber, String postponeTo,
                            String postponeTime, String startWorkTime, String turnOfficialTime, String dimissionTime, String workAge,
                            String birDate, String birYear, String birMounth, String age, String nation, String registered,
                            String education, String contactTel, String isOfficial, String contractType, String remark, String sign,
                            String userIdCardAddress, String nation1) {
        super(userId, userName, userPwd, identityCard, wpId, wpName, wdId, wdName, postName, userSex, practiceStart,
                practiceNumber, practiceEnd, isPostpone, postponeNumber, postponeTo, postponeTime, startWorkTime,
                turnOfficialTime, dimissionTime, workAge, birDate, birYear, birMounth, age, nation, registered, education,
                contactTel, isOfficial, contractType, remark, sign, userIdCardAddress, nation1);
        // TODO Auto-generated constructor stub
    }

    public WyzcPersonnelBo2(String searchMo, String postponeTime1, String postponeTime2, String startWorkTime1,
                            String startWorkTime2, String workAge1, String workAge2, String age1, String age2, String registered1,
                            String education1, String userName1, String wpName1, String postName1, String wdName1, String isOfficial1,
                            String contractType1, String userTel, String fileNumber, String order, String sort, String contractTime,
                            String contractEndTime, String contractDedline, String turnOfficialTime1, String turnOfficialTime2,
                            String contractTime1, String contractTime2, String contractEndTime1, String contractEndTime2) {
        super();
        this.searchMo = searchMo;
        this.postponeTime1 = postponeTime1;
        this.postponeTime2 = postponeTime2;
        this.startWorkTime1 = startWorkTime1;
        this.startWorkTime2 = startWorkTime2;
        this.workAge1 = workAge1;
        this.workAge2 = workAge2;
        this.age1 = age1;
        this.age2 = age2;
        this.registered1 = registered1;
        this.education1 = education1;
        this.userName1 = userName1;
        this.wpName1 = wpName1;
        this.postName1 = postName1;
        this.wdName1 = wdName1;
        this.isOfficial1 = isOfficial1;
        this.contractType1 = contractType1;
        this.userTel = userTel;
        this.fileNumber = fileNumber;
        this.order = order;
        this.sort = sort;
        this.contractTime = contractTime;
        this.contractEndTime = contractEndTime;
        this.contractDedline = contractDedline;
        this.turnOfficialTime1 = turnOfficialTime1;
        this.turnOfficialTime2 = turnOfficialTime2;
        this.contractTime1 = contractTime1;
        this.contractTime2 = contractTime2;
        this.contractEndTime1 = contractEndTime1;
        this.contractEndTime2 = contractEndTime2;
    }

    @Override
    public String toString() {
        return "WyzcPersonnelBo2 [searchMo=" + searchMo + ", postponeTime1=" + postponeTime1 + ", postponeTime2="
                + postponeTime2 + ", startWorkTime1=" + startWorkTime1 + ", startWorkTime2=" + startWorkTime2
                + ", workAge1=" + workAge1 + ", workAge2=" + workAge2 + ", age1=" + age1 + ", age2=" + age2
                + ", registered1=" + registered1 + ", education1=" + education1 + ", userName1=" + userName1
                + ", wpName1=" + wpName1 + ", postName1=" + postName1 + ", wdName1=" + wdName1 + ", isOfficial1="
                + isOfficial1 + ", contractType1=" + contractType1 + ", userTel=" + userTel + ", fileNumber="
                + fileNumber + ", order=" + order + ", sort=" + sort + ", contractTime=" + contractTime
                + ", contractEndTime=" + contractEndTime + ", contractDedline=" + contractDedline
                + ", turnOfficialTime1=" + turnOfficialTime1 + ", turnOfficialTime2=" + turnOfficialTime2
                + ", contractTime1=" + contractTime1 + ", contractTime2=" + contractTime2 + ", contractEndTime1="
                + contractEndTime1 + ", contractEndTime2=" + contractEndTime2 + "]";
    }

    public String getSearchMo() {
        return searchMo;
    }

    public void setSearchMo(String searchMo) {
        this.searchMo = searchMo;
    }

    public String getPostponeTime1() {
        return postponeTime1;
    }

    public void setPostponeTime1(String postponeTime1) {
        this.postponeTime1 = postponeTime1;
    }

    public String getPostponeTime2() {
        return postponeTime2;
    }

    public void setPostponeTime2(String postponeTime2) {
        this.postponeTime2 = postponeTime2;
    }

    public String getStartWorkTime1() {
        return startWorkTime1;
    }

    public void setStartWorkTime1(String startWorkTime1) {
        this.startWorkTime1 = startWorkTime1;
    }

    public String getStartWorkTime2() {
        return startWorkTime2;
    }

    public void setStartWorkTime2(String startWorkTime2) {
        this.startWorkTime2 = startWorkTime2;
    }

    public String getWorkAge1() {
        return workAge1;
    }

    public void setWorkAge1(String workAge1) {
        this.workAge1 = workAge1;
    }

    public String getWorkAge2() {
        return workAge2;
    }

    public void setWorkAge2(String workAge2) {
        this.workAge2 = workAge2;
    }

    public String getAge1() {
        return age1;
    }

    public void setAge1(String age1) {
        this.age1 = age1;
    }

    public String getAge2() {
        return age2;
    }

    public void setAge2(String age2) {
        this.age2 = age2;
    }

    public String getRegistered1() {
        return registered1;
    }

    public void setRegistered1(String registered1) {
        this.registered1 = registered1;
    }

    public String getEducation1() {
        return education1;
    }

    public void setEducation1(String education1) {
        this.education1 = education1;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getWpName1() {
        return wpName1;
    }

    public void setWpName1(String wpName1) {
        this.wpName1 = wpName1;
    }

    public String getPostName1() {
        return postName1;
    }

    public void setPostName1(String postName1) {
        this.postName1 = postName1;
    }

    public String getWdName1() {
        return wdName1;
    }

    public void setWdName1(String wdName1) {
        this.wdName1 = wdName1;
    }

    public String getIsOfficial1() {
        return isOfficial1;
    }

    public void setIsOfficial1(String isOfficial1) {
        this.isOfficial1 = isOfficial1;
    }

    public String getContractType1() {
        return contractType1;
    }

    public void setContractType1(String contractType1) {
        this.contractType1 = contractType1;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
    }

    public String getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getContractDedline() {
        return contractDedline;
    }

    public void setContractDedline(String contractDedline) {
        this.contractDedline = contractDedline;
    }

    public String getTurnOfficialTime1() {
        return turnOfficialTime1;
    }

    public void setTurnOfficialTime1(String turnOfficialTime1) {
        this.turnOfficialTime1 = turnOfficialTime1;
    }

    public String getTurnOfficialTime2() {
        return turnOfficialTime2;
    }

    public void setTurnOfficialTime2(String turnOfficialTime2) {
        this.turnOfficialTime2 = turnOfficialTime2;
    }

    public String getContractTime1() {
        return contractTime1;
    }

    public void setContractTime1(String contractTime1) {
        this.contractTime1 = contractTime1;
    }

    public String getContractTime2() {
        return contractTime2;
    }

    public void setContractTime2(String contractTime2) {
        this.contractTime2 = contractTime2;
    }

    public String getContractEndTime1() {
        return contractEndTime1;
    }

    public void setContractEndTime1(String contractEndTime1) {
        this.contractEndTime1 = contractEndTime1;
    }

    public String getContractEndTime2() {
        return contractEndTime2;
    }

    public void setContractEndTime2(String contractEndTime2) {
        this.contractEndTime2 = contractEndTime2;
    }


}
