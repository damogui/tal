package com.gongsibao.entity.igirl.ic.ex;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.CustomerSource;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.igirl.ic.ex.dict.BusinessType;
import com.gongsibao.entity.igirl.ic.ex.dict.OperatorType;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "ic_ex_register_case",header = "公司注册信息登记表")
public class IcExRegisterCase extends Entity {
    @Column(name = "customer_id",header = "客户ID")
    private Integer customerId;

    @JsonIgnore
    @Reference(foreignKey = "customerId",header = "客户")
    private NCustomer customer;

    @Column(name = "customer_mobile",header = "客户电话")
    private String customerMobile;

    @Column(name = "customer_name",header = "客户姓名")
    private String customerName;

    @Column(name = "owner",header = "后期")
    private String owner;

    @Column(name = "owner_id",header = "后期ID")
    private Integer ownerId;

    @Column(name = "operator",header = "业务")
    private String operator;

    @Column(name = "operator_id",header = "业务ID")
    private Integer operatorId;

    @Column(name = "collector",header = "材料")
    private String collector;

    @Column(name = "collector_id",header = "材料ID")
    private Integer collectorId;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;

    @Column(name = "source_id",header = "客户来源")
    private CustomerSource source = CustomerSource.CUSTOMER_SOURCE_4181;

    @Column(name = "consult_way", header = "421 CRM咨询途径: 4211 400电话、 4212 在线客服、 4213企业QQ、 4214 PC官网、 4215 H5官网、 4216 手机APP")
    private ConsultWay consultWay=ConsultWay.CONSULT_WAY_4211;

    @JsonIgnore
    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "department_id",header = "部门Id")
    private Integer departmentId;

    @Reference(foreignKey = "departmentId",header = "部门")
    private SupplierDepartment department;

    @Column(name = "approval_name",header = "核准公司名称")
    private String approvalName;

    @Column(name = "approval_type",header = "审核状态")
    private ApprovalType approvalType = ApprovalType.WAIT;

    @Column(name = "corp_reg_statue",header = "工商业务状态")
    private CorpRegStatue corpRegStatue = CorpRegStatue.UNCOMMITTED;

    @Column(name = "operator_type",header = "填报账户")
    private OperatorType operatorType = OperatorType.LEI_JUAN;

    @Column(name = "business_type",header = "业务类型")
    private BusinessType businessType = BusinessType.ESTABLISHMENT;

    @Column(name = "token_img_url", size = 512, header = "二维码")
    private String tokenImgUrl;

    @Column(name = "code", size = 256, header = "案件编码")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTokenImgUrl() {
        return tokenImgUrl;
    }

    public void setTokenImgUrl(String tokenImgUrl) {
        this.tokenImgUrl = tokenImgUrl;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public NCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(NCustomer customer) {
        this.customer = customer;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public ApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    public CorpRegStatue getCorpRegStatue() {
        return corpRegStatue;
    }

    public void setCorpRegStatue(CorpRegStatue corpRegStatue) {
        this.corpRegStatue = corpRegStatue;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public Integer getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(Integer collectorId) {
        this.collectorId = collectorId;
    }

    public OperatorType getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public CustomerSource getSource() {
        return source;
    }

    public void setSource(CustomerSource source) {
        this.source = source;
    }

    public ConsultWay getConsultWay() {
        return consultWay;
    }

    public void setConsultWay(ConsultWay consultWay) {
        this.consultWay = consultWay;
    }
}
