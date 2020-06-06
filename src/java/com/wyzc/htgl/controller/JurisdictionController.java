package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.JurisdictionBo;
import com.wyzc.htgl.service.JurisdictionService;

/**
 * 权限管理控制层
 *
 * @author Administrator
 */
@Controller
public class JurisdictionController {
    @Autowired
    private JurisdictionService js;


    /**
     * 查所有员工的权限信息
     */
    @RequestMapping("/loadAllData")
    public Map<String, Object> loadAllData(JurisdictionBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.js.loadAllData(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获得该权限
     */
    @RequestMapping("/getJurisdictionr")
    public Map<String, Object> getJurisdictionr(String arrObj, JurisdictionBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.js.getJurisdictionr(arrObj, bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 设置全选复选框状态
     */
    @RequestMapping("/setCheckboxState")
    public Map<String, Object> setCheckboxState(JurisdictionBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.js.setCheckboxState(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

}
