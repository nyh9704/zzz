package com.wyzc.htgl.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.dao.VisitCaseStatisticsDao;

@Service
public class VisitCaseStatisticsService {
    @Autowired
    private VisitCaseStatisticsDao vcsd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */

    public Map<String, Object> searchVisitCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        //查询当前登录用户的姓名
        String userName = this.vcsd.searchUserName(bo);
        bo.setUserName(userName);
        Map<String, Object> map = new HashMap<>();
        List<ClientInfoBo> list = new ArrayList<>();
        Page page = new Page(bo);

        //获取当前系统时间
        LocalDateTime ldt = LocalDateTime.now();
        bo.setNowDate(ldt.format(DateTimeFormatter.ISO_DATE));
        if (bo.getTodayVisit().equals("1")) {
            String todayVisit = ldt.format(DateTimeFormatter.ISO_DATE);
            bo.setTodayVisit(todayVisit);
            list = this.vcsd.searchVisitCaseStatistics(page);
        } else if (bo.getYestodayVisit().equals("2")) {
            //将当前时间减一天后的时间转成字符串
            String yestodayVisit = ldt.minusDays(1).format(DateTimeFormatter.ISO_DATE);
            bo.setYestodayVisit(yestodayVisit);
            list = this.vcsd.searchVisitCaseStatistics(page);
        } else if (bo.getThreeDaysVisit().equals("3")) {
            //将当前时间减三天后的时间转成字符串
            String threeDaysVisit = ldt.minusDays(3).format(DateTimeFormatter.ISO_DATE);
            bo.setThreeDaysVisit(threeDaysVisit);
            list = this.vcsd.searchVisitCaseStatistics(page);
        } else if (bo.getOneWeekVisit().equals("4")) {
            //将当前时间减一周后的时间转成字符串
            String oneWeekVisit = ldt.minusWeeks(1).format(DateTimeFormatter.ISO_DATE);
            bo.setOneWeekVisit(oneWeekVisit);
            list = this.vcsd.searchVisitCaseStatistics(page);
        } else if (bo.getOneMonthVisit().equals("5")) {
            //将当前时间减一月后的时间转成字符串
            String oneMonthVisit = ldt.minusMonths(1).format(DateTimeFormatter.ISO_DATE);
            bo.setOneMonthVisit(oneMonthVisit);
            list = this.vcsd.searchVisitCaseStatistics(page);
        } else {
            list = this.vcsd.searchVisitCaseStatistics(page);
        }
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
        return map;
    }
}
