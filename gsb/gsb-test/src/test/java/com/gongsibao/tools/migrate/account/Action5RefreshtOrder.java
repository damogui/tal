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

		// 11.更新so_order 中 owner_id = add_user_id，
		//so_order_prod_user_map 
		//关系类型序号，type=306，3061业务、3062客服（关注）、3063操作
		//订单项和用户关系状态，type=314，3141正在负责、3142曾经负责
		
		String cmdText = "UPDATE so_order so LEFT JOIN ( SELECT so_order.pkid AS order_id, so_order_prod_user_map.user_id AS user_id FROM so_order LEFT JOIN so_order_prod ON so_order.`pkid` = so_order_prod.`order_id` LEFT JOIN so_order_prod_user_map ON so_order_prod.`pkid` = so_order_prod_user_map.`order_prod_id` AND `so_order_prod_user_map`.`type_id` = 3061 AND `so_order_prod_user_map`.`status_id` = 3141 ) u ON so.pkid = u.order_id SET so.owner_id = u.user_id";
		dao.executeInsert(cmdText, null);

		// 再更新supplier_id、department_id
		cmdText = "UPDATE so_order so LEFT JOIN sp_salesman sa ON so.owner_id = sa.employee_id SET so.supplier_id = sa.supplier_id, so.department_id = sa.department_id;";
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

