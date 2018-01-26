package com.gongsibao.panda.crm.workspace;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.service.allot.ActionCustomerTaskAddFollowRecord;
import com.gongsibao.crm.service.allot.ActionCustomerTaskAllot;
import com.gongsibao.crm.service.allot.ActionCustomerTaskSendMessageToLeader;
import com.gongsibao.crm.service.allot.ActionCustomerTaskSendMessageToSalesman;
import com.gongsibao.crm.service.allot.ActionCustomerTaskUpdateSalesmanInfo;
import com.gongsibao.crm.service.allot.ActionCustomerTaskVerifyStatus;

public class NCustomerTaskActionTest {

	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Before
	public void setup(){
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		this.resourceNode = resourceNodeService.byCode("GSB_CRM_Customer_Manager_Task_ALL");
	}

	
	@Test
	public void allotTask(){
		
		String pathName =  "gsb/crm/customer/task/allot";
		
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
			bean.setName("业务员配置信息的回写");
			bean.setType(ActionCustomerTaskUpdateSalesmanInfo.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getItems().add(bean);
		}
		/*bean = new Bean();
		{
			bean.toNew();
			bean.setName("任务的分配状态的回写");
			bean.setType(ActionCustomerTaskUpdateTaskStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(400);
			
			beanPath.getItems().add(bean);
		}*/
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("写入跟进记录");
			bean.setType(ActionCustomerTaskAddFollowRecord.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(500);
			
			beanPath.getItems().add(bean);
		}				
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("发送业务员通知");
			bean.setType(ActionCustomerTaskSendMessageToSalesman.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(600);
			
			beanPath.getItems().add(bean);
		}		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("发送业务员部门负责人通知");
			bean.setType(ActionCustomerTaskSendMessageToLeader.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(700);
			
			beanPath.getItems().add(bean);
		}
		
		beanPathService.save(beanPath);
	}
}
