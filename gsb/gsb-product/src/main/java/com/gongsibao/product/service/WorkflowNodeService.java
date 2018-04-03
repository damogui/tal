package com.gongsibao.product.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.product.base.IWorkflowNodeService;

@Service
public class WorkflowNodeService extends PersistableService<WorkflowNode> implements IWorkflowNodeService {

	public WorkflowNodeService() {
		super();
		this.type = WorkflowNode.class;
	}

	@Override
	public Integer getWorkflowNodeMaxVersion(Integer prodId, Integer cityId) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT MAX(version) as version FROM prod_workflow_node WHERE ");
		sqlBuilder.append("workflow_id IN ( ");
		sqlBuilder.append("SELECT w.pkid FROM prod_workflow w ");
		sqlBuilder.append("LEFT JOIN prod_workflow_bd_dict_map m ON w.pkid = m.workflow_id ");
		sqlBuilder.append("WHERE w.product_id = ? AND m.city_id = ? )");
		QueryParameters qps = new QueryParameters();
		{
			qps.add("prodId", prodId, Types.INTEGER);
			qps.add("cityId", cityId, Types.INTEGER);
		}
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), qps);

		Integer version = null;
		try {
			for (IRow row : dataTable) {

				version = row.getInteger("version");

			}
		} catch (Exception e) {

		}
		return version;
	}

	@Override
	public List<WorkflowNode> queryWorkflowNodeList(Integer prodId, Integer cityId, Integer version) {

		String filter = "	workflow_id IN "
				+ "( SELECT w.pkid FROM prod_workflow w "
				+ "LEFT JOIN prod_workflow_bd_dict_map m ON w.pkid = m.workflow_id "
				+ " WHERE w.product_id = ? AND m.city_id = ? )"
				+ " and version=?";
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter(filter);
			oql.setOrderby("sort");

			oql.getParameters().add("prodId", prodId, Types.INTEGER);
			oql.getParameters().add("cityId", cityId, Types.INTEGER);
			oql.getParameters().add("version", version, Types.INTEGER);
		}
		return this.queryList(oql);
	}
}