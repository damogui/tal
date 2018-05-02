package com.gongsibao.trade.web;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

import com.gongsibao.trade.web.audithelper.SetOfBooksNameHelper;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangchao on 2018/3/23.
 */
public class AuditInvoiceListPart extends AdvancedListPart {

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        String filterPrefix = "type_id=" + AuditLogType.Fbsq.getValue() + " and form_id in ";

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
            filters.add("no ='" + keyword + "'");
            filters.add("channel_order_no = '" + keyword + "'");
            filters.add("account_mobile = '" + keyword + "'");
            filters.add("account_name = '" + keyword + "'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in ( select pkid from so_order where " + StringManager.join(" or ", filters) + "))";
        }
        //发票类型
        if (parameter.getKey().equals("invoice_typeId")) {
            return filterPrefix + " (select pkid from so_invoice where type_id in (" + keyword + "))";
        }
        //产品名称
        if (parameter.getKey().equals("prodName")) {
            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in (select pkid from so_order where prod_name like '%" + keyword + "%' ))";
        }
        //订单创建时间
        if (parameter.getKey().equals("orderCreateTime")) {
            List<String> soOrderCreateTime = new ArrayList<>();
            if (parameter.getValue1() != null) {
                soOrderCreateTime.add(" add_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                soOrderCreateTime.add(" add_time <= '" + parameter.getValue2().toString() + "'");
            }

            return filterPrefix + " (select invoice_id from so_order_invoice_map where order_id in (select pkid from so_order where " + StringManager.join(" and ", soOrderCreateTime) + " ))";
        }

        if (parameter.getKey().equals("pay.setOfBooksId")) {//付款账套的筛选

            return String.format("form_id   IN (SELECT    invoice_id FROM  so_order_invoice_map  WHERE    order_id IN (SELECT order_id FROM so_order_pay_map WHERE pay_id IN ( SELECT pkid FROM so_pay WHERE set_of_books_id='%s')))", keyword);

        }

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("auditLog.*,invoice.*,invoice.salesman.{id,name}");
        oql.setOrderby("pkid DESC");
        List<AuditLog> resList = (List<AuditLog>) super.doQuery(oql);
        List<Integer> invoiceIdList = getInvoiceIdList(resList);
        List<OrderInvoiceMap> orderInvoiceMapList = orderInvoiceMapService.getByInvoiceIdList(invoiceIdList);
        for (AuditLog auditLog : resList) {
            SoOrder order = getOrderByInvoiceId(auditLog.getFormId(), orderInvoiceMapList);
            if (order != null) {
                setOrderInfo(auditLog.getInvoice(), order);
            }
        }
        return resList;
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            ob2.get(i).put("invoice_soOrderNo", getOrderInfoByPropertyName(list, i, "invoice_soOrderNo"));
            ob2.get(i).put("invoice_prodName", getOrderInfoByPropertyName(list, i, "invoice_prodName"));
            ob2.get(i).put("invoice_channelOrderNo", getOrderInfoByPropertyName(list, i, "invoice_channelOrderNo"));
            ob2.get(i).put("invoice_accountTypeName", getOrderInfoByPropertyName(list, i, "invoice_accountTypeName"));
            ob2.get(i).put("invoice_orderPayablePrice", getOrderInfoByPropertyName(list, i, "invoice_orderPayablePrice"));
            ob2.get(i).put("invoice_orderPaidPrice", getOrderInfoByPropertyName(list, i, "invoice_orderPaidPrice"));
            AuditLog auditLog = ((AuditLog) list.get(i));
            Integer invoiceId = auditLog.getFormId();
            String setOfBooksName = SetOfBooksNameHelper.getSetOfBooksNameByInvoiceId(invoiceId);
            ob2.get(i).put("booksName", setOfBooksName);
        }
        return json;
    }


    //region 私有方法
    private List<Integer> getInvoiceIdList(List<AuditLog> resList) {
        List<Integer> invoiceIdList = new ArrayList<>();
        for (AuditLog auditLog : resList) {
            if (!invoiceIdList.contains(auditLog.getFormId())) {
                invoiceIdList.add(auditLog.getFormId());
            }
        }
        return invoiceIdList;
    }

    private SoOrder getOrderByInvoiceId(Integer formId, List<OrderInvoiceMap> orderInvoiceMapList) {
        SoOrder order = null;
        for (OrderInvoiceMap orderInvoiceMap : orderInvoiceMapList) {
            if (formId.equals(orderInvoiceMap.getInvoiceId())) {
                order = orderInvoiceMap.getSoOrder();
            }
        }
        return order;
    }

    //订单信息的赋值
    private void setOrderInfo(Invoice invoive, SoOrder order) {
        invoive.setSoOrderNo(order.getNo());
        invoive.setChannelOrderNo(order.getChannelOrderNo());
        invoive.setAccountTypeName(order.getAccountType().getText());
        invoive.setProdName(order.getProdName());
        invoive.setOrderPayablePrice(order.getPayablePrice());
        invoive.setOrderPaidPrice(order.getPaidPrice());
    }

    //获取属性的值
    private Object getOrderInfoByPropertyName(List<?> list, Integer index, String propertyName) {
        Object res = new Object();
        AuditLog auditLog = ((AuditLog) list.get(index));
        if (auditLog == null) return null;
        Invoice invoice = auditLog.getInvoice();
        if (invoice == null) {
            return null;
        }
        switch (propertyName) {
            case "invoice_soOrderNo":
                res = invoice.getSoOrderNo();
                break;
            case "invoice_prodName":
                res = invoice.getProdName();
                break;
            case "invoice_channelOrderNo":
                res = invoice.getChannelOrderNo();
                break;
            case "invoice_accountTypeName":
                res = invoice.getAccountTypeName();
                break;
            case "invoice_orderPayablePrice":
                res = invoice.getOrderPayablePrice();
                break;
            case "invoice_orderPaidPrice":
                res = invoice.getOrderPaidPrice();
                break;
        }
        return res;
    }
    // endregion


}