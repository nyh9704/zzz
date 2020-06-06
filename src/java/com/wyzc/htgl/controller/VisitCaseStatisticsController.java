package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.VisitCaseStatisticsService;

@Controller
public class VisitCaseStatisticsController {
    @Autowired
    private VisitCaseStatisticsService vcss;

    /**
     * 分页查询
     */
    @RequestMapping("/searchVisitCaseStatistics")
    public Map<String, Object> searchVisitCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.vcss.searchVisitCaseStatistics(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
