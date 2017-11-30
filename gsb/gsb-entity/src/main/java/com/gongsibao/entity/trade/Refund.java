package com.gongsibao.entity.trade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_refund")
public class Refund extends BaseEntity {

	private static final long serialVersionUID = 9015009742333617920L;
	
	@Column(name="order_id",header="订单")
    private Integer orderId;
    @Column(name="audit_status_id",header="审核")
    private Integer auditStatusId;
    @Column(name="way_type_id",header="退款方式")
    private Integer wayTypeId;
    @Column(name="is_full_refund",header="全额退款")
    private Integer isFullRefund;
    @Column(header="编号")
    private String no;
    @Column(name="payer_name",header="对方姓名")
    private String payerName;
    @Column(name="bank_no",header="银行账号")
    private String bankNo;
    @Column(header="金额")
    private Integer amount;
    @Column(header="成本")
    private Integer cost;
    @Column(header="备注")
    private String remark;
    @Column(name="add_time",header="创建时间")
    private Date addTime;
    @Column(name="add_user_id",header="创建人")
    private Integer addUserId;
    
    @Subs(subType=RefundItem.class,foreignKey="refundId",header="退款明细")
    private List<RefundItem> refunds = new ArrayList<RefundItem>();
    
    @Subs(subType=RefundItemPrice.class,foreignKey="refundId",header="退款价格")
    private List<RefundItemPrice> prices= new ArrayList<RefundItemPrice>();

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }
    public Integer getWayTypeId() {
        return wayTypeId;
    }
    public void setWayTypeId(Integer wayTypeId) {
        this.wayTypeId = wayTypeId;
    }
    public Integer getIsFullRefund() {
        return isFullRefund;
    }
    public void setIsFullRefund(Integer isFullRefund) {
        this.isFullRefund = isFullRefund;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getPayerName() {
        return payerName;
    }
    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }
    public String getBankNo() {
        return bankNo;
    }
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
	public List<RefundItem> getRefunds() {
		return refunds;
	}
	public void setRefunds(List<RefundItem> refunds) {
		this.refunds = refunds;
	}
	public List<RefundItemPrice> getPrices() {
		return prices;
	}
	public void setPrices(List<RefundItemPrice> prices) {
		this.prices = prices;
	}
}