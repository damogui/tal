package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_discount")
public class OrderDiscount extends BaseEntity {

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1894864532185900021L;
	@Column(name="order_id",header="订单序号")
    private Integer orderId;
	
    @Column(name="type_id",header="优惠类型序号，type=309")
    private Integer typeId;
    
    @Column(name="preferential_id",header="优惠券ID")
    private Integer preferentialId;
    
    @Column(name="amount",header="优惠金额，优惠前-优惠后")
    private Integer amount;
    
    @Column(name="remark",header="说明")
    private String remark;
    
    @Column(name="sqlid",header="对应SQL库的优惠券ID")
    private String sqlid;
    
    @Column(name="no",header="优惠券号码")
    private String no;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public Integer getPreferentialId() {
        return preferentialId;
    }
    public void setPreferentialId(Integer preferentialId) {
        this.preferentialId = preferentialId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSqlid() {
        return sqlid;
    }
    public void setSqlid(String sqlid) {
        this.sqlid = sqlid;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }

    
}