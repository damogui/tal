package org.netsharp.scrum.meta.bug;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.scrum.entity.Project;

public class BugWorkspaceTest  extends WorkspaceCreationBase {
	
	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}
	
	
	@Override
	@Before
	public void setup() {
		
		// 在子类中重定义
		urlList = "/bug/list";
		urlForm = "/bug/form";

		entity = Bug.class;
		meta = MtableManager.getMtable(entity);
		this.resourceNodeCode="bugList";
		listPartName =formPartName=  "研发缺陷";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(25);

		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "project.name", "项目", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, true);
		
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100, true);
		addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "urgency", "紧急性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "actualHours", "耗时(小时)", ControlTypes.TEXT_BOX, 100);
		
		addColumn(datagrid, "testor.name", "测试员", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "developer.name", "开发者", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "putor.name", "提出人", ControlTypes.TEXT_BOX, 100, true);
		
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("缺陷");
			queryProject.setResourceNode(node);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("name");
			item.setHeader("缺陷名称");
			item.setControlType(ControlTypes.TEXT_BOX);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("testor.name");
			item.setHeader("测试员");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			item.setReference(reference);

			queryProject.getQueryItems().add(item);
		}
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("developer.name");
			item.setHeader("开发者");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			item.setReference(reference);

			queryProject.getQueryItems().add(item);
		}
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("project.name");
			item.setHeader("项目");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Project.class.getSimpleName());
			item.setReference(reference);
			queryProject.getQueryItems().add(item);
		}
		

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("status");
			item.setHeader("项目状态");
			item.setControlType(ControlTypes.ENUM_BOX);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("importance");
			item.setHeader("重要性");
			item.setControlType(ControlTypes.ENUM_BOX);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("urgency");
			item.setHeader("紧急性");
			item.setControlType(ControlTypes.ENUM_BOX);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("createTime");
			item.setHeader("创建时间");
			item.setControlType(ControlTypes.DATE_BOX);
			queryProject.getQueryItems().add(item);
		}

		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		PFormField field = null;

		
		field = addFormField(form, "project.name", "项目", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Project.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "status", "状态", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "importance", "重要性", ControlTypes.ENUM_BOX, false, false);
		field = addFormField(form, "urgency", "紧急性", ControlTypes.ENUM_BOX, false, false);
		
		field = addFormField(form, "estimateHours", "估时（小时）",  ControlTypes.DECIMAL_BOX, true, false);
		field = addFormField(form, "actualHours", "耗时（小时）",ControlTypes.DECIMAL_BOX, false, false);
		
//		field = addFormField(form, "putor.name", "提出人", ControlTypes.REFERENCE_BOX, true, false);
//		{
//			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
//			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
//			//Assert.isTrue(reference!=null);
//			field.setReference(reference);
//		}

		field = addFormField(form, "testor.name", "测试人员", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		
		field = addFormField(form, "developer.name", "开发人员", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		
		field = addFormField(form, "filePath", "图片",ControlTypes.QINIUUPLOAD, false, false);
		
		field = addFormField(form, "content", "缺陷内容", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		field = addFormField(form, "service", "开发说明", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		return form;
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		
		IOperationService service = ServiceFactory.create(IOperationService.class);

		service.addOperations(node);
	}
}
