package com.gongsibao.trade.web.platform;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;

public class PayPerformanceListPart extends SalesmanOrderReceivedListPart{

	@Override
	public String getDefaultFilter() {

		String defaultFilter = null;
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		List<Integer> supplierIdList = supplierService.getSupplierIdListByOwnerId(SessionManager.getUserId());
		if (supplierIdList != null && supplierIdList.size() > 0) {

			defaultFilter =  " supplier_id in (" + StringManager.join(",", supplierIdList) + ")";
		}
		System.out.println("回款业绩：" +defaultFilter);
		return defaultFilter;
	}
}
