package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.dao.ChannelHouseDao;
import com.wyzc.htgl.po.ChannelHousePo;
import com.wyzc.htgl.po.ChannelHousePo2;

@Service
public class ChannelHouseService {

    @Autowired
    private ChannelHouseDao chd;

    /**
     * 查询+展现
     *
     * @param po
     * @return
     */
    public Map<String, Object> showChannelHouse(ChannelHousePo po, ChannelHousePo2 po2) {
        if (po.getSort() == "channelCoverArea") {
            po.setSort("channel_coverarea");
        } else if (po.getSort() == "channelBuildArea") {
            po.setSort("channel_buildarea");
        } else if (po.getSort() == "channelPrice") {
            po.setSort("channel_price");
        } else if (po.getSort() == "channelBuildAge") {
            po.setSort("channel_buildage");
        }
        Map<String, Object> map = new HashMap<>();
        if (po.getSearchMo() != null || po.getSearchMo() != "" || po.getChannelPrice1() != null || po.getChannelPrice1() != ""
                || po.getChannelPrice2() != null || po.getChannelPrice2() != ""
                || po.getChannelBuildArea1() != null || po.getChannelBuildArea1() != ""
                || po.getChannelBuildArea2() != null || po.getChannelBuildArea2() != ""
                || po.getChannelNature() != null || po.getChannelNature() != ""
                || po.getChannelArea() != null || po.getChannelArea() != ""
                || po.getChannelDecoration() != null || po.getChannelDecoration() != ""
                || po.getChannelBuildAge() != null || po.getChannelBuildAge() != ""
        ) {
            String userId = CommonFunction.getUserFromSession().getUserId();
            List<ChannelHousePo> list = this.chd.showChannelHouse1(po);
            //map.put("rows", list);


            po2.setUserId(userId);
            this.chd.deleteChannelHouseSearch(po2);
            for (int i = 0; i < list.size(); i++) {
                po2.setChannelCode(list.get(i).getChannelCode());
                po2.setChannelNature(list.get(i).getChannelNature());
                po2.setChannelArea(list.get(i).getChannelArea());
                po2.setChannelPosition(list.get(i).getChannelPosition());
                po2.setChannelBuildName(list.get(i).getChannelBuildName());
                po2.setChannelCoverArea(list.get(i).getChannelCoverArea());
                po2.setChannelBuildArea(list.get(i).getChannelBuildArea());
                po2.setChannelInterior(list.get(i).getChannelInterior());
                po2.setChannelDecoration(list.get(i).getChannelDecoration());
                po2.setChannelPurchasing(list.get(i).getChannelPurchasing());
                po2.setChannelIntroduce(list.get(i).getChannelIntroduce());
                po2.setChannelPrice(list.get(i).getChannelPrice());
                po2.setChannelDeposit(list.get(i).getChannelDeposit());
                po2.setChannelMortgage(list.get(i).getChannelMortgage());
                po2.setChannelTax(list.get(i).getChannelTax());
                po2.setChannelFloor(list.get(i).getChannelFloor());
                po2.setChannelBuildAge(list.get(i).getChannelBuildAge());
                po2.setChannelHouseKeeping(list.get(i).getChannelHouseKeeping());
                po2.setChannelHouseKeepingPs(list.get(i).getChannelHouseKeepingPs());
                po2.setChannelRoom(list.get(i).getChannelRoom());
                po2.setChannelHall(list.get(i).getChannelHall());
                po2.setChannelKitchen(list.get(i).getChannelKitchen());
                po2.setChannelToilet(list.get(i).getChannelToilet());
                po2.setOrder(list.get(i).getOrder());
                po2.setSort(list.get(i).getSort());
                po2.setUserId(userId);
                this.chd.addChannelHouseCopy(po2);
            }
            po2.setUserId(userId);
            po2.setChannelBuildArea1(po.getChannelBuildArea1());
            po2.setChannelBuildArea2(po.getChannelBuildArea2());
            po2.setChannelPrice1(po.getChannelPrice1());
            po2.setChannelPrice2(po.getChannelPrice2());
            po2.setChannelArea1(po.getChannelArea());
            po2.setChannelDecoration1(po.getChannelDecoration());
            po2.setChannelNature1(po.getChannelNature());
            po2.setChannelBuildAge1(po.getChannelBuildAge());
            Page page = new Page(po2);
            List<ChannelHousePo2> list2 = this.chd.searchChannelHouseFj(page);
            for (ChannelHousePo2 co : list2) {
                System.err.println(co);
            }
            map.put("rows", list2);
            map.put("total", page.getTotalRecord());
        } else {
            Page page = new Page(po);
            List<ChannelHousePo> list = this.chd.showChannelHouse(page);
            map.put("rows", list);
            map.put("total", page.getTotalRecord());
        }
        return map;
    }


