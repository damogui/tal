package com.gongsibao.entity.crm;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.CustomerFollowStatus;
import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

@Table(name = "n_crm_customer_task", header = "客户任务")
public class NCustomerTask extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 4684375504055933956L;

	@JsonIgnore
	@Reference(foreignKey = "customerId", header = "客户")
	private NCustomer customer;

	@Column(name = "customer_id", header = "客户")
	private Integer customerId = 0;

	@Column(name = "name", size = 200, header = "名称")
	private String name;
	
	@Column(name = "supplier_id", header = "分配服务商Id")
	private Integer supplierId;

	@JsonIgnore
	@Reference(foreignKey = "supplierId", header = "分配服务商")
	private Supplier supplier;
	
	@Column(name = "department_id", header = "分配服务商部门Id")
	private Integer departmentId;

	@JsonIgnore
	@Reference(foreignKey = "departmentId", header = "分配服务商部门")
	private SupplierDepartment department;
	
	@Column(name = "last_allocation_time", header = "最后分配时间")
	private Date lastAllocationTime;
	
	@Column(name = "last_allocation_user_id", header = "最后分配人Id")
	private Integer lastAllocationUserId = 0;
	
	@JsonIgnore
	@Reference(foreignKey = "lastAllocationUserId", header = "最后分配人")
	private Employee lastAllocationUser;
	
	@Column(name = "foolow_status", header = "跟进状态")
	private CustomerFollowStatus foolowStatus;

	@Column(name = "intention_category", header = "质量分类")
	private QualityCategory intentionCategory;
	
	@Column(name = "quality_Id", header = "客户质量id")
	private Integer qualityId = 0;
	
	@Reference(foreignKey = "qualityId", header = "质量")
	private NCustomerTaskQuality quality;
	
	@Column(name = "last_follow_time", header = "最近跟进时间")
	private Date lastFollowTime;
	
	@Column(name = "last_foolow_user_id", header = "最后跟进人Id")
	private Integer lastFoolowUserId = 0;
	
	@JsonIgnore
	@Reference(foreignKey = "lastFoolowUserId", header = "最后跟进人")
	private Employee lastFoolowUser;
	
	@Column(name = "last_content", size = 1000, header = "最后跟进内容")
	private String lastContent;
	
	@Column(name = "next_foolow_time", header = "下次跟进时间")
	private Date nextFoolowTime;
	
	@Column(name = "old", header = "是否老客户")
	private Boolean old = false;
	
    @Column(name = "memoto",header="备注", size = 1000)
    private String memoto;
	
	@Subs(foreignKey = "taskId", header = "意向产品", subType = NCustomerProduct.class)
	private List<NCustomerProduct> products;

	@Subs(foreignKey = "taskId", header = "沟通日志", subType = NCustomerTaskFoolow.class)
	private List<NCustomerTaskFoolow> follows;

	@Subs(foreignKey = "customerId", header = "通知日志", subType = NCustomerTaskNotify.class)
	private List<NCustomerTaskNotify> notifys;

	@Subs(foreignKey = "customerId", header = "流转日志", subType = NCustomerChange.class)
	private List<NCustomerChange> changes;

	public NCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(NCustomer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public QualityCategory getIntentionCategory() {
		return intentionCategory;
	}

	public void setIntentionCategory(QualityCategory intentionCategory) {
		this.intentionCategory = intentionCategory;
	}

	public NCustomerTaskQuality getQuality() {
		return quality;
	}

	public void setQuality(NCustomerTaskQuality quality) {
		this.quality = quality;
	}

	public Boolean getOld() {
		return old;
	}

	public void setOld(Boolean old) {
		this.old = old;
	}

	public String getMemoto() {
		return memoto;
	}

	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}

	public List<NCustomerProduct> getProducts() {
		return products;
	}

	public void setProducts(List<NCustomerProduct> products) {
		this.products = products;
	}

	public List<NCustomerTaskFoolow> getFollows() {
		return follows;
	}

	public void setFollows(List<NCustomerTaskFoolow> follows) {
		this.follows = follows;
	}

	public List<NCustomerTaskNotify> getNotifys() {
		return notifys;
	}

	public void setNotifys(List<NCustomerTaskNotify> notifys) {
		this.notifys = notifys;
	}

	public List<NCustomerChange> getChanges() {
		return changes;
	}

	public void setChanges(List<NCustomerChange> changes) {
		this.changes = changes;
	}

	public CustomerFollowStatus getFoolowStatus() {
		return foolowStatus;
	}

	public void setFoolowStatus(CustomerFollowStatus foolowStatus) {
		this.foolowStatus = foolowStatus;
	}

	public Date getLastFollowTime() {
		return lastFollowTime;
	}

	public void setLastFollowTime(Date lastFollowTime) {
		this.lastFollowTime = lastFollowTime;
	}

	public Integer getLastFoolowUserId() {
		return lastFoolowUserId;
	}

	public void setLastFoolowUserId(Integer lastFoolowUserId) {
		this.lastFoolowUserId = lastFoolowUserId;
	}

	public Employee getLastFoolowUser() {
		return lastFoolowUser;
	}

	public void setLastFoolowUser(Employee lastFoolowUser) {
		this.lastFoolowUser = lastFoolowUser;
	}

	public String getLastContent() {
		return lastContent;
	}

	public void setLastContent(String lastContent) {
		this.lastContent = lastContent;
	}

	public Date getNextFoolowTime() {
		return nextFoolowTime;
	}

	public void setNextFoolowTime(Date nextFoolowTime) {
		this.nextFoolowTime = nextFoolowTime;
	}

	public Date getLastAllocationTime() {
		return lastAllocationTime;
	}

	public void setLastAllocationTime(Date lastAllocationTime) {
		this.lastAllocationTime = lastAllocationTime;
	}

	public Integer getLastAllocationUserId() {
		return lastAllocationUserId;
	}

	public void setLastAllocationUserId(Integer lastAllocationUserId) {
		this.lastAllocationUserId = lastAllocationUserId;
	}

	public Employee getLastAllocationUser() {
		return lastAllocationUser;
	}

	public void setLastAllocationUser(Employee lastAllocationUser) {
		this.lastAllocationUser = lastAllocationUser;
	}
}
