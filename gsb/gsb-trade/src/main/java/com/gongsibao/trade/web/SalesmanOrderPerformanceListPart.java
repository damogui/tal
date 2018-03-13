package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        //业务员
        if (parameter.getKey().equals("ywyName")) {
            return "owner_id in (select id from sys_permission_employee where name = '" + keyword + "')";
        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("depReceivable.*,soOrder.*,owner.*,companyIntention.*");
        List<SoOrder> resList = (List<SoOrder>) super.doQuery(oql);
        for (SoOrder order : resList) {
            List<NDepReceivable> depReceivableList = order.getDepReceivable();
            NDepReceivable depReceivable = getDepReceivableByUserId(depReceivableList);
            if (depReceivable != null) {
                order.setDepReceivableAmount(depReceivable.getAmount());
                order.setDepReceivableCreator(depReceivable.getCreator());
                order.setDepReceivableCreateTime(depReceivable.getCreateTime());
            }
        }
        return resList;
    }

    private NDepReceivable getDepReceivableByUserId(List<NDepReceivable> depReceivableList) {
        Integer userId = SessionManager.getUserId();
        NDepReceivable res = null;
        for (NDepReceivable depReceivable : depReceivableList) {
            if (NumberUtils.toInt(depReceivable.getEmployeeId()) == NumberUtils.toInt(userId)) {
                res = depReceivable;
                break;
            }
        }

        return res;

    }

}
