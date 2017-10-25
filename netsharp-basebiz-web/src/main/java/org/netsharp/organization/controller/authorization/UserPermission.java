package org.netsharp.organization.controller.authorization;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Operation;

/**
 * @Description: 用户 权限对象 ----session对象 需要转化为json格式保存
 * 
 * @Copyright (C) 2015 YKX All Right Reserved.
 * @createDate：2015-5-5
 * @author：xufangbo,TangWenWu
 * @version 1.0
 */
public class UserPermission implements Serializable{
	
	private static final long serialVersionUID = 2546114162696528413L;
	private Employee employee;                       // 员工
	private List<Operation> operations;              // 当前员工的操作权限
	private Map<String, List<String>> fieldGeteways; // 当前员工的字段权限
	private boolean isPermission;                   // 是否已经加载了权限数据
	private Date loginTime;                          // 登录时间
	private String posts;                            // 当前员工的岗位，多个值时以","分割
	private String departments;                      // 当前员工的组织结构节点，多个值时以","分割
	private List<String> departmentPathCodes;        // 当前员工的部门的pathCode，多个值时以","分割
	
	public void desctroy(){
		
		this.employee = null;
		if(this.isPermission){
			this.operations.clear();
			this.fieldGeteways.clear();
		}
	}
	
	public void reset(){
		
		this.isPermission = false;
		if(operations!=null){
			this.operations.clear();
		}
		
		if(operations!=null){
			this.fieldGeteways.clear();
		}
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
		this.loginTime = new Date();
	}
	
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public Map<String, List<String>> getFieldGeteways() {
		return fieldGeteways;
	}
	public void setFieldGeteways(Map<String, List<String>> fieldGeteways) {
		this.fieldGeteways = fieldGeteways;
	}
	
	public boolean isPermission() {
		return isPermission;
	}
	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getPosts() {
		return posts;
	}

	public void setPosts(String posts) {
		this.posts = posts;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public List<String> getDepartmentPathCodes() {
		return departmentPathCodes;
	}

	public void setDepartmentPathCodes(List<String> departmentPathCodes) {
		this.departmentPathCodes = departmentPathCodes;
	}
}
