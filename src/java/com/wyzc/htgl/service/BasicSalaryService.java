package com.wyzc.htgl.service;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.dao.BasicSalaryDao;
import com.wyzc.htgl.po.BasicSalaryPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicSalaryService {

    @Autowired
    private BasicSalaryDao bd;

    public Map<String, Object> showBasicSalary(BasicSalaryPo bp) {
        Page page = new Page(bp);
        Map<String, Object> map = new HashMap<>();
        List<BasicSalaryPo> list = this.bd.showBasicSalary(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }


    public Map<String, Object> addBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        map.put("addBasicSalary", this.bd.addBasicSalary(bp));
        return map;
    }

    public Map<String, Object> searchModifyBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        List<BasicSalaryPo> list = this.bd.searchModifyBasicSalary(bp);
        map.put("searchModifyBasicSalary", list);
        return map;
    }

    public Map<String, Object> modifyBasicSalary(BasicSalaryPo bp) {
        Map<String, Object> map = new HashMap<>();
        map.put("modifyBasicSalary", this.bd.modifyBasicSalary(bp));
        return map;
    }
}
