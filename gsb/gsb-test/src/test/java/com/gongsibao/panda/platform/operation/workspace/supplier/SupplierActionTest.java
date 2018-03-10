package com.gongsibao.panda.platform.operation.workspace.supplier;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.supplier.service.action.close.ActionSupplierDisableAccount;
import com.gongsibao.supplier.service.action.open.ActionSupplierCreateAdmin;
import com.gongsibao.supplier.service.action.open.ActionSupplierSendMessage;
import com.gongsibao.supplier.service.action.open.ActionSupplierVerifyStatus;

public class SupplierActionTest {

	private ResourceNode resourceNode = null;
	private IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	
	@Before
	public void setup(){
		IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
		this.resourceNode = resourceNodeService.byCode("GSB_Operation_Supplier");
	}
	
	@Test
	public void openAccount(){
		
		String pathName =  "gsb/operation/supplier/account/open";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("服务商开户");
		}
		
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("状态验证");
			bean.setType(ActionSupplierVerifyStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getCodons().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("创建服务商管理员帐号");
			bean.setType(ActionSupplierCreateAdmin.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getItems().add(bean);
		}
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("发送通知");
			bean.setType(ActionSupplierSendMessage.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(300);
			
			beanPath.getItems().add(bean);
		}
		
		beanPathService.save(beanPath);
	}
	
	@Test
	public void closeAccount(){
		
		String pathName =  "gsb/operation/supplier/account/close";
		
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("服务商销户");
		}
		
		Bean bean = new Bean();
		{
			bean.toNew();
			bean.setName("状态验证");
			bean.setType(com.gongsibao.supplier.service.action.close.ActionSupplierVerifyStatus.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(100);
			
			beanPath.getCodons().add(bean);
		}
		
		bean = new Bean();
		{
			bean.toNew();
			bean.setName("停用所有帐号");
			bean.setType(ActionSupplierDisableAccount.class.getName());
			bean.setResourceNode(resourceNode);
			bean.setSeq(200);
			
			beanPath.getCodons().add(bean);
		}
		
		beanPathService.save(beanPath);
	}
}
