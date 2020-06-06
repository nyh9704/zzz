package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.dao.WyzcDepartmentDao;

@Service
public class WyzcDepartmentService {
    @Autowired
    private WyzcDepartmentDao wdd;


    /***
     * 得到一级跟目录
     * @param pb
     * @return
     */
    public Map<String, Object> ProjectMangementUrl(WyzcDepartmentBo wb) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(wb.getParentId());
        //所有一级项目
        List<WyzcDepartmentBo> list = this.wdd.ProjectMangementUrl(wb);
        Integer num = 1;
        for (WyzcDepartmentBo wdb : list) {
            wdb.setId(wdb.getWpId());
            wdb.setText(wdb.getWpName());
            //查该项目下所属人员
            List<WyzcDepartmentBo> listCrowd = this.wdd.listCrowd(wdb);
            //查该项目人员有无重复
            for (WyzcDepartmentBo db : listCrowd) {

                Integer result = this.wdd.checkRepetition(db);

                if (result.equals(0)) {
                    //没有重复则将该人员加入进去

                    db.setId(num.toString());
                    this.wdd.insertIntoDepart(db);
                    num++;
                }
            }
        }
        Chident(list);//递归查询对应父级的子级
        map.put("parent", list);
        return map;
        // TODO Auto-generated method stub
    }


    /***
     * 通过查询的父级集合递归查询对应的子级
     * @param list
     */
    public void Chident(List<WyzcDepartmentBo> list) {
        List<WyzcDepartmentBo> list2 = null;
        String nums = this.wdd.getMaxWpId();
        Integer num = Integer.valueOf(nums);
        for (WyzcDepartmentBo projectMangementBo : list) {

            list2 = this.wdd.ProjectMangementUrlDown(projectMangementBo);//查询下级
            if (list2 != null && list2.size() != 0) {
                for (WyzcDepartmentBo wdb : list2) {
                    wdb.setId(wdb.getWpId());
                    wdb.setText(wdb.getWpName());
                    //查该项目下所属人员
                    List<WyzcDepartmentBo> listCrowd = this.wdd.listCrowd(wdb);
                    //查该项目人员有无重复
                    for (WyzcDepartmentBo db : listCrowd) {

                        Integer result = this.wdd.checkRepetition(db);

                        if (result.equals(0)) {
                            //没有重复则将该人员加入进去

                            db.setId(num.toString());
                            this.wdd.insertIntoDepart(db);
                            num++;
                        }
                    }
                }
                projectMangementBo.setChildren(list2);
                Chident(list2);
            }
        }
    }


    /***
     * 通过传入的参数进行查询判断
     * @param pb
     * @return
     */
    public Map<String, Object> checkedOnlyOne(WyzcDepartmentBo pb, String upProjectid) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        if (upProjectid != null && upProjectid != "") {
            pb.setWpId(upProjectid.substring(0, upProjectid.length() - 1) + pb.getWpId());
        }

        Integer i = this.wdd.checkedOnlyOne(pb);
        if (i == 1) {
            map.put("resultCode", "0");
        } else {
            map.put("resultCode", "1");
        }
        return map;
    }


    /***
     * 新增父级
     * @param pb
     * @return
     */
    public Map<String, Object> addProjectonr(WyzcDepartmentBo pb) {
        // TODO Auto-generated method stub
        pb.setParentId("0");//设置一级目录
        Map<String, Object> map = new HashMap<>();
        int rows = this.wdd.addProjectonr(pb);
        map.put("rows", rows);
        return map;
    }


    /***
     * 新增子级
     * @param pb
     * @param pid
     * @return
     */
    public Map<String, Object> addNextProjectonr(WyzcDepartmentBo pb, String pid) {
        // TODO Auto-generated method stub
        pb.setWpId(pid.substring(0, pid.length() - 1) + pb.getWpId());//设置子级编号
        pb.setParentId(pid.substring(0, pid.length() - 1));//设置父级编号
        Map<String, Object> map = new HashMap<>();
        int rows = this.wdd.addNextProjectonr(pb);
        map.put("rows", rows);
        return map;
    }


    /***
     * 根据编号查询详细信息
     * @param pb
     * @return
     */
    public Map<String, Object> findOnwProjrct(WyzcDepartmentBo pb) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<WyzcDepartmentBo> rows = this.wdd.findOnwProjrct(pb);
        map.put("rows", rows);
        return map;
    }


    /***
     * 修改某个项目
     * @param pb
     * @return
     */
    public Map<String, Object> modifyProjrct(WyzcDepartmentBo pb) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        int rows = this.wdd.modifyProjrct(pb);
        map.put("rows", rows);
        return map;
    }

}
