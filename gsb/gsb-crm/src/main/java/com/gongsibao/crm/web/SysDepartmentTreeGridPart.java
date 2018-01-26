package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.supplier.web.panda.BaseSupplierTreeGridPart;
import com.gongsibao.utils.SupplierSessionManager;

public class SysDepartmentTreeGridPart extends BaseSupplierTreeGridPart {

	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();

		if(this.pdatagrid.getLazy()){

			String filter = getRequest("filter");
			if (!StringManager.isNullOrEmpty(filter)) {
				
				return null;
			}
			String id = this.getRequest("id");
			if (StringManager.isNullOrEmpty(id)) {

				ss.add("parentId=0 or parentId is null");
			}else{
				ss.add("parentId="+id);
			}
		}
		
		// 过滤服务商ID
		String requestSupplierId = this.getRequest("supplierId");
		if (!StringManager.isNullOrEmpty(requestSupplierId)) {

			ss.add("supplierId=" + requestSupplierId);
		} else {

			Integer supplierId = SupplierSessionManager.getSupplierId();
			if (supplierId != null) {

				ss.add("supplierId=" + supplierId);
			}
		}
		return StringManager.join(" and ", ss);
	}
}
