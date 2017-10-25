package org.netsharp.scrum.meta.story;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
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
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.web.StoryTraceFormPart;

public class StoryTraceWorkspaceTest extends WorkspaceCreationBase {
	
	@Override
	@Before
	public void setup() {		
		// 在子类中重定义
		urlList = "/scrum/trace/list";
		urlForm = "/scrum/trace/form";
		
		
		entity = StoryTrace.class;
		this.resourceNodeCode=entity.getSimpleName();
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "项目跟进";
		
		this.formServiceController=StoryTraceFormPart.class.getName();
		this.formJsController=StoryTraceFormPart.class.getName();
		this.formJsImport="/addins/scrum/StoryTraceFormPart.js";
	} 

	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(25);
		
		addColumn(datagrid, "story.name", "项目", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "content", "内容", ControlTypes.TEXTAREA, 500, true);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("项目跟进");
			queryProject.setResourceNode(node);
		}

		PQueryItem item =  new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("story.name");
			item.setHeader("项目");
			item.setControlType(ControlTypes.REFERENCE_BOX);
			
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Story.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			item.setReference(reference);
			
			queryProject.getQueryItems().add(item);
		}
		
		item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("creator");
			item.setHeader("创建人");
			item.setControlType(ControlTypes.TEXT_BOX);
			
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

		field = addFormField(form, "story.name", "任务", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Story.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		
		field = addFormField(form, "content", "内容", ControlTypes.TEXTAREA, false, false);
		field.setHeight(300);
		field.setWidth(600);
		
		return form;
	}
}
