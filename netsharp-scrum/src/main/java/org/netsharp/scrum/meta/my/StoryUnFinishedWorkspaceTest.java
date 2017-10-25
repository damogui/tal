package org.netsharp.scrum.meta.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.scrum.meta.story.StoryWorkspaceTest;
import org.netsharp.scrum.web.StoryFormPart;

public class StoryUnFinishedWorkspaceTest extends StoryWorkspaceTest {
	
	@Override
	@Before
	public void setup() {	
		
		// 在子类中重定义
		urlList = "/scrum/my/unfinished/list";
		urlForm = "/scrum/my/unfinished/form";
		entity = Story.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "未完成任务";
		resourceNodeCode="my-scrum-unfinished";
		
		this.listPartJsController = "org.netsharp.scrum.web.StoryListPart";
		this.listPartImportJs = "/addins/scrum/StoryListPart.js";
		this.listToolbarPath = "story/list/toolbar";
		formServiceController = StoryFormPart.class.getName();
		
		String tableName = MtableManager.getMtable(StoryParticipant.class).getTableName();
		listFilter = "status in (1,3) and story.status != 10 and (owner_id = '{userId}' or id in (select story_id from "+tableName+" where participant_id = '{userId}'))";
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
		
//		item = new PQueryItem();
//		{
//			item.toNew();
//			item.setPropertyName("cat.name");
//			item.setHeader("分类");
//			item.setControlType(ControlTypes.ReferenceBox);
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

		return queryProject;
	}
}
