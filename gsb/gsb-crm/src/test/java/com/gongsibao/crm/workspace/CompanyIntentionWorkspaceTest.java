package com.gongsibao.crm.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.CompanyIntention;

public class CompanyIntentionWorkspaceTest extends WorkspaceCreationBase{


	@Before
	public void setup() {

		entity = CompanyIntention.class;
		urlList = "/crm/customer/company/list";
		urlForm = "/crm/customer/company/form";
		listPartName = formPartName = "企业信息";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = CompanyIntention.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("企业信息列表");
		}
		
		addColumn(datagrid, "companyName", "公司名称", ControlTypes.TEXT_BOX, 150);

		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);

		addFormField(form, "companyName", "公司名称", null, ControlTypes.TEXT_BOX, true);

		return form;
	}
	

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "companyName", "公司名称", ControlTypes.TEXT_BOX);

		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		//operationService.addOperation(node,OperationTypes.delete);
	}
}
