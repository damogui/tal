package com.gongsibao.crm.web;

import com.gongsibao.supplier.web.panda.BaseSupplierTreePart;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.panda.commerce.TreePart;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/1/22.
 */
public class SysSalesmanTreePart extends TreePart {

    @Override
    public String getExtraFilter() {

        List<String> ss = new ArrayList<String>();
        String filter = getRequest("filter");
        if (!StringManager.isNullOrEmpty(filter)) {

            return null;
        }
        String id = this.getRequest("id");
        if (StringManager.isNullOrEmpty(id)) {

            ss.add("(parent_id=0 or parent_id is null)");
        }else{
            ss.add("parentId="+id);
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
