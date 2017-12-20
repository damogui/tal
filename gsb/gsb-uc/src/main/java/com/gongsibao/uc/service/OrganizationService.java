package com.gongsibao.uc.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.uc.Organization;
import com.gongsibao.uc.base.IOrganizationService;

@Service
public class OrganizationService extends PersistableService<Organization> implements IOrganizationService {

	public OrganizationService() {
		super();
		this.type = Organization.class;
	}

	@Override
	public List<Integer> getChildDepartmentIdList(Integer departmentId) {

		List<Integer> idList = new ArrayList<Integer>();
		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("pkid");
			builder.from(MtableManager.getMtable(this.type).getTableName());
			builder.where("pid=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("pid", departmentId, Types.INTEGER);

		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
		for (IRow row : dataTable) {

			Integer id = row.getInteger("pkid");
			idList.add(id);
		}
		return idList;
	}

	@Override
	public Boolean hasChildDepartment(Integer departmentId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("pid=?");
			oql.getParameters().add("pid", departmentId, Types.INTEGER);
		}

		Boolean isHas = this.queryCount(oql) > 0;
		return isHas;
	}

	@Override
	public Integer getParentDepartementId(Integer departmentId) {

		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("pid");
			builder.from(MtableManager.getMtable(this.type).getTableName());
			builder.where("pkid=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("departmentId", departmentId, Types.INTEGER);

		DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
		return dataTable.get(0).getInteger("pid");
	}

	@Override
	public List<Integer> getLateralDepartementIdList(Integer departmentId) {

		Integer parentDepartementId = getParentDepartementId(departmentId);
		List<Integer> idList = getChildDepartmentIdList(parentDepartementId);
		return idList;
	}
}