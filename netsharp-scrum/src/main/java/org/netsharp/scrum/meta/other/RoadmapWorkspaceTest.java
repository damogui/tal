package org.netsharp.scrum.meta.other;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.scrum.entity.RoadmapDetail;

public class RoadmapWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		// 在子类中重定义
		entity = Roadmap.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "计划路线图";
		resourceNodeCode = Roadmap.class.getSimpleName();
		
		this.urlList="/scrum/roadmap/list";
		this.urlForm="/scrum/roadmap/form";
		
		this.listPartImportJs="/addins/scrum/RoadmapListPart.js";
		this.listPartJsController="org.netsharp.scrum.web.RoadmapListPart";
	}
	
	@Override
	protected void createFormWorkspace() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);

		PForm pform = this.createForm(node);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(meta.getName());
			workspace.setUrl(urlForm);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("roadmapForm");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setForm(pform);
			part.setDockStyle(DockType.TOP);
			part.setToolbar("panda/form/edit");
		}
		workspace.getParts().add(part);

		node = resourceService.byCode(RoadmapDetail.class.getSimpleName());
		Assert.assertNotNull(node);
		
		PDatagrid datagrid = this.createDetailGrid(node);
		part = new PPart();
		{
			part.toNew();
			part.setCode("details");
			part.setParentCode("roadmapForm");
			part.setRelationRole("details");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
		}
		workspace.getParts().add(part);

		workspaceService.save(workspace);
	}

	protected PDatagrid createDetailGrid(ResourceNode node) {

		PDatagrid datagrid = new PDatagrid();
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("项目跟进");
		}

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100, false);
		addColumn(datagrid, "date", "完成月份", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "seq", "顺序", ControlTypes.NUMBER_BOX, 60, false);
		addColumn(datagrid, "memoto", "描述", ControlTypes.TEXT_BOX, 800, false);
		
		addColumn(datagrid, "updateTime", "修改日期", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "updator", "修改人", ControlTypes.TEXT_BOX, 80, false);
		addColumn(datagrid, "createTime", "创建日期", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80, false);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, "项目列表");
		form.setColumnCount(3);
		
		String groupName = null;
		// 基本信息
		addFormField(form, "code", "编码",groupName, ControlTypes.TEXT_BOX, false, true);	
		addFormField(form, "name", "名称",groupName, ControlTypes.TEXT_BOX, false);
		
		
//		field = addFormField(form, "ykxManager.name", "项目负责人", "基本信息", ControlTypes.ReferenceBox, true);
//		{
//			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
//			PReference pr = referenceService.byCode("Employee");
//			field.setReference(pr);
//		}
		
        // 操作信息
		addFormField(form, "creator", "创建人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "updator", "最后修改人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updateTime", "最后修改时间",groupName, ControlTypes.DATE_BOX, false, true);

		return form;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		
		// 基本信息
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 120, true);
		
		// 操作信息
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 120, false);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATE_BOX, 120, false);
		
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName(meta.getName());
			queryProject.setResourceNode(node);
		}
		queryProject.setColumnCount(3);
		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("code");
			item.setHeader("编码");
			item.setControlType(ControlTypes.TEXT_BOX);

		}
		queryProject.getQueryItems().add(item);
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("name");
			item.setHeader("名称");
			item.setControlType(ControlTypes.TEXT_BOX);

		}
		queryProject.getQueryItems().add(item);
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("creator");
			item.setHeader("创建人");
			item.setControlType(ControlTypes.TEXT_BOX);

		}
		queryProject.getQueryItems().add(item);
		
		

		return queryProject;
	}

	@Test
	public void operation() {
		
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);

		service.addAllOperations(node);
	}
}