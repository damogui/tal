package com.gongsibao.trade.web.interactive;

import com.gongsibao.utils.SupplierSessionManager;

public class OperationPoolListPart extends MyInChargeListPart {

	public String getDefaultFilter() {

		// 子查询比较慢
		Integer supplierId = SupplierSessionManager.getSupplierId();
		String filter = "pkid in (select order_prod_id from so_order_prod_organization_map where supplier_id =" + supplierId + ") ";
		return filter;
	}

}
