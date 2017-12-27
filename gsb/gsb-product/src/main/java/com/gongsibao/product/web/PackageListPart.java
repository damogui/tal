package com.gongsibao.product.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.product.base.IProductPackageService;

public class PackageListPart extends ListPart {

	public Boolean updateEnabled(int id, Boolean state) {

		IProductPackageService service = ServiceFactory.create(IProductPackageService.class);
		return service.updateEnabled(id, state);
	}
}