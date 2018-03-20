package com.gongsibao.trade.service;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.base.IInvoiceService;

@Service
public class InvoiceService extends PersistableService<Invoice> implements IInvoiceService {

    public InvoiceService(){
        super();
        this.type=Invoice.class;
    }

	@Override
	public Boolean applyInvoice(Invoice invoice,Map<String,Object> paraMap) {
		ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/invoice");
            ctx.setItem (invoice);
            ctx.setState (invoice.getEntityState ());
            ctx.setStatus(paraMap);
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
		return true;
	}
}