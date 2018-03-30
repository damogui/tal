package com.gongsibao.trade.web;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
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

        return parameter.getFilter();
    }

    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("auditLog.*,invoice.*,invoice.salesman.{id,name}");
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
    
	protected Object serialize(List<?> list, Oql oql) {

		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(list);
			result.setFooter(this.getFooter(oql));
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}

//		DatagridResultJson parser = new DatagridResultJson(result, pdatagrid);
//		Object json = parser.parse();
		return result;
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
    // endregion


}