package com.gongsibao.trade.web;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.trade.web.audithelper.SetOfBooksNameHelper;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by win on 2018/3/23.
 */
public class AuditPayPerformanceListPart extends AdvancedListPart {


    @Override
    public String getFilterByParameter(FilterParameter parameter) {
        ArrayList<String> filters = new ArrayList<String> ();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1 ().toString ();
        String keyword2 = "";
        if (parameter.getValue2 () != null) {
            keyword2 = parameter.getValue2 ().toString ();//时间
        }

        if (parameter.getKey ().equals ("keyword")) {

            filters.add ("soOrder.no ='" + keyword + "'");
            filters.add ("soOrder.channel_order_no = '" + keyword + "'");
            filters.add ("soOrder.account_mobile = '" + keyword + "'");
            filters.add ("soOrder.account_name = '" + keyword + "'");
            filters.add ("soOrder.company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return " soOrder.pkid>0  and (" + StringManager.join (" or ", filters) + ")";
        }
        if (parameter.getKey ().equals ("payStatus")) {//付款状态

            return "soOrder.pay_status_id ='" + keyword + "'";

        }
        if (parameter.getKey ().equals ("name")) {//业务员

            return "soOrder.owner.name  like '%" + keyword + "%' ";
        }
        if (parameter.getKey ().equals ("orderCreateTime")) {//订单创建时间

            return   String.format ("soOrder.add_time >='%s' and  soOrder.add_time <'%s'",keyword,keyword2);


        }
        if (parameter.getKey().equals("pay.setOfBooksId")) {//付款账套的筛选

            return String.format("soOrder.pkid  IN ( SELECT order_id FROM so_order_pay_map WHERE pay_id IN ( SELECT pkid FROM so_pay WHERE set_of_books_id='%s'))", keyword);

        }

        return parameter.getFilter ();
    }
    @Override
    public List<?> doQuery(Oql oql) {
        StringBuilder selects = new StringBuilder ();
        selects.append ("auditLog.*,");
        selects.append ("auditLog.soOrder.*,");
        selects.append ("auditLog.employee.name,");
        selects.append ("auditLog.soOrder.owner.name");

       // selects.append ("auditLog.soOrder.nDepPay.*");

        IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);
        oql.setSelects (selects.toString ());
        oql.setOrderby("add_time DESC");
        List<AuditLog> auditLogs = auditLogService.queryList (oql);
        return auditLogs;
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            AuditLog auditLog = ((AuditLog) list.get(i));
            Integer orderId = auditLog.getFormId();
            String setOfBooksName = SetOfBooksNameHelper.getSetOfBooksNameByOrderId(orderId);
            ob2.get(i).put("booksName", setOfBooksName);
        }
        return json;
    }
}
