package com.wyzc.htgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bn.frame.util.CommonFunction;
import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.bo.WyzcPersonnelTestBo;
import com.wyzc.htgl.dao.ClientInfoDao;
import com.wyzc.htgl.po.WyzcDepartmentPo;

@Service
public class ClientInfoService {
    @Autowired
    private ClientInfoDao cid;

    /**
     * 根据顾问名字查找对应部门和领导
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchLeaderAndPart(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //查询该顾问所在的部门
        WyzcDepartmentPo part = this.cid.searchPart(bo);
        bo.setWpId(part.getWpId());
        //查询该顾问的职务
        String duty = this.cid.searchDuty(bo);
        //查询该顾问的上级领导
        if (duty.equals("1") || duty.equals("4")) {
            bo.setDutyClass("2");
            List<ClientInfoBo> list = this.cid.searchLeader(bo);
            map.put("leader", list);
        } else if (duty.equals("2")) {
            bo.setDutyClass("3");
            List<ClientInfoBo> list = this.cid.searchLeader(bo);
            map.put("leader", list);
        } else if (duty.equals("3")) {
            map.put("leader", null);
        }
        map.put("part", part);
        map.put("duty", duty);

        return map;
    }

    /**
     * 新增到访客户信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> saveClientInfo(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        //获取当前谈单人所属部门
        String depart = this.cid.searchDepartByDecider(bo);
        bo.setcDepart(depart);
        if (bo.getcDate().equals("")) {
            bo.setcDate(null);
        }
        if (bo.getcTime().equals("")) {
            bo.setcTime(null);
        }
        Integer result = this.cid.saveClientInfo(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 查询当前用户所属部门及职务
     *
     * @return
     */
    public Map<String, Object> searchUserPartAndDuty() {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        ClientInfoBo bo = new ClientInfoBo();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
		        //查询当前登陆用户的所属部门
        List<String> part = this.cid.getUserPart(bo);
        //查询当前用户所在部门的上级部门
        List<String> leaderPart = this.cid.getUserLeaderPart(bo);
        map.put("leaderPart", leaderPart);
        if (part.size() > 1) {
            if (userId.equals("admin") || userId.equals("kefu")
                    || userId.equals("admin1")
                    || part.get(1).equals("0904")
                    || userId.equals("test")) {
                map.put("part", "");
            } else {
                //查询当前登陆用户的职务
                List<String> duty = this.cid.getUserDuty(bo);
                map.put("duty", duty);
                map.put("part", part);
            }
        } else {
            if (userId.equals("admin") || userId.equals("kefu") || userId.equals("admin1") ||
                    part.get(0).equals("0904") || part.get(0).equals("01")
                    || part.get(0).equals("0302") || part.get(0).equals("0303")
                    || userId.equals("test")) {
                map.put("part", "");
            } else {
                //查询当前登陆用户的职务
                List<String> duty = this.cid.getUserDuty(bo);
                map.put("duty", duty);
                map.put("part", part);
            }
        }

        return map;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public Map<String, Object> searchClientData(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        bo.setUserId(userId);
        List<String> part = new ArrayList<>();
        //查询当前登陆用户的姓名
        if (!userId.equals("admin")) {
            List<String> userName = this.cid.searchUserName(bo);
            part = this.cid.getUserPart(bo);
            bo.setUserName(userName.get(0));
        }


        Page page = new Page(bo);
        //查询当前登陆用户的职务
        List<String> duty = this.cid.getUserDuty(bo);
        if (part.size() > 1) {
            if (userId.equals("admin") || userId.equals("kefu") || userId.equals("admin1") ||
                    part.get(1).equals("0904")) {
                //客服专用
                List<ClientInfoBo> list = this.cid.searchClientDataByKefu(page);
                //System.out.println(list);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("1") || duty.get(0).equals("4")) {
                //普通员工专用
                List<ClientInfoBo> list = this.cid.searchClientDataByStaff(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("2")) {
                //组长专用
                List<ClientInfoBo> list = this.cid.searchClientDataByGroupLeader(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("3")) {
                //经理专用
                List<ClientInfoBo> list = this.cid.searchClientDataByManager(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            }

        } else if (part.size() == 1) {
            if (userId.equals("admin") || userId.equals("kefu") || userId.equals("admin1") || part.get(0).equals("0904")
                    || part.get(0).equals("0903") ||
                    part.get(0).equals("01") || part.get(0).equals("0302") ||
                    part.get(0).equals("0303")) {
                //客服专用
                List<ClientInfoBo> list = this.cid.searchClientDataByKefu(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("1") || duty.get(0).equals("4")) {
                //普通员工专用
                List<ClientInfoBo> list = this.cid.searchClientDataByStaff(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("2")) {
                //组长专用				
                List<ClientInfoBo> list = this.cid.searchClientDataByGroupLeader(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            } else if (duty.get(0).equals("3")) {
                //经理专用
                List<ClientInfoBo> list = this.cid.searchClientDataByManager(page);
                map.put("rows", list);
                map.put("total", page.getTotalRecord());
            }
        } else {
            List<ClientInfoBo> list = this.cid.searchClientDataByKefu(page);
            map.put("rows", list);
            map.put("total", page.getTotalRecord());
        }

        return map;
    }

    /**
     * 查所有的主管
     *
     * @return
     */
    public Map<String, Object> searchAllGroupLeader() {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<ClientInfoBo> list = this.cid.searchAllGroupLeader();
        //查询所有部门
        List<ClientInfoBo> parts = this.cid.searchAllPart();
        Iterator<ClientInfoBo> it = parts.iterator();
        while (it.hasNext()) {
            ClientInfoBo wpb = it.next();
            if (wpb.getcPart().equals("总经办")) {
                it.remove();
            }
        }
        map.put("result", list);
        map.put("parts", parts);
        return map;
    }

    /**
     * 根据编号查客户信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchModifyClientInfo(ClientInfoBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        ClientInfoBo result = this.cid.searchModifyClientInfo(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 提交修改客户信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> modifyClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        String depart = this.cid.searchDepartByDecider(bo);
        bo.setcDepart(depart);
        Integer result = this.cid.modifyClientInfo(bo);
        //修改时间
        this.cid.insertModiftyTime(bo);
        map.put("result", result);
        return map;
    }

    /**
     * 删除客户信息
     *
     * @param bo
     * @return
     */
    public Map<String, Object> deleteClientInfo(ClientInfoBo bo) {
        Map<String, Object> map = new HashMap<>();
        Integer result = this.cid.deleteClientInfo(bo);
        //删除修改时间
        this.cid.deleteModifyClientInfo(bo);
        map.put("result", result);
        return map;
    }
}
