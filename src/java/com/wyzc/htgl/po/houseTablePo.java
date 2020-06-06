package com.wyzc.htgl.po;

import com.bn.javax.po.BasePo;


public class houseTablePo extends BasePo {
    private String shiCode;//id号
    private String shiArea;//区域
    private String shiName;//楼盘名称
    private String shiHouseNumber;//房号
    private String shiSpace;//面积
    private String shiConstruction;//结构
    private String shiFinish;//装修
    private String shiPrice;//市场价
    private String shiStartPrice;//起拍价
    private String shiGuaranteePrice;//保证金
    private String shiIsElevactor;//有无电梯
    private String shiAddPrice;//加价幅度
    private String shiAuctionDate;//竞拍日期
    private String nowAuctionDate; //系统当前时间
    private String shiBulidDate;//建成时间
    private String shiLocation;//位置
    private String shiUrl;//网址
    private String shiUploadDate; //上传日期
    private String nowUploadDate; //系统当前时间
    private String shiEndDate; //结束日期
    private String nowEndDate; //系统当前时间
    private String shiRecallDate; //撤回日期
    private String shiSign; //标记
    private String shiAppendix; //附件
    private String nowTerminationDate; //系统当前时间

    public houseTablePo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public houseTablePo(String shiCode, String shiArea, String shiName, String shiHouseNumber, String shiSpace,
                        String shiConstruction, String shiFinish, String shiPrice, String shiStartPrice, String shiGuaranteePrice,
                        String shiIsElevactor, String shiAddPrice, String shiAuctionDate, String shiBulidDate, String shiLocation,
                        String shiUrl) {
        super();
        this.shiCode = shiCode;
        this.shiArea = shiArea;
        this.shiName = shiName;
        this.shiHouseNumber = shiHouseNumber;
        this.shiSpace = shiSpace;
        this.shiConstruction = shiConstruction;
        this.shiFinish = shiFinish;
        this.shiPrice = shiPrice;
        this.shiStartPrice = shiStartPrice;
        this.shiGuaranteePrice = shiGuaranteePrice;
        this.shiIsElevactor = shiIsElevactor;
        this.shiAddPrice = shiAddPrice;
        this.shiAuctionDate = shiAuctionDate;
        this.shiBulidDate = shiBulidDate;
        this.shiLocation = shiLocation;
        this.shiUrl = shiUrl;
    }

    @Override
    public String toString() {
        return "houseTablePo [shiCode=" + shiCode + ", shiArea=" + shiArea + ", shiName=" + shiName
                + ", shiHouseNumber=" + shiHouseNumber + ", shiSpace=" + shiSpace + ", shiConstruction="
                + shiConstruction + ", shiFinish=" + shiFinish + ", shiPrice=" + shiPrice + ", shiStartPrice="
                + shiStartPrice + ", shiGuaranteePrice=" + shiGuaranteePrice + ", shiIsElevactor=" + shiIsElevactor
                + ", shiAddPrice=" + shiAddPrice + ", shiAuctionDate=" + shiAuctionDate + ", shiBulidDate="
                + shiBulidDate + ", shiLocation=" + shiLocation + ", shiUrl=" + shiUrl + "]";
    }

    public String getShiCode() {
        return shiCode;
    }

    public void setShiCode(String shiCode) {
        this.shiCode = shiCode;
    }

    public String getShiArea() {
        return shiArea;
    }

    public void setShiArea(String shiArea) {
        this.shiArea = shiArea;
    }

    public String getShiName() {
        return shiName;
    }

    public void setShiName(String shiName) {
        this.shiName = shiName;
    }

    public String getShiHouseNumber() {
        return shiHouseNumber;
    }

    public void setShiHouseNumber(String shiHouseNumber) {
        this.shiHouseNumber = shiHouseNumber;
    }

    public String getShiSpace() {
        return shiSpace;
    }

    public void setShiSpace(String shiSpace) {
        this.shiSpace = shiSpace;
    }

    public String getShiConstruction() {
        return shiConstruction;
    }

    public void setShiConstruction(String shiConstruction) {
        this.shiConstruction = shiConstruction;
    }

    public String getShiFinish() {
        return shiFinish;
    }

    public void setShiFinish(String shiFinish) {
        this.shiFinish = shiFinish;
    }

    public String getShiPrice() {
        return shiPrice;
    }

    public void setShiPrice(String shiPrice) {
        this.shiPrice = shiPrice;
    }

    public String getShiStartPrice() {
        return shiStartPrice;
    }

    public void setShiStartPrice(String shiStartPrice) {
        this.shiStartPrice = shiStartPrice;
    }

    public String getShiGuaranteePrice() {
        return shiGuaranteePrice;
    }

    public void setShiGuaranteePrice(String shiGuaranteePrice) {
        this.shiGuaranteePrice = shiGuaranteePrice;
    }

    public String getShiIsElevactor() {
        return shiIsElevactor;
    }

    public void setShiIsElevactor(String shiIsElevactor) {
        this.shiIsElevactor = shiIsElevactor;
    }

    public String getShiAddPrice() {
        return shiAddPrice;
    }

    public void setShiAddPrice(String shiAddPrice) {
        this.shiAddPrice = shiAddPrice;
    }

    public String getShiAuctionDate() {
        return shiAuctionDate;
    }

    public void setShiAuctionDate(String shiAuctionDate) {
        this.shiAuctionDate = shiAuctionDate;
    }

    public String getShiBulidDate() {
        return shiBulidDate;
    }

    public void setShiBulidDate(String shiBulidDate) {
        this.shiBulidDate = shiBulidDate;
    }

    public String getShiLocation() {
        return shiLocation;
    }

    public void setShiLocation(String shiLocation) {
        this.shiLocation = shiLocation;
    }

    public String getShiUrl() {
        return shiUrl;
    }

    public void setShiUrl(String shiUrl) {
        this.shiUrl = shiUrl;
    }

    public String getShiUploadDate() {
        return shiUploadDate;
    }

    public void setShiUploadDate(String shiUploadDate) {
        this.shiUploadDate = shiUploadDate;
    }

    public String getShiEndDate() {
        return shiEndDate;
    }

    public void setShiEndDate(String shiEndDate) {
        this.shiEndDate = shiEndDate;
    }

    public String getShiRecallDate() {
        return shiRecallDate;
    }

    public void setShiRecallDate(String shiRecallDate) {
        this.shiRecallDate = shiRecallDate;
    }

    public String getShiSign() {
        return shiSign;
    }

    public void setShiSign(String shiSign) {
        this.shiSign = shiSign;
    }

    public String getShiAppendix() {
        return shiAppendix;
    }

    public void setShiAppendix(String shiAppendix) {
        this.shiAppendix = shiAppendix;
    }

    public String getNowAuctionDate() {
        return nowAuctionDate;
    }

    public void setNowAuctionDate(String nowAuctionDate) {
        this.nowAuctionDate = nowAuctionDate;
    }

    public String getNowUploadDate() {
        return nowUploadDate;
    }

    public void setNowUploadDate(String nowUploadDate) {
        this.nowUploadDate = nowUploadDate;
    }

    public String getNowEndDate() {
        return nowEndDate;
    }

    public void setNowEndDate(String nowEndDate) {
        this.nowEndDate = nowEndDate;
    }

    public String getNowTerminationDate() {
        return nowTerminationDate;
    }

    public void setNowTerminationDate(String nowTerminationDate) {
        this.nowTerminationDate = nowTerminationDate;
    }


}
