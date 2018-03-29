package com.gongsibao.cw.service.action.loan;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class ActionLoanApplyAudit  implements IAction{

	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
		
	@Override
	public void execute(ActionContext ctx) {
		 Loan loan = (Loan) ctx.getItem();
		 if(loan != null && loan.getId() != null){
			//保存创建人上级主管审核信息
		   	 AuditRecord au = new AuditRecord();
		   	 au.toNew();
		   	 au.setAuditUserId(3619);    //获取上级主管id
		   	 au.setFormType(FinanceDict.FormType.JKD);
		   	 au.setFormId(loan.getId());
		   	 au.setStatus(FinanceDict.AuditDetailStatus.WAIT); //待审核
		   	 auditRecordService.save(au);
		 }
		
	}
}
