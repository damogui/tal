package org.netsharp.organization.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Operation;
import org.netsharp.panda.commerce.ListPart;

public class EmployeeListPart extends ListPart {

	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);

	public boolean resetPassword(String ids) {

		String[] arr = ids.split("_");
		for (String id : arr) {
			employeeService.resetPassword(Integer.valueOf(id));
		}
		return true;
	}

	/**
	 * 获取员工的权限列表
	 * 
	 * @param employeeId
	 * @return
	 */
	public Object permissions(Integer employeeId) {

		UserPermission up = new UserPermission();
		{
			Employee emp = employeeService.byId(employeeId);
			up.setEmployee(emp);
		}
		up = UserPermissionManager.getUserPermission(up);
		List<String> ls = new ArrayList<String>();
		Map<String, String> ss = new HashMap<String, String>();
		for (Operation op : up.getOperations()) {
			String key = op.getResourceNodeId().toString();
			if (ss.containsKey(key)) {
				String value = "【" + op.getOperationType().getName() + "】";
				ss.put(key, ss.get(key) + value + "、");
			} else {
				ss.put(key, op.getResourceNode().getPathName() + "：");
			}
		}

		for (Map.Entry<String, String> entry : ss.entrySet()) {

			String key = entry.getKey();
			String value = ss.get(key);

			if (value.split("：").length >= 2) {
				value = value.substring(0, value.lastIndexOf('、'));
				ls.add(value);
			}

		}
		return ls;
	}

}
