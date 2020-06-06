
package com.wyzc.htgl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.service.NegotiateRecordService;

@Controller
/**
 * 谈单记录
 * @author Administrator
 *
 */
public class NegotiateRecordController {
    @Autowired
    private NegotiateRecordService nrs;


    /**
     * 分页查询
     */
    @RequestMapping("/searchnegotiateRecord")
    public Map<String, Object> searchnegotiateRecord(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        map = this.nrs.searchnegotiateRecord(bo);
        return map;
    }
}
