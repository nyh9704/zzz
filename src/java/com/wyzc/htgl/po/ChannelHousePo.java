package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;

public class ChannelHousePo extends BasePo {
    private String channelCode;//编号
    private String channelNature;//性质
    private String channelArea;//区域
    private String channelPosition;//位置
    private String channelBuildName;//楼盘名称
    private String channelCoverArea;//套内面积
    private String channelBuildArea;//建筑面积
    private String channelInterior;//室内结构
    private String channelDecoration;//装修情况
    private String channelPurchasing;//购房流程
    private String channelIntroduce;//介绍
    private String channelPrice;//价格
    private String channelDeposit;//订金
    private String channelMortgage;//按揭情况
    private String channelTax;//税费情况
    private String channelFloor;//楼层
    private String channelBuildAge;//建筑年代
    private String channelHouseKeeping;//看房情况
    private String channelHouseKeepingPs;//看房情况备注
    private String channelRoom;//室
    private String channelHall;//厅
    private String channelKitchen;//厨
    private String channelToilet;//卫
    private String order;//排序
    private String sort;//排序
    private String channelPrice1;//价格
    private String channelPrice2;//价格
    private String channelBuildArea1;//建筑面积
    private String channelBuildArea2;//建筑面积
    private String searchMo;//搜索
    private String userId;//用户名
    private String wpId;//部门id

    public ChannelHousePo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ChannelHousePo(String channelCode, String channelNature, String channelArea, String channelPosition,
                          String channelBuildName, String channelCoverArea, String channelBuildArea, String channelInterior,
                          String channelDecoration, String channelPurchasing, String channelIntroduce, String channelPrice,
                          String channelDeposit, String channelMortgage, String channelTax, String channelFloor,
                          String channelBuildAge, String channelHouseKeeping, String channelHouseKeepingPs, String channelRoom,
                          String channelHall, String channelKitchen, String channelToilet, String order, String sort,
                          String channelPrice1, String channelPrice2, String channelBuildArea1, String channelBuildArea2,
                          String searchMo, String userId, String wpId) {
        super();
        this.channelCode = channelCode;
        this.channelNature = channelNature;
        this.channelArea = channelArea;
        this.channelPosition = channelPosition;
        this.channelBuildName = channelBuildName;
        this.channelCoverArea = channelCoverArea;
        this.channelBuildArea = channelBuildArea;
        this.channelInterior = channelInterior;
        this.channelDecoration = channelDecoration;
        this.channelPurchasing = channelPurchasing;
        this.channelIntroduce = channelIntroduce;
        this.channelPrice = channelPrice;
        this.channelDeposit = channelDeposit;
        this.channelMortgage = channelMortgage;
        this.channelTax = channelTax;
        this.channelFloor = channelFloor;
        this.channelBuildAge = channelBuildAge;
        this.channelHouseKeeping = channelHouseKeeping;
        this.channelHouseKeepingPs = channelHouseKeepingPs;
        this.channelRoom = channelRoom;
        this.channelHall = channelHall;
        this.channelKitchen = channelKitchen;
        this.channelToilet = channelToilet;
        this.order = order;
        this.sort = sort;
        this.channelPrice1 = channelPrice1;
        this.channelPrice2 = channelPrice2;
        this.channelBuildArea1 = channelBuildArea1;
        this.channelBuildArea2 = channelBuildArea2;
        this.searchMo = searchMo;
        this.userId = userId;
        this.wpId = wpId;
    }

