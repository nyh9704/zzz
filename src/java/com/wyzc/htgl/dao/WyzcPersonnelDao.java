package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo2;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;
import com.wyzc.htgl.bo.houseTableBo;

public interface WyzcPersonnelDao {

    //展现和查询
    List<WyzcPersonnelBo> showDepartureData1(WyzcPersonnelBo bo);

    //根据id删除查询
    void deleteDepartureDataSerche(WyzcPersonnelBo2 bo2);

    //删除当前导出数据
    void deleteDepartureDataExportHouse(WyzcPersonnelBo2 bo2);

    //添加数据到复制表
    void addDepartureData(WyzcPersonnelBo2 bo2);

    List<WyzcPersonnelBo2> searchDepartureData1(WyzcPersonnelBo2 bo2);

    List<WyzcPersonnelBo2> searchDepartureData(WyzcPersonnelBo2 bo2);


    void addExportDepartureData(WyzcPersonnelBo2 bo2);

    List<WyzcPersonnelBo> showDepartureData(Page page);

    List<WyzcPersonnelBo> searchBm(WyzcPersonnelBo bo);

    //导出当前
    List<WyzcPersonnelBo> exportPersonDq(WyzcPersonnelBo bo);

    //导出全部
    List<WyzcPersonnelBo> exportPerson(WyzcPersonnelBo bo);

    //删除
    Integer deletePerson(WyzcPersonnelBo bo);

    //查询当前登录用户的部门及岗位等级
    WyzcPersonnelTestBo userJurisdiction(WyzcPersonnelTestBo bo);

    List<WyzcPersonnelBo> searchDepartureQx(WyzcPersonnelBo bo);

}
