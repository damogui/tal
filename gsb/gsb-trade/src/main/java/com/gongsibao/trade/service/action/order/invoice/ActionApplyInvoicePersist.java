package com.gongsibao.trade.service.action.order.invoice;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

public class ActionApplyInvoicePersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
		//保存开票信息
		Invoice  invoice = (Invoice) ctx.getItem();
		Map<String,Object> paraMap = ctx.getStatus();
		IInvoiceService invoiceService =ServiceFactory.create(IInvoiceService.class);
		invoice.toNew();
		invoice.setFileId(0);
		Invoice temp = invoiceService.save(invoice);
		if(temp != null && temp.getId() !=null){
			//获取订单id
			Integer orderId = Integer.parseInt(paraMap.get("orderId").toString());
			IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);
			OrderInvoiceMap orderInvoice = new OrderInvoiceMap();
			orderInvoice.toNew();
			orderInvoice.setInvoiceId(temp.getId());
			orderInvoice.setOrderId(orderId);
			orderInvoiceMapService.save(orderInvoice);
		}
		
	}

}
