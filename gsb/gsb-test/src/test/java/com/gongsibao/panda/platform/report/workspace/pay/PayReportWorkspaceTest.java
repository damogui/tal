package com.gongsibao.panda.platform.report.workspace.pay;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.igirl.tm.HelpBook;

public class PayReportWorkspaceTest extends WorkspaceCreationBase{
	@Before
	public void setup() {

		entity = HelpBook.class;
		urlList = "/report/pay/day";
		resourceNodeCode = "GSB_Report_Pay_Pay";
	}

	protected void createListWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		if (node == null) {
			node = resourceService.byCode(entity.getSimpleName());
		}
		Mtable meta = MtableManager.getMtable(this.entity);
		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(meta.getName());
			workspace.setUrl(urlList);
			workspace.setCached(listIsCache);
			workspace.setRedirectUrl("/nav/gsb/platform/report/pay");
		}
		createListWorkspaceParts(node, meta, workspace);
		workspaceService.save(workspace);
	}
	
	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		if (node == null) {
			node = resourceService.byCode(entity.getSimpleName());
		}
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
