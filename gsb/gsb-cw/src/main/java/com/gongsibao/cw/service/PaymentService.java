package com.gongsibao.cw.service;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.dict.FinanceDict;

@Service
public class PaymentService extends PersistableService<Payment> implements IPaymentService {

	ICostDetailService costDetailService = ServiceFactory.create(ICostDetailService.class);
	
	IFileService fileService = ServiceFactory.create(IFileService.class);
	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
	public PaymentService() {
		super();
		this.type = Payment.class;
	}

	@Override
	public Boolean savePayment(Payment payment) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/payment/form");
            ctx.setItem(payment);
            ctx.setState(payment.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}

	@Override
	public Payment getBillByFormId(Integer formId) {
		Oql oql = new Oql();
		oql.setType(Payment.class);
		oql.setSelects("payment.*,payment.setOfBooks.name");
		oql.setFilter("id=?");
		oql.getParameters().add("id", formId, Types.INTEGER);
		Payment entity = this.queryFirst(oql);
		if(entity != null){
			entity.setCostDetailItem(costDetailService.getCostDetailItem(formId, FinanceDict.FormType.FKD.getValue()));
			//附件信息
			entity.setFiles(fileService.getByTabNameFormId("cw_payment", formId));
			//审核信息
			entity.setAuditItem(auditRecordService.getAuditRecordList(formId, FinanceDict.FormType.FKD.getValue()));
		}
		return entity;
	}


	@Override
	public Boolean updateStatus(AuditRecord auditRecord){
		String sql = "UPDATE cw_payment SET status ="+FinanceDict.AuditStatus.Status_5.getValue() +"   WHERE id = "+auditRecord.getFormId();
		Boolean bool =  this.pm.executeNonQuery(sql, null) > 0;
		if(bool){
			auditRecordService.saveFinance(auditRecord);
		}
		return bool;
	}
	
}
