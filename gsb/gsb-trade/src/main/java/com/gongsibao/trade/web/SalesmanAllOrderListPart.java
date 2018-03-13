package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.u8.base.ISoOrderService;

/**
 * Created by win on 2018/3/2.
 */
public class SalesmanAllOrderListPart extends AdvancedListPart {
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

        return parameter.getFilter();
    }

    //转移/分配（包括批量转移/分配）
    public void orderTran(List<Integer> orderList, Integer toUserId) {
        orderService.orderTran(orderList, toUserId);
    }

    public int saveNDepReceivableBySoder(SoOrder entity) {
        orderService.save(entity);
        return 1;
    }
    
    /**
	 * 是否是分期付款的订单
	 * @param id
	 * @return
	 */
	public Boolean isStaged(Integer id){
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		SoOrder entity = orderService.queryFirst(oql);
		return entity.getStaged() == null ? false : entity.getStaged();
	}
}
