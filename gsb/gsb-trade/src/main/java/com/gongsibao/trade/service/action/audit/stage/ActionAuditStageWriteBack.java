package com.gongsibao.trade.service.action.audit.stage;

import java.util.Date;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.INOrderStageService;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionAuditStageWriteBack implements IAction{
	
	IAuditService auditService = ServiceFactory.create(IAuditService.class);
	ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
	
	
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
                writeBackBhshOrder(auditLog.getFormId(),AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId(),remark);
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                	writeBackShtgOrder(auditLog.getFormId(),AuditStatusType.Shtg);
                }
                break;
        }
    }
	/**
	 * 回写审核通过的订单分期的信息
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackShtgOrder(Integer formId, AuditStatusType state){
		//1.回写订单：分期审核状态、是否分期
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("installment_audit_status_id", state.getValue());
			updateSql.set("is_installment", true);
			//回写可能有其他的回写？？？
			
			//审核通过回写订单相关的信息
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
	/**
	 * 回写驳回审核的订单分期的信息
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackBhshOrder(Integer formId, AuditStatusType state){
		//1.回写订单：分期审核状态
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("installment_audit_status_id", state.getValue());
			updateSql.where("pkid =" + formId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
