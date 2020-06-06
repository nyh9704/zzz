package com.wyzc.htgl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.bn.frame.util.CommonFunction;
import com.wyzc.htgl.bo.WyzcDepartmentBo;
import com.wyzc.htgl.bo.WyzcUserBo;
import com.wyzc.htgl.dao.WyzcUserDao;
import com.wyzc.htgl.po.WyzcUserInfoPo;

/**
 * @author Administrator
 * 服务层
 */
@Service
public class WyzcUserService {
    @Autowired
    private WyzcUserDao wud;

    /**
     * 注册功能实现
     *
     * @param userBo
     * @return
     */

    public Map<String, Object> registerUser(WyzcUserBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        System.out.println(bo);
//		if(bo.getWpId().equals("05")) {
//			bo.setRoleId("ROLE_SCB");
//		}else if(bo.getWpId().equals("06")) {
//			bo.setRoleId("ROLE_QDB");
//		}else if(bo.getWpId().equals("07")) {
//			bo.setRoleId("ROLE_TZB");
//		}else if(bo.getWpId().equals("08")) {
//			bo.setRoleId("ROLE_DXB");
//		}else if(bo.getWpIdChild().equals("0902")) {
//			bo.setRoleId("ROLE_RS");
//		}else if(bo.getWpIdChild().equals("0903")) {
//			bo.setRoleId("ROLE_XZB");
//		}else if(bo.getWpIdChild().equals("0904")) {
//			bo.setRoleId("ROLE_KFB");
//		}else if(bo.getWpIdChild().equals("0905")) {
//			bo.setRoleId("ROLE_PXB");
//		}else if(bo.getWpIdChild().equals("0906")) {
//			bo.setRoleId("ROLE_CWB");
//		}else if(bo.getWpIdChild().equals("0907")) {
//			bo.setRoleId("ROLE_JRB");
//		}else if(bo.getWpIdChild().equals("0908")) {
//			bo.setRoleId("ROLE_JDB");
//		}else if(bo.getWpIdChild().equals("0909")) {
//			bo.setRoleId("ROLE_QZB");
//		}
        String userPwd = DigestUtils.md5DigestAsHex(bo.getUserPwd().getBytes());
        bo.setUserPwd(userPwd.toUpperCase());
        //根据用户输入的信息在表中查询
        WyzcUserBo wub = this.wud.searchUserInfoByTel(bo);
        if (wub.getSign().equals("3") || wub.getSign().equals("1")) {
            map.put("result", "0");
        } else {
            List<WyzcUserInfoPo> list = this.wud.queryUserInfo();
            for (WyzcUserInfoPo po : list) {
                if (bo.getUserId().equals(po.getUserIdInfo()) &&
                        bo.getUserName().equals(po.getUserNameInfo()) &&
                        bo.getIdentityCard().equals(po.getUserIdEntityInfo())) {
                    //完成注册
                    Integer result = this.wud.registerUser(bo);
                    this.wud.addSysRole(bo);
                    this.wud.registerUserQx(bo);
                    map.put("result", result);

                }
            }
        }
        return map;
    }

    /**
     * 查询上级部门
     *
     * @return
     */
    public Map<String, Object> searchParentPart() {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        List<WyzcDepartmentBo> list = this.wud.searchParentPart();
        map.put("result", list);
        return map;
    }

    /**
     * 查询下级部门
     *
     * @param bo
     * @return
     */
    public Map<String, Object> searchChildPart(WyzcDepartmentBo bo) {
        Map<String, Object> map = new HashMap<>();
        List<WyzcDepartmentBo> list = this.wud.searchChildPart(bo);
        map.put("result", list);
        return map;
    }

    public Map<String, Object> isAdmin(WyzcDepartmentBo bo) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        String userId = CommonFunction.getUserFromSession().getUserId();
        if (userId.equals("admin")) {
            map.put("result", "1");
        }
        return map;
    }
}
