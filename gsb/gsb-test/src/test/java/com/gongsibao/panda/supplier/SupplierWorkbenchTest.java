package com.gongsibao.panda.supplier;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.dic.WorkspaceType;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.workbench.supplier.SupplierWorkbench;

public class SupplierWorkbenchTest {

	IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	@Test
	public void createWorkspace() {

		IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
		ResourceNode node = service.byCode("Gsb_Supplier_System");
		PWorkspace workspace = new PWorkspace();
		{
			workspace.setResourceNode(node);
			workspace.setUrl("/supplier/workbench");
			workspace.setServiceController(SupplierWorkbench.class.getName());
			workspace.setJsController("org.netsharp.panda.Workbench");
			workspace.setName("供应商工作台");
			workspace.setType(WorkspaceType.WORKBENCH);
			workspace.toNew();
		}

		IPWorkspaceService workspaceServcie = ServiceFactory.create(IPWorkspaceService.class);
		workspaceServcie.save(workspace);
	}
}
