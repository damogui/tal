package com.gongsibao.crm.web.platform;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.supplier.base.ISupplierService;

public class PlatformCustomerAllListPart extends NCustomerAllListPart{

	@Override
	public String getDefaultFilter() {

		// 当前登录人为运营专员时，过滤服务商Id
		String defaultFilter = null;
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		List<Integer> supplierIdList = supplierService.getSupplierIdListByOwnerId(SessionManager.getUserId());
		if (supplierIdList != null && supplierIdList.size() > 0) {

			defaultFilter =  " pkid in (select customer_id from n_crm_customer_task where supplier_id in (" + StringManager.join(",", supplierIdList) + "))";
		}
		System.out.println("全部客户：" +defaultFilter);
		return defaultFilter;
	}
}
