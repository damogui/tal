package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/14.
 */
public class OrderSalesmanRefundListPart extends AdvancedListPart {

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
        //订单付款状态
        if (parameter.getKey().equals("soOrder_payStatus")) {
            return "order_id in (select pkid from so_order where pay_status_id in (" + keyword + ") )";
        }
        //退款业绩创建人
        if (parameter.getKey().equals("payCreator")) {
            return "pay_id in (select pkid from so_pay where creator like '%" + keyword + "%' )";
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
            return "order_id in (select pkid from so_order where " + StringManager.join(" and ", soOrderCreateTime) + " )";
        }

        return parameter.getFilter();
    }


    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("refund.*,soOrder.*,soOrder.owner.{id,name},soOrder.companyIntention.{pkid,name,full_name,company_name},depRefunds.*,setOfBooks.{id,name},u8Bank.{id,name}");
        List<Refund> resList = (List<Refund>) super.doQuery(oql);
        for (Refund refund : resList) {
            NDepRefund depRefund = getDepRefundByUserId(refund.getDepRefunds());
            //获取我的回款业绩额
            if (depRefund != null) {
                refund.setMyDepRefundAmount(depRefund.getAmount());
            }
        }
        return resList;
    }


    //获取我的回款信息
    private NDepRefund getDepRefundByUserId(List<NDepRefund> depRefunds) {
        NDepRefund depRefund = null;
        Integer userId = SessionManager.getUserId();
        for (NDepRefund df : depRefunds) {
            if (NumberUtils.toInt(df.getEmployeeId()) == NumberUtils.toInt(userId)) {
                depRefund = df;
                break;
            }
        }
        return depRefund;
    }

}
