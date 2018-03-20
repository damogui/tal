package com.gongsibao.trade.service.action.order.invoice;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.entity.trade.Invoice;

public class ActionApplyInvoiceVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		//验证发票金额不能大于开票金额
		Invoice  invoice = (Invoice) ctx.getItem();
	}

}
