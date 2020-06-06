package com.wyzc.htgl.dao;

import java.util.List;

import com.bn.javax.dao.Page;
import com.wyzc.htgl.bo.DueDiligenceDisposeBo;

public interface FinancialDisposeDao {
    /**
     * 展示数据
     *
     * @param page
     * @return
     */
    List<DueDiligenceDisposeBo> showDueDiligenceDisposeData(Page page);

    /**
     * 改变数据状态
     *
     * @param bo
     * @return
     */
    Integer amendUserPost(DueDiligenceDisposeBo bo);

    /**
     * 改变数据状态为已完成
     *
     * @param bo
     * @return
     */
    Integer amendUserPostEnd(DueDiligenceDisposeBo bo);

    /**
     * 改变用户是否申请取消
     *
     * @param bo
     * @return
     */
    Integer amendUserCancel(DueDiligenceDisposeBo bo);

    /**
     * 添加备注
     *
     * @param bo
     * @return
     */
    Integer addRemark(DueDiligenceDisposeBo bo);

    /**
     * 添加备注原因
     *
     * @param bo
     * @return
     */
    Integer addRejectRemark(DueDiligenceDisposeBo bo);

    /**
     * 查询所选择房屋的链接
     *
     * @param bo
     * @return
     */
    String searchHouseUrlByChooseHouse(DueDiligenceDisposeBo bo);

    /**
     * 获取附件下载地址
     *
     * @param bo
     * @return
     */
    String getFilePathByUserId(DueDiligenceDisposeBo bo);

}
