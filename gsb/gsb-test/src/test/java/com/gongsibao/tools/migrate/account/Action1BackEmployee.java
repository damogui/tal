package com.gongsibao.tools.migrate.account;

/**
 * @ClassName: Action1BackEmployee
 * @Description:TODO
 * @author: 韩伟
 * @date: 2018年3月26日 下午2:55:32
 *        1.创建sys_permission_employee_back（结构与sys_permission_employee一样）
 *        2.备份sys_permission_employee 至 sys_permission_employee_back
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class Action1BackEmployee extends AbstractActionService {

	@Override
	public void run() {

		// 1.删除 sys_permission_employee_back
		pm.executeNonQuery("drop table sys_permission_employee_back", null);

		// 创建 sys_permission_employee_back
		String cmdText = "CREATE TABLE `sys_permission_employee_back` (" + " `email` varchar(50) DEFAULT NULL," + "`nationality` varchar(50) DEFAULT NULL," + "`address` varchar(50) DEFAULT NULL,"
				+ "  `mobile` varchar(100) DEFAULT NULL," + " `qq` varchar(50) DEFAULT NULL," + " `weixin` varchar(50) DEFAULT NULL," + " `birthday` datetime DEFAULT NULL,"
				+ "  `gender` int(11) DEFAULT NULL," + " `native_place` varchar(50) DEFAULT NULL," + " `education` int(11) DEFAULT NULL," + "  `zip_code` varchar(50) DEFAULT NULL,"
				+ "  `type` int(11) DEFAULT NULL," + "  `bank_no` varchar(50) DEFAULT NULL," + "  `entry_date` datetime DEFAULT NULL," + "  `quit_date` datetime DEFAULT NULL,"
				+ "  `photo` varchar(500) DEFAULT NULL," + "  `post_id` int(11) DEFAULT NULL," + "  `department_id` int(11) DEFAULT NULL," + "  `login_name` varchar(50) DEFAULT NULL,"
				+ "  `pwd` varchar(50) DEFAULT NULL," + "  `authorization_principal_id` int(11) DEFAULT NULL," + "  `last_login_time` datetime DEFAULT NULL," + "  `login_num` int(11) DEFAULT 0,"
				+ "  `ticket` varchar(50) DEFAULT NULL," + "  `ding_qr_code_url` varchar(50) DEFAULT NULL," + "  `disabled` tinyint(1) DEFAULT NULL," + "  `shorthand` varchar(50) DEFAULT NULL,"
				+ "  `code` varchar(50) DEFAULT NULL," + "  `name` varchar(200) DEFAULT NULL," + "  `memoto` varchar(1000) DEFAULT NULL," + "  `id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "  `creator_id` int(11) DEFAULT NULL," + "  `creator` varchar(50) DEFAULT NULL," + "  `create_time` datetime DEFAULT NULL," + "  `updator_id` int(11) DEFAULT NULL,"
				+ "  `updator` varchar(50) DEFAULT NULL," + " `update_time` datetime DEFAULT NULL," + "  `ts` datetime DEFAULT NULL," + "  PRIMARY KEY (`id`)"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=1803 DEFAULT CHARSET=utf8;";

		pm.executeNonQuery(cmdText, null);

		// 导入数据
		cmdText = "INSERT INTO sys_permission_employee_back SELECT * from sys_permission_employee;";
		dao.executeInsert(cmdText, null);
	}
}
