package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.po.HouseImgUrlPo;
import com.wyzc.htgl.service.HouseImgService;

@Controller
public class HouseImgController {
    @Autowired
    private HouseImgService his;

    /**
     * 图片信息添加
     */
    @RequestMapping("/AddHouseImg")
    public Map<String, Object> AddHouseImg(HouseImgUrlPo hp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.his.AddHouseImg(hp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 图片信息添加
     */
    @RequestMapping("/ShowHouseImg")
    public Map<String, Object> ShowHouseImg(HouseImgUrlPo hp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.his.ShowHouseImg(hp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
