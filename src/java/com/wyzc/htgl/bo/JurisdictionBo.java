package com.wyzc.htgl.bo;

import com.wyzc.htgl.po.JurisdictionPo;

public class JurisdictionBo extends JurisdictionPo {
    private String state;
    private String code;
    private String wpId;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWpId() {
        return wpId;
    }

    public void setWpId(String wpId) {
        this.wpId = wpId;
    }


}
