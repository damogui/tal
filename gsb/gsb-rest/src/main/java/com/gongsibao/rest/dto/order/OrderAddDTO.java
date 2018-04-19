package com.gongsibao.rest.dto.order;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.rest.dto.product.ProductPriceDTO;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: orderAddDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 新建订单接收实体
 * @date
 */
public class OrderAddDTO implements Serializable {
    private static final long serialVersionUID = -5008091677949699970L;

    private String openId;

    /* 订单归属业务员id */
    private Integer ownerId;
    /* 套餐id */
    private Integer packageId;
    /* 来源id */
    private OrderPlatformSourceType orderPlatformSourceType = OrderPlatformSourceType.Gsb;

    /* 订单当前平台，默认微信 */
    private CouponPlatformType platformType = CouponPlatformType.WEIXIN;

    /* 优惠打折信息 */
    private String orderDiscount;

    /* 下单的产品id，适用于单个产品下单 */
    private Integer productId;

    // crm companyId
    private Integer companyId;

    /* 商标类别id(多个) */
    private String categoryIds;

    /* 收货地址 */
    private Integer deliverId;

    /* 发票id */
    private Integer invoiceId;

    /* 会员 */
    private Account account;

    /* 产品信息 */
    private List<OrderProdAddDto> productList;

    public Integer getOwnerId() {
        return ownerId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public OrderPlatformSourceType getOrderPlatformSourceType() {
        return orderPlatformSourceType;
    }

    public void setOrderPlatformSourceType(OrderPlatformSourceType orderPlatformSourceType) {
        this.orderPlatformSourceType = orderPlatformSourceType;
    }

    public CouponPlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(CouponPlatformType platformType) {
        this.platformType = platformType;
    }

    public String getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<OrderProdAddDto> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProdAddDto> productList) {
        this.productList = productList;
    }
}
