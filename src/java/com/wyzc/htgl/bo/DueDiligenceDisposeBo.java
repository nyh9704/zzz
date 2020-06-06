package com.wyzc.htgl.bo;

import com.wyzc.htgl.po.DueDiligenceDisposePo;

/**
 * @author Administrator
 */
public class DueDiligenceDisposeBo extends DueDiligenceDisposePo {
    private String pendingOrder; //待处理
    private String underwayOrder; //进行中
    private String rejectOrder; //已驳回
    private String accomplishOrder; //已结束
    private String cancelOrder; //被取消订单
    private String applyCancelOrder; //取消申请中

    public DueDiligenceDisposeBo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DueDiligenceDisposeBo(String userName, String userId, String userChooseHouse, String userPost,
                                 String userPostDate, String userPs, String userCancel, String userEndDate) {
        super(userName, userId, userChooseHouse, userPost, userPostDate, userPs, userCancel, userEndDate);
        // TODO Auto-generated constructor stub
    }

    public DueDiligenceDisposeBo(String pendingOrder, String underwayOrder, String rejectOrder, String accomplishOrder,
                                 String cancelOrder, String applyCancelOrder) {
        super();
        this.pendingOrder = pendingOrder;
        this.underwayOrder = underwayOrder;
        this.rejectOrder = rejectOrder;
        this.accomplishOrder = accomplishOrder;
        this.cancelOrder = cancelOrder;
        this.applyCancelOrder = applyCancelOrder;
    }

    @Override
    public String toString() {
        return "DueDiligenceDisposeBo [pendingOrder=" + pendingOrder + ", underwayOrder=" + underwayOrder
                + ", rejectOrder=" + rejectOrder + ", accomplishOrder=" + accomplishOrder + ", cancelOrder="
                + cancelOrder + ", applyCancelOrder=" + applyCancelOrder + "]";
    }

    public String getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(String pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public String getUnderwayOrder() {
        return underwayOrder;
    }

    public void setUnderwayOrder(String underwayOrder) {
        this.underwayOrder = underwayOrder;
    }

    public String getRejectOrder() {
        return rejectOrder;
    }

    public void setRejectOrder(String rejectOrder) {
        this.rejectOrder = rejectOrder;
    }

    public String getAccomplishOrder() {
        return accomplishOrder;
    }

    public void setAccomplishOrder(String accomplishOrder) {
        this.accomplishOrder = accomplishOrder;
    }

    public String getCancelOrder() {
        return cancelOrder;
    }

    public void setCancelOrder(String cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    public String getApplyCancelOrder() {
        return applyCancelOrder;
    }

    public void setApplyCancelOrder(String applyCancelOrder) {
        this.applyCancelOrder = applyCancelOrder;
    }


}
