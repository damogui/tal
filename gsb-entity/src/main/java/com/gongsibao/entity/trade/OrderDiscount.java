package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_discount")
public class OrderDiscount extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1894864532185900021L;
	@Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="type_id",header="TypeId")
    private Integer typeId;
    @Column(name="preferential_id",header="PreferentialId")
    private Integer preferentialId;
    @Column(header="amount")
    private Integer amount;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(header="remark")
    private String remark;
    @Column(header="sqlid")
    private String sqlid;
    @Column(header="no")
    private String no;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;

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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
}