package com.gongsibao.entity.trade;


import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_contract",header="合同")
public class Contract extends BaseEntity {

	private static final long serialVersionUID = -802740307282932651L;
	@Column(name="order_id",header="订单序号")
    private Integer orderId;
	
    @Column(name="sgining_time",header="签约日期")
    private Date sginingTime;
    
    @Column(name="sgining_company_id",header="签单公司，type=316，3161汉唐信通（北京）咨询股份有限公司、3162汉唐信通（北京）科技有限公司")
    private Integer sginingCompanyId;
    
    @Column(name="is_urgeney",header="是否加急")
    private Integer isUrgeney;
    
    @Column(name="sgining_user_id",header="签单业务员序号")
    private Integer sginingUserId;
    
    @Column(name="customer_id",header="客户序号")
    private Integer customerId;
    
    @Column(name="real_amount",header="实际合同额")
    private Integer realAmount;
    
    @Column(name="has_data_fee",header="是否有材料撰写情况")
    private Integer hasDataFee;

    @Column(name="data_fee_count_type_id",header="材料撰写次数类型序号，type=317，3171无、3172首期一次、3173末期一次、3174首期一次末期一次")
    private Integer dataFeeCountTypeId;
    
    @Column(name="first_payment",header="首期付款")
    private Integer firstPayment;
    
    @Column(name="final_payment",header="末期付款")
    private Integer finalPayment;
    
    @Column(name="has_liquidated_damages",header="是否有违约金")
    private Integer hasLiquidatedDamages;
    
    @Column(name="has_breach",header="是否有违约责任事项")
    private Integer hasBreach;
    
    @Column(name="liquidated_damages",header="违约金额")
    private Integer liquidatedDamages;
    
    @Column(name="breach_info",header="违约责任")
    private String breachInfo;
    
    @Column(name="file_id",header="附件序号")
    private Integer fileId;
    
    @Column(name="audit_status_id",header="审核状态序号，type=105，1051待审核、1052通过、1053不通过")
    private Integer auditStatusId;
    
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";

    @Column(name="remark",header="说明")
    private String remark;
    
    @Column(name="license_no",header="营业执照号")
    private String licenseNo;
    
    @Column(name="contract_title",header="合同标题")
    private String contractTitle;
    
    @Column(name="company_name",header="公司名称")
    private String companyName;
    
    @Column(name="contract_type",header="合同类型1：个人；2：企业")
    private Integer contractType;
    
    @Column(name="contract_sign",header="合同签署状态0：平台没签署；1：平台签署")
    private Integer contractSign;
    
    @Column(name="is_electronics",header="是否电子合同0：纸质；1：电子")
    private Integer isElectronics;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public Date getSginingTime() {
        return sginingTime;
    }
    public void setSginingTime(Date sginingTime) {
        this.sginingTime = sginingTime;
    }
    public Integer getSginingCompanyId() {
        return sginingCompanyId;
    }
    public void setSginingCompanyId(Integer sginingCompanyId) {
        this.sginingCompanyId = sginingCompanyId;
    }
    public Integer getIsUrgeney() {
        return isUrgeney;
    }
    public void setIsUrgeney(Integer isUrgeney) {
        this.isUrgeney = isUrgeney;
    }
    public Integer getSginingUserId() {
        return sginingUserId;
    }
    public void setSginingUserId(Integer sginingUserId) {
        this.sginingUserId = sginingUserId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getRealAmount() {
        return realAmount;
    }
    public void setRealAmount(Integer realAmount) {
        this.realAmount = realAmount;
    }
    public Integer getHasDataFee() {
        return hasDataFee;
    }
    public void setHasDataFee(Integer hasDataFee) {
        this.hasDataFee = hasDataFee;
    }
    public Integer getDataFeeCountTypeId() {
        return dataFeeCountTypeId;
    }
    public void setDataFeeCountTypeId(Integer dataFeeCountTypeId) {
        this.dataFeeCountTypeId = dataFeeCountTypeId;
    }
    public Integer getFirstPayment() {
        return firstPayment;
    }
    public void setFirstPayment(Integer firstPayment) {
        this.firstPayment = firstPayment;
    }
    public Integer getFinalPayment() {
        return finalPayment;
    }
    public void setFinalPayment(Integer finalPayment) {
        this.finalPayment = finalPayment;
    }
    public Integer getHasLiquidatedDamages() {
        return hasLiquidatedDamages;
    }
    public void setHasLiquidatedDamages(Integer hasLiquidatedDamages) {
        this.hasLiquidatedDamages = hasLiquidatedDamages;
    }
    public Integer getHasBreach() {
        return hasBreach;
    }
    public void setHasBreach(Integer hasBreach) {
        this.hasBreach = hasBreach;
    }
    public Integer getLiquidatedDamages() {
        return liquidatedDamages;
    }
    public void setLiquidatedDamages(Integer liquidatedDamages) {
        this.liquidatedDamages = liquidatedDamages;
    }
    public String getBreachInfo() {
        return breachInfo;
    }
    public void setBreachInfo(String breachInfo) {
        this.breachInfo = breachInfo;
    }
    public Integer getFileId() {
        return fileId;
    }
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
    public Integer getAuditStatusId() {
        return auditStatusId;
    }
    public void setAuditStatusId(Integer auditStatusId) {
        this.auditStatusId = auditStatusId;
    }

    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }
    public String getContractTitle() {
        return contractTitle;
    }
    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getContractType() {
        return contractType;
    }
    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }
    public Integer getContractSign() {
        return contractSign;
    }
    public void setContractSign(Integer contractSign) {
        this.contractSign = contractSign;
    }
    public Integer getIsElectronics() {
        return isElectronics;
    }
    public void setIsElectronics(Integer isElectronics) {
        this.isElectronics = isElectronics;
    }
}