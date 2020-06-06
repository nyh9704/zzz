package com.wyzc.htgl.controller;


import com.wyzc.htgl.po.BasicSalaryPo;
import com.wyzc.htgl.service.BasicSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class BasicSalaryController {

    @Autowired
    private BasicSalaryService bs;

    @RequestMapping("/showBasicSalary")
    public Map<String, Object> showBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bs.showBasicSalary(bp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /*
     * 	新增
     */
    @RequestMapping("/addBasicSalary")
    public Map<String, Object> addBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bs.addBasicSalary(bp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /*
     * 	修改
     */
    @RequestMapping("/modifyBasicSalary")
    public Map<String, Object> modifyBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bs.modifyBasicSalary(bp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /*
     * 	修改
     */
    @RequestMapping("/searchModifyBasicSalary")
    public Map<String, Object> searchModifyBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.bs.searchModifyBasicSalary(bp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }
}
