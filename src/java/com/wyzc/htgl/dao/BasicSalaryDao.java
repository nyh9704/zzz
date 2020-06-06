package com.wyzc.htgl.dao;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.po.BasicSalaryPo;

import java.util.List;

public interface BasicSalaryDao {
    List<BasicSalaryPo> showBasicSalary(Page page);

    Integer addBasicSalary(BasicSalaryPo bp);

    Integer modifyBasicSalary(BasicSalaryPo bp);

    List<BasicSalaryPo> searchModifyBasicSalary(BasicSalaryPo bp);
}
