package com.gongsibao.trade.web;

import com.gongsibao.entity.bd.dic.AuditLogType;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/23.
 */
public class AuditInvoiceListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        String filterPrefix = "type_id=" + AuditLogType.Fbsq.getValue() + " and form_id in ";

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
            filters.add("no ='" + keyword + "'");
            filters.add("channel_order_no = '" + keyword + "'");
            filters.add("account_mobile = '" + keyword + "'");
            filters.add("account_name = '" + keyword + "'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in ( select pkid from so_order where " + StringManager.join(" or ", filters) + "))";
        }
        //发票类型
        if (parameter.getKey().equals("invoice_typeId")) {
            return filterPrefix + " (select pkid from so_invoice where type_id in (" + keyword + "))";
        }
        //产品名称
        if (parameter.getKey().equals("prodName")) {
            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in (select pkid from so_order where prod_name like '%" + keyword + "%' ))";
        }
        //订单创建时间
        if (parameter.getKey().equals("orderCreateTime")) {
            List<String> soOrderCreateTime = new ArrayList<>();
            if (parameter.getValue1() != null) {
                soOrderCreateTime.add(" add_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                soOrderCreateTime.add(" add_time <= '" + parameter.getValue2().toString() + "'");
            }

            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in (select pkid from so_order where " + StringManager.join(" and ", soOrderCreateTime) + " ))";
        }

        return parameter.getFilter();
    }
}