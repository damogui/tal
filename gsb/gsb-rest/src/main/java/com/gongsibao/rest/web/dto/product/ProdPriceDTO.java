package com.gongsibao.rest.web.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by oupeng on 16/10/21.
 */
public class ProdPriceDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /* 组织结构id */
    @JsonIgnore
    private Integer organizationId;

    /* 单位id */
    @JsonIgnore
    private Integer unitId;

    /* 单位名称 */
    private String unitName;
    /* 产品实际售卖价格 */
    private Integer price;

    /* 定价id */
    private int priceId;

    /* 分类ID */
    @JsonIgnore
    private Integer typeId;

    /* 分类名称 */
    private String typeName;

    private Integer isMust;

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getIsMust() {
        return isMust;
    }

    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }
}
