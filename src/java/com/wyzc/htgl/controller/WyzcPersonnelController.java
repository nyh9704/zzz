package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo2;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.service.WyzcPersonnelService;

@Controller
public class WyzcPersonnelController {
    @Autowired
    private WyzcPersonnelService wps;


    /*
     * 分页查询
     */
    @RequestMapping("/showDepartureData")
    public Map<String, Object> showDepartureData(WyzcPersonnelBo bo, WyzcPersonnelBo2 bo2) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.showDepartureData(bo, bo2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     * 查询部门回显
     */
    @RequestMapping("/searchBm")
    public Map<String, Object> searchBm(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.searchBm(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出当前数据
     */
    @RequestMapping("/exportPersonDq")
    public Map<String, Object> exportPersonDq(WyzcPersonnelBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.exportPersonDq(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出所有数据
     */
    @RequestMapping("/exportPerson")
    public Map<String, Object> exportPerson(WyzcPersonnelBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.exportPerson(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 删除
     */
    @RequestMapping("/deletePerson")
    public Map<String, Object> deletePerson(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.deletePerson(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 权限
     */
    @RequestMapping("/searchDepartureQx")
    public Map<String, Object> searchDepartureQx(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.searchDepartureQx(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
