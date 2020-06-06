package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.service.TerminationRecallService;

@Controller
public class TerminationRecallController {
    @Autowired
    private TerminationRecallService trs;


    @RequestMapping("/showTerminationRecall")
    public Map<String, Object> showTerminationRecall(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.trs.showTerminationRecall(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


}
