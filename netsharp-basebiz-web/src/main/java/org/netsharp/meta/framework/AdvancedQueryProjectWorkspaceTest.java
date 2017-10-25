//package org.netsharp.meta.framework;
//
//import org.junit.Test;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.panda.base.IPWorkspaceService;
//import org.netsharp.panda.core.AdvancedQueryProjectWorkspace;
//import org.netsharp.panda.entity.PWorkspace;
//import org.netsharp.resourcenode.IResourceNodeService;
//import org.netsharp.resourcenode.entity.ResourceNode;
//
//public class AdvancedQueryProjectWorkspaceTest {
//	
//	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
//	@Test
//	public void create() {
//
//		//暂时挂在这个资源下
//		IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
//		ResourceNode node = service.byCode("workbench");
//		this.createWorkspace(node);
//	}
//	
//	public void createWorkspace(ResourceNode node){
//		PWorkspace workspace = new PWorkspace();
//		{
//			workspace.setResourceNode(node);
//			workspace.setUrl("/advanced/query");
//			workspace.setServiceController(AdvancedQueryProjectWorkspace.class.getName());
//			workspace.setName("高级查询");
//			workspace.toNew();
//		}
//
//		IPWorkspaceService workspaceServcie = ServiceFactory.create(IPWorkspaceService.class);
//		workspaceServcie.save(workspace);
//	}
//}
