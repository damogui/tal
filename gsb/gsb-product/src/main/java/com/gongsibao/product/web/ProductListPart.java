package com.gongsibao.product.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.product.base.IProductService;

public class ProductListPart extends ListPart {

	public Boolean updateEnabled(int id, Boolean state) {

		IProductService service = ServiceFactory.create(IProductService.class);
		return service.updateEnabled(id, state);
	}
}