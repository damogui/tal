package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.service.action.allocation.ActionAllocationSaveLog;
import com.gongsibao.crm.service.action.autoAllot.ActionCustomerTaskAllot;
import com.gongsibao.crm.service.action.autoAllot.ActionCustomerTaskSendMessage;
import com.gongsibao.crm.service.action.autoAllot.ActionCustomerTaskUpdateTaskStatus;
import com.gongsibao.crm.service.action.autoAllot.ActionCustomerTaskVerifyStatus;

public class AutoAllotActionTest {

	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Before
	public void setup(){
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		this.resourceNode = resourceNodeService.byCode("GSB_CRM_Customer_Manager_Task_ALL");
	}

	
	@Test
	public void allotTask(){
		
		String pathName =  "gsb/crm/customer/task/autoAllot";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("任务分配");
		}
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("状态验证");
			bean.setType(ActionCustomerTaskVerifyStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("分配逻辑");
			bean.setType(ActionCustomerTaskAllot.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getItems().add(bean);
		}	
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("任务的分配状态的回写");
			bean.setType(ActionCustomerTaskUpdateTaskStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("写入跟进记录");
			bean.setType(ActionAllocationSaveLog.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(400);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("发送通知消息");
			bean.setType(ActionCustomerTaskSendMessage.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(500);
			
			beanPath.getItems().add(bean);
		}
		
		beanPathService.save(beanPath);
	}
}
