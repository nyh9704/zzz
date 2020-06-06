package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.po.HouseImgUrlPo;

public interface HouseImgDao {

    //添加
    Integer AddHouseImg(HouseImgUrlPo bo);

    //查询及模糊查询
    List<houseTableBo> ShowHouseImg(Page page);

}
