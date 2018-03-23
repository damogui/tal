package com.gongsibao.trade.web.dto;

/**
 * Created by win on 2018/3/23.
 */
public class OrderInfoDTO {
    /*订单号*/
    private String orderNo;
    /*订单分配金额*/
    private String orderCut;
    /*付款类别*/
    private String payType;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderCut() {
        return orderCut;
    }

    public void setOrderCut(String orderCut) {
        this.orderCut = orderCut;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