    /**
     * 新增
     *
     * @param po
     * @return
     */
    public Map<String, Object> addChannelHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        if (po.getChannelRoom() == "" || po.getChannelRoom() == null) {
            po.setChannelRoom("0");
        }
        if (po.getChannelHall() == "" || po.getChannelHall() == null) {
            po.setChannelHall("0");
        }
        if (po.getChannelKitchen() == "" || po.getChannelKitchen() == null) {
            po.setChannelKitchen("0");
        }
        if (po.getChannelToilet() == "" || po.getChannelToilet() == null) {
            po.setChannelToilet("0");
        }
        po.setChannelInterior(po.getChannelRoom() + "室" + po.getChannelHall() + "厅" + po.getChannelKitchen() + "卫" + po.getChannelToilet() + "厨");
        map.put("addChannelHouse", this.chd.addChannelHouse(po));
        return map;
    }


    /**
     * 修改回显
     *
     * @param po
     * @return
     */
    public Map<String, Object> searchModifyChannelXq(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        List<ChannelHousePo> list = this.chd.searchModifyChannelXq(po);
        map.put("searchModifyChannelXq", list);
        return map;
    }

    /**
     * 修改房源
     *
     * @param po
     * @return
     */
    public Map<String, Object> modifyChannelHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        if (po.getChannelRoom() == "" || po.getChannelRoom() == null) {
            po.setChannelRoom("0");
        }
        if (po.getChannelHall() == "" || po.getChannelHall() == null) {
            po.setChannelHall("0");
        }
        if (po.getChannelKitchen() == "" || po.getChannelKitchen() == null) {
            po.setChannelKitchen("0");
        }
        if (po.getChannelToilet() == "" || po.getChannelToilet() == null) {
            po.setChannelToilet("0");
        }
        po.setChannelInterior(po.getChannelRoom() + "室" + po.getChannelHall() + "厅" + po.getChannelKitchen() + "卫" + po.getChannelToilet() + "厨");
        map.put("modifyChannelHouse", this.chd.modifyChannelHouse(po));
        return map;
    }

    /**
     * 删除
     *
     * @param po
     * @return
     */
    public Map<String, Object> deleteCode(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        map.put("deleteCode", this.chd.deleteCode(po));
        return map;
    }


    /**
     * 其他信息展示
     *
     * @param po
     * @return
     */
    public Map<String, Object> showChannelHouseXq(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        List<ChannelHousePo> list = this.chd.showChannelHouseXq(po);
        map.put("showChannelHouseXq", list);
        return map;
    }

    /**
     * 批量删除
     *
     * @param po
     * @return
     */
    public Map<String, Object> deleteChannelMoreHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        map.put("deleteChannelMoreHouse", this.chd.deleteCode(po));
        return map;
    }


    /**
     * 查询是否渠道部
     *
     * @param po
     * @return
     */
    public Map<String, Object> searchQd(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        po.setUserId(userId);
        List<ChannelHousePo> list = this.chd.searchQd(po);
        map.put("searchQd", list);

        map.put("result", "admin");
        return map;
    }
}
