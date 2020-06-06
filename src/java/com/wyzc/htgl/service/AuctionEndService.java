package com.wyzc.htgl.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.houseTableBo;
import com.wyzc.htgl.dao.AuctionEndDao;

@Service
public class AuctionEndService {
    @Autowired
    private AuctionEndDao aed;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> showAuctionEnd(houseTableBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Page page = new Page(bo);
        List<houseTableBo> list = this.aed.showAuctionEnd(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }

    /**
     * 修改房源信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> modifyAuctionEnd(houseTableBo bo) {
        Map<String, Object> map = new HashMap<>();
        //设置结束时间
        //1.将字符串格式的开拍时间转为时间格式
        LocalDate ld = LocalDate.parse(bo.getShiAuctionDate());
        //2.判断该条数据里面是否有‘变卖’字样
        String endDate = "";
        if (bo.getShiHouseNumber().indexOf("变卖") != -1) {
            //3.如果有则结束时间为开拍时间加上60天
            LocalDate re = ld.plusDays(60);
            endDate = re.format(DateTimeFormatter.ISO_DATE);
            bo.setShiEndDate(endDate);
        } else {
            //4.没有则结束时间为开拍时间加上1天
            LocalDate re = ld.plusDays(1);
            endDate = re.format(DateTimeFormatter.ISO_DATE);
            bo.setShiEndDate(endDate);
        }
        Integer result = this.aed.modifyAuctionEnd(bo);
        map.put("modifyHouse", result);
        return map;
    }

    /**
     * 权限
     *
     * @param bo
     * @return
     */
    public Map<String, Object> gameOfThrones(houseTableBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        if (userId.equals("admin")) {
            map.put("result", "admin");
            map.put("searchId", "");
        } else {
            String result = this.aed.gameOfThrones(bo);
            map.put("searchId", result);
        }
        return map;
    }
}
