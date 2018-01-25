package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.SupplierSessionManager;

public class SysSalesmanListPart extends ListPart {

	
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	
	/**   
	 * @Title: setDisabled   
	 * @Description: TODO(设置停用/启用状态)   
	 * @param: @param salesmanId
	 * @param: @param state
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean setDisabled(Integer salesmanId, boolean state) {

		return salesmanService.setDisabled(salesmanId, state);
	}

	/**   
	 * @Title: setReceiving   
	 * @Description: TODO(设置接单状态)   
	 * @param: @param salesmanId
	 * @param: @param state
	 * @param: @return      
	 * @return: boolean      
	 * @throws   
	 */
	public boolean setReceiving(Integer salesmanId, boolean state) {

		return salesmanService.setReceiving(salesmanId, state);
	}

	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();

		if (this.pdatagrid.getLazy()) {

			String filter = getRequest("filter");
			if (!StringManager.isNullOrEmpty(filter)) {

				return null;
			}
			String id = this.getRequest("id");
			if (StringManager.isNullOrEmpty(id)) {

				ss.add("parentId=0 or parentId is null");
			} else {
				ss.add("parentId=" + id);
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
