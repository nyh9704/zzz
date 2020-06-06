package com.wyzc.htgl.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.dao.HouseImgDao;
import com.wyzc.htgl.po.HouseImgUrlPo;

@Service
public class HouseImgService {
    @Autowired
    private HouseImgDao hid;

    //添加
    public Map<String, Object> AddHouseImg(HouseImgUrlPo hp) {
        Map<String, Object> map = new HashMap<>();
        Integer add = this.hid.AddHouseImg(hp);
        map.put("add", add);
        return map;
    }

    //展现及分页查询
    public Map<String, Object> ShowHouseImg(HouseImgUrlPo hp) {
        Page page = new Page(hp);
        Map<String, Object> map = new HashMap<>();
        List<houseTableBo> list = this.hid.ShowHouseImg(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }
}

