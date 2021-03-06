package com.gongsibao.panda.supplier.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.panda.platform.operation.workspace.crm.form.TaskEditWorkspaceTest;

public class SalesmanTaskEditWorkspaceTest extends TaskEditWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/salesman/task/edit";
		listPartName = formPartName = "商机信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_TASK_EDIT";
		
		List<String> ss = new ArrayList<String>();
		
		
		ss.add("/gsb/platform/operation/crm/js/task-add-form.part.js");
		ss.add("/gsb/supplier/crm/base/js/task-base-edit-form.part.js");
		ss.add("/gsb/supplier/crm/salesman/js/task-edit-form.part.js");
		ss.add("/gsb/supplier/crm/base/js/task-follow-base.ctrl.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		
		formJsController = NCustomerTaskEditFormPart.class.getName();
		formServiceController = NCustomerTaskEditFormPart.class.getName();
		
		productsDetailResourceNodeCode = "CRM_SALESMAN_Products";
		foolowDetailResourceNodeCode = "CRM_SALESMAN_Foolow";
		notifyDetailResourceNodeCode = "CRM_SALESMAN_Notify";
		changeDetailResourceNodeCode = "CRM_SALESMAN_Change";
		inspectionDetailResourceNodeCode ="CRM_SALESMAN_Inspection";
		
		taskFollowDetailPart = "com.gongsibao.crm.web.SalesmanTaskFollowDetailPart";
	}
	
	@Test
	public void detailPart() {
		
	}
}
