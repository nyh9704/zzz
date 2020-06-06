package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.po.WyzcUserPo;
import com.wyzc.htgl.service.WyzcUserService;

/**
 * @author Administrator
 * 控制层
 */
@Controller
public class WyzcUserController {
    @Autowired
    private WyzcUserService wus;

    /**
     * 注册功能实现
     *
     * @param userBo
     * @return
     */
    @RequestMapping("/registerUser")
    public Map<String, Object> registerUser(WyzcUserBo bo) {//123445445566
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wus.registerUser(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询上级部门
     */
    @RequestMapping("/searchParentPart")
    public Map<String, Object> searchParentPart() {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wus.searchParentPart();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询下级部门
     */
    @RequestMapping("/searchChildPart")
    public Map<String, Object> searchChildPart(WyzcDepartmentBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wus.searchChildPart(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询当前用户是否是管理员
     */
    @RequestMapping("/isAdmin")
    public Map<String, Object> isAdmin(WyzcDepartmentBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wus.isAdmin(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
