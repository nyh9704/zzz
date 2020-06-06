package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;

public interface AuctionEndDao {

    //分页查询
    List<houseTableBo> showAuctionEnd(Page page);

    //修改房源信息
    Integer modifyAuctionEnd(houseTableBo bo);

    String gameOfThrones(houseTableBo bo);

}
