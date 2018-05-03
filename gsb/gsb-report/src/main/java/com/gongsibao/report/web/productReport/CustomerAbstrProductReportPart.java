package com.gongsibao.report.web.productReport;

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
import com.gongsibao.entity.report.customer.CustomerProductReport;
import com.gongsibao.report.web.RecursiveOrgaUtils;
import com.gongsibao.uc.base.IOrganizationService;

public class CustomerAbstrProductReportPart extends ListPart{

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

	HashMap<String, String> map;
	//临时存储前台传来的组织机构Id以及下属Id
	RecursiveOrgaUtils orgaUtils = new RecursiveOrgaUtils();
	String tempOrgaIds="";	
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
				tempOrgaIds = orgaUtils.getChildOragId(getOragId);
			}
			tempOrgaIds =StringManager.isNullOrEmpty(tempOrgaIds) ? null : tempOrgaIds.substring(0,tempOrgaIds.length()-1);
			List<BaseCustomerReportEntity> rows = getOrganList(tempOrgaIds);
			json = this.serialize(rows, oql);
		}
		return json;
	}

	/**
	 * 根据组织机构的集合获取 客户报表的实体
	 * @param 组织机构Id
	 * @return
	 */
	protected List<BaseCustomerReportEntity> getOrganList(String orgaId){
		List<BaseCustomerReportEntity> resultList = new ArrayList<>();
		DataTable getDt = getDataTable(map,orgaId);
		for (IRow row : getDt) {
			CustomerProductReport entity = new CustomerProductReport();
			Integer newCustomer = Integer.parseInt(row.getString("newCustomer"));
			Integer newShareCustomer = Integer.parseInt(row.getString("newShareCustomer"));
			String prodName = row.getString("prodName");
			String prodSubClass = row.getString("prodSubClass");
			String prodCategory = row.getString("prodCategory");
			
			entity.setNewCount(newCustomer);
			entity.setNewShareCount(newShareCustomer);
			entity.setProdName(prodName);
			entity.setProdSubClass(prodSubClass);
			entity.setProdCategory(prodCategory);
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
	protected DataTable getDataTable(HashMap<String, String> filterMap,String orgaId) {
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		//获取客户新增数量
		StringBuilder cmdNewCountSql=new StringBuilder();
		cmdNewCountSql.append("SELECT one.newCustomer,");
		cmdNewCountSql.append("IFNULL(two.newShareCustomer,0) newShareCustomer,");
		cmdNewCountSql.append("p.`name` as prodName,");
		cmdNewCountSql.append("dict.`name` as 'prodSubClass',");
		cmdNewCountSql.append("(SELECT `name` from bd_dict where pkid = dict.pid) as 'prodCategory'");
		cmdNewCountSql.append(" from (SELECT prod.product_id productId,");
		cmdNewCountSql.append("count(distinct c.pkid) newCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" LEFT JOIN crm_customer_prod_map prod");
		cmdNewCountSql.append(" on prod.customer_id = c.pkid");
		cmdNewCountSql.append(" LEFT JOIN uc_user_organization_map m");
		cmdNewCountSql.append(" ON c.follow_user_id = m.user_id");
		cmdNewCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewCountSql.append(" WHERE c.add_time <='"+endDate+"'");
		cmdNewCountSql.append(" and c.add_time >= '"+startDate+"'");
		if(orgaId!=null){
			cmdNewCountSql.append(" and o.pkid in("+orgaId+")");
		}
		cmdNewCountSql.append(" and product_id is not NULL");
		cmdNewCountSql.append(" GROUP BY prod.product_id)as one");
		cmdNewCountSql.append(" LEFT JOIN(SELECT prod.product_id productId,");
		cmdNewCountSql.append("count(distinct s.customer_id) as newShareCustomer");
		cmdNewCountSql.append(" from crm_customer c");
		cmdNewCountSql.append(" LEFT JOIN crm_customer_share s");
		cmdNewCountSql.append(" on c.pkid=s.customer_id");
		cmdNewCountSql.append(" LEFT JOIN crm_customer_prod_map prod");
		cmdNewCountSql.append(" on prod.customer_id = c.pkid");
		cmdNewCountSql.append(" LEFT JOIN uc_user_organization_map m");
		cmdNewCountSql.append(" ON s.share_user_id = m.user_id");
		cmdNewCountSql.append(" LEFT JOIN uc_organization o");
		cmdNewCountSql.append(" ON m.organization_id = o.pkid");
		cmdNewCountSql.append(" where s.share_time <='"+endDate+"'");
		cmdNewCountSql.append(" and s.share_time >= '"+startDate+"'");
		if(orgaId!=null){
			cmdNewCountSql.append(" and o.pkid in("+orgaId+")");
		}
		cmdNewCountSql.append(" and product_id is not NULL");
		cmdNewCountSql.append(" GROUP BY prod.product_id)as two");
		cmdNewCountSql.append(" on one.productId=two.productId");
		cmdNewCountSql.append(" LEFT JOIN prod_product as p");
		cmdNewCountSql.append(" on p.pkid=one.productId");
		cmdNewCountSql.append(" LEFT JOIN bd_dict dict");
		cmdNewCountSql.append(" on p.type_id=dict.pkid");
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
