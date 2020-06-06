package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.BazaarNegotiateCaseService;

/**
 * 谈单统计-市场部
 *
 * @author Administrator
 */
@Controller
public class BazaarNegotiateCaseController {
    @Autowired
    private BazaarNegotiateCaseService bncs;

    /**
     * 分页查询
     */
    @RequestMapping("/searchBazaarNegotiateCaseStatistics")
    public Map<String, Object> searchBazaarNegotiateCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bncs.searchBazaarNegotiateCaseStatistics(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 提交网签信息
     */
    @RequestMapping("/saveNegotiateCaseStatistics")
    public Map<String, Object> saveNegotiateCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bncs.saveNegotiateCaseStatistics(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 补全结果
     */
    @RequestMapping("/modifyBazaarNegotiateCaseStatisticsById")
    public Map<String, Object> modifyBazaarNegotiateCaseStatisticsById(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bncs.modifyBazaarNegotiateCaseStatisticsById(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
