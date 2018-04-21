package com.gongsibao.rest.web.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: OrderMessageDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 消息处展示订单详情
 * @date 2018/4/21 13:24
 */
public class OrderMessageDTO implements Serializable {
    private static final long serialVersionUID = -797929734712996771L;

    /* 订单号 */
    private String orderNo;
    /* 商品名称 */
    private String productName;
    /* 商品区域 */
    private String cityName;
    /* 订单价格 */
    private String orderPrice;
    /* 下单人 */
    private String accountName;
    /* 状态 */
    private String statusName;
    /*  */
    private BigDecimal orderProdPrice;
    /*  */
    private Date addTime;

    private List<OrderProdTraceDTO> traceList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public BigDecimal getOrderProdPrice() {
        return orderProdPrice;
    }

    public void setOrderProdPrice(BigDecimal orderProdPrice) {
        this.orderProdPrice = orderProdPrice;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public List<OrderProdTraceDTO> getTraceList() {
        return traceList;
    }

    public void setTraceList(List<OrderProdTraceDTO> traceList) {
        this.traceList = traceList;
    }
}
