package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.crm.dic.ServiceType;

@Table(name="crm_customer_service_config",header="客服配置")
public class CustomerServiceConfig  extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7484137671265138660L;
	
    @Reference(foreignKey="employeeId")
    private Employee employee;
    
    @Column(name="employee_id",header="员工Id")
    private Integer employeeId;
    
    @Column(name="swt_service_id",header="商务通客服Id")
    private String swtServiceId;
    
    @Column(name="type",header="类型")
    private ServiceType type;
    
    @Column(name="swt_service_id_md5",header="冗余加密")
    private String swtServiceIdMD5;
    
	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getSwtServiceId() {
		return swtServiceId;
	}

	public void setSwtServiceId(String swtServiceId) {
		this.swtServiceId = swtServiceId;
	}

	public String getSwtServiceIdMD5() {
		return swtServiceIdMD5;
	}

	public void setSwtServiceIdMD5(String swtServiceIdMD5) {
		this.swtServiceIdMD5 = swtServiceIdMD5;
	}
	
}
