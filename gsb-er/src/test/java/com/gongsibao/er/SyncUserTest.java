package com.gongsibao.er;

import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

import com.gongsibao.entity.er.User;
import com.gongsibao.er.base.IUserService;

/**
 * @ClassName: SyncUserTest
 * @Description:TODO 同步用户、用户岗位
 * @author: 韩伟
 * @date: 2017年10月10日 下午8:31:17
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public class SyncUserTest {

	IPersister<Employee> pm = PersisterFactory.create();
	IUserService userService = ServiceFactory.create(IUserService.class);
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
	String memotoKey = "iDuty";

	@Test
	public void run() {

		deleteOldSyncUser();

		List<User> userList = this.getUserList();
		for (User user : userList) {

			String cmdText = "select count(0) from " + MtableManager.getMtable(Employee.class).getTableName() + " where mobile='" + user.getMobile() + "'";
			int count = pm.executeInt(cmdText, null);
			if (count > 0) {
				continue;
			}

			Employee employee = new Employee();
			{

				employee.toNew();
				employee.setId(Integer.parseInt(user.getId().toString()));
				if (StringManager.isNullOrEmpty(user.getName())) {

					employee.setName(user.getMobile());
				} else {
					employee.setName(user.getName());
				}
				employee.setMobile(user.getMobile());
				employee.setEmail(user.getEmail());
				employee.setPwd(user.getPasswd());
				employee.setLoginName(user.getMobile());

				boolean disabled = Boolean.parseBoolean(user.getIsEnabled().toString());
				employee.setDisabled(disabled);
				employee.setMemoto(memotoKey);
			}

			employeeService.save(employee);
		}
	}

	private void deleteOldSyncUser() {

		// 删除用户
		DeleteBuilder builder = new DeleteBuilder();
		{
			builder.deleteFrom("sys_permission_employee");
			builder.where("memoto='" + memotoKey + "'");
		}
		pm.executeNonQuery(builder.toSQL(), null);
	}

	private List<User> getUserList() {

		Oql oql = new Oql();
		{
			oql.setType(User.class);
			oql.setSelects("*");
		}
		return userService.queryList(oql);
	}
}
