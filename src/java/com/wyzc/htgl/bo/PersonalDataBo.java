package com.wyzc.htgl.bo;

import com.wyzc.htgl.po.PersonalDataPo;

public class PersonalDataBo extends PersonalDataPo {
    private String houseFloor;//楼层
    private String houseType;//户型
    private String userId;//登陆编号
    private String hidId; //编号
    private String keyWorld; //关键字
    private String sort; //排序字段
    private String order; //排序方式

    public String getHouseFloor() {
        return houseFloor;
    }

    public void setHouseFloor(String houseFloor) {
        this.houseFloor = houseFloor;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHidId() {
        return hidId;
    }

    public void setHidId(String hidId) {
        this.hidId = hidId;
    }

    public String getKeyWorld() {
        return keyWorld;
    }

    public void setKeyWorld(String keyWorld) {
        this.keyWorld = keyWorld;
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


}
