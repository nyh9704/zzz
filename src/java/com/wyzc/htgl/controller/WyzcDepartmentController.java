package com.wyzc.htgl.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.service.WyzcDepartmentService;

@Controller
public class WyzcDepartmentController {
    @Autowired
    private WyzcDepartmentService wds;


    /***
     * 页面加载树型表格
     * @return
     */
    @RequestMapping("/ProjectMangementUrl")
    public Map<String, Object> ProjectMangementUrl(WyzcDepartmentBo wb) {
        Map<String, Object> map = null;
        try {
            map = this.wds.ProjectMangementUrl(wb);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /***
     * 通过请求参数传入的项目编号检查父级是否为重复项目编号
     * @return
     */
    @RequestMapping("/checkedOnlyOne")
    public Map<String, Object> checkedOnlyOne(WyzcDepartmentBo pb, String upProjectid) {
        Map<String, Object> map = null;
        try {
            map = this.wds.checkedOnlyOne(pb, upProjectid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /***
     * 新增一级项目信息
     * @param pb
     * @return
     */
    @RequestMapping("/addProjectonr")
    public Map<String, Object> addProjectonr(WyzcDepartmentBo pb) {
        Map<String, Object> map = null;
        try {
            map = this.wds.addProjectonr(pb);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /***
     * 新增子级项目信息
     * @param pb
     * @return
     */
    @RequestMapping("/addNextProjectonr")
    public Map<String, Object> addNextProjectonr(WyzcDepartmentBo pb, String pid) {
        Map<String, Object> map = null;
        try {
            map = this.wds.addNextProjectonr(pb, pid);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /***
     * 查询某个项目
     * @param pb
     * @return
     */
    @RequestMapping("/findOnwProjrct")
    public Map<String, Object> findOnwProjrct(WyzcDepartmentBo pb) {
        Map<String, Object> map = null;
        try {
            map = this.wds.findOnwProjrct(pb);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /***
     * 修改某个项目
     * @param pb
     * @return
     */
    @RequestMapping("/modifyProjrct")
    public Map<String, Object> modifyProjrct(WyzcDepartmentBo pb) {
        Map<String, Object> map = null;
        try {
            map = this.wds.modifyProjrct(pb);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

}
