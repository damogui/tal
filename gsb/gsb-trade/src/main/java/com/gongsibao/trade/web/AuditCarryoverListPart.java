package com.gongsibao.trade.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

public class AuditCarryoverListPart extends AdvancedListPart{

	@Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("carryover.form_order_no = '" + keyword + "'");
            filters.add("carryover.to_order_no = '" + keyword + "'");
            return "(" + StringManager.join(" or ", filters) + ")";
        }

        return parameter.getFilter();
    }
}
