package com.wyzc.htgl.service;
/**
 * 服务层
 *
 * @author Administrator
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.DueDiligenceDisposeBo;
import com.wyzc.htgl.dao.FinancialDisposeDao;

@Service
public class FinancialDisposeService {
    @Autowired
    private FinancialDisposeDao fdd;

    /**
     * 展示信息
     * @param bo
     * @return
     */
    public Map<String, Object> showDueDiligenceDisposeData(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Page page = new Page(bo);
        Map<String, Object> map = new HashMap<>();
        List<DueDiligenceDisposeBo> list = this.fdd.showDueDiligenceDisposeData(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 改变数据状态
     * @param bo
     * @return
     */
    public Map<String, Object> amendUserPost(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = 0;
        if (bo.getUserPost().equals("已完成")) {
            result = this.fdd.amendUserPostEnd(bo);
        }

        if (bo.getUserPost().equals("已取消")) {
            result = this.fdd.amendUserCancel(bo);
        }

        if (bo.getUserPost().equals("进行中")) {
            result = this.fdd.amendUserPost(bo);
        }

        map.put("result", result);
        return map;
    }

    /**
     * 添加备注
     * @param bo
     * @return
     */
    public Map<String, Object> addRemark(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.fdd.addRemark(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 添加备注原因
     * @param bo
     * @return
     */
    public Map<String, Object> addRejectRemark(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.fdd.addRejectRemark(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 查询所选择房屋的链接
     * @param bo
     * @return
     */
    public Map<String, Object> searchHouseUrlByChooseHouse(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String result = this.fdd.searchHouseUrlByChooseHouse(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 获取附件下载地址
     * @param bo
     * @return
     */
    public String getFilePathByUserId(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        String filePath = this.fdd.getFilePathByUserId(bo);
        return filePath;
    }
}
