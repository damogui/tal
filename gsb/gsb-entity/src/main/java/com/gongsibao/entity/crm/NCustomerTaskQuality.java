package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_task_quality", orderBy = "seq", header = "客户质量")
public class NCustomerTaskQuality extends BizEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -2257727131244260461L;

	@Column(name = "intention_category", header = "质量分类")
	private QualityCategory intentionCategory;

	@Column(name = "next_foolow_date_required", header = "下次跟进时间必填")
	private Boolean nextFoolowDateRequired = false;

	@Column(name = "returned_amount_required", header = "估计回款额必填")
	private Boolean returnedAmountRequired = false;

	@Column(name = "signing_amount_required", header = "估计签单额必填")
	private Boolean signingAmountRequired = false;

	@Column(name = "content_required", header = "内容必填")
	private Boolean contentRequired = false;

	@Column(name = "product_required", header = "意向产品必填写")
	private Boolean productRequired = false;

	@Column(name = "district_required", header = "意向地区必填写")
	private Boolean districtRequired = false;

	@Column(name = "required_info", header = "必填信息", size = 200)
	private String requiredInfo;

	@Column(name = "next_foolow_type", header = "下次跟进方式", size = 200)
	private String nextFoolowType;

	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;

	@Column(name = "department_id", header = "分配服务商部门Id")
	private Integer departmentId;

	@Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;

	@Column(name = "score", header = "分值")
	private Integer score = 0;

	@Column(name = "seq", header = "排序")
	private Integer seq = 0;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public QualityCategory getIntentionCategory() {
		return intentionCategory;
	}

	public void setIntentionCategory(QualityCategory intentionCategory) {
		this.intentionCategory = intentionCategory;
	}

	public Boolean getNextFoolowDateRequired() {
		return nextFoolowDateRequired;
	}

	public void setNextFoolowDateRequired(Boolean nextFoolowDateRequired) {
		this.nextFoolowDateRequired = nextFoolowDateRequired;
	}

	public String getRequiredInfo() {
		return requiredInfo;
	}

	public void setRequiredInfo(String requiredInfo) {
		this.requiredInfo = requiredInfo;
	}

	public String getNextFoolowType() {
		return nextFoolowType;
	}

	public void setNextFoolowType(String nextFoolowType) {
		this.nextFoolowType = nextFoolowType;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getReturnedAmountRequired() {
		return returnedAmountRequired;
	}

	public void setReturnedAmountRequired(Boolean returnedAmountRequired) {
		this.returnedAmountRequired = returnedAmountRequired;
	}

	public Boolean getSigningAmountRequired() {
		return signingAmountRequired;
	}

	public void setSigningAmountRequired(Boolean signingAmountRequired) {
		this.signingAmountRequired = signingAmountRequired;
	}

	public Boolean getContentRequired() {
		return contentRequired;
	}

	public void setContentRequired(Boolean contentRequired) {
		this.contentRequired = contentRequired;
	}

	public Boolean getProductRequired() {
		return productRequired;
	}

	public void setProductRequired(Boolean productRequired) {
		this.productRequired = productRequired;
	}

	public Boolean getDistrictRequired() {
		return districtRequired;
	}

	public void setDistrictRequired(Boolean districtRequired) {
		this.districtRequired = districtRequired;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
