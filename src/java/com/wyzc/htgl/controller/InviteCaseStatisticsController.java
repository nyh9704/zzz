package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.InviteCaseStatisticsService;

@Controller
public class InviteCaseStatisticsController {
    @Autowired
    private InviteCaseStatisticsService icss;

    /**
     * 分页查询
     */
    @RequestMapping("/searchInviteCaseStatistics")
    public Map<String, Object> searchInviteCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.icss.searchInviteCaseStatistics(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 权利的游戏
     */
    @RequestMapping("/gGameOfThrones")
    public Map<String, Object> gGameOfThrones() {
        Map<String, Object> map = new HashMap<>();
        map = this.icss.gGameOfThrones();
        return map;
    }


}
