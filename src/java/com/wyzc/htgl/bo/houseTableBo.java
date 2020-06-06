package com.wyzc.htgl.bo;

import java.io.Serializable;

import com.wyzc.htgl.po.houseTablePo;

/**
 * 救救孩子吧
 *
 * @author 二娃
 */
public class houseTableBo extends houseTablePo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String searchMo;
    private String shiTudi;
    private String shiLc;
    private String filePath;
    private String order;
    private String sort;
    private String shiStartPrice1;
    private String shiStartPrice2;
    private String shiSpace1;
    private String shiSpace2;
    private String userId;
    private String shiArea;
    private String shiFinish;
    private String shiIsElevactor;
    private String shiBulidDate;

    public houseTableBo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public houseTableBo(String shiCode, String shiArea, String shiName, String shiHouseNumber, String shiSpace,
                        String shiConstruction, String shiFinish, String shiPrice, String shiStartPrice, String shiGuaranteePrice,
                        String shiIsElevactor, String shiAddPrice, String shiAuctionDate, String shiBulidDate, String shiLocation,
                        String shiUrl) {
        super(shiCode, shiArea, shiName, shiHouseNumber, shiSpace, shiConstruction, shiFinish, shiPrice, shiStartPrice,
                shiGuaranteePrice, shiIsElevactor, shiAddPrice, shiAuctionDate, shiBulidDate, shiLocation, shiUrl);
        // TODO Auto-generated constructor stub
    }

    public houseTableBo(String searchMo, String shiTudi, String shiLc, String filePath, String order, String sort,
                        String shiStartPrice1, String shiStartPrice2, String shiSpace1, String shiSpace2, String userId,
                        String shiArea, String shiFinish, String shiIsElevactor, String shiBulidDate) {
        super();
        this.searchMo = searchMo;
        this.shiTudi = shiTudi;
        this.shiLc = shiLc;
        this.filePath = filePath;
        this.order = order;
        this.sort = sort;
        this.shiStartPrice1 = shiStartPrice1;
        this.shiStartPrice2 = shiStartPrice2;
        this.shiSpace1 = shiSpace1;
        this.shiSpace2 = shiSpace2;
        this.userId = userId;
        this.shiArea = shiArea;
        this.shiFinish = shiFinish;
        this.shiIsElevactor = shiIsElevactor;
        this.shiBulidDate = shiBulidDate;
    }

    @Override
    public String toString() {
        return "houseTableBo [searchMo=" + searchMo + ", shiTudi=" + shiTudi + ", shiLc=" + shiLc + ", filePath="
                + filePath + ", order=" + order + ", sort=" + sort + ", shiStartPrice1=" + shiStartPrice1
                + ", shiStartPrice2=" + shiStartPrice2 + ", shiSpace1=" + shiSpace1 + ", shiSpace2=" + shiSpace2
                + ", userId=" + userId + ", shiArea=" + shiArea + ", shiFinish=" + shiFinish + ", shiIsElevactor="
                + shiIsElevactor + ", shiBulidDate=" + shiBulidDate + "]";
    }

    public String getSearchMo() {
        return searchMo;
    }

    public void setSearchMo(String searchMo) {
        this.searchMo = searchMo;
    }

    public String getShiTudi() {
        return shiTudi;
    }

    public void setShiTudi(String shiTudi) {
        this.shiTudi = shiTudi;
    }

    public String getShiLc() {
        return shiLc;
    }

    public void setShiLc(String shiLc) {
        this.shiLc = shiLc;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getShiStartPrice1() {
        return shiStartPrice1;
    }

    public void setShiStartPrice1(String shiStartPrice1) {
        this.shiStartPrice1 = shiStartPrice1;
    }

    public String getShiStartPrice2() {
        return shiStartPrice2;
    }

    public void setShiStartPrice2(String shiStartPrice2) {
        this.shiStartPrice2 = shiStartPrice2;
    }

    public String getShiSpace1() {
        return shiSpace1;
    }

    public void setShiSpace1(String shiSpace1) {
        this.shiSpace1 = shiSpace1;
    }

    public String getShiSpace2() {
        return shiSpace2;
    }

    public void setShiSpace2(String shiSpace2) {
        this.shiSpace2 = shiSpace2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShiArea() {
        return shiArea;
    }

    public void setShiArea(String shiArea) {
        this.shiArea = shiArea;
    }

    public String getShiFinish() {
        return shiFinish;
    }

    public void setShiFinish(String shiFinish) {
        this.shiFinish = shiFinish;
    }

    public String getShiIsElevactor() {
        return shiIsElevactor;
    }

    public void setShiIsElevactor(String shiIsElevactor) {
        this.shiIsElevactor = shiIsElevactor;
    }

    public String getShiBulidDate() {
        return shiBulidDate;
    }

    public void setShiBulidDate(String shiBulidDate) {
        this.shiBulidDate = shiBulidDate;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
