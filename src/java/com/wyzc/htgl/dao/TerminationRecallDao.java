package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;

public interface TerminationRecallDao {

    //分页查询
    List<houseTableBo> showTerminationRecall(Page page);

}
