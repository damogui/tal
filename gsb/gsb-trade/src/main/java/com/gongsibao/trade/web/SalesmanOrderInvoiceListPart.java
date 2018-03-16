package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/16.
 */
public class SalesmanOrderInvoiceListPart extends AdvancedListPart {

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

            String orderWhere = "order_id in(select pkid from so_order where (" + StringManager.join(" or ", filters) + ") )";

            return orderWhere;
        }
        //业务员
        if (parameter.getKey().equals("ywyName")) {
            return "order_id in (select pkid from so_order where owner_id in (select id from sys_permission_employee where name = '" + keyword + "') )";
        }
        //产品名称
        if (parameter.getKey().equals("prodName")) {
            return "order_id in (select pkid from so_order where prod_name like '%" + keyword + "%' )";
        }
        //审核状态
        if (parameter.getKey().equals("invoice_auditStatusId")) {
            return "invoice_id in (select pkid from so_invoice where audit_status_id in (" + keyword + ") )";
        }
        //发票申请人
        if (parameter.getKey().equals("creator")) {
            return "invoice_id in (select pkid from so_invoice where creator like '%" + keyword + "%' )";
        }
        //发票类型
        if (parameter.getKey().equals("invoice_typeId")) {
            return "invoice_id in (select pkid from so_invoice where type_id in (" + keyword + ") )";
        }
        //发票申请时间
        if (parameter.getKey().equals("createTime")) {
            List<String> invoiceCreateTime = new ArrayList<>();
            if (parameter.getValue1() != null) {
                invoiceCreateTime.add(" add_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                invoiceCreateTime.add(" add_time <= '" + parameter.getValue2().toString() + "'");
            }
            return "invoice_id in (select pkid from so_invoice where " + StringManager.join(" and ", invoiceCreateTime) + " )";
        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("soOrder.*,soOrder.owner.{id,name},invoice.*");
        List<OrderInvoiceMap> resList = (List<OrderInvoiceMap>) super.doQuery(oql);
        return resList;
    }
}
