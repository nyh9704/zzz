package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;

public interface NegotiateCaseStatisticsDao {

    //分页查询
    List<ClientInfoBo> searchNegotiateCaseStatistics(Page page);

    //根据编号查询信息
    ClientInfoBo searchNegotiateCaseStatisticsById(ClientInfoBo bo);

    //修改
    Integer modifyNegotiateCaseStatisticsById(ClientInfoBo bo);

    //查询所填谈单人所属部门
    String searchDepartOfDecider(ClientInfoBo bo);

    //删除
    Integer deletenegotiateCaseStatistics(ClientInfoBo bo);

    //权利的游戏之行政部门
    String administrativeGameOfThrones(ClientInfoBo bo);

}
