package com.gongsibao.trade.service.action.audit.carryover;

import java.util.List;
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
import com.gongsibao.trade.base.INOrderCarryoverService;

public class ActionAuditCarryoverWriteBack implements IAction{
	IAuditService auditService = ServiceFactory.create(IAuditService.class);
	INOrderCarryoverService carryoverService = ServiceFactory.create(INOrderCarryoverService.class);
	
	@Override
	public void execute(ActionContext ctx) {
		AuditContext auditContext = (AuditContext) ctx.getItem();

        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Integer orderId = (Integer)objectMap.get("orderId");
        //审核
        audit(state, auditLog,orderId, remark);
		
	}
	private void audit(AuditState state, AuditLog auditLog,Integer orderId, String remark) {
        switch (state.getValue()) {
            case 0://驳回审核
                auditService.auditRejected(auditLog.getId(), remark);
                writeBackCarryover(auditLog.getFormId(),AuditStatusType.Bhsh);
                writeBackOrder(orderId,AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId(),remark);
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                	writeBackCarryover(auditLog.getFormId(),AuditStatusType.Shtg);
                    writeBackOrder(orderId,AuditStatusType.Shtg);
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
			updateSql.set("audit_status", state.getValue());
			updateSql.where("id =" + formId);
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
	private void writeBackOrder(Integer orderId, AuditStatusType state){
		//1.审核成功，获取来源订单的结转金额 
		Integer allAmout = 0;
		Integer toOrderId = 0;
		if(state.equals(AuditStatusType.Shtg)){
			NOrderCarryover carryover = carryoverService.queryByFormOrderId(orderId);
			allAmout = carryover.getAmount();
			toOrderId = carryover.getToOrderId();
		}
		//2.回写订单:结转审核状态、结转转出额
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("carry_status_id", state.getValue());
			updateSql.set("carry_amount", allAmout);
			updateSql.where("pkid =" + orderId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
		//3.回写订单:结转审核状态、结转转入额
        UpdateBuilder updateSqlTwo = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("carry_status_id", state.getValue());
			updateSql.set("carry_into_amount", allAmout);
			updateSql.where("pkid =" + toOrderId);
		}
		String cmdTextTwo = updateSqlTwo.toSQL();
		IPersister<SoOrder> pmTwo = PersisterFactory.create();
		pmTwo.executeNonQuery(cmdTextTwo, null);
	}
}
