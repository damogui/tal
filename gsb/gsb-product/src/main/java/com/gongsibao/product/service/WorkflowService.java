package com.gongsibao.product.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Workflow;
import com.gongsibao.product.base.IWorkflowService;

@Service
public class WorkflowService extends PersistableService<Workflow> implements IWorkflowService {

    public WorkflowService(){
        super();
        this.type=Workflow.class;
    }

    @Override
	public Boolean updateEnabled(Integer id, Boolean state) {

		String cmdText = "UPDATE prod_workflow set is_enabled=? where pkid=?";
		QueryParameters qps = new QueryParameters();
		qps.add("enabled",state,Types.BOOLEAN);
		qps.add("id", id, Types.INTEGER);
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}
}