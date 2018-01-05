package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.SoOrder;

@Table(name = "bd_audit_log")
public class AuditLog extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -8815735808036860870L;
	@Column(name = "type_id", header = "审核类型序号，type=104，1041产品定价申请审核、1042订单改价申请审核、1043合同申请审核、1044发票申请审核、1045收款申请审核、1046退单申请审核、1047分期申请审核、1048产品改价申请审核")
	private AuditLogType typeId = AuditLogType.wu;
	@Column(name = "form_id", header = "关联表外键")
	private Integer formId;
	@Column(name = "status_id", header = "审核状态序号，type=105，1051 待审核、1052 审核中、1053 驳回审核、1054 审核通过、1055排队、1056关闭")
	private AuditLogStatusType statusId = AuditLogStatusType.TOAUDIT;
	@Column(name = "content", header = "审批内容")
	private String content;
	@Column(name = "remark", header = "说明")
	private String remark;
	@Column(name = "level", header = "审核层级")
	private Integer level;

	// 订单
	@Reference(foreignKey = "formId")
	private SoOrder soOrder;

	// 合同
	@Reference(foreignKey = "formId")
	private Contract contract;

	public AuditLogType getTypeId() {
		return typeId;
	}

	public void setTypeId(AuditLogType typeId) {
		this.typeId = typeId;
	}

	public AuditLogStatusType getStatusId() {
		return statusId;
	}

	public void setStatusId(AuditLogStatusType statusId) {
		this.statusId = statusId;
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
	
	
}