    @Override
    public String toString() {
        return "ChannelHousePo [channelCode=" + channelCode + ", channelNature=" + channelNature + ", channelArea="
                + channelArea + ", channelPosition=" + channelPosition + ", channelBuildName=" + channelBuildName
                + ", channelCoverArea=" + channelCoverArea + ", channelBuildArea=" + channelBuildArea
                + ", channelInterior=" + channelInterior + ", channelDecoration=" + channelDecoration
                + ", channelPurchasing=" + channelPurchasing + ", channelIntroduce=" + channelIntroduce
                + ", channelPrice=" + channelPrice + ", channelDeposit=" + channelDeposit + ", channelMortgage="
                + channelMortgage + ", channelTax=" + channelTax + ", channelFloor=" + channelFloor
                + ", channelBuildAge=" + channelBuildAge + ", channelHouseKeeping=" + channelHouseKeeping
                + ", channelHouseKeepingPs=" + channelHouseKeepingPs + ", channelRoom=" + channelRoom + ", channelHall="
                + channelHall + ", channelKitchen=" + channelKitchen + ", channelToilet=" + channelToilet + ", order="
                + order + ", sort=" + sort + ", channelPrice1=" + channelPrice1 + ", channelPrice2=" + channelPrice2
                + ", channelBuildArea1=" + channelBuildArea1 + ", channelBuildArea2=" + channelBuildArea2
                + ", searchMo=" + searchMo + ", userId=" + userId + ", wpId=" + wpId + "]";
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelNature() {
        return channelNature;
    }

    public void setChannelNature(String channelNature) {
        this.channelNature = channelNature;
    }

    public String getChannelArea() {
        return channelArea;
    }

    public void setChannelArea(String channelArea) {
        this.channelArea = channelArea;
    }

    public String getChannelPosition() {
        return channelPosition;
    }

    public void setChannelPosition(String channelPosition) {
        this.channelPosition = channelPosition;
    }

    public String getChannelBuildName() {
        return channelBuildName;
    }

    public void setChannelBuildName(String channelBuildName) {
        this.channelBuildName = channelBuildName;
    }

    public String getChannelCoverArea() {
        return channelCoverArea;
    }

    public void setChannelCoverArea(String channelCoverArea) {
        this.channelCoverArea = channelCoverArea;
    }

    public String getChannelBuildArea() {
        return channelBuildArea;
    }

    public void setChannelBuildArea(String channelBuildArea) {
        this.channelBuildArea = channelBuildArea;
    }

    public String getChannelInterior() {
        return channelInterior;
    }

    public void setChannelInterior(String channelInterior) {
        this.channelInterior = channelInterior;
    }

    public String getChannelDecoration() {
        return channelDecoration;
    }

    public void setChannelDecoration(String channelDecoration) {
        this.channelDecoration = channelDecoration;
    }

    public String getChannelPurchasing() {
        return channelPurchasing;
    }

    public void setChannelPurchasing(String channelPurchasing) {
        this.channelPurchasing = channelPurchasing;
    }

    public String getChannelIntroduce() {
        return channelIntroduce;
    }

    public void setChannelIntroduce(String channelIntroduce) {
        this.channelIntroduce = channelIntroduce;
    }

    public String getChannelPrice() {
        return channelPrice;
    }

    public void setChannelPrice(String channelPrice) {
        this.channelPrice = channelPrice;
    }

    public String getChannelDeposit() {
        return channelDeposit;
    }

    public void setChannelDeposit(String channelDeposit) {
        this.channelDeposit = channelDeposit;
    }

    public String getChannelMortgage() {
        return channelMortgage;
    }

    public void setChannelMortgage(String channelMortgage) {
        this.channelMortgage = channelMortgage;
    }

    public String getChannelTax() {
        return channelTax;
    }

    public void setChannelTax(String channelTax) {
        this.channelTax = channelTax;
    }

    public String getChannelFloor() {
        return channelFloor;
    }

    public void setChannelFloor(String channelFloor) {
        this.channelFloor = channelFloor;
    }

    public String getChannelBuildAge() {
        return channelBuildAge;
    }

    public void setChannelBuildAge(String channelBuildAge) {
        this.channelBuildAge = channelBuildAge;
    }

    public String getChannelHouseKeeping() {
        return channelHouseKeeping;
    }

    public void setChannelHouseKeeping(String channelHouseKeeping) {
        this.channelHouseKeeping = channelHouseKeeping;
    }

    public String getChannelHouseKeepingPs() {
        return channelHouseKeepingPs;
    }

    public void setChannelHouseKeepingPs(String channelHouseKeepingPs) {
        this.channelHouseKeepingPs = channelHouseKeepingPs;
    }

    public String getChannelRoom() {
        return channelRoom;
    }

    public void setChannelRoom(String channelRoom) {
        this.channelRoom = channelRoom;
    }

    public String getChannelHall() {
        return channelHall;
    }

    public void setChannelHall(String channelHall) {
        this.channelHall = channelHall;
    }

    public String getChannelKitchen() {
        return channelKitchen;
    }

    public void setChannelKitchen(String channelKitchen) {
        this.channelKitchen = channelKitchen;
    }

    public String getChannelToilet() {
        return channelToilet;
    }

    public void setChannelToilet(String channelToilet) {
        this.channelToilet = channelToilet;
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

    public String getChannelPrice1() {
        return channelPrice1;
    }

    public void setChannelPrice1(String channelPrice1) {
        this.channelPrice1 = channelPrice1;
    }

    public String getChannelPrice2() {
        return channelPrice2;
    }

    public void setChannelPrice2(String channelPrice2) {
        this.channelPrice2 = channelPrice2;
    }

    public String getChannelBuildArea1() {
        return channelBuildArea1;
    }

    public void setChannelBuildArea1(String channelBuildArea1) {
        this.channelBuildArea1 = channelBuildArea1;
    }

    public String getChannelBuildArea2() {
        return channelBuildArea2;
    }

    public void setChannelBuildArea2(String channelBuildArea2) {
        this.channelBuildArea2 = channelBuildArea2;
    }

    public String getSearchMo() {
        return searchMo;
    }

    public void setSearchMo(String searchMo) {
        this.searchMo = searchMo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }

}
