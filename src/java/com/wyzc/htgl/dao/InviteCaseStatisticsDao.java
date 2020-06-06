package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;

public interface InviteCaseStatisticsDao {

    //权利的游戏
    String gGameOfThrones(ClientInfoBo bo);

    //查询当前登录用户的部门
    String searchPartByUserId(ClientInfoBo bo);

    //查所有数据
    List<ClientInfoBo> searchInviteCaseStatisticsAll(Page page);

    List<ClientInfoBo> searchInviteCaseStatisticsSome(Page page);

    //条件查询
    List<ClientInfoBo> searchInviteCaseStatisticsByCondition(Page page);

    //查询当前登录用户的姓名
    String searchUserNameById(ClientInfoBo bo);

    //


}
