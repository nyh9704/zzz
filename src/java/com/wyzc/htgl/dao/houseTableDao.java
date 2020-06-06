package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;

/**
 * 救救孩子吧
 *
 * @author 二娃
 */
public interface houseTableDao {

    //分页查询
    List<houseTableBo> showHouse(Page page);

    //添加房源
    Integer addHouse(houseTableBo bo);

    //房源详情
    List<houseTableBo> showFyXq(houseTableBo bo);

    //修改房源信息
    Integer modifyHouse(houseTableBo bo);

    //删除房源信息
    Integer deleteHouse(houseTableBo bo);

    //导出查询所有房源
    List<houseTableBo> exportHouse(houseTableBo bo);

    //当日新增导入
    Integer toDayXz(houseTableBo bo);

    //当日开拍导入
    Integer toDayKp(houseTableBo bo);

    //当日撤回导入
    Integer toDayCh(houseTableBo bo);

    //查询当日新增
    List<houseTableBo> searchTodyXz(Page page);

    //查询当日开拍
    List<houseTableBo> searchTodyKp(Page page);

    //查询当日撤回
    List<houseTableBo> searchTodyCh(Page page);

    //导入之前删除新增的数据
    Integer deleteTodyXz(houseTableBo bo);

    //导入之前删除开拍的数据
    Integer deleteTodyKp(houseTableBo bo);

    //导入之前删除撤回的数据
    Integer deleteTodyCh(houseTableBo bo);

    //上传过期文件
    Integer addPastHouse(houseTableBo bo);

    //查询过期房源表中的数据
    List<houseTableBo> searchPastHouse();

    //删除过期表中的数据
    Integer deletePastHouse(houseTableBo bo);

    //权限
    List<WyzcUserBo> searchId(WyzcUserBo bo);

    //添加
    Integer addHouseCopy(houseTableBo2 bo2);

    //查询
    List<houseTableBo2> searchFj(Page page);

    List<houseTableBo> showHouse1(houseTableBo bo);

    Integer deleteSearch(houseTableBo2 bo2);

    //导出当前查询的房源
    //List<houseTableBo> exportHouseDq(houseTableBo bo);

    //删除导出当前的表数据
    Integer deleteExportHouse(houseTableBo2 bo2);

    //添加当前表数据
    Integer addExportHouse(houseTableBo2 bo2);

    //添加
    Integer addExportHouseCopy(houseTableBo2 bo2);

    List<houseTableBo2> searchFj1(houseTableBo2 bo2);

    //查询房源表中的数据
    List<houseTableBo> searchFromShowHouseInfo();

    //添加撤回时间
    Integer addRecallDateToShi(houseTableBo rb);

    //导出当前房源
    List<houseTableBo> exportNow(houseTableBo bo);
}
