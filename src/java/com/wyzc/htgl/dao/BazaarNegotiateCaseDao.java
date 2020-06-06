package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;

public interface BazaarNegotiateCaseDao {

    //查询当前登录用户的部门
    String searchPartById(ClientInfoBo bo);

    //查询当前登录用户的姓名
    String searchUserNameById(ClientInfoBo bo);

    //查询当前用户的职务等级
    String searchDutyById(ClientInfoBo bo);

    //分页查询查全部
    List<ClientInfoBo> searchBazaarNegotiateCaseStatisticsAll(Page page);

    //分页查询条件查询
    List<ClientInfoBo> searchBazaarNegotiateCaseStatisticsByCondition(Page page);

    //提交网签信息
    Integer saveNegotiateCaseStatistics(ClientInfoBo bo);

    //补全结果
    Integer modifyBazaarNegotiateCaseStatisticsById(ClientInfoBo bo);

}
