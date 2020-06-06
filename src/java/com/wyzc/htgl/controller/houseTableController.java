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

import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.bo.houseTableBo2;
import com.wyzc.htgl.service.houseTableService;

/**
 * 救救孩子吧
 *
 * @author 二娃
 */
@Controller
public class houseTableController {

    @Autowired
    private houseTableService hts;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    /*
     * 分页查询
     */
    @RequestMapping("/showHouse")
    public Map<String, Object> showHouse(houseTableBo bo, houseTableBo2 bo2) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.showHouse(bo, bo2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     * 	查询当日新增
     */
    @RequestMapping("/searchTodyXz")
    public Map<String, Object> searchTodyXz(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.searchTodyXz(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     *	查询当日开拍
     */
    @RequestMapping("/searchTodyKp")
    public Map<String, Object> searchTodyKp(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.searchTodyKp(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     *	查询当日撤回
     */
    @RequestMapping("/searchTodyCh")
    public Map<String, Object> searchTodyCh(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.searchTodyCh(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 添加房源
     */
    @RequestMapping("/addHouse")
    public Map<String, Object> addHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.addHouse(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 房源详情
     */
    @RequestMapping("/showFyXq")
    public Map<String, Object> showFyXq(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.showFyXq(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 修改房源信息
     */
    @RequestMapping("/modifyHouse")
    public Map<String, Object> modifyHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.modifyHouse(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除房源
     */
    @RequestMapping("/deleteHouse")
    public Map<String, Object> deleteHouse(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.deleteHouse(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导出所有数据
     */
    @RequestMapping("/exportHouse")
    public Map<String, Object> exportHouse(houseTableBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.exportHouse(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 导出当前数据
     */
    @RequestMapping("/exportHouseDq")
    public Map<String, Object> exportHouseDq(houseTableBo bo, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.exportHouseDq(bo, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导入excel_新增房源
     *
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addFyExcel")
    public Map<String, Object> addFyExcel(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.addFyExcel(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导入excel_当日新增
     *
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/toDayXz")
    public Map<String, Object> toDayXz(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.toDayXz(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 导入excel_当日开拍
     *
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/toDayKp")
    public Map<String, Object> toDayKp(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.toDayKp(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导入excel_当日撤回
     *
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/toDayCh")
    public Map<String, Object> toDayCh(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.toDayCh(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 导入过期房源
     */
    @RequestMapping("/doGqFy")
    public Map<String, Object> doGqFy(@RequestParam("file1") MultipartFile[] file1, houseTableBo bo) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.doGqFy(file1, bo, request);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 前台权限
     *
     * @param bo
     * @return
     */
    @RequestMapping("/searchId")
    public Map<String, Object> searchId(WyzcUserBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.hts.searchId(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
