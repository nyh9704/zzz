package com.wyzc.htgl.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;
import com.wyzc.htgl.service.StopHouseService;

/**
 * 谁改谁孙子系列
 *
 * @author Administrator
 */
@Controller
public class StopHouseController {

    @Autowired
    private StopHouseService shs;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;


    /*
     * 分页查询
     */
    @RequestMapping("/showStopHouse")
    public Map<String, Object> showStopHouse(houseTableBo bo, houseTableBo2 bo2) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.shs.showStopHouse(bo, bo2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 导入
     *
     * @param file1
     * @param bo
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addStopFyExcel")
    public Map<String, Object> addStopFyExcel(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.shs.addStopFyExcel(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 导出所有数据
     */
    @RequestMapping("/exportStopHouse")
    public Map<String, Object> exportStopHouse(houseTableBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.shs.exportStopHouse(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出当前数据
     */
    @RequestMapping("/exportStopHouseDq")
    public Map<String, Object> exportHouseDq(houseTableBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.shs.exportStopHouseDq(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出所有数据
     */
    @RequestMapping("/showStopFyXq")
    public Map<String, Object> showStopFyXq(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.shs.showStopFyXq(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
