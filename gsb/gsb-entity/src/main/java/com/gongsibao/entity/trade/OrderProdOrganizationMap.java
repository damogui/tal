package com.gongsibao.entity.trade;

import java.sql.Date;
import java.sql.Timestamp;

import com.gongsibao.entity.uc.Organization;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_organization_map")
public class OrderProdOrganizationMap extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 69923830325841766L;
	@Column(name="order_prod_id",header="OrderProdId")
    private Integer orderProdId;
    @Reference(foreignKey="orderProdId",header="订单明细")
    private OrderProd orderProd;

    @Column(name="organization_id",header="OrganizationId")
    private Integer organizationId;
    @Reference(foreignKey="organizationId",header="组织机构")
    private Organization organization;


    @Column(name="add_time",header="AddTime")
    private Timestamp addTime;
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";

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
    public Timestamp getAddTime() {
        return addTime;
    }
    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
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
}