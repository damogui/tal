package com.gongsibao.crm.web;

import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

public class SysSalesmanListPart extends ListPart{
//
//	ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
//
//	/**
//	 * @Title: open
//	 * @Description: TODO(开户)
//	 * @param: @param supplierId
//	 * @param: @return
//	 * @return: Boolean
//	 * @throws
//	 */
//	public Boolean openAccount(Integer supplierId){
//
//		return supplierService.openAccount(supplierId);
//	}
//
//	/**
//	 * @Title: close
//	 * @Description: TODO(销户)
//	 * @param: @param supplierId
//	 * @param: @return
//	 * @return: Boolean
//	 * @throws
//	 */
//	public Boolean closeAccount(Integer supplierId){
//
//		return supplierService.closeAccount(supplierId);
//	}

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
