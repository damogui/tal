package com.gongsibao.panda.temp;

import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

/**
 * @ClassName: SyncUserIdToEmployeeTest
 * @Description:TODO 同步Beehive帐号Id至Netsharp中（根据手机号匹配，重复怎么处理？）
 * @author: 韩伟
 * @date: 2017年12月1日 上午10:42:46
 * 
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class SyncUserIdToEmployeeTest {

	IUserService userService = ServiceFactory.create(IUserService.class);
	IPersister<Employee> pm = PersisterFactory.create();
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
	IOrganizationEmployeeService oeService = ServiceFactory.create(IOrganizationEmployeeService.class);

	@Test
	public void run() {

		syncUpdate();

		syncNew();
	}

	private void syncNew() {

		// 处理未同步的
		List<User> unSyncUserList = queryUnSyncUserList();
		for (User user : unSyncUserList) {
			this.createEmployee(user);
		}

	}

	private void syncUpdate() {

		updateEmployee();

		List<User> userList = this.queryAllUserList();
		List<Employee> employeeList = this.queryAllEmployeeList();

		this.clearEmployee();

		int syncCount = 0;
		for (Employee employee : employeeList) {
			employee.toNew();
			employee.setMemoto(employee.getId().toString());// 备份原Id
			if(employee.getLoginName().equals("13301503086")){
				
				System.out.println("13301503086");
			}
			Integer userId = this.getUserIdByMobile(userList, employee.getLoginName());
			if (userId != null) {

				syncCount++;
				
				employee.setId(userId);
				employeeService.save(employee);
			}else{
				
				employee.setId(null);
				
			}
			
			Integer oldEmployeeId = Integer.parseInt(employee.getMemoto());
			this.updateOrganizationEmployee(oldEmployeeId, userId);
		}
		System.out.println("同步数量：" + syncCount);
	}

	private void createEmployee(User user) {

		if (isHas(user.getMobilePhone())) {

			return;
		}
		Employee employee = new Employee();
		{
			employee.toNew();
			employee.setLoginName(user.getMobilePhone());
			employee.setMobile(user.getMobilePhone());
			
			String name = user.getName();
			if(StringManager.isNullOrEmpty(name)){
				name = user.getMobilePhone();
			}
			employee.setName(name);
			employee.setEmail(user.getEmail());
			employee.setDisabled(!(user.getEnabled()));
		}
		employeeService.save(employee);
		
		System.err.println("新增：" + employee.getMobile()+"，"+employee.getName());
	}

	private boolean isHas(String mobile) {

		String cmdText = "select count(0) from sys_permission_employee where mobile='" + mobile + "'";

		int count = pm.executeInt(cmdText, null);

		return count > 0;
	}

	private List<User> queryUnSyncUserList() {

		Oql oql = new Oql();
		{
			oql.setType(User.class);
			oql.setSelects("*");
			oql.setFilter("mobile_phone not in (select mobile from sys_permission_employee) and mobile_phone <>'' and mobile_phone is not null");
		}

		List<User> list = userService.queryList(oql);
		System.out.println("未同步User数量：" + list.size());
		return list;
	}

	private void updateEmployee() {

		String cmdText = "UPDATE sys_permission_employee set mobile=login_name";
		Integer deleteCount = pm.executeNonQuery(cmdText, null);
		System.out.println("共更新：" + deleteCount);
	}

	/**
	 * @Title: clearEmployee
	 * @Description: TODO(清空Employee)
	 * @param:
	 * @return: void
	 * @throws
	 */
	private void clearEmployee() {

		String cmdText = "delete from sys_permission_employee";
		Integer deleteCount = pm.executeNonQuery(cmdText, null);
		System.out.println("共删除：" + deleteCount);
	}

	private Integer getUserIdByMobile(List<User> userList, String mobile) {

		for (User user : userList) {

			if (user.getMobilePhone().equals(mobile)) {

				return user.getId();
			}
		}
		return null;
	}

	private List<User> queryAllUserList() {

		Oql oql = new Oql();
		{
			oql.setType(User.class);
			oql.setSelects("*");
		}

		List<User> list = userService.queryList(oql);
		System.out.println("User数量：" + list.size());
		return list;
	}

	private List<Employee> queryAllEmployeeList() {

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("*");
		}

		List<Employee> list = employeeService.queryList(oql);
		System.out.println("Employee数量：" + list.size());
		return list;
	}

	/**
	 * @Title: updateOrganizationEmployee
	 * @Description: TODO(更新组织机构)
	 * @param:
	 * @return: void
	 * @throws
	 */
	private void updateOrganizationEmployee(Integer oldEmployeeId, Integer newEmployeeId) {

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("*");
			oql.setFilter("employeeId=" + oldEmployeeId);
		}

		List<OrganizationEmployee> list = oeService.queryList(oql);
		System.out.println("oldEmployeeId：" + oldEmployeeId + "，newEmployeeId：" + newEmployeeId);
		for (OrganizationEmployee oe : list) {

			oe.setEmployeeId(newEmployeeId);
		}
		oeService.saves(list);
	}
}

//
///*1.更新员工登录帐号*/
//update sys_permission_employee set login_name = mobile where mobile is not null and mobile <>'';
//
///*2.备份原Id到Code*/
//update sys_permission_employee set `code` = id;
//
///*3.更新user的Id至员工备注*/
//UPDATE sys_permission_employee employee LEFT JOIN uc_user USER1 ON employee.login_name = USER1.mobile_phone set memoto = USER1.pkid;
//
//UPDATE sys_permission_employee set id=memoto where memoto is not null and memoto<>''
//
///*5.更新组织机构对应关系的employeeId*/
//
//update sys_permission_organization_employee oe LEFT JOIN sys_permission_employee employee on oe.employee_id = employee.code set oe.employee_id = employee.id;
//
//INSERT INTO sys_permission_employee (
//		id,
//		login_name,
//	  pwd,
//		NAME,
//		mobile,
//		email,
//		weixin,
//		qq
//	) SELECT
//		*
//	FROM
//		(
//			SELECT
//				b.pkid,
//				b.mobile_phone,
//				b.passwd,
//				b.real_name,
//				b.mobile_phone as mobile,
//				b.email,
//				b.weixin,
//				b.qq
//			FROM
//				uc_user as b
//			WHERE
//				b.mobile_phone NOT IN (
//					SELECT
//						mobile
//					FROM
//						sys_permission_employee
//				)
//		) AS u


