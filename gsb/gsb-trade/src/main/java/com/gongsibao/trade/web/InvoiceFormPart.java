package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.trade.base.ISoOrderService;

public class InvoiceFormPart extends FormPart {

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

    IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);

    IFileService fileService = ServiceFactory.create(IFileService.class);

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);

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
        SoOrder soOrder = soOrderService.getByOrderId(orderId);
        soOrder.setToBeInvoicePrice(soOrder.getPaidPrice() - NumberUtils.toInt(soOrder.getRefundPrice()));
        return soOrder;
    }


    @Override
    public FormNavigation byId(Object id) {

        FormNavigation navigation = this.createFormNavigation(id);
        Oql oql = new Oql();
        {
            oql.setType(Invoice.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", NumberUtils.toInt(id), Types.INTEGER);
        }
        Invoice invoice = invoiceService.queryFirst(oql);
        if (invoice == null) {
            navigation.Entity = this.newInstance(null);
        } else {

            List<OrderInvoiceMap> orderInvoiceMapList = orderInvoiceMapService.getByInvoiceId(invoice.getId());

            if (CollectionUtils.isNotEmpty(orderInvoiceMapList)) {
                invoice.setSoOrder(orderInvoiceMapList.get(0).getSoOrder());
            }
            List<File> filelist = fileService.getByTabNameFormId("so_invoice", invoice.getId());
            invoice.setFiles(filelist);

            List<AuditLog> contractAuditList = auditService.getByTypeIdFormId(AuditLogType.Fbsq, invoice.getId());
            invoice.setAuditLogs(contractAuditList);
            navigation.Entity = invoice;
        }
        return navigation;
    }


    /*
    *检查该订单是否已经有发票了
    * */
    public int checkInvoice(Integer orderId) {

        int res = 0;

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
            res = -1;
        } else {
            SoOrder order = orderService.getByOrderId(orderId);
            if (order.getPaidPrice() <= NumberUtils.toInt(order.getRefundPrice())) {
                res = -2;
            }
        }
        return res;
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
