package com.gongsibao.cw.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IEmployeeService;

@Service
public class EmployeeService extends PersistableService<Employee> implements IEmployeeService{

	public EmployeeService() {
		super();
		this.type = Employee.class;
	}
	
	/**
	 * 获取同一级别领导 
	 * 同级别查询组长
	 */
	public List<Employee> getEmployeeByLeader( Integer departmentId) {
		/**
		 * 业务逻辑
		 * 1、获取登录人组织机构id  
		 * 2、查询相同组织机构下主管，存在主管为上级领导，不存在查询上一级别主管领导
		 * 3、并且判断登录人与上级领导相同，向上查询。
		 */
		List<Employee> resultList = new ArrayList<Employee>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.id ,e.mobile,e.name,e.login_name FROM sys_permission_employee e ");
		sql.append("LEFT JOIN sys_permission_organization o ON o.id = e.post_id  ");
		sql.append("LEFT JOIN sys_permission_position p ON  o.position_id  = p.id   ");
		sql.append("WHERE e.department_id = '"+departmentId+"' AND o.position_id =3 ");
		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		for (IRow row : dataTable) {
			Employee employee = new Employee();
			employee.setId(row.getInteger("id"));
			employee.setMobile(row.getString("mobile"));
			employee.setName(row.getString("name"));
			resultList.add(employee);
		}
		return resultList;
	}
	/**
	 * 获取上一基级别领导 查询主管
	* @Title: getEmployeeByParentLeader  
	* @Description: TODO
	* @param @return    参数  
	* @return Employee    返回类型  
	* @throws
	 */
	public List<Employee> getEmployeeByParentLeader(Integer departmentId){
		List<Employee> resultList = new ArrayList<Employee>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.id ,mobile,e.name,login_name FROM sys_permission_employee e  ");
		sql.append("LEFT JOIN sys_permission_organization o ON e.department_id = o.parent_id  ");
		sql.append("LEFT JOIN sys_permission_organization p ON e.post_id = p.id   ");
		sql.append("WHERE o.id = '"+departmentId+ "'  AND (p.position_id = 2  OR  p.position_id = 5 )");
		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		if(dataTable != null && dataTable.size()>0){
			for (IRow row : dataTable) {
				Employee employee = new Employee();
				employee.setId(row.getInteger("id"));
				employee.setMobile(row.getString("mobile"));
				employee.setName(row.getString("name"));
				resultList.add(employee);
			}
		}
		return resultList;
	}

}
