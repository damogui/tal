package com.gongsibao.panda.supplier.igirl.workspace.tm.common;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.igirl.tm.baseinfo.NclMap;
import com.gongsibao.igirl.tm.web.NclMapPart;

public class NclMapWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup(){
		urlList = "/igirl/nclmap/list";
		urlForm = "/igirl/nclmap/form";
		entity = NclMap.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "尼斯映射";
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		resourceNodeCode = "NCL_All_NclMap";

		formServiceController = NclMapPart.class.getName();
		formJsController = NclMapPart.class.getName();
		formJsImport = "/gsb/igirl/js/nclmap.form.part.js";

	}
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {//创建数据表格
		PDatagrid pid = super.createDatagrid(node);
		pid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
		pid.setName("尼斯映射");
		PDatagridColumn column = null;
		addColumn(pid, "nclBeachId", "尼斯期间", ControlTypes.TEXT_BOX, 100);
		addColumn(pid, "nclOneId", "商标大类", ControlTypes.TEXT_BOX, 100);
		addColumn(pid, "nclTwoContent", "商标小类", ControlTypes.TEXT_BOX, 800);
		return pid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {//创建表单
		PForm form = new PForm(node, this.formPartName);
		form.setResourceNode(node);
		form.setName(this.meta.getName() + "表单");
		form.setColumnCount(1);//每行列数
		PFormField formField = null;
		String groupName = null;
		formField = addFormFieldRefrence(form, "nclBatch.code", "期间", groupName, "nclBatch", false, true);
		formField = addFormFieldRefrence(form, "nclOne.name", "商标大类", null, "NCLOne", true, false);
		{
			formField.setTroikaTrigger("controllernclMap .nclOneChange(newValue,oldValue);");
			formField.setWidth(150);
		}
		formField = addFormField(form, "nclTwoContent", "所选小类", groupName, ControlTypes.TEXTAREA, false, true);
		{
			formField.setHeight(150);
			formField.setFullColumn(true);
		}
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "nclBeachId", "尼斯期间", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "nclOneId", "商标大类", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {//操作
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
