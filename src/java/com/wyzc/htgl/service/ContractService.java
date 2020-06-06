package com.wyzc.htgl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.WyzcPersonnelBo;
import com.wyzc.htgl.bo.WyzcPersonnelBo2;
import com.wyzc.htgl.dao.ContractDao;

@Service
public class ContractService {
    @Autowired
    private ContractDao cd;


    public Map<String, Object> showContract(WyzcPersonnelBo bo, WyzcPersonnelBo2 bo2) {
        if (bo.getSort() == "turnOfficialTime") {
            bo.setSort("turn_official_time");
        } else if (bo.getSort() == "startWorkTime") {
            bo.setSort("start_work_time");
        } else if (bo.getSort() == "workAge") {
            bo.setSort("work_age");
        } else if (bo.getSort() == "age") {
            bo.setSort("age");
        } else if (bo.getSort() == "contractTime") {
            bo.setSort("contract_time");
        } else if (bo.getSort() == "contractDeadline") {
            bo.setSort("contract_deadline");
        } else if (bo.getSort() == "contractEndTime") {
            bo.setSort("contract_end_time");
        }
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        List<WyzcPersonnelBo> list = this.cd.showContract1(bo);
        bo2.setUserId(userId);
        this.cd.deleteShowContract(bo2);
        for (int i = 0; i < list.size(); i++) {
            bo2.setAge(list.get(i).getAge());
            bo2.setAge1(list.get(i).getAge1());
            bo2.setAge2(list.get(i).getAge2());
            bo2.setBirDate(list.get(i).getBirDate());
            bo2.setBirMounth(list.get(i).getBirMounth());
            bo2.setBirYear(list.get(i).getBirYear());
            bo2.setContactTel(list.get(i).getContactTel());
            bo2.setContractType(list.get(i).getContractType());
            bo2.setDimissionTime(list.get(i).getDimissionTime());
            bo2.setEducation(list.get(i).getEducation());
            bo2.setIdentityCard(list.get(i).getIdentityCard());
            bo2.setIsOfficial(list.get(i).getIsOfficial());
            bo2.setIsPostpone(list.get(i).getIsPostpone());
            bo2.setNation(list.get(i).getNation());
            bo2.setOrder(list.get(i).getOrder());
            bo2.setPage(list.get(i).getPage());
            bo2.setFileNumber(list.get(i).getFileNumber());
            bo2.setPostName(list.get(i).getPostName());
            bo2.setPostponeNumber(list.get(i).getPostponeNumber());
            bo2.setPostponeTime(list.get(i).getPostponeTime());
            bo2.setPostponeTime1(list.get(i).getPostponeTime1());
            bo2.setPostponeTime2(list.get(i).getPostponeTime2());
            bo2.setPostponeTo(list.get(i).getPostponeTo());
            bo2.setPracticeEnd(list.get(i).getPracticeEnd());
            bo2.setPracticeNumber(list.get(i).getPracticeNumber());
            bo2.setPracticeStart(list.get(i).getPracticeStart());
            bo2.setRegistered(list.get(i).getRegistered());
            bo2.setRemark(list.get(i).getRemark());
            bo2.setRows(list.get(i).getRows());
            bo2.setSearchMo(list.get(i).getSearchMo());
            bo2.setSign(list.get(i).getSign());
            bo2.setSort(list.get(i).getSort());
            bo2.setStartWorkTime(list.get(i).getStartWorkTime());
            bo2.setStartWorkTime1(list.get(i).getStartWorkTime1());
            bo2.setStartWorkTime2(list.get(i).getStartWorkTime2());
            bo2.setTurnOfficialTime(list.get(i).getTurnOfficialTime());
            bo2.setTurnOfficialTime1(list.get(i).getTurnOfficialTime1());
            bo2.setTurnOfficialTime2(list.get(i).getTurnOfficialTime2());
            bo2.setUserIdCardAddress(list.get(i).getUserIdCardAddress());
            bo2.setUserName(list.get(i).getUserName());
            bo2.setUserSex(list.get(i).getUserSex());
            bo2.setWdId(list.get(i).getWdId());
            bo2.setWdName(list.get(i).getWdName());
            bo2.setWorkAge(list.get(i).getWorkAge());
            bo2.setWorkAge1(list.get(i).getWorkAge1());
            bo2.setWorkAge2(list.get(i).getWorkAge2());
            bo2.setWpId(list.get(i).getWpId());
            bo2.setWpName(list.get(i).getWpName());
            bo2.setUserId(userId);
            bo2.setContractTime(list.get(i).getContractTime());
            bo2.setContractTime1(list.get(i).getContractTime1());
            bo2.setContractTime2(list.get(i).getContractTime2());
            bo2.setContractDedline(list.get(i).getContractDedline());
            bo2.setContractEndTime(list.get(i).getContractEndTime());
            bo2.setContractEndTime1(list.get(i).getContractEndTime1());
            bo2.setContractEndTime2(list.get(i).getContractEndTime2());
            this.cd.addContract(bo2);
        }
        bo2.setUserId(userId);
        bo2.setTurnOfficialTime1(bo.getTurnOfficialTime1());
        bo2.setTurnOfficialTime2(bo.getTurnOfficialTime2());
        bo2.setContractTime1(bo.getContractTime1());
        bo2.setContractTime2(bo.getContractTime2());
        bo2.setContractEndTime1(bo.getContractEndTime1());
        bo2.setContractEndTime2(bo.getContractEndTime2());
        bo2.setStartWorkTime1(bo.getStartWorkTime1());
        bo2.setStartWorkTime2(bo.getStartWorkTime2());
        bo2.setWorkAge1(bo.getWorkAge1());
        bo2.setWorkAge2(bo.getWorkAge2());
        bo2.setAge1(bo.getAge1());
        bo2.setAge2(bo.getAge2());
        bo2.setNation1(bo.getNation());//民族
        bo2.setRegistered1(bo.getRegistered());//户口
        bo2.setEducation1(bo.getEducation());
        bo2.setWpName1(bo.getWpName());
        bo2.setEducation1(bo.getEducation());
        bo2.setUserName1(bo.getUserName());
        bo2.setPostName1(bo.getPostName());
        bo2.setWdName1(bo.getWdName());
        bo2.setIsOfficial1(bo.getIsOfficial());
        bo2.setContractType1(bo.getContractType());
        Page page = new Page(bo2);
        List<WyzcPersonnelBo2> list2 = this.cd.searchContract(bo2);
        map.put("rows", list2);
        map.put("total", page.getTotalRecord());
        return map;
    }


    public Map<String, Object> modifyContract(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        map.put("modifyChannelHouse", this.cd.modifyContract(bo));
        return map;
    }

    public Map<String, Object> searchContractQx(WyzcPersonnelBo bo) {
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<WyzcPersonnelBo> list = this.cd.searchContractQx(bo);
        map.put("searchContractQx", list);
        return map;
    }

}
