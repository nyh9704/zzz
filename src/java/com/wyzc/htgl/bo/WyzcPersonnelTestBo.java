package com.wyzc.htgl.bo;

import java.io.Serializable;

import com.wyzc.htgl.po.WyzcPersonnelTestPo;

public class WyzcPersonnelTestBo extends WyzcPersonnelTestPo implements Serializable {
    private String startMounth; //起始月份
    private String endMounth; //截止月份
    private String keyWorld; //关键字
    private String startYear; //起始年份
    private String endYear; //截止年份
    private String startAge; //起始年龄
    private String endAge; //截止年龄
    private String wpName; //部门名称
    private String wdName;//职位等级名称
    private String cardAddress;//身份证地址
    private String practiceStartBig; //大于试岗起始时间
    private String practiceStartSmall; //小于试岗起始时间
    private String practiceEndBig; //大于试岗结束时间
    private String practiceEndSmall; //小于试岗结束时间
    private String fileNumber; //档案编号
    private String userIdCardAddress; //身份证地址
    private String contractEndTime; //合同截止时间
    private String contractDeadline; //合同期限
    private String sort; //排序字段
    private String order; //排序方式
    private String turnOfficialTimeBig; //大于转正起始时间
    private String turnOfficialTimeSmall; //小于转正起始时间
    private String startWorkTimeBig; //大于到岗起始时间
    private String startWorkTimeSmall; //小于到岗结束时间
    private String workAgeBig; //工龄上限
    private String workAgeSmall; //工龄下限

    public WyzcPersonnelTestBo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcPersonnelTestBo(String userId, String userName, String userPwd, String identityCard, String wpId,
                               String wpIdChild, String wdId, String postName, String userSex, String practiceStart, String practiceNumber,
                               String practiceEnd, String isPostpone, String postponeNumber, String postponeTo, String postponeTime,
                               String startWorkTime, String turnOfficialTime, String dimissionTime, String workAge, String birDate,
                               String birYear, String birMounth, String age, String nation, String registered, String education,
                               String contactTel, String isOfficial, String contractType, String remark, String sign,
                               String contractTime) {
        super(userId, userName, userPwd, identityCard, wpId, wpIdChild, wdId, postName, userSex, practiceStart, practiceNumber,
                practiceEnd, isPostpone, postponeNumber, postponeTo, postponeTime, startWorkTime, turnOfficialTime,
                dimissionTime, workAge, birDate, birYear, birMounth, age, nation, registered, education, contactTel, isOfficial,
                contractType, remark, sign, contractTime);
        // TODO Auto-generated constructor stub
    }

    public WyzcPersonnelTestBo(String startMounth, String endMounth, String keyWorld, String startYear, String endYear,
                               String startAge, String endAge, String wpName, String wdName, String cardAddress, String practiceStartBig,
                               String practiceStartSmall, String practiceEndBig, String practiceEndSmall) {
        super();
        this.startMounth = startMounth;
        this.endMounth = endMounth;
        this.keyWorld = keyWorld;
        this.startYear = startYear;
        this.endYear = endYear;
        this.startAge = startAge;
        this.endAge = endAge;
        this.wpName = wpName;
        this.wdName = wdName;
        this.cardAddress = cardAddress;
        this.practiceStartBig = practiceStartBig;
        this.practiceStartSmall = practiceStartSmall;
        this.practiceEndBig = practiceEndBig;
        this.practiceEndSmall = practiceEndSmall;
    }

    @Override
    public String toString() {
        return "WyzcPersonnelBo [startMounth=" + startMounth + ", endMounth=" + endMounth + ", keyWorld=" + keyWorld
                + ", startYear=" + startYear + ", endYear=" + endYear + ", startAge=" + startAge + ", endAge=" + endAge
                + ", wpName=" + wpName + ", wdName=" + wdName + ", cardAddress=" + cardAddress + ", practiceStartBig="
                + practiceStartBig + ", practiceStartSmall=" + practiceStartSmall + ", practiceEndBig=" + practiceEndBig
                + ", practiceEndSmall=" + practiceEndSmall + "]";
    }

    public String getStartMounth() {
        return startMounth;
    }

    public void setStartMounth(String startMounth) {
        this.startMounth = startMounth;
    }

    public String getEndMounth() {
        return endMounth;
    }

    public void setEndMounth(String endMounth) {
        this.endMounth = endMounth;
    }

    public String getKeyWorld() {
        return keyWorld;
    }

    public void setKeyWorld(String keyWorld) {
        this.keyWorld = keyWorld;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getStartAge() {
        return startAge;
    }

    public void setStartAge(String startAge) {
        this.startAge = startAge;
    }

    public String getEndAge() {
        return endAge;
    }

    public void setEndAge(String endAge) {
        this.endAge = endAge;
    }

    public String getWpName() {
        return wpName;
    }

    public void setWpName(String wpName) {
        this.wpName = wpName;
    }

    public String getWdName() {
        return wdName;
    }

    public void setWdName(String wdName) {
        this.wdName = wdName;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public String getPracticeStartBig() {
        return practiceStartBig;
    }

    public void setPracticeStartBig(String practiceStartBig) {
        this.practiceStartBig = practiceStartBig;
    }

    public String getPracticeStartSmall() {
        return practiceStartSmall;
    }

    public void setPracticeStartSmall(String practiceStartSmall) {
        this.practiceStartSmall = practiceStartSmall;
    }

    public String getPracticeEndBig() {
        return practiceEndBig;
    }

    public void setPracticeEndBig(String practiceEndBig) {
        this.practiceEndBig = practiceEndBig;
    }

    public String getPracticeEndSmall() {
        return practiceEndSmall;
    }

    public void setPracticeEndSmall(String practiceEndSmall) {
        this.practiceEndSmall = practiceEndSmall;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getUserIdCardAddress() {
        return userIdCardAddress;
    }

    public void setUserIdCardAddress(String userIdCardAddress) {
        this.userIdCardAddress = userIdCardAddress;
    }

    public String getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(String contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getContractDeadline() {
        return contractDeadline;
    }

    public void setContractDeadline(String contractDeadline) {
        this.contractDeadline = contractDeadline;
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

    public String getTurnOfficialTimeBig() {
        return turnOfficialTimeBig;
    }

    public void setTurnOfficialTimeBig(String turnOfficialTimeBig) {
        this.turnOfficialTimeBig = turnOfficialTimeBig;
    }

    public String getTurnOfficialTimeSmall() {
        return turnOfficialTimeSmall;
    }

    public void setTurnOfficialTimeSmall(String turnOfficialTimeSmall) {
        this.turnOfficialTimeSmall = turnOfficialTimeSmall;
    }

    public String getStartWorkTimeBig() {
        return startWorkTimeBig;
    }

    public void setStartWorkTimeBig(String startWorkTimeBig) {
        this.startWorkTimeBig = startWorkTimeBig;
    }

    public String getStartWorkTimeSmall() {
        return startWorkTimeSmall;
    }

    public void setStartWorkTimeSmall(String startWorkTimeSmall) {
        this.startWorkTimeSmall = startWorkTimeSmall;
    }

    public String getWorkAgeBig() {
        return workAgeBig;
    }

    public void setWorkAgeBig(String workAgeBig) {
        this.workAgeBig = workAgeBig;
    }

    public String getWorkAgeSmall() {
        return workAgeSmall;
    }

    public void setWorkAgeSmall(String workAgeSmall) {
        this.workAgeSmall = workAgeSmall;
    }


}
