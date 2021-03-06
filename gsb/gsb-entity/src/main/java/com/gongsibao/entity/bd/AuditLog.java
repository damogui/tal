package com.gongsibao.entity.bd;

import com.gongsibao.entity.bd.dic.AuditLogStatusType;

import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.settle.OrderProdSettle;
import com.gongsibao.entity.trade.settle.Settle;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.dic.AuditLogType;
import org.netsharp.organization.entity.Employee;

import java.util.Date;

@Table(name = "bd_audit_log")
public class AuditLog extends BaseEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8815735808036860870L;

	@Column(name = "type_id", header = "审核类型序号，type=104，1041产品定价申请审核、1042订单改价申请审核、1043合同申请审核、1044发票申请审核、1045收款申请审核、1046退单申请审核、1047分期申请审核、1048产品改价申请审核、1050订单业绩审核")
	private AuditLogType type = AuditLogType.wu;

	@Column(name = "form_id", header = "关联表外键")
	private Integer formId;

	@Column(name = "status_id", header = "审核状态序号，type=105，1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过、1055排队、1056关闭")
	private AuditLogStatusType status = AuditLogStatusType.TOAUDIT;

	@Column(name = "content", header = "审批说明")
	private String content;

	@Column(name = "remark", header = "审批内容")
	private String remark;

	@Column(name = "level", header = "审核层级")
	private Integer level;

	@Column(name = "max_level", header = "最高审核层级（用于在审核通过时，和当前级别比较下，相等时进行修改主实体状态等操作）")
	private Integer maxLevel;

    @Column(name = "audit_time", header = "审核操作时间")
    private Date auditTime;

    // 订单
    @Reference(foreignKey = "creatorId")
    private Employee employee;

	// 订单（改价审核） 订单  订单业绩和回款业绩共用
    @Reference(foreignKey = "formId", primaryKey = "pkid")
    private SoOrder soOrder;

    /*订单业绩，回款业绩 总金额*/
    @Column(name = "amount", header = "总金额")
    private Integer amount;

    // 支付记录(回款审核)
    @Reference(foreignKey = "formId",primaryKey ="pkid")
    private Pay pay;

    // 合同
    @Reference(foreignKey = "formId", primaryKey = "pkid")
    private Contract contract;

    // 发票
    @Reference(foreignKey = "formId", primaryKey = "pkid")
    private Invoice invoice;

    // 退单记录
    @Reference(foreignKey = "formId", primaryKey = "pkid")
    private Refund fefund;

    @Reference(foreignKey = "formId")
    private Settle settle;
    //付款账套名
    @Exclusive
    private  String booksName;

    // 结转记录
    @Reference(foreignKey = "formId")
    private NOrderCarryover carryover;

    public AuditLogType getType() {
        return type;
    }

    public void setType(AuditLogType type) {
        this.type = type;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Pay getPay() {
        return pay;
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    public Refund getFefund() {
        return fefund;
    }

    public void setFefund(Refund fefund) {
        this.fefund = fefund;
    }

    public NOrderCarryover getCarryover() {
        return carryover;
    }

    public void setCarryover(NOrderCarryover carryover) {
        this.carryover = carryover;
    }

    public AuditLogStatusType getStatus() {
        return status;
    }

    public void setStatus(AuditLogStatusType status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

    public Settle getSettle() {
        return settle;
    }

    public void setSettle(Settle settle) {
        this.settle = settle;
    }

    public String getBooksName() {
        return booksName;
    }

    public void setBooksName(String booksName) {
        this.booksName = booksName;
    }
}