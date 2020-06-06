package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

/**
 * @author Administrator
 * 个人资料实体类
 */
public class PersonalDataPo extends BasePo {
    private String hidArea;//区域
    private String hidName;//小区名称
    private String hidAddress;//具体地址
    private String hidTitle;//标题
    private String hidRoom;//室
    private String hidHall;//厅
    private String hidKitchen;//厨
    private String hidToilet;//卫
    private String hidAttach;//附带品
    private String hidSpace;//面积
    private String hidPrice;//价格
    private String hidOrientation;//朝向
    private String hidFloorNow;//当前房屋楼层
    private String hidFloorAll;//房屋总楼层
    private String hidFinish;//装修
    private String hidHouseAge;//房龄
    private String hidFirstPicType;//首图类型
    private String hidFirstPic;//首图
    private String hidIssueDate;//发布时间
    private String hidClickRateDay;//日点击量
    private String hidConsultClientDay;//当日咨询客户非中介
    private String hidConsult;//咨询
    private String hidCClient;//C类客户
    private String hidBClient;//B类客户
    private String hidAClient;//A类客户
    private String hidSuccessClient;//签单客户

    public PersonalDataPo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PersonalDataPo(String hidArea, String hidName, String hidAddress, String hidTitle, String hidRoom,
                          String hidHall, String hidKitchen, String hidToilet, String hidAttach, String hidSpace, String hidPrice,
                          String hidOrientation, String hidFloorNow, String hidFloorAll, String hidFinish, String hidHouseAge,
                          String hidFirstPicType, String hidFirstPic, String hidIssueDate, String hidClickRateDay,
                          String hidConsultClientDay, String hidConsult, String hidCClient, String hidBClient, String hidAClient,
                          String hidSuccessClient) {
        super();
        this.hidArea = hidArea;
        this.hidName = hidName;
        this.hidAddress = hidAddress;
        this.hidTitle = hidTitle;
        this.hidRoom = hidRoom;
        this.hidHall = hidHall;
        this.hidKitchen = hidKitchen;
        this.hidToilet = hidToilet;
        this.hidAttach = hidAttach;
        this.hidSpace = hidSpace;
        this.hidPrice = hidPrice;
        this.hidOrientation = hidOrientation;
        this.hidFloorNow = hidFloorNow;
        this.hidFloorAll = hidFloorAll;
        this.hidFinish = hidFinish;
        this.hidHouseAge = hidHouseAge;
        this.hidFirstPicType = hidFirstPicType;
        this.hidFirstPic = hidFirstPic;
        this.hidIssueDate = hidIssueDate;
        this.hidClickRateDay = hidClickRateDay;
        this.hidConsultClientDay = hidConsultClientDay;
        this.hidConsult = hidConsult;
        this.hidCClient = hidCClient;
        this.hidBClient = hidBClient;
        this.hidAClient = hidAClient;
        this.hidSuccessClient = hidSuccessClient;
    }

    @Override
    public String toString() {
        return "PersonalDataPo [hidArea=" + hidArea + ", hidName=" + hidName + ", hidAddress=" + hidAddress
                + ", hidTitle=" + hidTitle + ", hidRoom=" + hidRoom + ", hidHall=" + hidHall + ", hidKitchen="
                + hidKitchen + ", hidToilet=" + hidToilet + ", hidAttach=" + hidAttach + ", hidSpace=" + hidSpace
                + ", hidPrice=" + hidPrice + ", hidOrientation=" + hidOrientation + ", hidFloorNow=" + hidFloorNow
                + ", hidFloorAll=" + hidFloorAll + ", hidFinish=" + hidFinish + ", hidHouseAge=" + hidHouseAge
                + ", hidFirstPicType=" + hidFirstPicType + ", hidFirstPic=" + hidFirstPic + ", hidIssueDate="
                + hidIssueDate + ", hidClickRateDay=" + hidClickRateDay + ", hidConsultClientDay=" + hidConsultClientDay
                + ", hidConsult=" + hidConsult + ", hidCClient=" + hidCClient + ", hidBClient=" + hidBClient
                + ", hidAClient=" + hidAClient + ", hidSuccessClient=" + hidSuccessClient + "]";
    }

    public String getHidArea() {
        return hidArea;
    }

