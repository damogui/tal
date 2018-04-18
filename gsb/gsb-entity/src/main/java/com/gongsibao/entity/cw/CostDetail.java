package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Organization;

import com.gongsibao.entity.cw.dict.FinanceDict;

/**
 * 
*  费用明细
* 项目名称：gsb-entity   
* 类名称：CostDetail   
* 类描述：（报销明细，借款明细，借款明细）     
* 创建人：angang
* 创建时间：2018年3月21日 下午4:45:30   
* @version
 */

@Table(name="cw_cost_detail",header="费用明细")
public class CostDetail extends BizEntity{

	/**  
	* @Fields field:field:{todo}  
	*/ 				
	private static final long serialVersionUID = 8609911943520443685L;

	@Column(name="form_id",header="单据id")
	private Integer formId ;
	
	@Column(name="form_type",header="单据类型")
	private FinanceDict.FormType formType  ;
	
	@Column(name="cost_type_id",header="费用类型id")
	private Integer costTypeId ;
	
	@Column(name="cost_type_name",header="费用类型名称")
	private String costTypeName;
	
	@Reference(foreignKey = "costTypeId")
	private CostType costType ;
	
	@Column(name="invoice_type",header="发票类型")
	private FinanceDict.InvoiceType invoiceType = FinanceDict.InvoiceType.GI ;
	
	@Column(name="detail_money",header="费用金额")
	private Integer detailMoney;
	
	@Column(name="tax_rate",header="费率")
	private FinanceDict.TaxRateType taxRate =FinanceDict.TaxRateType.TaxRate_1 ;
	
	@Column(name="detail_taxation",header="税费金额")
	private Integer detailTaxation;
	
	@Column(name = "organization_id", header = "费用归属部门（组织机构id）")
	private Integer organizationId;
	
	@Column(name = "pathName", header = "部门名称（冗余字段）")
	private String pathName;
	
	
	@Reference(foreignKey = "organizationId")
	private Organization organization;
	
	
	@Column(name = "reason_type", header = "费用事由")
	private Integer reasonType ;

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}



	public Integer getCostTypeId() {
		return costTypeId;
	}

	public void setCostTypeId(Integer costTypeId) {
		this.costTypeId = costTypeId;
	}

	public CostType getCostType() {
		return costType;
	}

	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	public Integer getDetailMoney() {
		return detailMoney;
	}

	public void setDetailMoney(Integer detailMoney) {
		this.detailMoney = detailMoney;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getReasonType() {
		return reasonType;
	}

	public void setReasonType(Integer reasonType) {
		this.reasonType = reasonType;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public FinanceDict.FormType getFormType() {
		return formType;
	}

	public void setFormType(FinanceDict.FormType formType) {
		this.formType = formType;
	}

	public FinanceDict.TaxRateType getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(FinanceDict.TaxRateType taxRate) {
		this.taxRate = taxRate;
	}

	public Integer getDetailTaxation() {
		return detailTaxation;
	}

	public void setDetailTaxation(Integer detailTaxation) {
		this.detailTaxation = detailTaxation;
	}

	public FinanceDict.InvoiceType getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(FinanceDict.InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getCostTypeName() {
		return costTypeName;
	}

	public void setCostTypeName(String costTypeName) {
		this.costTypeName = costTypeName;
	}

	

	
	
	
}
