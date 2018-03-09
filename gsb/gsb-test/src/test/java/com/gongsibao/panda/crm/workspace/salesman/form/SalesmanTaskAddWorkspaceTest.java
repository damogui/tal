package com.gongsibao.panda.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerTaskAddFormPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.panda.operation.workspace.crm.form.TaskAddWorkspaceTest;

public class SalesmanTaskAddWorkspaceTest extends TaskAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/salesman/task/add";
		listPartName = formPartName = "新增任务";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_TASK_ADD";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/operation/crm/js/task-add-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		ss.add("/gsb/supplier/crm/base/js/task-follow-base.ctrl.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = NCustomerTaskAddFormPart.class.getName();
		formServiceController = NCustomerTaskAddFormPart.class.getName();
		
		productsDetailResourceNodeCode = "CRM_SALESMAN_Products";
	}
}
