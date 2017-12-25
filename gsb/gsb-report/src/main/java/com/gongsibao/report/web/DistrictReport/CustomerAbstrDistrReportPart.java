package com.gongsibao.report.web.DistrictReport;

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

public class CustomerAbstrDistrReportPart extends ListPart{

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	HashMap<String, String> map;
	@Override
	public Object query() throws IOException {
		this.pdatagrid = this.context.getDatagrid();
		Integer getOragId=null;
		Oql oql = new Oql();
		Object json = null;
		this.map = getMapFilters();
		if (this.map.size() > 0) {
			String departmentId = map.get("departmentId");
			if (!StringManager.isNullOrEmpty(departmentId)) {
				getOragId = Integer.parseInt(departmentId.replace("'", "").trim());
			}
			List<BaseCustomerReportEntity> rows = getOrganList(getOragId);
			json = this.serialize(rows, oql);
		}
		return json;
	}

	/**
	 * 根据组织机构的集合获取 客户报表的实体
	 * @param 组织机构Id
	 * @return
	 */
	protected List<BaseCustomerReportEntity> getOrganList(Integer orgaId){
		List<BaseCustomerReportEntity> resultList = new ArrayList<>();
		DataTable getDt = getDataTable(map,orgaId);
	
		for (IRow row : getDt) {
			BaseCustomerReportEntity entity = new BaseCustomerReportEntity();
			Integer newCustomer = Integer.parseInt(row.getString("newCustomer"));
			Integer newShareCustomer = Integer.parseInt(row.getString("newShareCustomer"));
			String zone = row.getString("zone");
			String city = row.getString("city");
			String province = row.getString("province");
			entity.setNewCount(newCustomer);
			entity.setNewShareCount(newShareCustomer);
			entity.setZone(zone);
			entity.setCity(city);
			entity.setProvince(province);
			
			resultList.add(entity);
		}	
		return resultList;
	}
	
	/**
	 * 根据添加返回客户统计的 状态报表
	 * @param filterMap 时间段
	 * @param orgaId 组织机构id。null查询所有的
	 * @return
	 */
	protected DataTable getDataTable(HashMap<String, String> filterMap,Integer orgaId) {
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		//获取客户新增数量
		StringBuilder cmdNewCountSql=new StringBuilder();
		cmdNewCountSql.append("SELECT three.newCustomer,three.newShareCustomer");
		cmdNewCountSql.append(",dictA.`name` as 'zone'");
		cmdNewCountSql.append(",IFNULL(dictB.`name`,dictA.`name`) as 'city'");
		cmdNewCountSql.append(",IFNULL(dictC.`name`,IFNULL(dictB.`name`,dictA.`name`)) as 'province'");
		cmdNewCountSql.append(" from (SELECT one.city_id,");
		cmdNewCountSql.append("one.newCustomer,");
		cmdNewCountSql.append("IFNULL(two.newShareCustomer,0) as newShareCustomer");
		cmdNewCountSql.append(" from(SELECT c.city_id,");
		cmdNewCountSql.append("count(distinct c.pkid) newCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" LEFT JOIN uc_user_organization_map m");
		cmdNewCountSql.append(" ON c.follow_user_id = m.user_id");
		cmdNewCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewCountSql.append(" WHERE c.add_time <='"+endDate+"'");
		cmdNewCountSql.append(" and c.add_time >= '"+startDate+"'");
		cmdNewCountSql.append(" and c.city_id <> 0");
		if(orgaId!=null){
			cmdNewCountSql.append(" and o.pkid = "+orgaId);
		}
		cmdNewCountSql.append(" GROUP BY c.city_id)as one");
		cmdNewCountSql.append(" LEFT JOIN(SELECT c.city_id,");
		cmdNewCountSql.append("count(distinct s.customer_id) as newShareCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" LEFT JOIN crm_customer_share s");
		cmdNewCountSql.append(" on c.pkid=s.customer_id");
		cmdNewCountSql.append(" LEFT JOIN uc_user_organization_map m");
		cmdNewCountSql.append(" ON s.share_user_id = m.user_id");
		cmdNewCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewCountSql.append(" where s.share_time <='"+endDate+"'");
		cmdNewCountSql.append(" and s.share_time >= '"+startDate+"'");
		cmdNewCountSql.append(" and c.city_id <> 0");
		if(orgaId!=null){
			cmdNewCountSql.append(" and o.pkid = "+orgaId);
		}
		cmdNewCountSql.append(" GROUP BY c.city_id)as two");
		cmdNewCountSql.append(" ON one.city_id=two.city_id)as three");
		cmdNewCountSql.append(" LEFT JOIN bd_dict dictA");
		cmdNewCountSql.append(" on three.city_id = dictA.pkid");
		cmdNewCountSql.append(" LEFT JOIN bd_dict dictB");
		cmdNewCountSql.append(" on dictA.pid=dictB.pkid");
		cmdNewCountSql.append(" LEFT JOIN bd_dict dictC");
		cmdNewCountSql.append(" on dictC.pkid=dictB.pid");
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
}
