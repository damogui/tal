package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.Map;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.base.IInvoiceService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

@Service
public class InvoiceService extends PersistableService<Invoice> implements IInvoiceService {

    public InvoiceService() {
        super();
        this.type = Invoice.class;
    }

    @Override
    public Boolean applyInvoice(Invoice invoice, Map<String, Object> paraMap) {
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/order/invoice");
            ctx.setItem(invoice);
            ctx.setState(invoice.getEntityState());
            ctx.setStatus(paraMap);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("so_invoice");
            updateBuilder.set("audit_status_id", auditStatusType.getValue());
            updateBuilder.where("pkid=?");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }
}