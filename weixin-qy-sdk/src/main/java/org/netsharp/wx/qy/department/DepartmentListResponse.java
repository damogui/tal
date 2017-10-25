package org.netsharp.wx.qy.department;

import java.util.List;

import org.netsharp.wx.qy.Response;

public class DepartmentListResponse extends Response {
	
	private List<Department> department;

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}
	
}