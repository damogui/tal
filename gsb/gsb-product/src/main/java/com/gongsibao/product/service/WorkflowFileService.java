package com.gongsibao.product.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.product.base.IWorkflowFileService;

@Service
public class WorkflowFileService extends PersistableService<WorkflowFile> implements IWorkflowFileService {

	public WorkflowFileService() {
		super();
		this.type = WorkflowFile.class;
	}

	@Override
	public List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId) {

		Integer version = this.getWorkflowFileMaxVersion(prodId, cityId);
		return this.queryWorkflowMustFileList(prodId, cityId, version);
	}

	@Override
	public List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId, Integer version) {

		return this.queryWorkflowMustFileList(prodId, cityId, version, true);
	}

	@Override
	public Integer getWorkflowFileMaxVersion(Integer prodId, Integer cityId) {

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT MAX(version) as version FROM prod_workflow_file WHERE ");
		sqlBuilder.append("prod_workflow_id IN ( ");
		sqlBuilder.append("SELECT w.pkid FROM prod_workflow w ");
		sqlBuilder.append("LEFT JOIN prod_workflow_bd_dict_map m ON w.pkid = m.workflow_id where w.is_enabled = 1 ");
		sqlBuilder.append("and w.product_id = ? AND m.city_id = ? )");
		QueryParameters qps = new QueryParameters();
		{
			qps.add("prodId", prodId, Types.INTEGER);
			qps.add("cityId", cityId, Types.INTEGER);
		}
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), qps);

		Integer version = null;
		;
		try {
			for (IRow row : dataTable) {

				version = row.getInteger("version");
			}
		} catch (Exception e) {

		}
		return version;
	}

	@Override
	public List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId) {

		Integer version = this.getWorkflowFileMaxVersion(prodId, cityId);
		return this.queryWorkflowFileList(prodId, cityId, version);
	}

	@Override
	public List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId, Integer version) {

		return this.queryWorkflowMustFileList(prodId, cityId, version, null);
	}

	private List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId, Integer version, Boolean must) {

		QueryParameters qps = new QueryParameters();
		qps.add("prodId", prodId, Types.INTEGER);
		qps.add("cityId", cityId, Types.INTEGER);
		qps.add("version", version, Types.INTEGER);
		List<String> ss = new ArrayList<String>();
		ss.add("prod_workflow_id IN " + "( SELECT w.pkid FROM prod_workflow w " + "LEFT JOIN prod_workflow_bd_dict_map m ON w.pkid = m.workflow_id "
				+ " WHERE w.is_enabled = 1 and w.product_id = ? AND m.city_id = ? )");
		ss.add("version=? ");
		if (must != null) {

			ss.add("must=? ");
			qps.add("must", must, Types.BOOLEAN);
		}
		String filter = StringManager.join(" and ", ss);
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter(filter);
			oql.setOrderby("sort");
			oql.setParameters(qps);
		}
		return this.queryList(oql);
	}
}