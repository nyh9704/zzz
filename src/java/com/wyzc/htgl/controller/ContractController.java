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
import com.wyzc.htgl.service.ContractService;
import com.wyzc.htgl.service.WyzcPersonnelService;

@Controller
public class ContractController {
    @Autowired
    private ContractService cs;


    /*
     * 分页查询
     */
    @RequestMapping("/showContract")
    public Map<String, Object> showContract(WyzcPersonnelBo bo, WyzcPersonnelBo2 bo2) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cs.showContract(bo, bo2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /*
     * 分页查询
     */
    @RequestMapping("/modifyContract")
    public Map<String, Object> modifyContract(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cs.modifyContract(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/searchContractQx")
    public Map<String, Object> searchContractQx(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.cs.searchContractQx(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
