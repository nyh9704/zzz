package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.service.AuctionEndService;

@Controller
public class AuctionEndController {
    @Autowired
    private AuctionEndService aes;


    /**
     * 分页查询
     */
    @RequestMapping("/showAuctionEnd")
    public Map<String, Object> showAuctionEnd(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.aes.showAuctionEnd(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 修改房源信息
     */
    @RequestMapping("/modifyAuctionEnd")
    public Map<String, Object> modifyAuctionEnd(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.aes.modifyAuctionEnd(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 权限
     */
    @RequestMapping("/gameOfThrones")
    public Map<String, Object> gameOfThrones(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.aes.gameOfThrones(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
