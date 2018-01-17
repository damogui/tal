package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

public class SysDepartmentTreegridPart extends TreegridPart {

	public void pathCode() {

		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
		service.generatePathCode(this.context.getEntityId());
	}

	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();
		
		//父类过滤条件
		String filter = super.getExtraFilter();
		if(!StringManager.isNullOrEmpty(filter)){

			ss.add(filter);
		}
		

		//过滤服务商ID
		Integer supplierId = SupplierSessionManager.getSupplierId();
		if (supplierId != null) {

			ss.add("supplierId=" + supplierId);
		}

		return StringManager.join(" and ", ss);
	}
}
