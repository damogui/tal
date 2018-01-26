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
	
	@Exclusive
	@Column(name="login_name",header="帐号")
	private String loginName;
	
	@Column(name = "follow_up_task_count", header = "跟进中任务数量")
	private Integer FollowUpTaskCount = 0;
	
	@Column(name = "follow_up_task_day_count", header = "当天分配的任务数量")
	private Integer FollowUpTaskDayCount = 0;
	
	@Column(name = "follow_up_task_week_count", header = "当天分配的任务数量")
	private Integer FollowUpTaskWeekCount = 0;

	@Exclusive
	@Column(name = "name", header = "姓名")
	private String name;

	@Exclusive
	@Column(name="email",header="电子邮件")
    private String email;
	
	@Exclusive
	@Column(name = "mobile", header = "手机号")
	private String mobile;

	@Exclusive
	@Column(name = "entry_date", header = "入职日期")
	private Date entryDate;

	@Exclusive
	@Column(name = "quit_date", header = "离职日期")
	private Date quitDate;

	@Exclusive
	@Subs(subType = SalesmanRole.class, foreignKey = "salesmanId", header = "用户角色")
	private List<SalesmanRole> roles;
	
	@Subs(subType = SalesmanProduct.class, foreignKey = "salesmanId", header = "服务范围")
	private List<SalesmanProduct> products;


    @Column(name = "is_old_client", header = "是否新客户")
    private Boolean isoldclient;

    @Column(name = "is_accpet_auto", header = "是否接受自动分配")
    private Boolean isaccpetauto;
    @Column(name = "day_max", header = "日分配上线")
    private Integer daymax;
    @Column(name = "week_max", header = "周分配上线")
    private Integer weekmax;
    @Column(name = "xab_max", header = "XAB类任务上限")
    private Integer xabmax;


	
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

    public Boolean getIsoldclient() {
        return isoldclient;
    }

    public void setIsoldclient(Boolean isoldclient) {
        this.isoldclient = isoldclient;
    }

    public Boolean getIsaccpetauto() {
        return isaccpetauto;
    }

    public void setIsaccpetauto(Boolean isaccpetauto) {
        this.isaccpetauto = isaccpetauto;
    }

    public Integer getDaymax() {
        return daymax;
    }

    public void setDaymax(Integer daymax) {
        this.daymax = daymax;
    }

    public Integer getWeekmax() {
        return weekmax;
    }

    public void setWeekmax(Integer weekmax) {
        this.weekmax = weekmax;
    }

    public Integer getXabmax() {
        return xabmax;
    }

    public void setXabmax(Integer xabmax) {
        this.xabmax = xabmax;
    }

	public Integer getFollowUpTaskCount() {
		return FollowUpTaskCount;
	}

	public void setFollowUpTaskCount(Integer followUpTaskCount) {
		FollowUpTaskCount = followUpTaskCount;
	}

	public Integer getFollowUpTaskDayCount() {
		return FollowUpTaskDayCount;
	}

	public void setFollowUpTaskDayCount(Integer followUpTaskDayCount) {
		FollowUpTaskDayCount = followUpTaskDayCount;
	}

	public Integer getFollowUpTaskWeekCount() {
		return FollowUpTaskWeekCount;
	}

	public void setFollowUpTaskWeekCount(Integer followUpTaskWeekCount) {
		FollowUpTaskWeekCount = followUpTaskWeekCount;
	}

}
