package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.uc.Organization;

@Table(name = "so_order_prod_organization_map")
public class OrderProdOrganizationMap extends BaseEntity {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 69923830325841766L;
    @Column(name = "order_prod_id", header = "产品订单序号")
    private Integer orderProdId;

    @Reference(foreignKey = "orderProdId", header = "组织序号", primaryKey = "pkid")
    private OrderProd orderProd;

    @Column(name = "organization_id", header = "OrganizationId")
    private Integer organizationId;

    @Reference(foreignKey = "organizationId", header = "组织机构", primaryKey = "pkid")
    private Organization organization;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "is_bbk", header = "是否是八百客的数据")
    private String isBbk;

    public Integer getOrderProdId() {
        return orderProdId;
    }

    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getIsBbk() {
        return isBbk;
    }

    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }

    public OrderProd getOrderProd() {
        return orderProd;
    }

    public void setOrderProd(OrderProd orderProd) {
        this.orderProd = orderProd;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}