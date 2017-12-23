package com.gongsibao.report.web.sourcReport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.uc.base.IOrganizationService;

public class CustomerAbstrSourceReportPart extends ListPart{

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

	HashMap<String, String> map;
	
	@Override
	public Object query() throws IOException {
		Oql oql = new Oql();
		List<Integer> getOnLine = OnOffLine.getOnLine();
		List<Integer> getOffLine = OnOffLine.getOnLine();
		List<BaseCustomerReportEntity> rows = new ArrayList<BaseCustomerReportEntity>();
		BaseCustomerReportEntity entity = new BaseCustomerReportEntity();
		DataTable getDt = getDataTable(map);
		int onLineCustomerCount=0;
		int onLineSharCustomerCount=0;
		int offLineCustomerCount=0;
		int offLineSharCustomerCount=0;
		for (IRow row : getDt) {
			Integer pkid = Integer.parseInt(row.getString("pkid"));
			Integer newCustomer = Integer.parseInt(row.getString("newCustomer"));
			Integer newShareCustomer = Integer.parseInt(row.getString("newShareCustomer"));
			String name = row.getString("name");
			entity.setNewCount(newCustomer);
			entity.setNewShareCount(newShareCustomer);
			entity.setSourceName(name);
			if (getOnLine.equals(pkid)) {
				onLineCustomerCount = newCustomer + onLineCustomerCount;
				onLineSharCustomerCount = newShareCustomer + onLineSharCustomerCount;
			}else if(getOffLine.equals(pkid)){
				offLineCustomerCount = newCustomer + offLineCustomerCount;
				offLineSharCustomerCount = newShareCustomer + offLineSharCustomerCount;
			}
			rows.add(entity);
		}
		//填充2行统计
		for (int i=0;i<=1;i++) {
			switch (i) {
			case 0:
				entity.setLineName("线上");
				entity.setSourceName("总计");
				entity.setNewCount(onLineCustomerCount);
				entity.setNewShareCount(onLineSharCustomerCount);
				break;

			default:
				entity.setLineName("线下");
				entity.setSourceName("总计");
				entity.setNewCount(offLineCustomerCount);
				entity.setNewShareCount(offLineSharCustomerCount);
				break;
			}
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
		StringBuilder cmdStr=new StringBuilder();
		cmdStr.append("SELECT three.newCustomer,three.newShareCustomer,dic.`name`,dic.pkid ");
		cmdStr.append(" from (");
		cmdStr.append("SELECT one.customer_source as customerSource,one.pkid,one.days,sum(one.newCustomer) newCustomer,");
		cmdStr.append("sum(IFNULL(two.newShareCustomer,0)) newShareCustomer");
		cmdStr.append(" from(SELECT c.pkid,c.customer_source,");
		cmdStr.append("DATE_FORMAT(c.add_time,'%Y-%m-%d') as days,");
		cmdStr.append("count(distinct c.pkid) newCustomer");
		cmdStr.append(" from crm_customer c");
		cmdStr.append(" where c.add_time <='"+endDate+"' and c.add_time >= '"+startDate+"'");
		cmdStr.append(" group by days");
		cmdStr.append(" ORDER BY days desc) as one");
		cmdStr.append(" LEFT JOIN(SELECT");
		cmdStr.append(" DATE_FORMAT(s.share_time,'%Y-%m-%d') days,");
		cmdStr.append("count(distinct s.customer_id) as newShareCustomer");
		cmdStr.append(" from crm_customer c ");
		cmdStr.append(" LEFT JOIN crm_customer_share s");
		cmdStr.append(" on c.pkid=s.customer_id");
		cmdStr.append(" where s.share_time <='"+endDate+"' and s.share_time >= '"+startDate+"'");
		cmdStr.append(" group by days");
		cmdStr.append(" ORDER BY days desc) as two");
		cmdStr.append(" on one.days=two.days");
		cmdStr.append(" group by one.customer_source) as three");
		cmdStr.append(" LEFT JOIN bd_dict as dic");
		cmdStr.append("on three.customerSource=dic.pkid");
		
		DataTable dtNewCount = organizationService.executeTable(cmdStr.toString(), null);
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
