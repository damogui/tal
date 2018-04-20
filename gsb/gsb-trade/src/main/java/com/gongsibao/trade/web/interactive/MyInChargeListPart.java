package com.gongsibao.trade.web.interactive;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;

public class MyInChargeListPart extends AdvancedListPart {

    IOrderProdTraceService orderProdTraceService = ServiceFactory.create(IOrderProdTraceService.class);
    IContractService contractService = ServiceFactory.create(IContractService.class);
    IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

    @Override
    public String getFilterByParameter(FilterParameter parameter) {
        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("no = '" + keyword + "'");
            filters.add("channel_order_no = '" + keyword + "'");
            filters.add("account_name like '%" + keyword + "%'");
            filters.add("account_mobile = '" + keyword + "'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");

            return "((OrderProd.pkid = '" + keyword + "') or order_id in ( select pkid from so_order where " + StringManager.join(" or ", filters) + "))";
        }
        //操作员
        if (parameter.getKey().equals("operator")) {
            String operatorWhere = "soOrder.pkid IN(SELECT order_prod_id FROM so_order_prod_user_map opm JOIN sys_permission_employee em ON opm.user_id = em.id AND opm.`status_id` = " + OrderProdUserMapStatus.Zzfz.getValue() + " AND opm.`type_id`=" + OrderProdUserMapType.Czy.getValue() + " WHERE em.name LIKE '%" + keyword + "%')";
            return operatorWhere;
        }

        //负责状态
        if (parameter.getKey().equals("inChargeStatus")) {
            String inChargeStatusWhere = "OrderProd.pkid IN(SELECT distinct order_prod_id FROM so_order_prod_user_map WHERE type_id=" + OrderProdUserMapType.Czy.getValue() + " AND status_id=" + keyword + " AND user_id = '{userId}')";
            return inChargeStatusWhere;
        }

        //分配状态(0:未分配 1：已分配)
        if (parameter.getKey().equals("operationAllocationStatus")) {
            String isNotIn = keyword.equals("'1'") ? "" : " NOT ";
            String inChargeStatusWhere = "OrderProd.pkid " + isNotIn + " IN (SELECT DISTINCT order_prod_id FROM so_order_prod_user_map WHERE type_id = " + OrderProdUserMapType.Czy.getValue() + " ) ";
            return inChargeStatusWhere;
        }

        return parameter.getFilter();
    }


    @Override
    public List<?> doQuery(Oql oql) {

        StringBuffer sqlSb = new StringBuffer();
        sqlSb.append("orderProd.*,");
        sqlSb.append("orderProd.processStatus.{pkid,name},");
        sqlSb.append("orderProd.owner.{id,name},");
        sqlSb.append("orderProd.companyIntention.{pkid,name,full_name,company_name},");
        sqlSb.append("orderProd.soOrder.*,");
        sqlSb.append("orderProd.soOrder.customer.{pkid,realName},");
        sqlSb.append("orderProd.soOrder.companyIntention.{pkid,name,full_name,company_name}");
        oql.setSelects(sqlSb.toString());
        List<OrderProd> resList = (List<OrderProd>) super.doQuery(oql);
        List<Integer> orderProdIdList = getOrderProdIdList(resList);
        //设置是否加急
        setIsUrgeney(orderProdIdList, resList);
        //设置操作员
        setOperator(orderProdIdList, resList);
        //设置剩余天数
        setSurplusDays(resList);
        //设置分配日期
        setAllocationOperatorDate(orderProdIdList, resList);
        return resList;
    }


    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            OrderProd orderProd = ((OrderProd) list.get(i));
            ob2.get(i).put("isUrgent", orderProd.getUrgent());
            ob2.get(i).put("operator", orderProd.getOperator());
            ob2.get(i).put("surplusDays", orderProd.getSurplusDays());
            ob2.get(i).put("allocationOperatorDate", orderProd.getAllocationOperatorDate());
        }
        return json;
    }

    //添加跟进
    public void addFollowUp(Integer orderProdId, String followContent) {
        orderProdTraceService.addFollowUp(orderProdId, followContent);
    }

    private List<Integer> getOrderProdIdList(List<OrderProd> resList) {
        List<Integer> orderProdIdList = new ArrayList<>();
        for (OrderProd orderProd : resList) {
            orderProdIdList.add(orderProd.getId());
        }
        return orderProdIdList;
    }

    //设置是否加急
    private void setIsUrgeney(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, Boolean> isUrgeneyMap = contractService.getIsUrgeneyByOrderProdIdList(orderProdIdList);
        for (OrderProd orderProd : resList) {
            orderProd.setUrgent(isUrgeneyMap.get(orderProd.getId()));
        }
    }

    //设置操作员
    private void setOperator(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, String> operatorMap = orderProdUserMapService.getOrderUserByIds(orderProdIdList, OrderProdUserMapType.Czy.getValue(), OrderProdUserMapStatus.Zzfz.getValue());
        for (OrderProd orderProd : resList) {
            orderProd.setOperator(operatorMap.get(orderProd.getId()));
        }
    }

    //设置剩余天数
    private void setSurplusDays(List<OrderProd> resList) {
        for (OrderProd orderProd : resList) {
            Integer surplusDays = orderProd.getNeedDays() - orderProd.getProcessedDays();
            orderProd.setSurplusDays(surplusDays <= 0 ? 0 : surplusDays);
        }
    }

    //设置分配日期
    private void setAllocationOperatorDate(List<Integer> orderProdIdList, List<OrderProd> resList) {
        Map<Integer, Date> allocationDate = orderProdUserMapService.getAllocationDate(orderProdIdList, OrderProdUserMapType.Czy.getValue(), OrderProdUserMapStatus.Zzfz.getValue());
        for (OrderProd orderProd : resList) {
            orderProd.setAllocationOperatorDate(allocationDate.get(orderProd.getId()));
        }
    }

}
