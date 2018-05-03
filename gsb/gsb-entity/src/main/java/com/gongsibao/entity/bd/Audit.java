package com.gongsibao.entity.bd;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.settle.Settle;

@Table(name = "bd_audit")
public class Audit extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7954101867833371703L;
	
	@Column(name = "type_id", header = "审核类型序号，type=104，1041产品定价申请审核、1042订单改价申请审核、1043合同申请审核、1044发票申请审核、1045收款申请审核、1046退单申请审核、1047分期申请审核、1048产品改价申请审核、1050订单业绩审核")
	private AuditLogType type = AuditLogType.wu;

	@Column(name = "form_id", header = "关联表外键")
	private Integer formId;

	@Column(name = "status_id", header = "审核状态序号，type=105，1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过、1055排队、1056关闭")
	private AuditLogStatusType status = AuditLogStatusType.TOAUDIT;
	
	@Column(name = "max_level", header = "最高审核层级（用于在审核通过时，和当前级别比较下，相等时进行修改主实体状态等操作）")
	private Integer maxLevel;
	
	@Subs(foreignKey = "auditId", header = "审核日志", subType = AuditLog.class)
	private List<AuditLog> logs;

    @Reference(foreignKey = "formId", primaryKey = "pkid")
    private SoOrder soOrder;
    
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

	public AuditLogStatusType getStatus() {
		return status;
	}

	public void setStatus(AuditLogStatusType status) {
		this.status = status;
	}

	public Integer getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}

	public List<AuditLog> getLogs() {
		return logs;
	}

	public void setLogs(List<AuditLog> logs) {
		this.logs = logs;
	}

	public SoOrder getSoOrder() {
		return soOrder;
	}

	public void setSoOrder(SoOrder soOrder) {
		this.soOrder = soOrder;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
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

	public Refund getFefund() {
		return fefund;
	}

	public void setFefund(Refund fefund) {
		this.fefund = fefund;
	}

	public Settle getSettle() {
		return settle;
	}

	public void setSettle(Settle settle) {
		this.settle = settle;
	}

	public NOrderCarryover getCarryover() {
		return carryover;
	}

	public void setCarryover(NOrderCarryover carryover) {
		this.carryover = carryover;
	}
}
