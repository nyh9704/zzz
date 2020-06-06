package com.wyzc.htgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyzc.htgl.bo.DueDiligenceDisposeBo;
import com.wyzc.htgl.service.PreliminaryOrderService;

/**
 * 控制层
 *
 * @author Administrator
 */
@Controller
public class PreliminaryOrderController {
    @Autowired
    private PreliminaryOrderService pos;

    /**
     * 展示数据
     */
    @RequestMapping("/showPreliminaryDueDiligenceDisposeData")
    public Map<String, Object> showDueDiligenceDisposeData(DueDiligenceDisposeBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pos.showDueDiligenceDisposeData(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 修改数据状态
     */
    @RequestMapping("/amendPreliminaryUserPost")
    public Map<String, Object> amendUserPost(DueDiligenceDisposeBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pos.amendUserPost(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 添加备注
     */
    @RequestMapping("/addPreliminaryRemark")
    public Map<String, Object> addRemark(DueDiligenceDisposeBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pos.addRemark(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 驳回原因
     */
    @RequestMapping("/addPreliminaryRejectRemark")
    public Map<String, Object> addRejectRemark(DueDiligenceDisposeBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pos.addRejectRemark(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;

    }

    /**
     * 查询所选择房屋的链接
     */
    @RequestMapping("/searchPreliminaryHouseUrlByChooseHouse")
    public Map<String, Object> searchHouseUrlByChooseHouse(DueDiligenceDisposeBo bo) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = this.pos.searchHouseUrlByChooseHouse(bo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 下载文件
     */
    @RequestMapping("PreliminaryDownFile")
    public void downFile(DueDiligenceDisposeBo bo, HttpServletResponse response, HttpServletRequest re) {
        String filePath = this.pos.getFilePathByUserId(bo);
        File file = new File(filePath);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");
        try {// 设置乱码转字符串file.getName().getBytes("utf-8"),"iso-8859-1")
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(file.getName().getBytes("utf-8"), "iso-8859-1"));
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
