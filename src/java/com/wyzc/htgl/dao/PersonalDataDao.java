package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.PersonalDataBo;

/**
 * 个人资料DAO层
 *
 * @author Administrator
 */
public interface PersonalDataDao {
    //分页查询
    List<PersonalDataBo> searchPersonalData(Page page);

    //录入房源
    Integer enteringHouseData(PersonalDataBo bo);

    //获取图片地址
    String getImgUrl(PersonalDataBo bo);

    //查询修改信息
    List<PersonalDataBo> searchModifyPersonal(PersonalDataBo bo);

    //修改
    Integer modifyPersonal(PersonalDataBo bo);

    //删除信息
    Integer deletePersonalData(PersonalDataBo bo);

    //将数据增加进备份表中
    void enteringHouseDataToBackup(PersonalDataBo bo);
}
