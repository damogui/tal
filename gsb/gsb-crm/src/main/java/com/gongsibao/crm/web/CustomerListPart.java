package com.gongsibao.crm.web;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;

/**
 * Created by zhangchao on 2018/2/8.
 */
public class CustomerListPart extends AdvancedListPart {

    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        if (parameter.getKey().equals("addOrderDate")) {

            String startAddOrderDate = parameter.getValue1() == null ? "" : parameter.getValue1().toString();
            if (!StringManager.isNullOrEmpty(startAddOrderDate)) {
                filters.add(" add_time>='" + startAddOrderDate + "' ");
            }
            String endAddOrderDate = parameter.getValue2() == null ? "" : parameter.getValue2().toString();
            if (!StringManager.isNullOrEmpty(endAddOrderDate)) {
                filters.add(" add_time<='" + endAddOrderDate + "' ");
            }
            String resFilters = "";
            if (CollectionUtils.isNotEmpty(filters)) {
                resFilters = " account_id IN(SELECT account_id FROM so_order WHERE " + StringManager.join(" and ", filters) + ") ";
            }
            return resFilters;
        }
        return parameter.getFilter();
    }

}
