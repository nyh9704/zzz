package com.wyzc.htgl.dao;

import java.util.List;

import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.po.WyzcUserInfoPo;

public interface WyzcUserDao {

    //注册功能实现
    Integer registerUser(WyzcUserBo bo);

    //查询员工信息表
    List<WyzcUserInfoPo> queryUserInfo();

    //查询上级部门
    List<WyzcDepartmentBo> searchParentPart();

    //查询下级部门
    List<WyzcDepartmentBo> searchChildPart(WyzcDepartmentBo bo);

    //注册权限
    void registerUserQx(WyzcUserBo bo);

    //根据用户输入的信息在表中查询
    WyzcUserBo searchUserInfoByTel(WyzcUserBo bo);

    /**
     * 添加角色
     *
     * @param bo
     * @return
     */
    Integer addSysRole(WyzcUserBo bo);

}
