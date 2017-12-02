package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_prod_cost",header="产品订单成本")
public class OrderProdCost extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7886316440641294453L;

	@Column(name="order_prod_id",header="产品订单id")
    private Integer orderProdId;
	
    @Column(name="receiver_id",header="收款方id")
    private Integer receiverId;
    
    @Column(name="cost",header="成本")
    private Integer cost;
    
    @Column(name="organization_id",header="成本部门")
    private Integer organizationId;
    
    @Column(name="remark",header="成本备注")
    private String remark;

    public Integer getOrderProdId() {
        return orderProdId;
    }
    public void setOrderProdId(Integer orderProdId) {
        this.orderProdId = orderProdId;
    }
    public Integer getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public Integer getOrganizationId() {
        return organizationId;
    }
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}