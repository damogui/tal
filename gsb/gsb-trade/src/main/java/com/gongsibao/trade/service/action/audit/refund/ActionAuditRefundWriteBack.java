package com.gongsibao.trade.service.action.audit.refund;

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
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.IRefundService;
import com.gongsibao.trade.service.RefundService;

public class ActionAuditRefundWriteBack implements IAction{
	IAuditService auditService = ServiceFactory.create(IAuditService.class);
	IRefundService refundService = ServiceFactory.create(IRefundService.class);
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
                writeBackRefund(auditLog.getFormId(),AuditStatusType.Bhsh);
                writeBackOrder(orderId,AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId(),remark);
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                	writeBackRefund(auditLog.getFormId(),AuditStatusType.Shtg);
                    writeBackOrder(orderId,AuditStatusType.Shtg);
                }
                break;
        }
    }
	
	/**
	 * 回写退款
	 * @param formId 来源Id
	 * @param state 审核状态
	 */
	private void writeBackRefund(Integer formId, AuditStatusType state){		
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_refund");
			updateSql.set("audit_status_id", state.getValue());
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
	private void writeBackOrder(Integer orderId, AuditStatusType state){
		//1.审核成功，获取订单退款金额
		Integer allAmout = 0;
		if(state.getValue().equals(1054)){
			List<Refund> refundList = refundService.queryByOrderId(orderId);
			for (Refund item : refundList) {
				allAmout += item.getAmount();
			}
		}
		//2.回写订单:退款审核状态、退款金额
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("refund_status_id", state.getValue());
			updateSql.set("refund_price", allAmout);
			
			updateSql.where("pkid =" + orderId);
		}
		String cmdText = updateSql.toSQL();
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, null);
	}
}
