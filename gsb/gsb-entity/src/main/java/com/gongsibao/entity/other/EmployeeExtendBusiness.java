package com.gongsibao.entity.other;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name="uc_user_business",header="归属事业部")
public class EmployeeExtendBusiness extends BaseEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -9138786352933263959L;

    @JsonIgnore
    @Reference(foreignKey="employeeId")
    private EmployeeExtend employee;
    
    @Column(name="employee_id",header="员工Id")
    private Integer employeeId;
    
	@Column(name="user_id")
    private Integer userId;
	
	@Reference(foreignKey="businessId",header="归属事业部")
	private Dict business;
	
    @Column(name="business_id")
    private Integer businessId;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
	public EmployeeExtend getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeExtend employee) {
		this.employee = employee;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Dict getBusiness() {
		return business;
	}
	public void setBusiness(Dict business) {
		this.business = business;
	}
    
}
