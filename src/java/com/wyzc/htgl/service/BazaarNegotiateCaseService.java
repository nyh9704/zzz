package com.wyzc.htgl.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.dao.BazaarNegotiateCaseDao;

@Service
public class BazaarNegotiateCaseService {
    @Autowired
    private BazaarNegotiateCaseDao bncd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchBazaarNegotiateCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<ClientInfoBo> list = new ArrayList<>();
        //获取当前用户的登录账号
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        //查询当前登录用户的部门
        String part = this.bncd.searchPartById(bo);
        //获取当前登录用户的姓名
        String userName = this.bncd.searchUserNameById(bo);
        //查询当前登录用户的职务
        String duty = this.bncd.searchDutyById(bo);
        if (userId.equals("admin") || userId.equals("test") ||
                part.equals("01") || part.equals("0302") || part.equals("0303") ||
                duty.equals("3")) {
            ClientInfoBo cib = collectData(bo);
            Page page = new Page(cib);
            list = this.bncd.searchBazaarNegotiateCaseStatisticsAll(page);
            map.put("total", page.getTotalRecord());
        } else {
            ClientInfoBo cib = collectData(bo);
            cib.setUserName(userName);
            Page page = new Page(cib);
            list = this.bncd.searchBazaarNegotiateCaseStatisticsByCondition(page);
            map.put("total", page.getTotalRecord());
        }
        map.put("rows", list);

        return map;

    }

    /**
     * 收集数据
     */
    public ClientInfoBo collectData(ClientInfoBo bo) {
        //获取当前系统时间
        LocalDateTime ldt = LocalDateTime.now();
        bo.setNowDate(ldt.format(DateTimeFormatter.ISO_DATE));
        if (bo.getVisitData().equals("1")) {
            bo.setTodayVisit(bo.getVisitData());
            String todayVisit = ldt.format(DateTimeFormatter.ISO_DATE);
            bo.setTodayVisit(todayVisit);
        } else if (bo.getVisitData().equals("2")) {
            bo.setYestodayVisit(bo.getVisitData());
            //将当前时间减一天后的时间转成字符串
            String yestodayVisit = ldt.minusDays(1).format(DateTimeFormatter.ISO_DATE);
            bo.setYestodayVisit(yestodayVisit);
        } else if (bo.getVisitData().equals("3")) {
            bo.setThreeDaysVisit(bo.getVisitData());
            //将当前时间减三天后的时间转成字符串
            String threeDaysVisit = ldt.minusDays(3).format(DateTimeFormatter.ISO_DATE);
            bo.setThreeDaysVisit(threeDaysVisit);
        } else if (bo.getVisitData().equals("4")) {
            bo.setOneWeekVisit(bo.getVisitData());
            //将当前时间减一周后的时间转成字符串
            String oneWeekVisit = ldt.minusWeeks(1).format(DateTimeFormatter.ISO_DATE);
            bo.setOneWeekVisit(oneWeekVisit);
        } else if (bo.getVisitData().equals("5")) {
            bo.setOneMonthVisit(bo.getVisitData());
            //将当前时间减一月后的时间转成字符串
            String oneMonthVisit = ldt.minusMonths(1).format(DateTimeFormatter.ISO_DATE);
            bo.setOneMonthVisit(oneMonthVisit);
        } else {
            return bo;
        }
        return bo;
    }

    /**
     * 提交网签信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> saveNegotiateCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.bncd.saveNegotiateCaseStatistics(bo);
        map.put("result", result);
        return map;
    }


    /**
     * 提交补全结果
     */

    public Map<String, Object> modifyBazaarNegotiateCaseStatisticsById(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.bncd.modifyBazaarNegotiateCaseStatisticsById(bo);
        map.put("result", result);
        return map;
    }

}
