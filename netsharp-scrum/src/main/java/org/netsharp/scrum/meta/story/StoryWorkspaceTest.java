package org.netsharp.scrum.meta.story;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.scrum.entity.Project;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.scrum.web.StoryFormPart;
import org.netsharp.util.ReflectManager;

public class StoryWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/scrum/story/list";
		urlForm = "/scrum/story/form";
		resourceNodeCode = Story.class.getSimpleName();
		entity = Story.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "任务管理";

		this.listPartJsController = "org.netsharp.scrum.web.StoryListPart";
		this.listPartImportJs = "/addins/scrum/StoryListPart.js";
		this.listToolbarPath = "story/list/toolbar";
		formServiceController = StoryFormPart.class.getName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);

		datagrid.setPageSize(25);

		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "iteration.name", "迭代", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "project.name", "项目", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "project.product.name", "产品", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "project.deploy.name", "上线计划", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "project.deploy.deployTime", "上线日期", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "owner.name", "负责人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "participantStr", "参与者", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "bizUser.name", "客户", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "csr", "客户满意度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "urgency", "紧急性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "actualHours", "耗时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "taskProperties", "工作性质", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "capacityIndex", "能力指数", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "startTime", "开始时间", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "endTime", "结束时间", ControlTypes.TEXT_BOX, 100);

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
			queryProject.setName("研发任务");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(4);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("name");
			item.setHeader("名称");
			item.setControlType(ControlTypes.TEXT_BOX);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("iteration.name");
			item.setHeader("迭代");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Iteration.class.getSimpleName());
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
			//Assert.isTrue(reference!=null);
			item.setReference(reference);

			queryProject.getQueryItems().add(item);
		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("owner.name");
			item.setHeader("负责人");
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
			item.setPropertyName("bizUser.name");
			item.setHeader("客户");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			item.setReference(reference);

			queryProject.getQueryItems().add(item);
		}

//		item = new PQueryItem();
//		{
//			item.toNew();
//			item.setPropertyName("cat.name");
//			item.setHeader("分类");
//			item.setControlType(ControlTypes.REFERENCE_BOX);
//
//			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
//			PReference reference = referenceService.byCode(ProjectCategory.class.getSimpleName());
//			//Assert.isTrue(reference!=null);
//			item.setReference(reference);
//
//			queryProject.getQueryItems().add(item);
//		}

		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("status");
			item.setHeader("任务状态");
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
			item.setPropertyName("organization.pathName");
			item.setHeader("部门");
			item.setControlType(ControlTypes.REFERENCE_BOX);

			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode("Organization-Department");
			//Assert.isTrue(reference!=null);

			item.setReference(reference);
			item.setRefFilter("Organization.parentId=2");

			queryProject.getQueryItems().add(item);
		}

		return queryProject;
	}
	
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(StoryParticipant.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(true);
		datagrid.setShowCheckbox(true);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;
		addColumn(datagrid, "participant.name", "参与者", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "memoto", "说明", ControlTypes.NUMBER_BOX, 300);
		PPart part = new PPart();
		{
			part.setCode("participants");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("participants");
			part.setResourceNode(node);
			part.setName("参与者");
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);

			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		PPart part1 = workspace.getParts().get(0);
		{
			part1.setCode(ReflectManager.getFieldName(meta.getCode()));
			part1.setName("任务信息");
			part1.setStyle("height:340px;");
			part1.setDockStyle(DockType.DOCUMENTHOST);
		}
	}
	
	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("参与者");
		
		PFormField field = null;
		field = addFormFieldRefrence(form, "participant.name", "参与者", null,  Employee.class.getSimpleName(), true, false);
		addFormField(form, "estimateHours", "估时(小时)", null, ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "memoto", "说明", null, ControlTypes.TEXTAREA, false, false);
		return form;
	}
	
//
//	@Override
//	protected void addDetailFormPart(PWorkspace workspace) {
//
//		ResourceNode node = this.resourceService.byCode(StoryParticipant.class.getSimpleName());
//
//		PDatagrid datagrid = new PDatagrid(node, "任务参与人");
//
//		PDatagridColumn column = addColumn(datagrid, "participant.name", "参与者", ControlTypes.REFERENCE_BOX, 150);
//		{
//			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
//			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
//			//Assert.isTrue(reference!=null);
//			column.setReference(reference);
//		}
//		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.DECIMAL_BOX, 100);
//
//		//
//		PPart part = new PPart();
//		{
//			part.toNew();
//
//			part.setCode("participants");
//			part.setParentCode(Story.class.getSimpleName().toLowerCase());
//			part.setRelationRole("participants");
//			part.setResourceNode(node);
//			part.setPartTypeId(PartType.DETAIL_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.BOTTOM);
//			part.setStyle("height:200px;");
//			part.setToolbar("panda/datagrid/detail");
//		}
//
//		workspace.getParts().add(part);
//	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, "任务表单");
		{
			form.setColumnCount(3);
		}
		
		PFormField field = addFormField(form, "code", "编码", "基本信息", ControlTypes.TEXT_BOX, true, true);
		field = addFormField(form, "name", "名称", "基本信息", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "startTime", "开始时间", "基本信息", ControlTypes.DATE_BOX, false, false);
		addFormField(form, "endTime", "结束时间", "基本信息", ControlTypes.DATE_BOX, false, false);
		field = addFormField(form, "iteration.name", "迭代", "基本信息", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Iteration.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		
		field = addFormField(form, "project.name", "项目", "基本信息", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Project.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}

		// --------------------------------
		field = addFormField(form, "organization.pathName", "部门", "任务安排", ControlTypes.REFERENCE_BOX, false, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode("Organization-Department");
			//Assert.isTrue(reference!=null);

			field.setReference(reference);
			field.setRefFilter("Organization.parentId=2");
		}

		field = addFormField(form, "owner.name", "负责人", "任务安排", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}

		//
		field = addFormField(form, "bizUser.name", "客户", "客户", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Employee.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);

			//field.setTooltip("业务负责人负责给客户满意度打分");
		}
		field = addFormField(form, "csr", "客户满意度", "客户", ControlTypes.ENUM_BOX, false, false);

		//
		field = addFormField(form, "taskProperties", "工作性质", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "capacityIndex", "能力指数", "工作性质", ControlTypes.ENUM_BOX, true, false);

		field = addFormField(form, "importance", "重要性", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "urgency", "紧急性", "工作性质", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "estimateHours", "估时（小时）", "任务内容", ControlTypes.DECIMAL_BOX, true, false);
		field = addFormField(form, "actualHours", "耗时（小时）", "任务内容", ControlTypes.DECIMAL_BOX, false, false);
		// --
		field = addFormField(form, "status", "任务状态", "任务内容", ControlTypes.ENUM_BOX, false, false);
		field = addFormField(form, "content", "内容", "任务内容", ControlTypes.TEXTAREA, false, false);
		field.setHeight(200);
		field.setFullColumn(true);

		return form;
	}
}
