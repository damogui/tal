package com.gongsibao.entity.report;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.entity.uc.User;

@Table(name="report_performance_statistics",header="业绩统计报表")
public class PerformanceStatistics extends BasePerformanceEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -63320755248597454L;

	@Column(name = "organization_type", header = "统计组织类型")
	private ReportOrganizationType organizationType;
	
    @Reference(foreignKey="departmentId",primaryKey="pkid")
	private Organization department;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId;
	
    @Column(name="salesman_id",header="业务员Id")
    private Integer salesmanId;
    
    @Reference(foreignKey="salesmanId",primaryKey="pkid")
    private User salesman;

	public ReportOrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(ReportOrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public Organization getDepartment() {
		return department;
	}

	public void setDepartment(Organization department) {
		this.department = department;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public User getSalesman() {
		return salesman;
	}

	public void setSalesman(User salesman) {
		this.salesman = salesman;
	}
}
