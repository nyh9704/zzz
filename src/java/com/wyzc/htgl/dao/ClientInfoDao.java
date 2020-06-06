package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.ClientInfoBo;
import com.wyzc.htgl.po.WyzcDepartmentPo;

public interface ClientInfoDao {

    //查询该顾问所在的部门
    WyzcDepartmentPo searchPart(ClientInfoBo bo);

    //查询该顾问的职务
    String searchDuty(ClientInfoBo bo);

    //查询该顾问的领导
    List<ClientInfoBo> searchLeader(ClientInfoBo bo);

    //新增到访客户信息
    Integer saveClientInfo(ClientInfoBo bo);

    //获取当前登陆用户职务
    List<String> getUserDuty(ClientInfoBo bo);

    //查询当前登陆用户所属部门
    List<String> getUserPart(ClientInfoBo bo);

    //查询当前登陆用户的姓名
    List<String> searchUserName(ClientInfoBo bo);

    //客服专用
    List<ClientInfoBo> searchClientDataByKefu(Page page);

    //普通员工专用
    List<ClientInfoBo> searchClientDataByStaff(Page page);

    //组长专用
    List<ClientInfoBo> searchClientDataByGroupLeader(Page page);

    //经理专用
    List<ClientInfoBo> searchClientDataByManager(Page page);

    //查所有的主管
    List<ClientInfoBo> searchAllGroupLeader();

    //查询当前登陆用户所在部门的上级部门
    List<String> getUserLeaderPart(ClientInfoBo bo);

    //根据编号查客户资料
    ClientInfoBo searchModifyClientInfo(ClientInfoBo bo);

    //提交修改客户信息
    Integer modifyClientInfo(ClientInfoBo bo);

    //修改时间
    Integer insertModiftyTime(ClientInfoBo bo);

    //删除客户信息
    Integer deleteClientInfo(ClientInfoBo bo);

    //删除修改时间
    void deleteModifyClientInfo(ClientInfoBo bo);

    //查询所有部门
    List<ClientInfoBo> searchAllPart();

    //获取当前谈单人所属部门
    String searchDepartByDecider(ClientInfoBo bo);


}
