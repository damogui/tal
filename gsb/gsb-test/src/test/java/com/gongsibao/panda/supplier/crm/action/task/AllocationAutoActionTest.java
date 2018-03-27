package com.gongsibao.panda.supplier.crm.action.task;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.service.action.task.allocation.auto.ActionAutoAllocationPersist;
import com.gongsibao.crm.service.action.task.allocation.auto.ActionAutoAllocationRecordLog;
import com.gongsibao.crm.service.action.task.allocation.auto.ActionAutoAllocationSendMessage;
import com.gongsibao.crm.service.action.task.allocation.auto.ActionAutoAllocationUpdateTaskStatus;
import com.gongsibao.crm.service.action.task.allocation.auto.ActionAutoAllocationVerify;

public class AllocationAutoActionTest {

	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Before
	public void setup(){
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		this.resourceNode = resourceNodeService.byCode("CRM_SALESMAN_CUSTOMER");
	}

	
	@Test
	public void allotTask(){
		
		String pathName =  "gsb/crm/task/allocation/auto";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("商机自动分配");
		}
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("状态验证");
			bean.setType(ActionAutoAllocationVerify.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("分配逻辑");
			bean.setType(ActionAutoAllocationPersist.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("商机的分配状态的回写");
			bean.setType(ActionAutoAllocationUpdateTaskStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("写入跟进记录");
			bean.setType(ActionAutoAllocationRecordLog.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(400);
			
			beanPath.getItems().add(bean);
		}
		/*bean = new Bean();
		{
			bean.toNew();
			bean.setName("发送通知消息");
			bean.setType(ActionAutoAllocationSendMessage.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(500);
			
			beanPath.getItems().add(bean);
		}*/
		
		beanPathService.save(beanPath);
	}
}
