package com.gongsibao.taurus.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserConsumptionView;
import com.gongsibao.taurus.base.IUserConsumptionService;

@Service
public class UserConsumptionService extends GsbPersistableService<UserConsumptionView> implements IUserConsumptionService{

	
	
	@Override
	public List<UserConsumptionView> queryList(Oql oql){
		
		String filterString = oql.getFilter();
		HashMap<String, String> mapFilters = new HashMap<String, String>();
		try {
			mapFilters = getMapFilters(filterString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();

		// 查询sql
		StringBuffer stringBuffer = getsql(0, mapFilters, startIndex, paging.getPageSize());
		// 获取总条数sql
		StringBuffer rcountsql = getsql(1, mapFilters, startIndex, paging.getPageSize());

		// 获取总条数
		paging.setTotalCount(getqueryListCount(rcountsql.toString()));
		oql.setPaging(paging);
		DataTable dataTable = this.pm.executeTable(stringBuffer.toString(), null);
		List<UserConsumptionView> list = new ArrayList<UserConsumptionView>();
		UserConsumptionView userConsumptionView = new UserConsumptionView();
		for (IRow row : dataTable) {
			userConsumptionView = new UserConsumptionView();
			userConsumptionView.setAddTime(row.getDate("addTime"));
			userConsumptionView.setMobile(row.getString("mobile"));
			userConsumptionView.setConsumptionBehavior(row.getString("type"));
			userConsumptionView.setPrice(row.getInteger("price"));
			list.add(userConsumptionView);
		}
		return list;
	}
	
	
	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer stringBuffer = new StringBuffer();

		if (type == 0) {
			stringBuffer.append("SELECT juwl.add_time as addTime,ju.mobile as mobile, ");
			stringBuffer.append("CASE juwl.type WHEN 0 THEN '消费' WHEN 1 THEN '充值' ELSE '赠送' END as type, juwl.price as price ");
			stringBuffer.append("FROM jnz_user_wallet_log juwl, jnz_user ju WHERE juwl.user_id = ju.pkid ");
			// 开始日期
			if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
				stringBuffer.append("AND juwl.add_time >= " + mapFilters.get("startAddTime") + " ");
			}

			// 结束日期
			if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
				stringBuffer.append("AND juwl.add_time <= " + mapFilters.get("endAddTime") + " ");
			}
			//手机号
			if (!StringManager.isNullOrEmpty(mapFilters.get("mobile"))) {
				stringBuffer.append("AND ju.mobile like " + mapFilters.get("mobile") + " ");
			}
			stringBuffer.append("LIMIT " + startIndex + ", " + pageSize + " ");
		} else {
			stringBuffer.append("SELECT COUNT(DISTINCT juwl.`pkid`) 'rount' FROM jnz_user_wallet_log juwl, jnz_user ju WHERE juwl.user_id = ju.pkid ");
			// 开始日期
			if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
				stringBuffer.append("AND juwl.add_time >= " + mapFilters.get("startAddTime") + " ");
			}

			// 结束日期
			if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
				stringBuffer.append("AND juwl.add_time <= " + mapFilters.get("endAddTime") + " ");
			}
			//手机号
			if (!StringManager.isNullOrEmpty(mapFilters.get("mobile"))) {
				stringBuffer.append("AND ju.mobile like " + mapFilters.get("mobile") + " ");
			}
		}
		return stringBuffer;
	}
	
	
	// 根据前段传过来的参数，得到Map
	protected HashMap<String, String> getMapFilters(String filter) throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();

		if (!StringManager.isNullOrEmpty(filter)) {

			filter = filter.replace("%", "|");
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace("|", "%");
			String[] ss = filter.split(" AND ");
			if (ss.length > 0) {

				for (String s : ss) {
					String splitString = "=";
					if (s.contains("LIKE")) {
						splitString = "LIKE";
					}
					if (s.contains("in")) {
						splitString = "in";
					}
					if (s.contains(">=")) {
						splitString = ">=";
					}
					if (s.contains("<=")) {
						splitString = "<=";
					}
					String[] a = s.split(splitString);
					String mapKeyString = a[0].trim();
					String mapValString = a[1].trim();
					// 下单时间
					if (mapKeyString.equals("addTime") && splitString.equals(">=")) {
						mapKeyString = "startAddTime";
					}
					if (mapKeyString.equals("addTime") && splitString.equals("<=")) {
						mapKeyString = "endAddTime";
					}
					// 订单回款时间
					if (mapKeyString.equals("payTime") && splitString.equals(">=")) {
						mapKeyString = "startPayTime";
					}
					if (mapKeyString.equals("payTime") && splitString.equals("<=")) {
						mapKeyString = "endPayTime";
					}
					map.put(mapKeyString, mapValString);
				}
				return map;
			}
		}
		return map;
	}
	
	// 获取查询的总条数
	protected int getqueryListCount(String sql) {
		int count = 0;
		Object rcount = this.pm.executeScalar(sql.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}
}
