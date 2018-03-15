package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.OrderProd;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/15.
 */
public class SalesmanOrderContractListPart extends AdvancedListPart {

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

        return parameter.getFilter();
    }


    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("contract.*,soOrder.*,soOrder.owner.{id,name},soOrder.products.*");
        List<Contract> resList = (List<Contract>) super.doQuery(oql);
        for (Contract contract : resList) {
            Integer contractPrice = 0;
            List<OrderProd> products = contract.getSoOrder().getProducts();
            for (OrderProd orderProd : products) {
                contractPrice = contractPrice + orderProd.getPrice();
            }
            contract.setContractPrice(contractPrice);

            if (contract.getHasDataFee()) {
                //合同总额- 业绩总额
                contract.setDataFee(contract.getSoOrder().getPayablePrice() - contractPrice);
            }

        }
        return resList;
    }


}
