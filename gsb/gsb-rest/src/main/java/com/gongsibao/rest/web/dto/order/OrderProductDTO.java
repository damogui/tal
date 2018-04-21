package com.gongsibao.rest.web.dto.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gongsibao.rest.dto.PkId;

import java.util.Date;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 订单下产品 (沿用原有参数)
 * @date 2018/4/20 11:39
 */
public class OrderProductDTO extends PkId{

    /**
     * 编号
     */
    private String no;

    /**
     * 订单序号
     */
    private Integer orderId;

    /***
     * 订单编号
     */
    private String orderNo;

    private String productIdStr;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 城市序号
     */
    private Integer cityId;

    /**
     * 订单类型，1订单，2合同
     */
    private Integer type;

    /**
     * 处理状态序号，type=206
     */
    private Integer processStatusId;
    /***
     * 处理状态名称
     */
    private String processStatusName;

    /**
     * 审核状态序号，type=105
     */
    private Integer auditStatusId;
    /***
     * 审核状态名称
     */
    private String auditStatusName;
    /**
     * 总价
     */
    private Integer price;

    /**
     * 原价
     */
    private Integer priceOriginal;

    private Integer soOrderPrice;

    private Integer soOrderPriceOriginal;
    private Integer soOrderPaidPrice;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date soOrderAddtime;

    private Integer soOrderProcessStatusId;

    private String soOrderProcessStatusName;

    private Integer soOrderPayStatusId;

    private Integer isinstallment;
    /**
     * 产品服务
     */
    private String itemNames;

    /**
     * 产品地区
     */
    private String placeName;
    /**
     * 下单人帐号
     */
    private String accountName;
    /**
     * 下单人手机号
     */
    private String accountMobile;
    /**
     * 是否退单
     */
    private Integer isRefund;

    /**
     * 已办理天数
     */
    private Integer processedDays;

    /**
     * 是否投诉
     */
    private Integer isComplaint;

    /**
     * 需要天数
     */
    private Integer needDays;

    /**
     * 超时天数
     */
    private Integer timeoutDays;

    /**
     * 订单状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 城市名称
     */
    private String cityName;

    /***
     * 订单id加密后字符串
     */
    private String orderIdStr;

    private String orderProdIdStr;

    private Integer  isPackage;
    //套餐名称（有套餐就显示套餐的名称，没有就显示单品名称）
    private String  prodPackageName;
    /***
     * 是否分配
     */
    private Integer isAssign;

    /* 是否显示进度 0否 1是 */
    private int showProcess;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
    @JsonIgnore
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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
    @JsonIgnore
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    @JsonIgnore
    public Integer getProcessStatusId() {
        return processStatusId;
    }

    public void setProcessStatusId(Integer processStatusId) {
        this.processStatusId = processStatusId;
    }

    public String getProcessStatusName() {
        return processStatusName;
    }

    public void setProcessStatusName(String processStatusName) {
        this.processStatusName = processStatusName;
    }
    @JsonIgnore
    public Integer getAuditStatusId() {
        return auditStatusId;
    }

    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(Integer priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public Integer getSoOrderPrice() {
        return soOrderPrice;
    }

    public void setSoOrderPrice(Integer soOrderPrice) {
        this.soOrderPrice = soOrderPrice;
    }

    public Integer getSoOrderPriceOriginal() {
        return soOrderPriceOriginal;
    }

    public void setSoOrderPriceOriginal(Integer soOrderPriceOriginal) {
        this.soOrderPriceOriginal = soOrderPriceOriginal;
    }

    public Integer getSoOrderPaidPrice() {
        return soOrderPaidPrice;
    }

    public void setSoOrderPaidPrice(Integer soOrderPaidPrice) {
        this.soOrderPaidPrice = soOrderPaidPrice;
    }

    public Date getSoOrderAddtime() {
        return soOrderAddtime;
    }

    public void setSoOrderAddtime(Date soOrderAddtime) {
        this.soOrderAddtime = soOrderAddtime;
    }
    @JsonIgnore
    public Integer getSoOrderProcessStatusId() {
        return soOrderProcessStatusId;
    }

    public void setSoOrderProcessStatusId(Integer soOrderProcessStatusId) {
        this.soOrderProcessStatusId = soOrderProcessStatusId;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
    }

    public Integer getProcessedDays() {
        return processedDays;
    }

    public void setProcessedDays(Integer processedDays) {
        this.processedDays = processedDays;
    }

    public Integer getIsComplaint() {
        return isComplaint;
    }

    public void setIsComplaint(Integer isComplaint) {
        this.isComplaint = isComplaint;
    }

    public Integer getNeedDays() {
        return needDays;
    }

    public void setNeedDays(Integer needDays) {
        this.needDays = needDays;
    }

    public Integer getTimeoutDays() {
        return timeoutDays;
    }

    public void setTimeoutDays(Integer timeoutDays) {
        this.timeoutDays = timeoutDays;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonIgnore
    public Integer getIsAssign() {
        return isAssign;
    }

    public void setIsAssign(Integer isAssign) {
        this.isAssign = isAssign;
    }

    public String getOrderIdStr() {
        return orderIdStr;
    }

    public void setOrderIdStr(String orderIdStr) {
        this.orderIdStr = orderIdStr;
    }

    public String getSoOrderProcessStatusName() {
        return soOrderProcessStatusName;
    }

    public void setSoOrderProcessStatusName(String soOrderProcessStatusName) {
        this.soOrderProcessStatusName = soOrderProcessStatusName;
    }

    public String getOrderProdIdStr() {
        return orderProdIdStr;
    }

    public void setOrderProdIdStr(String orderProdIdStr) {
        this.orderProdIdStr = orderProdIdStr;
    }

    public String getProductIdStr() {
        return productIdStr;
    }

    public void setProductIdStr(String productIdStr) {
        this.productIdStr = productIdStr;
    }

    public Integer getSoOrderPayStatusId() {
        return soOrderPayStatusId;
    }

    public void setSoOrderPayStatusId(Integer soOrderPayStatusId) {
        this.soOrderPayStatusId = soOrderPayStatusId;
    }

    public Integer getIsinstallment() {
        return isinstallment;
    }

    public void setIsinstallment(Integer isinstallment) {
        this.isinstallment = isinstallment;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProdPackageName() {
        return prodPackageName;
    }

    public void setProdPackageName(String prodPackageName) {
        this.prodPackageName = prodPackageName;
    }

    public Integer getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(Integer isPackage) {
        this.isPackage = isPackage;
    }

    public int getShowProcess() {
        return showProcess;
    }

    public void setShowProcess(int showProcess) {
        this.showProcess = showProcess;
    }
}
