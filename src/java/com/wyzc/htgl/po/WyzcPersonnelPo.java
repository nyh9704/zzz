package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

public class WyzcPersonnelPo extends BasePo {
    private String userId; //用户编号
    private String userName; //用户姓名
    private String userPwd; //用户密码
    private String identityCard; //身份证号码 
    private String wpId; //所属部门
    private String wpName; //所属子部门
    private String wdId; //所属职务
    private String wdName; //所属职务
    private String postName; //岗位名称
    private String userSex; //性别
    private String practiceStart; //试岗开始时间
    private String practiceNumber;//试岗天数
    private String practiceEnd; //试岗结束时间
    private String isPostpone; //是否延期 0为‘否’ 1为‘是’
    private String postponeNumber;//延期天数
    private String postponeTo; //试岗延期至
    private String postponeTime; //试岗期
    private String startWorkTime;//到岗日期
    private String turnOfficialTime; //转正日期
    private String dimissionTime; //离职日期
    private String workAge;// 工龄
    private String birDate; //出生日期
    private String birYear; //出生年份
    private String birMounth; //生日月份
    private String age; //年龄
    private String nation; //民族
    private String registered; //户口
    private String education; //学历
    private String contactTel; //联系电话
    private String isOfficial; // 1‘正式’ 0‘使用’
    private String contractType; //合同类别 2‘正式’ 1‘试用’  0‘未签’ 
    private String remark; //备注
    private String sign; //标记 1为试岗员工 不具备注册功能  对应数据只展示在试岗员工栏目里面
    private String userIdCardAddress;
    private String nation1;

    public WyzcPersonnelPo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcPersonnelPo(String userId, String userName, String userPwd, String identityCard, String wpId,
                           String wpName, String wdId, String wdName, String postName, String userSex, String practiceStart,
                           String practiceNumber, String practiceEnd, String isPostpone, String postponeNumber, String postponeTo,
                           String postponeTime, String startWorkTime, String turnOfficialTime, String dimissionTime, String workAge,
                           String birDate, String birYear, String birMounth, String age, String nation, String registered,
                           String education, String contactTel, String isOfficial, String contractType, String remark, String sign,
                           String userIdCardAddress, String nation1) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.identityCard = identityCard;
        this.wpId = wpId;
        this.wpName = wpName;
        this.wdId = wdId;
        this.wdName = wdName;
        this.postName = postName;
        this.userSex = userSex;
        this.practiceStart = practiceStart;
        this.practiceNumber = practiceNumber;
        this.practiceEnd = practiceEnd;
        this.isPostpone = isPostpone;
        this.postponeNumber = postponeNumber;
        this.postponeTo = postponeTo;
        this.postponeTime = postponeTime;
        this.startWorkTime = startWorkTime;
        this.turnOfficialTime = turnOfficialTime;
        this.dimissionTime = dimissionTime;
        this.workAge = workAge;
        this.birDate = birDate;
        this.birYear = birYear;
        this.birMounth = birMounth;
        this.age = age;
        this.nation = nation;
        this.registered = registered;
        this.education = education;
        this.contactTel = contactTel;
        this.isOfficial = isOfficial;
        this.contractType = contractType;
        this.remark = remark;
        this.sign = sign;
        this.userIdCardAddress = userIdCardAddress;
        this.nation1 = nation1;
    }

    @Override
    public String toString() {
        return "WyzcPersonnelPo [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd
                + ", identityCard=" + identityCard + ", wpId=" + wpId + ", wpName=" + wpName + ", wdId=" + wdId
                + ", wdName=" + wdName + ", postName=" + postName + ", userSex=" + userSex + ", practiceStart="
                + practiceStart + ", practiceNumber=" + practiceNumber + ", practiceEnd=" + practiceEnd
                + ", isPostpone=" + isPostpone + ", postponeNumber=" + postponeNumber + ", postponeTo=" + postponeTo
                + ", postponeTime=" + postponeTime + ", startWorkTime=" + startWorkTime + ", turnOfficialTime="
                + turnOfficialTime + ", dimissionTime=" + dimissionTime + ", workAge=" + workAge + ", birDate="
                + birDate + ", birYear=" + birYear + ", birMounth=" + birMounth + ", age=" + age + ", nation=" + nation
                + ", registered=" + registered + ", education=" + education + ", contactTel=" + contactTel
                + ", isOfficial=" + isOfficial + ", contractType=" + contractType + ", remark=" + remark + ", sign="
                + sign + ", userIdCardAddress=" + userIdCardAddress + ", nation1=" + nation1 + ", getPage()="
                + getPage() + ", getRows()=" + getRows() + ", getSort()=" + getSort() + ", getOrder()=" + getOrder()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
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

    public String getWpName() {
        return wpName;
    }

    public void setWpName(String wpName) {
        this.wpName = wpName;
    }

    public String getWdId() {
        return wdId;
    }

    public void setWdId(String wdId) {
        this.wdId = wdId;
    }

    public String getWdName() {
        return wdName;
    }

    public void setWdName(String wdName) {
        this.wdName = wdName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getPracticeStart() {
        return practiceStart;
    }

    public void setPracticeStart(String practiceStart) {
        this.practiceStart = practiceStart;
    }

    public String getPracticeNumber() {
        return practiceNumber;
    }

    public void setPracticeNumber(String practiceNumber) {
        this.practiceNumber = practiceNumber;
    }

    public String getPracticeEnd() {
        return practiceEnd;
    }

    public void setPracticeEnd(String practiceEnd) {
        this.practiceEnd = practiceEnd;
    }

    public String getIsPostpone() {
        return isPostpone;
    }

    public void setIsPostpone(String isPostpone) {
        this.isPostpone = isPostpone;
    }

    public String getPostponeNumber() {
        return postponeNumber;
    }

    public void setPostponeNumber(String postponeNumber) {
        this.postponeNumber = postponeNumber;
    }

    public String getPostponeTo() {
        return postponeTo;
    }

    public void setPostponeTo(String postponeTo) {
        this.postponeTo = postponeTo;
    }

    public String getPostponeTime() {
        return postponeTime;
    }

    public void setPostponeTime(String postponeTime) {
        this.postponeTime = postponeTime;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getTurnOfficialTime() {
        return turnOfficialTime;
    }

    public void setTurnOfficialTime(String turnOfficialTime) {
        this.turnOfficialTime = turnOfficialTime;
    }

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public String getBirDate() {
        return birDate;
    }

    public void setBirDate(String birDate) {
        this.birDate = birDate;
    }

    public String getBirYear() {
        return birYear;
    }

    public void setBirYear(String birYear) {
        this.birYear = birYear;
    }

    public String getBirMounth() {
        return birMounth;
    }

    public void setBirMounth(String birMounth) {
        this.birMounth = birMounth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserIdCardAddress() {
        return userIdCardAddress;
    }

    public void setUserIdCardAddress(String userIdCardAddress) {
        this.userIdCardAddress = userIdCardAddress;
    }

    public String getNation1() {
        return nation1;
    }

    public void setNation1(String nation1) {
        this.nation1 = nation1;
    }

}
