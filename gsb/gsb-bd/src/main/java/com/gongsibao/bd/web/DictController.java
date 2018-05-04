package com.gongsibao.bd.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.base.IDictService;

public class DictController extends ListPart {

	IDictService dictService = ServiceFactory.create(IDictService.class);

	@Override
	public boolean delete(String ids) {

		if (StringManager.isNullOrEmpty(ids)) {
			return false;
		}

		return dictService.delete(ids);
	}
}
