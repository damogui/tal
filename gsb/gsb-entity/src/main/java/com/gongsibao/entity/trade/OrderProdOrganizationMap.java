package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
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
    @Column(name="organization_id",header="OrganizationId")
    private Integer organizationId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
}