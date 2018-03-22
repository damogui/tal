package com.gongsibao.product.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.product.base.IProductService;
import com.gongsibao.product.base.IWorkflowService;

public class ProductListPart extends ListPart {

	public Boolean updateEnabled(int id, Boolean state) {

		IProductService service = ServiceFactory.create(IProductService.class);
		return service.updateEnabled(id, state);
	}
	
	public Boolean updatePojectEnabled(int id, Boolean state){
		IWorkflowService service = ServiceFactory.create(IWorkflowService.class);
		return service.updateEnabled(id, state);
	}
	
}