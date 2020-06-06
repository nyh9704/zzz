package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo2;
import com.wyzc.htgl.po.ChannelHousePo;
import com.wyzc.htgl.po.ChannelHousePo2;

public interface ChannelHouseDao {

    List<ChannelHousePo> showChannelHouse1(ChannelHousePo po);

    Integer deleteChannelHouseSearch(ChannelHousePo2 po2);

    Integer addChannelHouseCopy(ChannelHousePo2 po2);

    List<ChannelHousePo2> searchChannelHouseFj(Page page);

    List<ChannelHousePo> showChannelHouse(Page page);

    Integer addChannelHouse(ChannelHousePo po);

    List<ChannelHousePo> searchModifyChannelXq(ChannelHousePo po);

    Integer modifyChannelHouse(ChannelHousePo po);

    Integer deleteCode(ChannelHousePo po);

    List<ChannelHousePo> showChannelHouseXq(ChannelHousePo po);

    Integer deleteChannelMoreHouse(ChannelHousePo po);

    List<ChannelHousePo> searchQd(ChannelHousePo po);

}
