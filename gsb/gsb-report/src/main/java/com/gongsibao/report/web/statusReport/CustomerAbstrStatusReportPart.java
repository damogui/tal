package com.gongsibao.report.web.statusReport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.uc.base.IOrganizationService;

public class CustomerAbstrStatusReportPart extends ListPart{

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

	HashMap<String, String> map;

	@Override
	public Object query() throws IOException {
		Oql oql = new Oql();
		List<BaseCustomerReportEntity> rows = new ArrayList<BaseCustomerReportEntity>();
		BaseCustomerReportEntity entity = new BaseCustomerReportEntity();
		DataTable getDt = getDataTable(map);
		for (IRow row : getDt) {
			Integer newCustomer = Integer.parseInt(row.getString("newCustomer"));
			Integer newShareCustomer = Integer.parseInt(row.getString("newShareCustomer"));
			String name = row.getString("name");
			entity.setNewCount(newCustomer);
			entity.setNewShareCount(newShareCustomer);
			entity.setStatusName(name);
			rows.add(entity);
		}
		Object json = this.serialize(rows, oql);
		return json;
	}

	protected DataTable getDataTable(HashMap<String, String> filterMap) {
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		//获取客户新增数量
		StringBuilder cmdNewCountSql=new StringBuilder();
		cmdNewCountSql.append("SELECT three.newCustomer,three.newShareCustomer,three.days,dic.name from(");
		cmdNewCountSql.append("SELECT one.follow_status as followStatus,one.pkid,one.days,sum(one.newCustomer) newCustomer,");
		cmdNewCountSql.append("sum(IFNULL(two.newShareCustomer,0)) newShareCustomer");
		cmdNewCountSql.append(" from(SELECT c.pkid,c.follow_status,");
		cmdNewCountSql.append("DATE_FORMAT(c.add_time,'%Y-%m-%d') as days,");
		cmdNewCountSql.append("count(distinct c.pkid) newCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" where c.add_time <='"+endDate+"' and c.add_time >= '"+startDate+"'");
		cmdNewCountSql.append(" group by days");
		cmdNewCountSql.append(" ORDER BY days desc) as one");
		cmdNewCountSql.append(" LEFT JOIN(SELECT");
		cmdNewCountSql.append(" DATE_FORMAT(s.share_time,'%Y-%m-%d') days,");
		cmdNewCountSql.append("count(distinct s.customer_id) as newShareCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" LEFT JOIN crm_customer_share s");
		cmdNewCountSql.append(" on c.pkid=s.customer_id");
		cmdNewCountSql.append(" where s.share_time <='"+endDate+"' and s.share_time >= '"+startDate+"'");
		cmdNewCountSql.append(" group by days");
		cmdNewCountSql.append(" ORDER BY days desc) as two");
		cmdNewCountSql.append(" on one.days=two.days");
		cmdNewCountSql.append(" group by one.follow_status) as three");
		cmdNewCountSql.append(" LEFT JOIN bd_dict as dic");
		cmdNewCountSql.append(" on three.followStatus = dic.pkid");
		DataTable dtNewCount = organizationService.executeTable(cmdNewCountSql.toString(), null);
		return dtNewCount;
	}

	protected HashMap<String, String>  getDate(HashMap<String, String> filterMap) {

		return null;
	}

	protected BaseCustomerReportEntity replenishEntity(BaseCustomerReportEntity entity, DataTable dataTable) {		
		
		return entity;
	}

	protected HashMap<String, String> getMapFilters() throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();
		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {

			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
			String[] ss = filter.split(" AND ");
			if (ss.length > 0) {

				for (String s : ss) {

					String[] a = s.split("=");
					map.put(a[0].trim(), a[1].trim());
				}
				return map;
			}
		}
		return map;
	}

	@Override
	protected String getExtraFilter() {

		String id = this.getRequest("id");
		if (StringManager.isNullOrEmpty(id)) {

			return "is_enabled=1 and (pid=0 or pid is null)";
		}
		return "is_enabled=1 and pid=" + id;
	}
}
