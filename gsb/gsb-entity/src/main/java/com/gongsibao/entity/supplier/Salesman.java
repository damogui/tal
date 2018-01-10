package com.gongsibao.entity.supplier;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

@Table(name="sp_salesman",header="服务商业务员")
public class Salesman extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2235508906235938620L;

	@Column(name = "employee_id", header = "员工Id")
	private Integer employeeId = 0;
	
	@Reference(foreignKey = "employeeId", header = "员工")
	private Employee employee;
	
	
}
