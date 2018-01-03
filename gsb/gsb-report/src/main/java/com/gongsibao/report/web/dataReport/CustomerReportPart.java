package com.gongsibao.report.web.dataReport;

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
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.report.web.RecursiveOrgaUtils;
import com.gongsibao.uc.base.IOrganizationService;

public class CustomerReportPart extends TreegridPart {

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

	HashMap<String, String> map;

	@Override
	public Object query() throws IOException {

		// departmentId='324' AND year ='2017'
		List<String> ss = new ArrayList<String>();
		this.map = getMapFilters();
		if (this.map.size() > 0) {
			String departmentId = map.get("departmentId");
			if (!StringManager.isNullOrEmpty(departmentId)) {
				ss.add("pkid=" + departmentId);
			}
		}
		
		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {
			ss.add(extraFilter);
		}

		String filter = StringManager.join(" and ", ss);
		//当查询条件存在并且触发点击行时，需要过滤掉查询条件（id='3' and is_enabled=1 or pid='3' 不能同时满足）
		if(filter.contains("delete")){
			 int indexStart = filter.indexOf("delete") + 6;
			 filter = filter.substring(indexStart);
		}
		Oql oql = new Oql();
		{
			oql.setSelects("id,parentId,shortName,isLeaf");
			oql.setType(Organization.class);
			oql.setFilter(filter);
		}

		//DataTable dataTable = getDataTable(map);

		List<Organization> list = organizationService.queryList(oql);
		List<BaseCustomerReportEntity> rows = new ArrayList<BaseCustomerReportEntity>();
		for (Organization o : list) {
			BaseCustomerReportEntity entity = new BaseCustomerReportEntity();
			{
				entity.setId(o.getId());
				entity.setParentId(o.getParentId());
				entity.setOrgName(o.getShortName());
				entity.setIsLeaf(o.getIsLeaf());
				
			}
			//递归组织机构Id以及下属Id
			RecursiveOrgaUtils orgaUtils = new RecursiveOrgaUtils();
		    String tempOrgaIds = orgaUtils.getChildOragId(o.getId());
			
			Map<String, String> getResultMap = getDataTable(map,tempOrgaIds.substring(0,tempOrgaIds.length()-1));
			entity.setNewCount(Integer.parseInt(getResultMap.get("newCount")));
			entity.setNewShareCount(Integer.parseInt(getResultMap.get("newShareCount")));
			entity.setDate(getResultMap.get("date"));
			entity.setYear(Integer.parseInt(getResultMap.get("year")));
			entity.setMonth(Integer.parseInt(getResultMap.get("month")));
			//replenishEntity(entity, dataTable);
			rows.add(entity);
		}
		
		Object json = this.serialize(rows, oql);
		return json;
	}

	protected Map<String, String> getDataTable(HashMap<String, String> filterMap,String orgaId) {

		//返回相应的数据 key:newCount-新增数量、newShareCount-分享数量 、date
		Map<String, String> resultMap =new HashMap<>();
		
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		//判断时间范围的的请求，返回相应的数据
		resultMap.put("data", startDate.split(" ")[0]+"至"+endDate.split(" ")[0]);
		resultMap.put("year",filterMap.get("year")==null?"0":filterMap.get("year"));
		resultMap.put("month",filterMap.get("month")==null?"0":filterMap.get("month"));
		
		//获取客户新增数量
		StringBuilder cmdNewCountSql=new StringBuilder();
		cmdNewCountSql.append("SELECT count(DISTINCT c.pkid) count");
		cmdNewCountSql.append(" FROM(SELECT m.user_id userId from uc_user_organization_map m");
		cmdNewCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewCountSql.append(" where o.pkid in("+orgaId+")");
		cmdNewCountSql.append(" GROUP BY m.user_id) as one");
		cmdNewCountSql.append(" LEFT JOIN crm_customer c");
		cmdNewCountSql.append(" on c.follow_user_id = one.userId");
		cmdNewCountSql.append(" WHERE c.add_time >= '"+startDate+"'");
		cmdNewCountSql.append(" AND c.add_time <= '"+endDate+"'");
		DataTable dtNewCount = organizationService.executeTable(cmdNewCountSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("newCount", row.getString("count"));
		}
		//获取客户新增分享数量
		StringBuilder cmdNewShareCountSql=new StringBuilder();
		cmdNewShareCountSql.append("SELECT count(DISTINCT c.pkid) count");
		cmdNewShareCountSql.append(" FROM(SELECT m.user_id userId");
		cmdNewShareCountSql.append(" from uc_user_organization_map m");
		cmdNewShareCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewShareCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewShareCountSql.append(" where o.pkid in("+ orgaId +")");
		cmdNewShareCountSql.append(" GROUP BY m.user_id) as one");
		cmdNewShareCountSql.append(" LEFT JOIN crm_customer c");
		cmdNewShareCountSql.append(" on c.follow_user_id = one.userId");
		cmdNewShareCountSql.append(" LEFT JOIN  crm_customer_share s");
		cmdNewShareCountSql.append(" on s.customer_id=c.pkid");
		cmdNewShareCountSql.append(" WHERE s.share_time >= '"+startDate+"'");
		cmdNewShareCountSql.append(" AND s.share_time <= '"+endDate+"'");
		
		DataTable dtNewShareCount = organizationService.executeTable(cmdNewShareCountSql.toString(), null);
		for (IRow row : dtNewShareCount) {
			resultMap.put("newShareCount", row.getString("count"));
		}
		return resultMap;
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
		if (StringManager.isNullOrEmpty(id) && StringManager.isNullOrEmpty(map.get("departmentId"))) {
			return "is_enabled=1 and (pid=0 or pid is null)";
		}else if(StringManager.isNullOrEmpty(id) && !StringManager.isNullOrEmpty(map.get("departmentId"))){
			return "is_enabled=1";
		}else if(!StringManager.isNullOrEmpty(id) && !StringManager.isNullOrEmpty(map.get("departmentId"))){
			//当查询条件存在并且触发点击行时，需要过滤掉查询条件（id='3' and is_enabled=1 or pid='3' 不能同时满足）
			return "delete is_enabled=1 and pid=" + id;
		}
		return "is_enabled=1 and pid=" + id;
	}
}
