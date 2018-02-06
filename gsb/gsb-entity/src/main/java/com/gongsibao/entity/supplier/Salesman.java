package com.gongsibao.entity.supplier;

import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.dict.SupplierType;

@Table(name = "sp_salesman", header = "员工")
public class Salesman extends Entity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 2235508906235938620L;

	@Column(name = "employee_id", header = "员工Id")
	private Integer employeeId = 0;

	@Reference(foreignKey = "employeeId", header = "员工")
	private Employee employee;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId = 0;

	@Reference(foreignKey = "departmentId", header = "部门")
	private SupplierDepartment department;

	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId = 0;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;

	@Column(name = "receiving", header = "是否接单")
	private Boolean receiving = true;

	@Column(name = "disabled", header = "停用")
	private Boolean disabled = false;
	
	@Column(name="login_name",header="帐号")
	private String loginName;
	
	@Column(name = "name", header = "姓名")
	private String name;

	@Column(name="email",header="电子邮件")
    private String email;
	
	@Column(name = "mobile", header = "手机号")
	private String mobile;

	@Column(name = "entry_date", header = "入职日期")
	private Date entryDate;

	@Column(name = "quit_date", header = "离职日期")
	private Date quitDate;
	
	@Column(name = "is_leader", header = "是否主管")
	private Boolean isLeader;
	
	@Subs(subType = SalesmanRole.class, foreignKey = "salesmanId", header = "用户角色")
	private List<SalesmanRole> roles;
	
	@Subs(subType = SalesmanProduct.class, foreignKey = "salesmanId", header = "服务范围")
	private List<SalesmanProduct> products;

    @Column(name = "customer_type", header = "所属分组类别（1：新客户  2：老客户）")
    private TaskCustomerType customerType;
    
    @Column(name = "type", header = "类型：1自营，2平台;3不限")
	private SupplierType type = SupplierType.UNLIMITED;
    
    @Column(name = "day_max", header = "日分配上限")
    private Integer dayMax;
    
    @Column(name = "week_max", header = "周分配上限")
    private Integer weekMax;
    
    @Column(name = "xab_max", header = "XAB类上限")
    private Integer xabMax;
    
    public Boolean getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}

	//当日已分配的任务数
    @Exclusive
    private Integer dayAllocatedCount;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public List<SalesmanRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SalesmanRole> roles) {
		this.roles = roles;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getReceiving() {
		return receiving;
	}

	public void setReceiving(Boolean receiving) {
		this.receiving = receiving;
	}

	public List<SalesmanProduct> getProducts() {
		return products;
	}

	public void setProducts(List<SalesmanProduct> products) {
		this.products = products;
	}


    public TaskCustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(TaskCustomerType customerType) {
		this.customerType = customerType;
	}

	public Integer getDayMax() {
		return dayMax;
	}

	public void setDayMax(Integer dayMax) {
		this.dayMax = dayMax;
	}

	public Integer getWeekMax() {
		return weekMax;
	}

	public void setWeekMax(Integer weekMax) {
		this.weekMax = weekMax;
	}

	public Integer getXabMax() {
		return xabMax;
	}

	public void setXabMax(Integer xabMax) {
		this.xabMax = xabMax;
	}

	public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }

	public Integer getDayAllocatedCount() {
		return dayAllocatedCount;
	}

	public void setDayAllocatedCount(Integer dayAllocatedCount) {
		this.dayAllocatedCount = dayAllocatedCount;
	}
    
}
