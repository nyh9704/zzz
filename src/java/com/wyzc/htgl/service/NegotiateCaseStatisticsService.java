package com.wyzc.htgl.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.dao.NegotiateCaseStatisticsDao;

@Service
public class NegotiateCaseStatisticsService {
    @Autowired
    private NegotiateCaseStatisticsDao ncsd;

    /**
     * 分页查询
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchNegotiateCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        ClientInfoBo cib = collectData(bo);
        Page page = new Page(cib);
        List<ClientInfoBo> list = this.ncsd.searchNegotiateCaseStatistics(page);
        map.put("rows", list);
        map.put("total", page.getTotalRecord());
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
     * 根据编号查询信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchNegotiateCaseStatisticsById(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        ClientInfoBo cib = this.ncsd.searchNegotiateCaseStatisticsById(bo);
        map.put("result", cib);
        return map;
    }


    /**
     * 修改
     *
     * @param bo
     * @return
     */
    public Map<String, Object> modifyNegotiateCaseStatisticsById(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.ncsd.modifyNegotiateCaseStatisticsById(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 查询所填谈单人的部门
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchDepartOfDecider(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String result = this.ncsd.searchDepartOfDecider(bo);
        map.put("rsult", result);
        return map;
    }


    /**
     * 删除
     *
     * @param bo
     * @return
     */
    public Map<String, Object> deletenegotiateCaseStatistics(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        Integer result = this.ncsd.deletenegotiateCaseStatistics(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 权利的游戏之行政部门
     *
     * @param bo
     * @return
     */
    public Map<String, Object> administrativeGameOfThrones(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //查询当前登录用户的职务
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        String result = this.ncsd.administrativeGameOfThrones(bo);
        map.put("result", result);
        return map;
    }


}
