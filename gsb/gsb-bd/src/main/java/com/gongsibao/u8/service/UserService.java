package com.gongsibao.u8.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.panda.controls.other.I;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.uc.User;
import com.gongsibao.u8.base.IUserService;

@Service
public class UserService extends PersistableService<User> implements IUserService {

	public UserService() {
		super();
		this.type = User.class;
	}

	// 根据订单id集合获取订单的操作员
	@Override
	public Map<Integer, String> getOperatorByOrderIds(List<Integer> orderIdList) {

		Map<Integer, String> resMap = new HashMap();

		String orderIdsStr = StringManager.join(",",orderIdList);

		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECT so_order.pkid 'orderId', uc_user.`real_name` 'operatorName' FROM so_order ");
		sqlString.append("JOIN (SELECT MIN(so_order_prod.pkid) 'pkid', so_order_prod.order_id FROM so_order_prod GROUP BY order_id) so_order_prod1 ON so_order.`pkid`= so_order_prod1.`order_id`   ");
		sqlString.append("JOIN so_order_prod_user_map ON so_order_prod_user_map.`order_prod_id`=so_order_prod1.pkid AND `so_order_prod_user_map`.`type_id`=3061 AND `so_order_prod_user_map`.`status_id`=3141  ");
		sqlString.append("JOIN uc_user ON uc_user.`pkid` = so_order_prod_user_map.`user_id` WHERE so_order.`pkid` IN (" + orderIdsStr + ") ");

		DataTable dataTable = this.pm.executeTable(sqlString.toString(), null);

		for (IRow row : dataTable) {
			Integer orderId = row.getInteger("orderId");
			String operatorName = row.getString("operatorName");
			resMap.put(orderId, operatorName);
		}

		return resMap;
	}
}
