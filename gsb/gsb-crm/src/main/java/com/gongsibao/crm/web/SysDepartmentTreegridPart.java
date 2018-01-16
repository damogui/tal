package com.gongsibao.crm.web;

import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.TreegridPart;

public class SysDepartmentTreegridPart extends TreegridPart{

	public void pathCode() {
		
		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
		service.generatePathCode(this.context.getEntityId());
		
	}
}
