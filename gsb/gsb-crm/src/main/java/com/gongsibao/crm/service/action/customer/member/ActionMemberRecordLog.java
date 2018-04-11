package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.crm.base.INCustomerOperationLogService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerOperationLog;
import com.gongsibao.entity.crm.dic.ChangeType;
import com.gongsibao.entity.crm.dic.OperationType;

/**
 * @author hw
 * 开通会员：记录日志
 */
public class ActionMemberRecordLog implements IAction{

	@Override
	public void execute(ActionContext ctx) {

        Object object = ctx.getStatus().get("isSendSms");
        Boolean isSendSms = (Boolean)object;
        
        NCustomer customer = (NCustomer) ctx.getItem();
		NCustomerOperationLog changeLog = new NCustomerOperationLog();
		{
			changeLog.toNew();
			changeLog.setChangeType(ChangeType.INPUT);
			changeLog.setType(OperationType.MANUAL);
			changeLog.setCustomerId(customer.getId());
			changeLog.setFormUserId(SessionManager.getUserId());
			changeLog.setContent("【" + SessionManager.getUserName() + "】" + (isSendSms == true ? "" : "（静默）") + "开通会员");
			changeLog.setSupplierId(customer.getSupplierId());
			changeLog.setDepartmentId(customer.getDepartmentId());
		}
		
		INCustomerOperationLogService service = ServiceFactory.create(INCustomerOperationLogService.class);
		service.save(changeLog);
	}
}
