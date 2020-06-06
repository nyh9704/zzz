package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.ClientInfoService;

@Controller
public class ClientInfoController {
    @Autowired
    private ClientInfoService cis;

    /**
     * 根据顾问名字查找对应部门和领导
     *
     * @param bo
     * @return
     */
    @RequestMapping("searchLeaderAndPart")
    public Map<String, Object> searchLeaderAndPart(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.searchLeaderAndPart(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 新增到访客户信息
     */
    @RequestMapping("/saveClientInfo")
    public Map<String, Object> saveClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.saveClientInfo(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除客户信息
     */
    @RequestMapping("/deleteClientInfo")
    public Map<String, Object> deleteClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.deleteClientInfo(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 查询当前用户所属部门级职务
     */
    @RequestMapping("/searchUserPartAndDuty")
    public Map<String, Object> searchUserPartAndDuty() {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.searchUserPartAndDuty();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查所有的主管
     */
    @RequestMapping("/searchAllGroupLeader")
    public Map<String, Object> searchAllGroupLeader() {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.searchAllGroupLeader();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据编号查客户信息
     */
    @RequestMapping("/searchModifyClientInfo")
    public Map<String, Object> searchModifyClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.searchModifyClientInfo(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 提交修改客户信息
     */
    @RequestMapping("/modifyClientInfo")
    public Map<String, Object> modifyClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cis.modifyClientInfo(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 分页查询
     */
    @RequestMapping("/searchClientData")
    public Map<String, Object> searchClientData(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.cis.searchClientData(bo);
        return map;
    }


}
