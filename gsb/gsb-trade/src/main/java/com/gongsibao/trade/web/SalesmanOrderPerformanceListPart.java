package com.gongsibao.trade.web;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;

/**
 * Created by zhangchao on 2018/3/12.
 */
public class SalesmanOrderPerformanceListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {
        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("no like '%" + keyword + "%'");
            filters.add("channel_order_no like '%" + keyword + "%'");
            filters.add("account_mobile like '%" + keyword + "%'");
            filters.add("customer_name like '%" + keyword + "%'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");

            String orderWhere = " order_id IN (SELECT pkid FROM so_order WHERE " + StringManager.join(" or ", filters) + " ) ";

            return orderWhere;
        }
        //业务员
        if (parameter.getKey().equals("ywyName")) {
            return " order_id IN (SELECT pkid FROM so_order WHERE owner_id IN(SELECT id FROM sys_permission_employee WHERE NAME = '" + keyword + "' ))";
        }

        //支付状态
        if (parameter.getKey().equals("order_payStatus")) {
            return " order_id IN (SELECT pkid FROM so_order WHERE pay_status_id IN(" + keyword + "))";
        }

        //产品名称
        if (parameter.getKey().equals("prodName")) {
            return " order_id IN (SELECT pkid FROM so_order WHERE prod_name like '%" + keyword + "%') ";
        }

        return parameter.getFilter();
    }
}
