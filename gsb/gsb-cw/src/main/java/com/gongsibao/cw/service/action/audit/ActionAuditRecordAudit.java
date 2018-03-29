package com.gongsibao.cw.service.action.audit;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class ActionAuditRecordAudit  implements IAction{

	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
		
	@Override
	public void execute(ActionContext ctx) {
		AuditRecord auditRecord = (AuditRecord) ctx.getItem();
		 if(auditRecord != null && auditRecord.getId() != null){
			 //TODO 梳理完组织机构补充审批逻辑
			//保存创建人上级主管审核信息
		   	 AuditRecord au = new AuditRecord();
		   	 au.toNew();
		   	 au.setAuditUserId(3619);    //获取上级主管id
		   	 au.setFormType(FinanceDict.FormType.JKD);
		   	 au.setFormId(auditRecord.getFormId());
		   	 au.setStatus(FinanceDict.AuditDetailStatus.WAIT); //待审核
		   	 auditRecordService.save(au);
		 }
		
	}
}
