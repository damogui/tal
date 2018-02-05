package com.gongsibao.entity.supplier;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.NotifiedType;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.entity.supplier.dict.SupplierType;

@Table(name = "sp_supplier", header = "服务商")
public class Supplier extends Entity {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 9055417222309634407L;

    @Column(name = "status", header = "状态")
    private SupplierStatus status = SupplierStatus.NOTOPEN;

    @Column(name = "name", header = "服务商名称")
    private String name;

    @Column(name = "address", header = "地址")
    private String address;

    @Column(name = "contact", header = "联系人")
    private String contact;

    @Column(name = "mobile_phone", header = "手机号（开户时要校验手机号是否存在）")
    private String mobilePhone;

    @Column(name = "admin_id", header = "管理员帐号Id")
    private Integer adminId = 0;

    @Reference(foreignKey = "adminId", header = "管理员帐号")
    private Employee admin;

    @Column(name = "type", header = "类型：1自营，2平台;3不限")
    private SupplierType type;

    @Column(name = "customer_max_count", header = "客户池数量")
    private Integer customerMaxCount;

    @Column(name = "is_push_report", header = "是否推送报表  0否, 1是")
    private Boolean pushReport = true;

    @Column(name = "message_notified_type", header = "消息通知类型 ")
    private NotifiedType messageNotifiedType = NotifiedType.Wx;

    @Column(name = "is_auto_assign", header = "是否推自动分配  0否, 1是")
    private Boolean autoAssign = true;

    @Column(name = "is_auto_release", header = "是否推自动释放  0否, 1是")
    private Boolean autoRelease = true;

    @Column(name = "no_follow_days", header = "未跟进天数释放")
    private Integer noFollowDays;

    @Column(name = "is_enable_depart", header = "是否启用部门  0否, 1是")
    private Boolean enableDepart = true;

    @Column(name = "depart_level", header = "部门级次")
    private Integer departLevel;

    @Column(name = "category_id")
    private Integer categoryId;

    @Reference(foreignKey = "categoryId", header = "服务商分类")
    private SupplierCategory category;

    @Column(name = "open_time")
    private Date openTime;

    @Column(name = "department_count", header = "部门数量")
    private Integer departmentCount = 0;

    @Column(name = "salesman_count", header = "员工数量")
    private Integer salesmanCount = 0;

    @Subs(foreignKey = "supplierId", header = "服务产品", subType = SupplierProduct.class)
    private List<SupplierProduct> serviceProducts;

    @Subs(foreignKey = "supplierId", header = "开通模块", subType = SupplierFunctionModule.class)
    private List<SupplierFunctionModule> modules;

    @Column(name = "bank_name", header = "开户行")
    private String bankName;

    @Column(name = "bank_num", header = "开户行号")
    private String bankNum;

    @Column(name = "customer_type", header = "所属分组类别（1：新客户  2：老客户）")
    private TaskCustomerType customerType;
    @Column(name = "fix_phone", header = "座机")
    private String fixPhone;
    @Column(name = "postcode", header = "邮编")
    private String postcode;
    @Column(name = "fax", header = "传真")
    private String fax;


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public SupplierStatus getStatus() {
        return status;
    }

    public void setStatus(SupplierStatus status) {
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public SupplierCategory getCategory() {
        return category;
    }

    public void setCategory(SupplierCategory category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }

    public Integer getCustomerMaxCount() {
        return customerMaxCount;
    }

    public void setCustomerMaxCount(Integer customerMaxCount) {
        this.customerMaxCount = customerMaxCount;
    }

    public Boolean getPushReport() {
        return pushReport;
    }

    public void setPushReport(Boolean pushReport) {
        this.pushReport = pushReport;
    }

    public NotifiedType getMessageNotifiedType() {
        return messageNotifiedType;
    }

    public void setMessageNotifiedType(NotifiedType messageNotifiedType) {
        this.messageNotifiedType = messageNotifiedType;
    }

    public Boolean getAutoAssign() {
        return autoAssign;
    }

    public void setAutoAssign(Boolean autoAssign) {
        this.autoAssign = autoAssign;
    }

    public Boolean getAutoRelease() {
        return autoRelease;
    }

    public void setAutoRelease(Boolean autoRelease) {
        this.autoRelease = autoRelease;
    }

    public Integer getNoFollowDays() {
        return noFollowDays;
    }

    public void setNoFollowDays(Integer noFollowDays) {
        this.noFollowDays = noFollowDays;
    }

    public Boolean getEnableDepart() {
        return enableDepart;
    }

    public void setEnableDepart(Boolean enableDepart) {
        this.enableDepart = enableDepart;
    }

    public Integer getDepartLevel() {
        return departLevel;
    }

    public void setDepartLevel(Integer departLevel) {
        this.departLevel = departLevel;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Integer getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(Integer departmentCount) {
        this.departmentCount = departmentCount;
    }

    public Integer getSalesmanCount() {
        return salesmanCount;
    }

    public void setSalesmanCount(Integer salesmanCount) {
        this.salesmanCount = salesmanCount;
    }

    public List<SupplierProduct> getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(List<SupplierProduct> serviceProducts) {
        this.serviceProducts = serviceProducts;
    }

    public List<SupplierFunctionModule> getModules() {
        return modules;
    }

    public void setModules(List<SupplierFunctionModule> modules) {
        this.modules = modules;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public TaskCustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(TaskCustomerType customerType) {
        this.customerType = customerType;
    }

    public String getFixPhone() {
        return fixPhone;
    }

    public void setFixPhone(String fixPhone) {
        this.fixPhone = fixPhone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
