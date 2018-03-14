package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.NumberUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
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
public class SalesmanOrderReceivedListPart extends AdvancedListPart {

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
        if (parameter.getKey().equals("pay_offlineAuditStatus")) {
            return "pay_id in (select pkid from so_pay where offline_audit_status_id in (" + keyword + ") )";
        }
        //订单付款状态
        if (parameter.getKey().equals("soOrder_payStatus")) {
            return "order_id in (select pkid from so_order where pay_status_id in (" + keyword + ") )";
        }
        //支付状态
        if (parameter.getKey().equals("pay_successStatus")) {
            return "pay_id in (select pkid from so_pay where success_status_id in (" + keyword + ") )";
        }

        //回款业绩创建人
        if (parameter.getKey().equals("payCreator")) {
            return "pay_id in (select pkid from so_pay where creator like '%" + keyword + "%' )";
        }

        //是否一笔多单
        if (parameter.getKey().equals("pay_payForOrderCount")) {
            return "pay_id in (select pkid from so_pay where pay_for_order_count in (" + keyword + ") )";
        }

        //支付类型
        if (parameter.getKey().equals("pay_payWayType")) {
            return "pay_id in (select pkid from so_pay where pay_way_type_id in (" + keyword + ") )";
        }

        //回款业绩创建时间
        if (parameter.getKey().equals("payCreateTime")) {
            List<String> depPayCreateTimeWhere = new ArrayList<>();
            if (parameter.getValue1() != null) {
                depPayCreateTimeWhere.add(" add_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                depPayCreateTimeWhere.add(" add_time <= '" + parameter.getValue2().toString() + "'");
            }
            return "pay_id in (select pkid from so_pay where " + StringManager.join(" and ", depPayCreateTimeWhere) + " )";
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
        oql.setSelects("pay.*,soOrder.*,soOrder.owner.{id,name}*,soOrder.companyIntention.{pkid,name,full_name,company_name}*,depPays.*,pay.u8Bank.{id,name},pay.setOfBooks.{id,name}");
        List<OrderPayMap> resList = (List<OrderPayMap>) super.doQuery(oql);
        for (OrderPayMap orderPayMap : resList) {
            NDepPay depPay = getDepPayByUserId(orderPayMap.getDepPays());
            //获取我的回款业绩额
            if (depPay != null) {
                orderPayMap.setMyOrderCutAmount(depPay.getAmount());
            }
        }
        return resList;
    }


    //获取我的回款信息
    private NDepPay getDepPayByUserId(List<NDepPay> depPays) {
        NDepPay depPay = null;
        Integer userId = SessionManager.getUserId();
        for (NDepPay dp : depPays) {
            if (NumberUtils.toInt(dp.getEmployeeId()) == NumberUtils.toInt(userId)) {
                depPay = dp;
                break;
            }
        }
        return depPay;
    }

}
