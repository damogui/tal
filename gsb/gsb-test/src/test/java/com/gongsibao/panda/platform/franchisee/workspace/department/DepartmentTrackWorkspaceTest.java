package com.gongsibao.panda.platform.franchisee.workspace.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.franchisee.FranchiseeTrack;

public class DepartmentTrackWorkspaceTest  extends WorkspaceCreationBase{


	@Override
	@Before
	public void setup() {

		urlList = "/bd/department/franchisee/track/list";
		urlForm = "/bd/department/franchisee/track/form";
		entity = FranchiseeTrack.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "跟进信息";
		resourceNodeCode = "BD_DEPARTMENT_Franchisee_Track";
		listFilter = "franchisee.department_id in ({departments})";
		formOpenMode =OpenMode.WINDOW;
		openWindowHeight = 450;
		openWindowWidth = 900;
		
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
 
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "trackType", "跟进类型", ControlTypes.ENUM_BOX, 80);
		column = addColumn(datagrid, "creator", "跟进 人", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "createTime", "跟进时间", ControlTypes.DATETIME_BOX, 130);
		addColumn(datagrid, "franchisee.name", "公司名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "intentionDegree", "意向度", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "trackProgress", "进度", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "nextTrackDate", "下次跟进时间", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 300);

		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setReadOnly(true);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;
		
		String groupName =null;
//		addFormFieldRefrence(form, "franchisee.name", "公司名称", groupName, Franchisee.class.getSimpleName(), false, false);
		addFormField(form, "intentionDegree", "意向度", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "trackProgress", "进度", groupName, ControlTypes.ENUM_BOX, false, true);
		addFormField(form, "nextTrackDate", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "creator", "跟进人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setFullColumn(true);
			formField.setHeight(100);
		}
		return form;
	}


	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "franchisee.name", "公司名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "intentionDegree", "意向度", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "trackProgress", "进度", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "creator", "跟进人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "跟进时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
