package com.gongsibao.report.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.report.customer.BaseCustomerReportEntity;
import com.gongsibao.entity.uc.Organization;
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
				ss.add("id=" + departmentId);
			}
		}

		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {
			ss.add(extraFilter);
		}

		String filter = StringManager.join(" and ", ss);
		Oql oql = new Oql();
		{
			oql.setSelects("id,parentId,shortName,isLeaf");
			oql.setType(Organization.class);
			oql.setFilter(filter);
		}

		DataTable dataTable = getDataTable(map);

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
			replenishEntity(entity, dataTable);
			rows.add(entity);
		}

		Object json = this.serialize(rows, oql);
		return json;
	}

	protected DataTable getDataTable(HashMap<String, String> filterMap) {

		List<String> dataList = this.getDate(filterMap);

		String startDate = dataList.get(0);
		String endDate = dataList.get(1);

		String cmdText = "";
		QueryParameters qps = new QueryParameters();
		{
			qps.add("@startDate", startDate, Types.VARCHAR);
			qps.add("@endDate", startDate, Types.VARCHAR);
		}
		organizationService.executeTable(cmdText, qps);
		return null;
	}

	protected List<String> getDate(HashMap<String, String> filterMap) {

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
