package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.po.ChannelHousePo;
import com.wyzc.htgl.po.ChannelHousePo2;
import com.wyzc.htgl.service.ChannelHouseService;

@Controller
public class ChannelHouseController {

    @Autowired
    private ChannelHouseService chs;

    /*
     * 分页查询
     */
    @RequestMapping("/showChannelHouse")
    public Map<String, Object> showChannelHouse(ChannelHousePo po, ChannelHousePo2 po2) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.showChannelHouse(po, po2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /*
     * 	新增
     */
    @RequestMapping("/addChannelHouse")
    public Map<String, Object> addChannelHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.addChannelHouse(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     * 	新增
     */
    @RequestMapping("/modifyChannelHouse")
    public Map<String, Object> modifyChannelHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.modifyChannelHouse(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /*
     * 	修改回显
     */
    @RequestMapping("/searchModifyChannelXq")
    public Map<String, Object> searchModifyChannelXq(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.searchModifyChannelXq(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 删除
     */
    @RequestMapping("/deleteCode")
    public Map<String, Object> deleteCode(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.deleteCode(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 展示详情
     */
    @RequestMapping("/showChannelHouseXq")
    public Map<String, Object> showChannelHouseXq(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.showChannelHouseXq(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 展示详情
     */
    @RequestMapping("/deleteChannelMoreHouse")
    public Map<String, Object> deleteChannelMoreHouse(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.deleteChannelMoreHouse(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询是否渠道部
     */
    @RequestMapping("/searchQd")
    public Map<String, Object> searchQd(ChannelHousePo po) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.chs.searchQd(po);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
