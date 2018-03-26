package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.u8.base.ISoOrderService;

public class InvoiceFormPart extends FormPart {

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

    IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);


    /**
     * 订单id 查询订单信息
     *
     * @param @param  orderId
     * @param @return 参数
     * @return SoOrder    返回类型
     * @throws
     * @Title: querySoOrderById
     * @Description: TODO
     */
    public SoOrder querySoOrderById(Integer orderId) {
        ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
        SoOrder soOrder = soOrderService.byId(orderId);
        return soOrder;
    }

    /*
    *检查该订单是否已经有发票了
    * */
    public boolean checkInvoice(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(Invoice.class);
            oql.setSelects("*");
            StringBuffer sbWhere = new StringBuffer();
            sbWhere.append("audit_status_id in(" + AuditStatusType.Dsh.getValue() + "," + AuditStatusType.Shtg.getValue() + "," + AuditStatusType.Shz.getValue() + ") ");
            sbWhere.append("and pkid in(select invoice_id from so_order_invoice_map where order_id = ?) ");
            oql.setFilter(sbWhere.toString());
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }

        List<Invoice> invoices = invoiceService.queryList(oql);
        if (CollectionUtils.isNotEmpty(invoices)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 申请发票
     *
     * @param @return 参数
     * @return Boolean    返回类型
     * @throws
     * @Title: applyInvoice
     * @Description: TODO
     */
    public Boolean applyInvoice(Invoice invoice, Integer orderId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("orderId", orderId);
        return invoiceService.applyInvoice(invoice, paraMap);
    }


}
