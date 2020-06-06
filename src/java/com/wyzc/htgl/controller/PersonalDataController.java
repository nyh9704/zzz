package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wyzc.htgl.bo.PersonalDataBo;
import com.wyzc.htgl.service.PersonalDataService;

/**
 * 个人资料控制层
 *
 * @author Administrator
 */
@Controller
public class PersonalDataController {
    @Autowired
    private PersonalDataService pds;

    /**
     * 分页查询
     */
    @RequestMapping("/searchPersonalData")
    public Map<String, Object> searchPersonalData(PersonalDataBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.pds.searchPersonalData(bo);
        return map;
    }


    /**
     * 录入房源
     */
    @RequestMapping("/enteringHouseData")
    public Map<String, Object> enteringHouseData(PersonalDataBo bo, @RequestParam MultipartFile[] fileImg, HttpServletRequest re) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pds.enteringHouseData(bo, fileImg, re);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取图片地址
     */
    @RequestMapping("/getImgUrl")
    public Map<String, Object> getImgUrl(PersonalDataBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pds.getImgUrl(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 查单条数据
     */
    @RequestMapping("/searchModifyPersonal")
    public Map<String, Object> searchModifyPersonal(PersonalDataBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.pds.searchModifyPersonal(bo);
        return map;
    }


    /**
     * 提交修改信息
     */
    @RequestMapping("/modifyPersonal")
    public Map<String, Object> modifyPersonal(PersonalDataBo bo, @RequestParam MultipartFile[] fileImg, HttpServletRequest re) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pds.modifyPersonal(bo, fileImg, re);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除信息
     */
    @RequestMapping("/deletePersonalData")
    public Map<String, Object> deletePersonalData(PersonalDataBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.pds.deletePersonalData(bo);
        return map;
    }

    /**
     * 提交日报
     */
    @RequestMapping("/doTry")
    public Map<String, Object> doTry(HttpServletRequest re) {
        Map<String, Object> map = new HashMap<>();
        map = this.pds.doTry(re);
        return map;
    }
}
