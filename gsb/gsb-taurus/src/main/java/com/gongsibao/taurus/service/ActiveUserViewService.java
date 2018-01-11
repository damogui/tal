package com.gongsibao.taurus.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.taurus.base.IActiveUserViewService;

@Service
public class ActiveUserViewService extends GsbPersistableService<ActiveUserView> implements IActiveUserViewService{

	public ActiveUserViewService(){
		super();
		this.type=ActiveUserView.class;
	}
	
	@Override
	public List<ActiveUserView> queryList(Oql oql){
		
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
		List<ActiveUserView> list = new ArrayList<ActiveUserView>();
		ActiveUserView activeUserView = new ActiveUserView();
		for (IRow row : dataTable) {
			activeUserView = new ActiveUserView();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(row.getString("addTime"));
				activeUserView.setAddTime(date);
				activeUserView.setCount(Long.valueOf(row.getString("userCount")));
				list.add(activeUserView);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	// type：0查询sql 1获取页码sql
		protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
			StringBuffer stringBuffer = new StringBuffer();

			if (type == 0) {
				stringBuffer.append("SELECT DATE_FORMAT(add_time, '%Y-%m-%d') AS addTime, ");
				stringBuffer.append("COUNT(DISTINCT user_id) AS userCount FROM bd_user_behavior_log ");
				stringBuffer.append("WHERE behavior_act = '进入首页' ");
				// 开始日期
				if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
					stringBuffer.append("AND add_time >= " + mapFilters.get("startAddTime") + " ");
				}

				// 结束日期
				if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
					stringBuffer.append("AND add_time <= " + mapFilters.get("endAddTime") + " ");
				}
				stringBuffer.append("GROUP BY DATE_FORMAT(add_time, '%Y-%m-%d') ");
				stringBuffer.append("LIMIT " + startIndex + ", " + pageSize + " ");
			} else {
				stringBuffer.append("SELECT count(a.addTime) from ( ");
				stringBuffer.append("SELECT DATE_FORMAT(add_time, '%Y-%m-%d') AS addTime ");
				stringBuffer.append("FROM bd_user_behavior_log ");
				stringBuffer.append("WHERE behavior_act = '进入首页' ");
				// 开始日期
				if (!StringManager.isNullOrEmpty(mapFilters.get("startAddTime"))) {
					stringBuffer.append("AND add_time >= " + mapFilters.get("startAddTime") + " ");
				}

				// 结束日期
				if (!StringManager.isNullOrEmpty(mapFilters.get("endAddTime"))) {
					stringBuffer.append("AND add_time <= " + mapFilters.get("endAddTime") + " ");
				}
				stringBuffer.append("GROUP BY DATE_FORMAT(add_time, '%Y-%m-%d') ) a");
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
