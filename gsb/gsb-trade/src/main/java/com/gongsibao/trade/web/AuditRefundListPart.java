package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.NumberUtils;

public class AuditRefundListPart extends AdvancedListPart{

	 @Override
	    public String getFilterByParameter(FilterParameter parameter) {

	        ArrayList<String> filters = new ArrayList<String>();
	        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
	        String keyword = parameter.getValue1().toString();
	        if (parameter.getKey().equals("keyword")) {

	            filters.add("fefund.soOrder.no ='" + keyword + "'");
	            filters.add("fefund.soOrder.channel_order_no = '" + keyword + "'");
	            filters.add("fefund.soOrder.account_mobile = '" + keyword + "'");
	            filters.add("fefund.soOrder.account_name = '" + keyword + "'");
	            filters.add("fefund.soOrder.company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
	            return "(" + StringManager.join(" or ", filters) + ")";
	        }

	        return parameter.getFilter();
	    }
	 
	 @Override
	    public List<?> doQuery(Oql oql) {
	        oql.setSelects("auditLog.*,fefund.*,fefund.soOrder.*");
	        List<AuditLog> resList = (List<AuditLog>) super.doQuery(oql);
	        return resList;
	    }

	    @Override
	    protected Object serialize(List<?> list, Oql oql) {
	        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
	        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
	        for (int i = 0; i < ob2.size(); i++) {
	        	
	        	AuditLog auditLog = ((AuditLog) list.get(i));
	        	SoOrder soOrder  = auditLog.getFefund().getSoOrder();
	            //this.balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
	            Integer balance = NumberUtils.toInt(soOrder.getPaidPrice()) + NumberUtils.toInt(soOrder.getCarryIntoAmount()) - NumberUtils.toInt(soOrder.getRefundPrice()) - NumberUtils.toInt(soOrder.getCarryAmount());
	            Integer toBePaidPrice = soOrder.getPayablePrice().intValue() - balance;
	            ob2.get(i).put("fefund_soOrder_toBePaidPrice", toBePaidPrice);
	        	
	            //ob2.get(i).put("fefund_soOrderNo_payablePrice", getOrderInfoByPropertyName(list, i, "invoice_soOrderNo"));
	        }
	        return json;
	    }
}
