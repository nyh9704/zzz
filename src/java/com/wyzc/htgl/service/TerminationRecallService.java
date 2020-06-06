package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.dao.TerminationRecallDao;

@Service
public class TerminationRecallService {
    @Autowired
    private TerminationRecallDao trd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> showTerminationRecall(houseTableBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Page page = new Page(bo);
        List<houseTableBo> list = this.trd.showTerminationRecall(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }
}
