package com.gongsibao.trade.web.interactive;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatGroupQueryList extends AdvancedListPart {

    IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);
    IOrderProdOrganizationMapService organizationService = ServiceFactory.create(IOrderProdOrganizationMapService.class);

    @Override
    public String getFilterByParameter(FilterParameter parameter) {
        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、公司名称、客户手机号)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
            filters.add("no = '" + keyword + "'");
            filters.add("account_mobile = '" + keyword + "'");
            String compamyWhere = "company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )";
            filters.add(compamyWhere);
            return "((OrderProd." + compamyWhere + " ) or order_id in ( select pkid from so_order where " + StringManager.join(" or ", filters) + "))";
        }
        return super.getFilterByParameter(parameter);
    }

    @Override
    public List<?> doQuery(Oql oql) {

        StringBuffer sqlSb = new StringBuffer();
        sqlSb.append("orderProd.*,");
        sqlSb.append("orderProd.processStatus.{pkid,name},");
        sqlSb.append("orderProd.owner.{id,name},");
        sqlSb.append("orderProd.department.{id,name},");
        sqlSb.append("orderProd.companyIntention.{pkid,name,full_name,company_name},");
        sqlSb.append("orderProd.soOrder.*,");
        sqlSb.append("orderProd.soOrder.customer.{pkid,realName},");
        sqlSb.append("orderProd.soOrder.companyIntention.{pkid,name,full_name,company_name}");
        oql.setSelects(sqlSb.toString());
        oql.setOrderby("pkid DESC");
        List<OrderProd> resList = (List<OrderProd>) super.doQuery(oql);
        List<Integer> orderProdIdList = getOrderProdIdList(resList);
        //设置操作员
        setOperator(orderProdIdList, resList);
        //设置操作组
        setOperationGroup(orderProdIdList, resList);
        return resList;
    }

    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            OrderProd orderProd = ((OrderProd) list.get(i));
            ob2.get(i).put("operator", orderProd.getOperator());
            //设置操作组
            ob2.get(i).put("operationsGroup", orderProd.getOperationsGroup());
            //订单余额：paidPrice+carryIntoAmount-refundPrice-carryAmount
            Integer balance = NumberUtils.toInt(orderProd.getSoOrder().getPaidPrice()) + NumberUtils.toInt(orderProd.getSoOrder().getCarryIntoAmount()) -
                    NumberUtils.toInt(orderProd.getSoOrder().getRefundPrice()) - NumberUtils.toInt(orderProd.getSoOrder().getCarryAmount());
            ob2.get(i).put("soOrder_balance", balance);
        }
        return json;
    }


    private List<Integer> getOrderProdIdList(List<OrderProd> resList) {
        List<Integer> orderProdIdList = new ArrayList<>();
        for (OrderProd orderProd : resList) {
            orderProdIdList.add(orderProd.getId());
        }
        return orderProdIdList;
    }

    //设置操作员
    private void setOperator(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, String> operatorMap = orderProdUserMapService.getOrderUserByIds(orderProdIdList, OrderProdUserMapType.Czy.getValue(), OrderProdUserMapStatus.Zzfz.getValue());
        for (OrderProd orderProd : resList) {
            orderProd.setOperator(operatorMap.get(orderProd.getId()));
        }
    }

    //设置操作组
    private void setOperationGroup(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, String> OperationGroupMap = organizationService.getOrderOperationGroup(orderProdIdList);
        for (OrderProd orderProd : resList) {
            orderProd.setOperationsGroup(OperationGroupMap.get(orderProd.getId()));
        }
    }






}
