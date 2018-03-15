package com.gongsibao.trade.web;

import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanOrderCarryoverListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("form_order_no like '%" + keyword + "%'");
            filters.add("to_order_no like '%" + keyword + "%'");
            return "(" + StringManager.join(" or ", filters) + ")";
        }


        return parameter.getFilter();
    }
}
