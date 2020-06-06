package com.wyzc.htgl.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.WyzcPersonnelTestBo;
import com.wyzc.htgl.service.WyzcPersonnelOfficialService;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
public class WyzcPersonnelOfficialController {
    @Autowired
    private WyzcPersonnelOfficialService wps;

    /**
     * 查询所有部门
     */
    @RequestMapping("/loadDataToSelectOfficia")
    public Map<String, Object> searchAllPart() {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.loadDataToSelect();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 分页查询
     */
    @RequestMapping("/searchPersonnelTestDataOfficia")
    public Map<String, Object> searchPersonnelTestData(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.searchPersonnelTestData(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据员工编号查询员工信息
     */
    @RequestMapping("/searchPersonnelByUserIdOfficia")
    public Map<String, Object> searchPersonnelByUserId(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.searchPersonnelByUserId(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 增加员工信息
     */
    @RequestMapping("/savePersonnelOfficialInfoOfficia")
    public Map<String, Object> savePersonnelOfficialInfo(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.savePersonnelOfficialInfo(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 试岗员工修改保存
     */
    @RequestMapping("/saveModifyTestPersonnelOfficia")
    public Map<String, Object> saveModifyTestPersonnel(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.saveModiftyOfficialPersonnel(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 在职员工修改保存
     */
    @RequestMapping("/saveModiftyOfficialPersonnelOfficia")
    public Map<String, Object> saveModiftyOfficialPersonnel(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.saveModiftyOfficialPersonnel(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 离职员工修改保存
     */
    @RequestMapping("/saveModifyLeavePersonnelOfficia")
    public Map<String, Object> saveModifyLeavePersonnel(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.saveModifyLeavePersonnel(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将数据存入Excel表中
     */
    @RequestMapping("/exportSomeOfficia")
    public Map<String, Object> exportSome(String arr, HttpServletResponse re) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.exportSome(arr, re);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 下载Excel表
     *
     * @throws IOException
     * @throws WriteException
     * @throws RowsExceededException
     */
    @RequestMapping("/startExportSomeOfficia")
    public void startExportSome(HttpServletResponse re) throws RowsExceededException, WriteException, IOException {
        this.wps.startExportSome(re);
    }

    /**
     * 导出全部信息
     *
     * @throws IOException
     * @throws WriteException
     * @throws RowsExceededException
     */
    @RequestMapping("/exportAllOfficia")
    public void exportAll(HttpServletResponse re) throws RowsExceededException, WriteException, IOException {
        this.wps.exportAll(re);
    }

    /**
     * 删除员工信息
     */
    @RequestMapping("/removePersonnelByUerIdOfficia")
    public Map<String, Object> removePersonnelByUerId(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.removePersonnelByUerId(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 用户权限
     */
    @RequestMapping("/userJurisdictionOfficia")
    public Map<String, Object> userJurisdiction(WyzcPersonnelTestBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.wps.userJurisdiction(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

}
