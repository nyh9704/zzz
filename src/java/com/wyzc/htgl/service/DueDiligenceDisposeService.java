package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.DueDiligenceDisposeBo;
import com.wyzc.htgl.dao.DueDiligenceDisposeDao;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
public class DueDiligenceDisposeService {
    @Autowired
    private DueDiligenceDisposeDao dddd;

    /**
     * 展示信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> showDueDiligenceDisposeData(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Page page = new Page(bo);
        Map<String, Object> map = new HashMap<>();
        List<DueDiligenceDisposeBo> list = this.dddd.showDueDiligenceDisposeData(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 改变数据状态
     *
     * @param bo
     * @return
     */
    public Map<String, Object> amendUserPost(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = 0;
        if (bo.getUserPost().equals("已完成")) {
            result = this.dddd.amendUserPostEnd(bo);
        }

        if (bo.getUserPost().equals("已取消")) {
            result = this.dddd.amendUserCancel(bo);
        }

        if (bo.getUserPost().equals("进行中")) {
            result = this.dddd.amendUserPost(bo);
        }

        map.put("result", result);
        return map;
    }

    /**
     * 添加备注
     *
     * @param bo
     * @return
     */
    public Map<String, Object> addRemark(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.dddd.addRemark(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 添加备注原因
     *
     * @param bo
     * @return
     */
    public Map<String, Object> addRejectRemark(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.dddd.addRejectRemark(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 查询所选择房屋的链接
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchHouseUrlByChooseHouse(DueDiligenceDisposeBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String result = this.dddd.searchHouseUrlByChooseHouse(bo);
        map.put("result", result);
        return map;
    }
}