    public void setHidArea(String hidArea) {
        this.hidArea = hidArea;
    }

    public String getHidName() {
        return hidName;
    }

    public void setHidName(String hidName) {
        this.hidName = hidName;
    }

    public String getHidAddress() {
        return hidAddress;
    }

    public void setHidAddress(String hidAddress) {
        this.hidAddress = hidAddress;
    }

    public String getHidTitle() {
        return hidTitle;
    }

    public void setHidTitle(String hidTitle) {
        this.hidTitle = hidTitle;
    }

    public String getHidRoom() {
        return hidRoom;
    }

    public void setHidRoom(String hidRoom) {
        this.hidRoom = hidRoom;
    }

    public String getHidHall() {
        return hidHall;
    }

    public void setHidHall(String hidHall) {
        this.hidHall = hidHall;
    }

    public String getHidKitchen() {
        return hidKitchen;
    }

    public void setHidKitchen(String hidKitchen) {
        this.hidKitchen = hidKitchen;
    }

    public String getHidToilet() {
        return hidToilet;
    }

    public void setHidToilet(String hidToilet) {
        this.hidToilet = hidToilet;
    }

    public String getHidAttach() {
        return hidAttach;
    }

    public void setHidAttach(String hidAttach) {
        this.hidAttach = hidAttach;
    }

    public String getHidSpace() {
        return hidSpace;
    }

    public void setHidSpace(String hidSpace) {
        this.hidSpace = hidSpace;
    }

    public String getHidPrice() {
        return hidPrice;
    }

    public void setHidPrice(String hidPrice) {
        this.hidPrice = hidPrice;
    }

    public String getHidOrientation() {
        return hidOrientation;
    }

    public void setHidOrientation(String hidOrientation) {
        this.hidOrientation = hidOrientation;
    }

    public String getHidFloorNow() {
        return hidFloorNow;
    }

    public void setHidFloorNow(String hidFloorNow) {
        this.hidFloorNow = hidFloorNow;
    }

    public String getHidFloorAll() {
        return hidFloorAll;
    }

    public void setHidFloorAll(String hidFloorAll) {
        this.hidFloorAll = hidFloorAll;
    }

    public String getHidFinish() {
        return hidFinish;
    }

    public void setHidFinish(String hidFinish) {
        this.hidFinish = hidFinish;
    }

    public String getHidHouseAge() {
        return hidHouseAge;
    }

    public void setHidHouseAge(String hidHouseAge) {
        this.hidHouseAge = hidHouseAge;
    }

    public String getHidFirstPicType() {
        return hidFirstPicType;
    }

    public void setHidFirstPicType(String hidFirstPicType) {
        this.hidFirstPicType = hidFirstPicType;
    }

    public String getHidFirstPic() {
        return hidFirstPic;
    }

    public void setHidFirstPic(String hidFirstPic) {
        this.hidFirstPic = hidFirstPic;
    }

    public String getHidIssueDate() {
        return hidIssueDate;
    }

    public void setHidIssueDate(String hidIssueDate) {
        this.hidIssueDate = hidIssueDate;
    }

    public String getHidClickRateDay() {
        return hidClickRateDay;
    }

    public void setHidClickRateDay(String hidClickRateDay) {
        this.hidClickRateDay = hidClickRateDay;
    }

    public String getHidConsultClientDay() {
        return hidConsultClientDay;
    }

    public void setHidConsultClientDay(String hidConsultClientDay) {
        this.hidConsultClientDay = hidConsultClientDay;
    }

    public String getHidConsult() {
        return hidConsult;
    }

    public void setHidConsult(String hidConsult) {
        this.hidConsult = hidConsult;
    }

    public String getHidCClient() {
        return hidCClient;
    }

    public void setHidCClient(String hidCClient) {
        this.hidCClient = hidCClient;
    }

    public String getHidBClient() {
        return hidBClient;
    }

    public void setHidBClient(String hidBClient) {
        this.hidBClient = hidBClient;
    }

    public String getHidAClient() {
        return hidAClient;
    }

    public void setHidAClient(String hidAClient) {
        this.hidAClient = hidAClient;
    }

    public String getHidSuccessClient() {
        return hidSuccessClient;
    }

    public void setHidSuccessClient(String hidSuccessClient) {
        this.hidSuccessClient = hidSuccessClient;
    }


}
