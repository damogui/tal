package com.gongsibao.panda.platform.operation.workspace.crm.form;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;

public class SwtCustomerEditWorkspaceTest  extends CustomerEditWorkspaceTest{
	
	@Before
	public void setup() {
		super.setup();
		urlForm = "/crm/platform/customer/swt/edit";
	}

	@Test
	public void createFormToolbar() {
		
	}
	
	protected void addDetailGridPart(PWorkspace workspace) {

		super.addDetailGridPart(workspace);

		PPart part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
		}

		for(PPart part2 :workspace.getParts()){
			
			part2.setDockStyle(DockType.DOCUMENTHOST);
		}
	}
}
