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
import com.wyzc.htgl.dao.InviteCaseStatisticsDao;

@Service
public class InviteCaseStatisticsService {
    @Autowired
    private InviteCaseStatisticsDao icsd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchInviteCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<ClientInfoBo> list = new ArrayList<>();
        //获取当前用户的登录账号
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        //查询当前登录用户的部门
        String part = this.icsd.searchPartByUserId(bo);
        //获取当前登录用户的姓名
        String userName = this.icsd.searchUserNameById(bo);
        //查询当前登录用户的职务
        String duty = this.icsd.gGameOfThrones(bo);
        if (userId.equals("admin") || userId.equals("test") ||
                part.equals("01") || part.equals("0302") || part.equals("0303") ||
                duty.equals("3")) {
            ClientInfoBo cib = collectData(bo);
            Page page = new Page(cib);
            list = this.icsd.searchInviteCaseStatisticsAll(page);
            map.put("total", page.getTotalRecord());
        } else {
            ClientInfoBo cib = collectData(bo);
            cib.setUserName(userName);
            Page page = new Page(cib);
            list = this.icsd.searchInviteCaseStatisticsByCondition(page);
            map.put("total", page.getTotalRecord());
        }
        map.put("rows", list);

        return map;
    }

    /**
     * 权利的游戏
     *
     * @return
     */
    public Map<String, Object> gGameOfThrones() {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //获取当前用户的登录账号
        String userId = CommonFunction.getUserFromSession().getUserId();
        if (userId.equals("admin") || userId.equals("test")) {
            map.put("duty", "3");
        } else {
            ClientInfoBo bo = new ClientInfoBo();
            bo.setUserId(userId);
            //查询当前用户的职务
            String duty = this.icsd.gGameOfThrones(bo);
            map.put("duty", duty);
        }

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
}