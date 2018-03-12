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
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import com.gongsibao.entity.taurus.UcOrganizationUserView;
import com.gongsibao.taurus.base.IUcOrganizationService;

@Service
public class UcOrganizationService extends PersistableService<UcOrganizationUserView> implements IUcOrganizationService	{

	
	@Override
	public List<UcOrganizationUserView> queryList(Oql oql){
		
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
		List<UcOrganizationUserView> list = new ArrayList<UcOrganizationUserView>();
		UcOrganizationUserView ucOrganizationUserView = new UcOrganizationUserView();
		for (IRow row : dataTable) {
			ucOrganizationUserView = new UcOrganizationUserView();
			ucOrganizationUserView.setFirstLevelDepartment(row.getString("firstLevelDepartment"));
			ucOrganizationUserView.setTwoLevelDepartment(row.getString("twoLevelDepartment"));
			ucOrganizationUserView.setThreeLevelDepartment(row.getString("threeLevelDepartment"));
			ucOrganizationUserView.setName(row.getString("name"));
			ucOrganizationUserView.setMobile(row.getString("mobile"));
			ucOrganizationUserView.setLoginCount(row.getString("loginCount"));
			ucOrganizationUserView.setOperationCount(row.getString("operationCount"));
			ucOrganizationUserView.setMonthLoginCount(row.getString("monthLoginCount"));
			ucOrganizationUserView.setMonthOperationCount(row.getString("monthOperationCount"));
			ucOrganizationUserView.setWeekLoginCount(row.getString("weekLoginCount"));
			ucOrganizationUserView.setWeekOperationCount(row.getString("weekOperationCount"));
			list.add(ucOrganizationUserView);
		}
		return list;
	}
	
	
	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer stringBuffer = new StringBuffer();

		if (type == 0) {
			stringBuffer.append("SELECT uou.first_level_department AS firstLevelDepartment, uou.two_level_department AS twoLevelDepartment, ");
			stringBuffer.append("uou.three_level_department AS threeLevelDepartment, uou.name AS name, ju.mobile AS mobile, ");
			stringBuffer.append("SUM( CASE bubl.behavior_act WHEN '登录成功' THEN 1 ELSE 0 END ) AS loginCount, ");
			stringBuffer.append("( SELECT COUNT(bu.pkid) FROM bd_user_behavior_log bu WHERE bu.user_id = ju.pkid AND bu.behavior_source_type = 0 ) AS operationCount, ");
			stringBuffer.append("( SELECT COUNT(aa.pkid) FROM bd_user_behavior_log aa WHERE ");
			stringBuffer.append("date_format(aa.add_time, '%Y-%m') = date_format( DATE_SUB(curdate(), INTERVAL 1 MONTH),'%Y-%m' ) ");
			stringBuffer.append("AND aa.behavior_source_type = 0 AND aa.user_id = ju.pkid AND aa.behavior_act = '登录成功' ) AS monthLoginCount, ");
			stringBuffer.append("( SELECT COUNT(aa.pkid) FROM bd_user_behavior_log aa ");
			stringBuffer.append("WHERE date_format(aa.add_time, '%Y-%m') = date_format( DATE_SUB(curdate(), INTERVAL 1 MONTH), '%Y-%m' ) ");
			stringBuffer.append("AND aa.behavior_source_type = 0 AND aa.user_id = ju.pkid ) AS monthOperationCount, ");
			stringBuffer.append("( SELECT COUNT(bu.pkid) FROM bd_user_behavior_log bu ");
			stringBuffer.append("WHERE bu.user_id = ju.pkid AND YEARWEEK( DATE_FORMAT(bu.add_time, '%Y-%m-%d') ) = YEARWEEK(now()) - 1 ");
			stringBuffer.append("AND bu.behavior_source_type = 0  AND bu.behavior_act = '登录成功' ) AS weekLoginCount, ");
			stringBuffer.append("( SELECT COUNT(bub.pkid) FROM bd_user_behavior_log bub ");
			stringBuffer.append("WHERE YEARWEEK( DATE_FORMAT(bub.add_time, '%Y-%m-%d') ) = YEARWEEK(now()) - 1 ");
			stringBuffer.append("AND bub.behavior_source_type = 0  AND bub.user_id = ju.pkid ) AS weekOperationCount ");
			stringBuffer.append("FROM bd_user_behavior_log bubl,jnz_user ju,uc_organization_user uou ");
			stringBuffer.append("WHERE bubl.user_id = ju.pkid AND ju.mobile = uou.mobile AND bubl.behavior_source_type = 0 ");
			stringBuffer.append("GROUP BY ju.mobile ");
			  
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
