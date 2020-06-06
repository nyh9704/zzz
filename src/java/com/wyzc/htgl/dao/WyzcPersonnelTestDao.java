package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;

public interface WyzcPersonnelTestDao {

    //查询所有部门
    List<WyzcPersonnelTestBo> searchAllPart();

    //查询所有岗位等级
    List<WyzcPersonnelTestBo> searchAllDuty();

    //分页查询
    List<WyzcPersonnelTestBo> searchPersonnelTestData(Page page);

    //增加非延期员工信息
    Integer savePersonnelTestInfoNoPostpone(WyzcPersonnelTestBo bo);

    //增加延期员工信息
    Integer savePersonnelTestInfo(WyzcPersonnelTestBo bo);

    //根据员工编号查询员工信息
    WyzcPersonnelTestBo searchPersonnelByUserId(WyzcPersonnelTestBo bo);

    //保存试岗员工修改的信息
    Integer saveModifyTestPersonnel(WyzcPersonnelTestBo bo);

    //离职员工修改信息保存
    Integer saveModifyLeavePersonnel(WyzcPersonnelTestBo bo);

    //导出全部信息
    List<WyzcPersonnelTestBo> exportAll();

    //在账号库中删除该数据
    Integer deletePersonnelByUserId(WyzcPersonnelTestBo bo);

    //删除员工信息
    Integer removePersonnelByUerId(WyzcPersonnelTestBo bo);

    //查询当前登录用户所属部门和岗位等级
    WyzcPersonnelTestBo userJurisdiction(WyzcPersonnelTestBo bo);

    //删除role表中的数据
    void deletePersonnelByUserIdFromRole(WyzcPersonnelTestBo bo);

}
