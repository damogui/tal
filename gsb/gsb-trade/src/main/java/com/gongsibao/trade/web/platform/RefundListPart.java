package com.gongsibao.trade.web.platform;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.trade.web.OrderSalesmanRefundListPart;

public class RefundListPart extends OrderSalesmanRefundListPart{

	@Override
	public String getDefaultFilter() {

		String defaultFilter = null;
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		List<Integer> supplierIdList = supplierService.getSupplierIdListByOwnerId(SessionManager.getUserId());
		if (supplierIdList != null && supplierIdList.size() > 0) {

			defaultFilter =  " soOrder.supplier_id in (" + StringManager.join(",", supplierIdList) + ")";
		}
		System.out.println("全部订单：" +defaultFilter);
		return defaultFilter;
	}
}
