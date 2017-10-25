package com.gongsibao.entity.ma;

import java.math.BigDecimal;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.CompanyNature;
import com.gongsibao.entity.ma.dic.CompanyType;
import com.gongsibao.entity.ma.dic.DemandSoldOutState;

public abstract class Demand  extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4219586172995646875L;
	
    @Column(name = "code",header="编码")
    private String code;
    

	//--------------------
	// 基本信息
	//--------------------

	@Column(name="name",header="联系人")
	private String name;
	
	@Column(name="mobile",header="电话")
	private String mobile;
	
	@Column(name="weixin",header="微信")
	private String weixin;
	
	@Column(name="qq",header="QQ")
	private String qq;
	
	@Column(name="email",header="Email")
	private String email;
	
	@Column(name="company_type",header="公司类型")
	private CompanyType  companyType;
	
	@Column(name="company_nature",header="公司性质")
	private CompanyNature  companyNature;

	@Column(name="has_bank_account",header="银行账户")
	private Boolean hasBankAccount = false;
	
	@Column(name="tax_status",header="报税状态（正常/非正常）")
	private Boolean taxStatus = false;
	
	//国地税报道
	@Column(name="tax_register",header="国地税报到")
	private Boolean taxRegister = false;

	
	@Subs(foreignKey="demandId",header="无形资产",subType=DemandIntangibleAssets.class)
	private List<DemandIntangibleAssets> intangibleAssetss;
	
	@Subs(foreignKey="demandId",header="固定资产",subType=DemandFixedAssets.class)
	private List<DemandFixedAssets> fixedAssetss;

	@Subs(foreignKey="demandId",header="企业资质",subType=DemandQualificationDetail.class)
	private List<DemandQualificationDetail> qualificationDetails;
	
	@Column(name="other_info", size=500, header="其它")
	private String otherInfo;
	
	@Column(name="remark", size=1000, header="备注")
	private String remark;
	
	
	//--------------------
	// 交易信息
	//--------------------	
	@Column(name="expected_price", header="期望价格")
	private BigDecimal expectedPrice;
	
    @Column(name = "valuation_price",header="评估价格")
    private BigDecimal valuationPrice;

    @Column(name = "ha_entrust",header="委托出售协议")
    private Boolean hasEntrust = false;
	
    @Column(name = "ha_deposit",header="出售订金")
    private Boolean hasDeposit = false;
	
    @Column(name = "deposit_amount",header="订金金额")
    private BigDecimal depositAmount;
    
	@Column(name="sold_out_state",header="上下架状态")
	private DemandSoldOutState  soldOutState = DemandSoldOutState.SOLDOUT;
	

	public DemandSoldOutState getSoldOutState() {
		return soldOutState;
	}

	public void setSoldOutState(DemandSoldOutState soldOutState) {
		this.soldOutState = soldOutState;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public CompanyNature getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(CompanyNature companyNature) {
		this.companyNature = companyNature;
	}

	public Boolean getHasBankAccount() {
		return hasBankAccount;
	}

	public void setHasBankAccount(Boolean hasBankAccount) {
		this.hasBankAccount = hasBankAccount;
	}

	public Boolean getTaxStatus() {
		return taxStatus;
	}

	public void setTaxStatus(Boolean taxStatus) {
		this.taxStatus = taxStatus;
	}

	public Boolean getTaxRegister() {
		return taxRegister;
	}

	public void setTaxRegister(Boolean taxRegister) {
		this.taxRegister = taxRegister;
	}

	public List<DemandIntangibleAssets> getIntangibleAssetss() {
		return intangibleAssetss;
	}

	public void setIntangibleAssetss(List<DemandIntangibleAssets> intangibleAssetss) {
		this.intangibleAssetss = intangibleAssetss;
	}

	public List<DemandFixedAssets> getFixedAssetss() {
		return fixedAssetss;
	}

	public void setFixedAssetss(List<DemandFixedAssets> fixedAssetss) {
		this.fixedAssetss = fixedAssetss;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getExpectedPrice() {
		return expectedPrice;
	}

	public void setExpectedPrice(BigDecimal expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public BigDecimal getValuationPrice() {
		return valuationPrice;
	}

	public void setValuationPrice(BigDecimal valuationPrice) {
		this.valuationPrice = valuationPrice;
	}

	public Boolean getHasEntrust() {
		return hasEntrust;
	}

	public void setHasEntrust(Boolean hasEntrust) {
		this.hasEntrust = hasEntrust;
	}

	public Boolean getHasDeposit() {
		return hasDeposit;
	}

	public void setHasDeposit(Boolean hasDeposit) {
		this.hasDeposit = hasDeposit;
	}

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}

	public List<DemandQualificationDetail> getQualificationDetails() {
		return qualificationDetails;
	}

	public void setQualificationDetails(
			List<DemandQualificationDetail> qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}
}
