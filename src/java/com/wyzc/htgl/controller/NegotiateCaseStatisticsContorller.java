package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bn.frame.util.CommonFunction;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.NegotiateCaseStatisticsService;

@Controller
/**
 * 谈单情况统计
 * @author Administrator
 *
 */
public class NegotiateCaseStatisticsContorller {
    @Autowired
    private NegotiateCaseStatisticsService ncss;

    /**
     * 分页查询
     */
    @RequestMapping("/searchNegotiateCaseStatistics")
    public Map<String, Object> searchNegotiateCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.searchNegotiateCaseStatistics(bo);
        return map;
    }

    /**
     * 根据编号查询信息
     */
    @RequestMapping("/searchNegotiateCaseStatisticsById")
    public Map<String, Object> searchNegotiateCaseStatisticsById(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.searchNegotiateCaseStatisticsById(bo);
        return map;
    }

    /**
     * 修改
     */
    @RequestMapping("/modifyNegotiateCaseStatisticsById")
    public Map<String, Object> modifyNegotiateCaseStatisticsById(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.modifyNegotiateCaseStatisticsById(bo);
        return map;
    }

    /**
     * 查询所填谈单人所属部门
     */
    @RequestMapping("/searchDepartOfDecider")
    public Map<String, Object> searchDepartOfDecider(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.searchDepartOfDecider(bo);
        return map;
    }

    /**
     * 删除
     */
    @RequestMapping("/deletenegotiateCaseStatistics")
    public Map<String, Object> deletenegotiateCaseStatistics(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.deletenegotiateCaseStatistics(bo);
        return map;
    }

    /**
     * 权利的游戏之行政部门
     */
    @RequestMapping("/administrativeGameOfThrones")
    public Map<String, Object> administrativeGameOfThrones(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.ncss.administrativeGameOfThrones(bo);
        return map;
    }
}
