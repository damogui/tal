package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;


/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanStagingListPart extends SalesmanAllOrderListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("SoOrder.no ='" + keyword + "'");
            filters.add("SoOrder.channel_order_no = '" + keyword + "'");
            filters.add("SoOrder.account_mobile = '" + keyword + "'");
            filters.add("SoOrder.account_name = '" + keyword + "'");
            filters.add("SoOrder.company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return "(" + StringManager.join(" or ", filters) + ")";
        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setOrderby("add_time DESC");
        return super.doQuery(oql);
    }
}
