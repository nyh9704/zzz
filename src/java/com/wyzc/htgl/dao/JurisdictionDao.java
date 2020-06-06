package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.JurisdictionBo;

/**
 * 权限管理DAO层
 *
 * @author Administrator
 */
public interface JurisdictionDao {

    /**
     * 查全部员工的权限信息
     *
     * @param bo
     * @return
     */
    List<JurisdictionBo> loadAllData(JurisdictionBo bo);

    /**
     * 获得该权限
     *
     * @param bo
     * @return
     */

    Integer getJurisdictionr1(JurisdictionBo bo);

    Integer getJurisdictionr2(JurisdictionBo bo);

    Integer getJurisdictionr3(JurisdictionBo bo);

    Integer getJurisdictionr4(JurisdictionBo bo);

    Integer getJurisdictionr5(JurisdictionBo bo);

    Integer getJurisdictionr6(JurisdictionBo bo);

    Integer getJurisdictionr7(JurisdictionBo bo);

    Integer getJurisdictionr8(JurisdictionBo bo);

    Integer getJurisdictionr9(JurisdictionBo bo);

    Integer getJurisdictionr10(JurisdictionBo bo);

    Integer getJurisdictionr11(JurisdictionBo bo);

    Integer getJurisdictionr12(JurisdictionBo bo);

    Integer getJurisdictionr13(JurisdictionBo bo);

    Integer getJurisdictionr14(JurisdictionBo bo);

    Integer getJurisdictionr15(JurisdictionBo bo);

    /**
     * 清空某个字段的值
     *
     * @param bo
     */
    void deleteValue(JurisdictionBo bo);

    /**
     * 获取总数据长度
     *
     * @return
     */
    Integer getDataSize(JurisdictionBo bo);

    /**
     * 获取拥有各项权限的数据长度
     *
     * @return
     */
    Integer getJurisdictionr1Size(JurisdictionBo bo);

    Integer getJurisdictionr2Size(JurisdictionBo bo);

    Integer getJurisdictionr3Size(JurisdictionBo bo);

    Integer getJurisdictionr4Size(JurisdictionBo bo);

    Integer getJurisdictionr5Size(JurisdictionBo bo);

    Integer getJurisdictionr6Size(JurisdictionBo bo);

    Integer getJurisdictionr7Size(JurisdictionBo bo);

    Integer getJurisdictionr8Size(JurisdictionBo bo);

    Integer getJurisdictionr9Size(JurisdictionBo bo);

    Integer getJurisdictionr10Size(JurisdictionBo bo);

    Integer getJurisdictionr11Size(JurisdictionBo bo);

    Integer getJurisdictionr12Size(JurisdictionBo bo);

    Integer getJurisdictionr13Size(JurisdictionBo bo);

    Integer getJurisdictionr14Size(JurisdictionBo bo);

    Integer getJurisdictionr15Size(JurisdictionBo bo);

}
