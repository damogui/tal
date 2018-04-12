package com.gongsibao.tools.migrate.account;

/**
 * @ClassName: Action7RefreshtOrder
 * @Description:TODO
 * @author: 韩伟
 * @date: 2018年3月26日 下午2:59:10 11.更新so_order 中 owner_id = add_user_id，再更新
 *        supplier_id、department_id
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class Action5RefreshtOrder extends AbstractActionService {

	@Override
	public void run() {


		//修改订单字段【是否是改价订单】的字段长度为1，为了满足框架布尔类型的兼容
		pm.executeNonQuery("ALTER TABLE `gsb`.`so_order` CHANGE `is_change_price` `is_change_price` TINYINT(1) DEFAULT 0 NOT NULL COMMENT '是否改价订单，默认否';", null);

		// 11.更新so_order 中 owner_id = add_user_id，
		//so_order_prod_user_map
		//关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
		//订单项和用户关系状态，type=314，3141正在负责、3142曾经负责
		String cmdText = "UPDATE so_order so LEFT JOIN ( SELECT so_order.pkid AS order_id, so_order_prod_user_map.user_id AS user_id FROM so_order LEFT JOIN so_order_prod ON so_order.`pkid` = so_order_prod.`order_id` LEFT JOIN so_order_prod_user_map ON so_order_prod.`pkid` = so_order_prod_user_map.`order_prod_id` AND `so_order_prod_user_map`.`type_id` = 3061 AND `so_order_prod_user_map`.`status_id` = 3141 ) u ON so.pkid = u.order_id SET so.owner_id = u.user_id";
		dao.executeInsert(cmdText, null);

		// 再更新supplier_id、department_id
		cmdText = "UPDATE so_order so LEFT JOIN sp_salesman sa ON so.owner_id = sa.employee_id SET so.supplier_id = sa.supplier_id, so.department_id = sa.department_id;";
		dao.executeInsert(cmdText, null);

		//跟新so_order_prod的业务员id，和供应商id,部门id
		cmdText = "UPDATE so_order oi,so_order_prod od SET od.owner_id = oi.owner_id ,od.department_id = oi.department_id,od.supplier_id = oi.supplier_id WHERE oi.pkid=od.order_id;";
		dao.executeInsert(cmdText, null);

		//更新订单的客户信息
		cmdText = "UPDATE so_order oi, crm_customer c SET oi.customer_id = c.pkid,oi.customer_name = c.real_name WHERE oi.account_id = c.account_id;";
		dao.executeInsert(cmdText, null);

		//更新订单的退款金额
		cmdText = "UPDATE so_order oi ,(SELECT pkid, IFNULL((SELECT SUM(amount) FROM so_refund WHERE order_id = so_order.`pkid` AND audit_status_id = 1054),0) 'refundPrice' FROM so_order ) oi1 SET oi.refund_price=oi1.refundPrice WHERE oi.pkid=oi1.pkid;";
		dao.executeInsert(cmdText, null);

		//跟新so_contract的业务员id，和供应商id,部门id
		cmdText = "UPDATE so_order oi,so_contract con SET con.salesman_id = oi.owner_id ,con.department_id = oi.department_id,con.supplier_id = oi.supplier_id WHERE oi.pkid=con.order_id;";
		dao.executeInsert(cmdText, null);

		//跟新so_invoice的业务员id，和供应商id,部门id
		cmdText = "UPDATE so_order oi,so_order_invoice_map iom,so_invoice inv SET inv.salesman_id = oi.owner_id ,inv.department_id = oi.department_id,inv.supplier_id = oi.supplier_id WHERE oi.pkid = iom.order_id AND iom.invoice_id = inv.pkid;";
		dao.executeInsert(cmdText, null);

		//跟新合同创建人
		cmdText = "UPDATE so_contract c, sys_permission_employee em SET c.creator = em.name WHERE c.add_user_id= em.id;";
		dao.executeInsert(cmdText, null);

		//跟新发票创建人
		cmdText = "UPDATE so_invoice c, sys_permission_employee em SET c.creator = em.name WHERE c.add_user_id= em.id;";
		dao.executeInsert(cmdText, null);

		//跟新历史数据的支付记录的账套id和支付方式id
		cmdText = "UPDATE so_pay p,u8_bank_so_pay_map pm SET p.set_of_books_id = pm.set_of_books_id,p.u8_bank_id = pm.u8_bank_id WHERE p.pkid=pm.pay_id AND pm.type = 0;";
		dao.executeInsert(cmdText, null);

		//跟新历史数据的退款记录的账套id和支付方式id
		cmdText = "UPDATE so_refund r,u8_bank_so_pay_map pm SET r.set_of_books_id = pm.set_of_books_id,r.u8_bank_id = pm.u8_bank_id WHERE r.pkid=pm.pay_id AND pm.type = 1;";
		dao.executeInsert(cmdText, null);


	}
}


/****
 * 
 * 检查结果（对不上）
 * 
 * SELECT owner_id, count(0) count FROM so_order WHERE owner_id IS NOT NULL GROUP BY owner_id ORDER BY count DESC;

   SELECT so_order_prod_user_map.user_id, COUNT(0) count FROM so_order LEFT JOIN so_order_prod ON so_order.`pkid` = so_order_prod.`order_id` LEFT JOIN so_order_prod_user_map ON so_order_prod.`pkid` = so_order_prod_user_map.`order_prod_id` AND `so_order_prod_user_map`.`type_id` = 3061 AND `so_order_prod_user_map`.`status_id` = 3141 GROUP BY so_order_prod_user_map.user_id ORDER BY count DESC;
 * 
 */

