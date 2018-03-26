package com.gongsibao.trade.service.action.audit.carryover;

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
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;

public class ActionAuditCarryoverWriteBack implements IAction{
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
                writeBackCarryover(auditLog.getFormId(),AuditStatusType.Bhsh);
                writeBackOrder(auditLog.getFormId(),AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId());
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                	writeBackCarryover(auditLog.getFormId(),AuditStatusType.Shtg);
                    writeBackOrder(auditLog.getFormId(),AuditStatusType.Shtg);
                }
                break;
        }
    }
	
	/**
	 * 回写结转
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackCarryover(Integer formId, AuditStatusType state){		
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order_carryover");
			updateSql.set("auditStatus", state.getValue());
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<NOrderCarryover> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	} 
	/**
	 * 回写订单
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackOrder(Integer formId, AuditStatusType state){
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("refundStatus", state.getValue());
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
