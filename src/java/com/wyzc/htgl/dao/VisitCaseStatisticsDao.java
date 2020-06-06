package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;

public interface VisitCaseStatisticsDao {

    //分页查询
    List<ClientInfoBo> searchVisitCaseStatistics(Page page);

    //查询当前登录用户的姓名
    String searchUserName(ClientInfoBo bo);

}
