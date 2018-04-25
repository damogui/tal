package com.gongsibao.entity.igirl.ic.ex;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
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

    @Column(name = "owner",header = "所属人")
    private String owner;

    @Column(name = "owner_id",header = "所属人ID")
    private Integer ownerId;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = -1;

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

    @Column(name = "operator",header = "操作者")
    private OperatorType operator;

    @Column(name = "token_img_url", size = 256, header = "二维码")
    private String tokenImgUrl;

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

    public OperatorType getOperator() {
        return operator;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }
}
