package com.gongsibao.entity.igirl.ic.ex.baseinfo;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.CustomerSource;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import java.util.List;

@Table(name = "ic_ex_base_info",header = "核名信息")
public class ExcelBaseInfo extends Entity{
    @Column(name = "customer_id",header = "客户ID")
    private Integer customerId;

    @JsonIgnore
    @Reference(foreignKey = "customerId",header = "客户")
    private NCustomer customer;

    @Column(name = "customer_mobile",header = "客户电话")
    private String customerMobile;

    @Column(name = "customer_name",header = "客户姓名")
    private String customerName;

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

    @JsonIgnore
    @Reference(foreignKey = "departmentId",header = "部门")
    private SupplierDepartment department;

    private String companyName;

    @Column(name = "capital",header = "注册资金(万元)")
    private Integer capital;

    @Column(name = "period",header = "经营期限")
    private Integer period = 20;

    @Column(name = "pay_date",header = "注册资本实缴日期")
    private Integer payDate = 20;

    @Subs(foreignKey = "excelBaseInfoId",header = "备选公司名称",subType = CompanyName.class)
    private List<CompanyName> companyNames;

    @Subs(foreignKey = "excelBaseInfoId",header = "股东信息",subType = Shareholder.class)
    private List<Shareholder> shareholders;

    @Subs(foreignKey = "excelBaseInfoId",header = "成员信息",subType = Member.class)
    private List<Member> members;

    @Column(name = "ex_register_case_id",header = "工商注册申请单Id")
    private Integer exRegisterCaseId;

    @JsonIgnore
    @Reference(foreignKey = "exRegisterCaseId",header = "工商注册申请单")
    private IcExRegisterCase exRegisterCase;

    @Column(name = "state",header = "是否进行状态抓取")
    private Boolean state = false;

    public List<CompanyName> getCompanyNames() {
        return companyNames;
    }

    public void setCompanyNames(List<CompanyName> companyNames) {
        this.companyNames = companyNames;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPayDate() {
        return payDate;
    }

    public void setPayDate(Integer payDate) {
        this.payDate = payDate;
    }

    public List<Shareholder> getShareholders() {
        return shareholders;
    }

    public void setShareholders(List<Shareholder> shareholders) {
        this.shareholders = shareholders;
    }

    public Integer getExRegisterCaseId() {
        return exRegisterCaseId;
    }

    public void setExRegisterCaseId(Integer exRegisterCaseId) {
        this.exRegisterCaseId = exRegisterCaseId;
    }

    public IcExRegisterCase getExRegisterCase() {
        return exRegisterCase;
    }

    public void setExRegisterCase(IcExRegisterCase exRegisterCase) {
        this.exRegisterCase = exRegisterCase;
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

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
