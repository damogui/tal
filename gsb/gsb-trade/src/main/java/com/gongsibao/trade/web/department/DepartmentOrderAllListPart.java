package com.gongsibao.trade.web.department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;


public class DepartmentOrderAllListPart extends BaseDepartmentListPart{

	ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    IOrderService noService = ServiceFactory.create(IOrderService.class);

    @Override
    public List<?> doQuery(Oql oql) {

        StringBuilder sb = new StringBuilder();
        sb.append("SoOrder.*,");
        sb.append("SoOrder.companyIntention.{pkid,name,full_name,company_name},");
        sb.append("SoOrder.customer.realName,");
        sb.append("SoOrder.owner.{id,name}");
        oql.setSelects(sb.toString());
        List<?> rows = orderService.queryList(oql);
        return rows;
    }


    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            SoOrder soOrder = ((SoOrder) list.get(i));
            //this.balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
            Integer balance = NumberUtils.toInt(soOrder.getPaidPrice()) + NumberUtils.toInt(soOrder.getCarryIntoAmount()) - NumberUtils.toInt(soOrder.getRefundPrice()) - NumberUtils.toInt(soOrder.getCarryAmount());
            ob2.get(i).put("balance", balance);
        }
        return json;
    }
	
	
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

        //分期申请时间
        if (parameter.getKey().equals("stageCreateTime")) {

            List<String> stageCreateTime = new ArrayList<>();
            if (parameter.getValue1() != null) {
                stageCreateTime.add(" create_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                stageCreateTime.add(" create_time <= '" + parameter.getValue2().toString() + "'");
            }

            return "pkid in (select order_id from so_order_stage where " + StringManager.join(" and ", stageCreateTime) + " )";
        }

        //分期申请人
        if (parameter.getKey().equals("stageCreator")) {
            return "pkid in (select order_id from so_order_stage where creator like '%" + keyword + "%')";
        }

        return parameter.getFilter();
    }
}
