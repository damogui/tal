package com.gongsibao.entity.trade;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_contract")
public class Contract extends BaseEntity {

	private static final long serialVersionUID = -802740307282932651L;
	@Column(name="order_id",header="OrderId")
    private Integer orderId;
    @Column(name="sgining_time",header="SginingTime")
    private Date sginingTime;
    @Column(name="sgining_company_id",header="SginingCompanyId")
    private Integer sginingCompanyId;
    @Column(name="is_urgeney",header="IsUrgeney")
    private Integer isUrgeney;
    @Column(name="sgining_user_id",header="SginingUserId")
    private Integer sginingUserId;
    @Column(name="customer_id",header="CustomerId")
    private Integer customerId;
    @Column(name="real_amount",header="RealAmount")
    private Integer realAmount;
    @Column(name="has_data_fee",header="HasDataFee")
    private Integer hasDataFee;
    @Column(name="data_fee_count_type_id",header="DataFeeCountTypeId")
    private Integer dataFeeCountTypeId;
    @Column(name="first_payment",header="FirstPayment")
    private Integer firstPayment;
    @Column(name="final_payment",header="FinalPayment")
    private Integer finalPayment;
    @Column(name="has_liquidated_damages",header="HasLiquidatedDamages")
    private Integer hasLiquidatedDamages;
    @Column(name="has_breach",header="HasBreach")
    private Integer hasBreach;
    @Column(name="liquidated_damages",header="LiquidatedDamages")
    private Integer liquidatedDamages;
    @Column(name="breach_info",header="BreachInfo")
    private String breachInfo;
    @Column(name="file_id",header="FileId")
    private Integer fileId;
    @Column(name="audit_status_id",header="AuditStatusId")
    private Integer auditStatusId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;
    @Column(name="is_bbk",header="IsBbk")
    private String isBbk="0";
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(header="remark")
    private String remark;
    @Column(name="license_no",header="LicenseNo")
    private String licenseNo;
    @Column(name="contract_title",header="ContractTitle")
    private String contractTitle;
    @Column(name="company_name",header="CompanyName")
    private String companyName;
    @Column(name="contract_type",header="ContractType")
    private Integer contractType;
    @Column(name="contract_sign",header="ContractSign")
    private Integer contractSign;
    @Column(name="is_electronics",header="IsElectronics")
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
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public String getIsBbk() {
        return isBbk;
    }
    public void setIsBbk(String isBbk) {
        this.isBbk = isBbk;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
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