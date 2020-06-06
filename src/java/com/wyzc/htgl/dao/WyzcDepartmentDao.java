package com.wyzc.htgl.dao;

import java.util.List;

import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;

public interface WyzcDepartmentDao {

    //查询一级部门
    public List<WyzcDepartmentBo> ProjectMangementUrl(WyzcDepartmentBo wb);

    //查询下级部门
    public List<WyzcDepartmentBo> ProjectMangementUrlDown(WyzcDepartmentBo projectMangementBo);

    //查询父级编号是否重复
    public Integer checkedOnlyOne(WyzcDepartmentBo pb);

    //增加一级部门
    public int addProjectonr(WyzcDepartmentBo pb);

    //增加下级部门
    public int addNextProjectonr(WyzcDepartmentBo pb);

    //查找某个部门信息
    public List<WyzcDepartmentBo> findOnwProjrct(WyzcDepartmentBo pb);

    //修改某个部门
    public int modifyProjrct(WyzcDepartmentBo pb);

    //查该项目下所属人员
    public List<WyzcDepartmentBo> listCrowd(WyzcDepartmentBo wdb);

    //查该项目人员有无重复
    public Integer checkRepetition(WyzcDepartmentBo db);

    //没有重复则将该人员加入进去
    public void insertIntoDepart(WyzcDepartmentBo db);

    //查最大的编号
    public String getMaxWpId();


}
