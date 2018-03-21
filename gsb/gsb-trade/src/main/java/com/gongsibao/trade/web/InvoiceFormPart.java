package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
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
            oql.setType(OrderInvoiceMap.class);
            oql.setSelects("*");
            oql.setFilter("orderId=?");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }

        OrderInvoiceMap entity = orderInvoiceMapService.queryFirst(oql);
        if (entity != null) {
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
