package com.gongsibao.trade.web;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.trade.web.audithelper.SetOfBooksNameHelper;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AuditCarryoverListPart extends AdvancedListPart{

	@Override
    public String getFilterByParameter(FilterParameter parameter) {

        String filters = "";
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
        	filters = "form_id in(select id from so_order_carryover where (form_order_no like '%" + keyword + "%' or to_order_no like '%" + keyword + "%'))";
            return filters;
        }
        if (parameter.getKey().equals("pay.setOfBooksId")) {//付款账套的筛选

            return String.format("carryover.form_order_id  IN ( SELECT order_id FROM so_order_pay_map WHERE pay_id IN ( SELECT pkid FROM so_pay WHERE set_of_books_id='%s'))", keyword);

        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setOrderby("add_time DESC");
        return super.doQuery(oql);
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            AuditLog auditLog = ((AuditLog) list.get(i));
            String setOfBooksName = SetOfBooksNameHelper.getSetOfBooksNameByOrderId(auditLog.getCarryover().getFormOrderId());
            ob2.get(i).put("booksName", setOfBooksName);
        }
        return json;
    }
}
