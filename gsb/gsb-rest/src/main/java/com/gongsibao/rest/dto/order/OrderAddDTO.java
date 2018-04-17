package com.gongsibao.rest.dto.order;

import com.gongsibao.rest.dto.product.ProdPriceDTO;

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

    /* 订单归属业务员id */
    private Integer ownerId;
    /* 套餐id */
    private Integer packageId;
    /* 来源id */
    private Integer sourceTypeId;
    /* 优惠打折信息 */
    private String orderDiscount;

    /* 下单的产品id，适用于单个产品下单 */
    private int productId;

    // crm companyId
    private int companyId;

    /* 商标类别id(多个) */
    private String categoryIds;

    /* 收货地址 */
    private int deliverId;

    /* 发票id */
    private int invoiceId;

    /* 定价信息id */
    private List<ProdPriceDTO> priceList;

    public Integer getOwnerId() {
        return ownerId;
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

    public Integer getSourceTypeId() {
        return sourceTypeId;
    }

    public void setSourceTypeId(Integer sourceTypeId) {
        this.sourceTypeId = sourceTypeId;
    }

    public String getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public int getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(int deliverId) {
        this.deliverId = deliverId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<ProdPriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<ProdPriceDTO> priceList) {
        this.priceList = priceList;
    }
}
