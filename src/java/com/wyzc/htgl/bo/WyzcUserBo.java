package com.wyzc.htgl.bo;

import com.wyzc.htgl.po.WyzcUserPo;

public class WyzcUserBo extends WyzcUserPo {
    private String roleId;
    private String sign = "";

    public WyzcUserBo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WyzcUserBo(String userId, String userName, String userPwd, String identityCard, String wpId,
                      String wpIdChild, String wdId) {
        super(userId, userName, userPwd, identityCard, wpId, wpIdChild, wdId);
        // TODO Auto-generated constructor stub
    }

    public WyzcUserBo(String roleId, String sign) {
        super();
        this.roleId = roleId;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WyzcUserBo [roleId=" + roleId + ", sign=" + sign + "]";
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
