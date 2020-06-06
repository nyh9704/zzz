package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;

public interface StopHouseDao {

    List<houseTableBo> showStopHouse1(houseTableBo bo);

    Integer addStopHouseCopy(houseTableBo2 bo2);

    Integer addStopHouseCopy1(houseTableBo2 bo2);

    List<houseTableBo2> searchStopFj(Page page);

    List<houseTableBo> showStopHouse(Page page);

    Integer deleteStopSearch(houseTableBo2 bo2);

    Integer addStopHouse(houseTableBo bo);

    List<houseTableBo> exportStopHouse(houseTableBo bo);

    List<houseTableBo> showStopFyXq(houseTableBo bo);

    List<houseTableBo2> searchStopFj1(houseTableBo2 bo2);

    List<houseTableBo> exportStopHouseDq(houseTableBo bo);

    Integer deleteStopHouseSearch(houseTableBo2 bo2);

}
