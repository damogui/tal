package com.gongsibao.rest.web.dto.order;

import com.gongsibao.entity.trade.dic.OrderProcessStatusType;

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
    /* 明细订单价格 */
    private BigDecimal orderProdPrice;
    /* 订单价格 */
    private BigDecimal orderPrice;
    /* 下单人 */
    private String accountName;
    /* 状态 */
    private OrderProcessStatusType processStatus;
    /* 下单时间 */
    private Date createTime;

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

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public OrderProcessStatusType getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(OrderProcessStatusType processStatus) {
        this.processStatus = processStatus;
    }

    public BigDecimal getOrderProdPrice() {
        return orderProdPrice;
    }

    public void setOrderProdPrice(BigDecimal orderProdPrice) {
        this.orderProdPrice = orderProdPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderProdTraceDTO> getTraceList() {
        return traceList;
    }

    public void setTraceList(List<OrderProdTraceDTO> traceList) {
        this.traceList = traceList;
    }
}
