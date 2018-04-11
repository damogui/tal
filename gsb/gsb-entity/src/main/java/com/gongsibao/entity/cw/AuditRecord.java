package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.cw.dict.FinanceDict;

/**
 * 
*  报销审核记录表  
* 项目名称：gsb-entity   
* 类名称：CheckRecord   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月21日 下午5:31:46   
* @version
 */
@Table(name="cw_audit_record",header="审核记录表")
public class AuditRecord extends BizEntity{
	
	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 1396819395194478910L;

	@Column(name="form_id",header="单据id")
	private Integer formId ;
	
	@Column(name="form_type",header="单据类型")
	private FinanceDict.FormType formType = FinanceDict.FormType.JKD;
	
	@Column(name="audit_user_id",header="审核人id")
	private Integer auditUserId;
	
	@Column(name="apply_user_id",header="申请人id")
	private Integer applyUserId;
	
	@Column(name="apply_department_id",header="申请人部门id")
	private Integer applyDepartmentId;
	
	@Reference(foreignKey = "auditUserId" ,header="审核人信息" )
	private Employee employee;

	@Column(name = "content", header = "审核内容")
	private String content;
	
	@Column(name = "status", header = "状态 1:待审核 ，2：通过 ，3：驳回")
	private FinanceDict.AuditDetailStatus status = FinanceDict.AuditDetailStatus.WAIT;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public FinanceDict.FormType getFormType() {
		return formType;
	}

	public void setFormType(FinanceDict.FormType formType) {
		this.formType = formType;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FinanceDict.AuditDetailStatus getStatus() {
		return status;
	}

	public void setStatus(FinanceDict.AuditDetailStatus status) {
		this.status = status;
	}

	public Integer getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Integer getApplyDepartmentId() {
		return applyDepartmentId;
	}

	public void setApplyDepartmentId(Integer applyDepartmentId) {
		this.applyDepartmentId = applyDepartmentId;
	}
	
}
