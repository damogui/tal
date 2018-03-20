package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.u8.base.ISoOrderService;

/**
 * 订单审核/分期审核
 * @author yxb 2018/3/20
 *
 */
public class AuditStageListPart extends AdvancedListPart{
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);

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
