package com.gongsibao.trade.web;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;

import java.util.List;

public class AuditCarryoverListPart extends AdvancedListPart{

	@Override
    public String getFilterByParameter(FilterParameter parameter) {

        String filters = "";
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
        	filters = "form_id in(select id from so_order_carryover where (form_order_no like '%" + keyword + "%' or to_order_no like '%" + keyword + "%'))";
            return filters;
        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setOrderby("add_time DESC");
        return super.doQuery(oql);
    }
}
