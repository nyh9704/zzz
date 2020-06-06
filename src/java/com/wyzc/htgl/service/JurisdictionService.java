package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.JurisdictionBo;
import com.wyzc.htgl.dao.JurisdictionDao;

/**
 * 权限管理服务层
 *
 * @author Administrator
 */
@Service
public class JurisdictionService {
    @Autowired
    private JurisdictionDao jd;

    /**
     * 查询所有员工权限信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> loadAllData(JurisdictionBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<JurisdictionBo> list = this.jd.loadAllData(bo);
        map.put("rows", list);
        return map;
    }

    /**
     * 获得该权限
     *
     * @param bo
     * @param arrObj
     * @return
     */
    public Map<String, Object> getJurisdictionr(String arrObj, JurisdictionBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = 0;
        List<JurisdictionBo> list = JSON.parseArray(arrObj, JurisdictionBo.class);
        this.jd.deleteValue(bo);
        for (JurisdictionBo jb : list) {
            if (jb.getState().equals("authority01")) {
                result = this.jd.getJurisdictionr1(jb);
            } else if (jb.getState().equals("authority02")) {
                result = this.jd.getJurisdictionr2(jb);
            } else if (jb.getState().equals("authority03")) {
                result = this.jd.getJurisdictionr3(jb);
            } else if (jb.getState().equals("authority04")) {
                result = this.jd.getJurisdictionr4(jb);
            } else if (jb.getState().equals("authority05")) {
                result = this.jd.getJurisdictionr5(jb);
            } else if (jb.getState().equals("authority06")) {
                result = this.jd.getJurisdictionr6(jb);
            } else if (jb.getState().equals("authority07")) {
                result = this.jd.getJurisdictionr7(jb);
            } else if (jb.getState().equals("authority08")) {
                result = this.jd.getJurisdictionr8(jb);
            } else if (jb.getState().equals("authority09")) {
                result = this.jd.getJurisdictionr9(jb);
            } else if (jb.getState().equals("authority10")) {
                result = this.jd.getJurisdictionr10(jb);
            } else if (jb.getState().equals("authority11")) {
                result = this.jd.getJurisdictionr11(jb);
            } else if (jb.getState().equals("authority12")) {
                result = this.jd.getJurisdictionr12(jb);
            } else if (jb.getState().equals("authority13")) {
                result = this.jd.getJurisdictionr13(jb);
            } else if (jb.getState().equals("authority14")) {
                result = this.jd.getJurisdictionr14(jb);
            } else {
                result = this.jd.getJurisdictionr15(jb);
            }
        }
        map.put("result", result);
        return map;
    }

    public Map<String, Object> setCheckboxState(JurisdictionBo bo) {
        Map<String, Object> map = new HashMap<>();
        //获取总数据长度
        Integer list = this.jd.getDataSize(bo);
        //获取拥有权限1的数据长度
        Integer num1 = this.jd.getJurisdictionr1Size(bo);
        //获取拥有权限2的数据长度
        Integer num2 = this.jd.getJurisdictionr2Size(bo);
        //获取拥有权限3的数据长度
        Integer num3 = this.jd.getJurisdictionr3Size(bo);
        //获取拥有权限4的数据长度
        Integer num4 = this.jd.getJurisdictionr4Size(bo);
        //获取拥有权限5的数据长度
        Integer num5 = this.jd.getJurisdictionr5Size(bo);
        //获取拥有权限6的数据长度
        Integer num6 = this.jd.getJurisdictionr6Size(bo);
        //获取拥有权限7的数据长度
        Integer num7 = this.jd.getJurisdictionr7Size(bo);
        //获取拥有权限8的数据长度
        Integer num8 = this.jd.getJurisdictionr8Size(bo);
        //获取拥有权限9的数据长度
        Integer num9 = this.jd.getJurisdictionr9Size(bo);
        //获取拥有权限10的数据长度
        Integer num10 = this.jd.getJurisdictionr10Size(bo);
        //获取拥有权限11的数据长度
        Integer num11 = this.jd.getJurisdictionr11Size(bo);
        //获取拥有权限12的数据长度
        Integer num12 = this.jd.getJurisdictionr12Size(bo);
        //获取拥有权限13的数据长度
        Integer num13 = this.jd.getJurisdictionr13Size(bo);
        //获取拥有权限14的数据长度
        Integer num14 = this.jd.getJurisdictionr14Size(bo);
        //获取拥有权限15的数据长度
        Integer num15 = this.jd.getJurisdictionr15Size(bo);
        if (num1 < list) {
            map.put("j1", 0);
        } else {
            map.put("j1", 1);
        }

        if (num2 < list) {
            map.put("j2", 0);
        } else {
            map.put("j2", 1);
        }

        if (num3 < list) {
            map.put("j3", 0);
        } else {
            map.put("j3", 1);
        }

        if (num4 < list) {
            map.put("j4", 0);
        } else {
            map.put("j4", 1);
        }

        if (num5 < list) {
            map.put("j5", 0);
        } else {
            map.put("j5", 1);
        }

        if (num6 < list) {
            map.put("j6", 0);
        } else {
            map.put("j6", 1);
        }

        if (num7 < list) {
            map.put("j7", 0);
        } else {
            map.put("j7", 1);
        }

        if (num8 < list) {
            map.put("j8", 0);
        } else {
            map.put("j8", 1);
        }

        if (num9 < list) {
            map.put("j9", 0);
        } else {
            map.put("j9", 1);
        }

        if (num10 < list) {
            map.put("j10", 0);
        } else {
            map.put("j10", 1);
        }

        if (num11 < list) {
            map.put("j11", 0);
        } else {
            map.put("j11", 1);
        }

        if (num12 < list) {
            map.put("j12", 0);
        } else {
            map.put("j12", 1);
        }

        if (num13 < list) {
            map.put("j13", 0);
        } else {
            map.put("j13", 1);
        }

        if (num14 < list) {
            map.put("j14", 0);
        } else {
            map.put("j14", 1);
        }

        if (num15 < list) {
            map.put("j15", 0);
        } else {
            map.put("j15", 1);
        }

        return map;
    }
}
