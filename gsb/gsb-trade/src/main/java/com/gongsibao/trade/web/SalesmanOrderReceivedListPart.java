package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.trade.base.INDepPayService;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

/**
 * Created by zhangchao on 2018/3/14.
 */
/*回款业绩*/
public class SalesmanOrderReceivedListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String> ();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1 ().toString ();
        if (parameter.getKey ().equals ("keyword")) {

            filters.add ("soOrder.no  like  '%" + keyword + "%' ");
//            filters.add ("order.channel_order_no = '" + keyword + "'");
//            filters.add ("order.account_mobile = '" + keyword + "'");
//            filters.add ("order.account_name = '" + keyword + "'");
//            filters.add ("order.company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return "(" + StringManager.join (" or ", filters) + ")";
        }
        if (parameter.getKey ().equals ("isOnlinePay")) {

            filters.add ("soOrder.is_online_pay ='" + keyword + "'");


        }


        return parameter.getFilter ();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        StringBuilder selects = new StringBuilder ();
        selects.append ("nDepPay.*,");
        selects.append ("nDepPay.soOrder.*");
        //selects.append ("auditLog.soOrder.depReceivable.*");

        INDepPayService nDepPayService = ServiceFactory.create (INDepPayService.class);
        oql.setSelects (selects.toString ());
        List<NDepPay> depPays = nDepPayService.queryList (oql);
        return depPays;
    }

}
