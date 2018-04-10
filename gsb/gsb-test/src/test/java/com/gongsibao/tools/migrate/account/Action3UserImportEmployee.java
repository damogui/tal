package com.gongsibao.tools.migrate.account;

/**
 * @ClassName: Action3UserImportEmployee
 * @Description:TODO
 * @author: 韩伟
 * @date: 2018年3月26日 下午2:56:24 5.将 uc_user 数据导入 sys_permission_employee
 *        (字段尽量对应上) 6.将sys_permission_employee_back 在 uc_user中不存在的数据插入
 *        sys_permission_employee (字段尽量对应上)
 *        7.处理sys_permission_organization_employee关系（岗位）
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class Action3UserImportEmployee extends AbstractActionService {

	@Override
	public void run() {

		// 1.将 uc_user 数据导入 sys_permission_employee(字段尽量对应上)
		String cmdText = "INSERT INTO sys_permission_employee " + "(id,NAME,email,qq,weixin,mobile,login_name,pwd,disabled) "
				+ "SELECT pkid,real_name,email,qq,weixin,mobile_phone,mobile_phone,passwd,(case when is_enabled = 1 then 0 else 1 end) AS disabled FROM uc_user;";

		dao.executeInsert(cmdText, null);

		// 2.sys_permission_employee_back 在 uc_user中不存在的数据插入
		// sys_permission_employee
		cmdText = "INSERT INTO sys_permission_employee ( `email`, `nationality`, `address`, `mobile`, `qq`, `weixin`, `birthday`, `gender`, `native_place`, `education`, `zip_code`, `type`, `bank_no`, `entry_date`, `quit_date`, `photo`, `post_id`, `department_id`, `login_name`, `pwd`, `authorization_principal_id`, `last_login_time`, `login_num`, `ticket`, `ding_qr_code_url`, `disabled`, `shorthand`, `code`, `name`, `memoto`, `creator_id`, `creator`, `create_time`, `updator_id`, `updator`, `update_time`, `ts` ) SELECT `email`, `nationality`, `address`, `mobile`, `qq`, `weixin`, `birthday`, `gender`, `native_place`, `education`, `zip_code`, `type`, `bank_no`, `entry_date`, `quit_date`, `photo`, `post_id`, `department_id`, `login_name`, `pwd`, `authorization_principal_id`, `last_login_time`, `login_num`, `ticket`, `ding_qr_code_url`, `disabled`, `shorthand`, `code`, `name`, `memoto`, `creator_id`, `creator`, `create_time`, `updator_id`, `updator`, `update_time`, `ts` FROM sys_permission_employee_back WHERE login_name NOT IN ( SELECT login_name FROM sys_permission_employee );";
		dao.executeInsert(cmdText, null);

		//处理sys_permission_organization_employee关系（岗位）
		cmdText = "UPDATE sys_permission_organization_employee oe LEFT JOIN ( SELECT eb.id, eb.login_name, e.id AS newid FROM sys_permission_employee_back eb LEFT JOIN sys_permission_employee e ON eb.login_name = e.login_name) c ON oe.employee_id = c.id SET oe.employee_id = c.newid;";
		dao.executeUpdate(cmdText, null);
		
		//已有的salesman 同步 employee_id
		cmdText = "UPDATE sp_salesman s LEFT JOIN sys_permission_employee e ON s.mobile = e.mobile SET s.employee_id = e.id;";
		dao.executeUpdate(cmdText, null);
		
		//后期：要保证salesman的id与employee_id相同
		
		
	}
}
