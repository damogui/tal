package com.gongsibao.trade.service.action.audit.refund;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IAuditService;

public class ActionAuditRefundWriteBack implements IAction{
	IAuditService auditService = ServiceFactory.create(IAuditService.class);
	
	@Override
	public void execute(ActionContext ctx) {
		AuditContext auditContext = (AuditContext) ctx.getItem();

        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        
        //审核
        audit(state, auditLog, remark);
		
	}
	private void audit(AuditState state, AuditLog auditLog, String remark) {
        switch (state.getValue()) {
            case 0://驳回审核
                auditService.auditRejected(auditLog.getId(), remark);
                writeBackRefund(auditLog.getFormId(),state);
                writeBackOrder(auditLog.getFormId(),state);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId());
                writeBackRefund(auditLog.getFormId(),state);
                writeBackOrder(auditLog.getFormId(),state);
                break;
        }
    }
	
	/**
	 * 回写退款
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackRefund(Integer formId, AuditState state){		
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_refund");
			updateSql.set("auditStatus", state);
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<Refund> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	} 
	/**
	 * 回写订单
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackOrder(Integer formId, AuditState state){
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("refundStatus", state);
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
