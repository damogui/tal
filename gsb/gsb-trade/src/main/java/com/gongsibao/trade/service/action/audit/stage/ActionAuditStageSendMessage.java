package com.gongsibao.trade.service.action.audit.stage;

import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
/*分期申请*/
public class ActionAuditStageSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		AuditContext auditContext = (AuditContext) ctx.getItem();
		Map<String, Object> objectMap = ctx.getStatus();
		AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
		SoOrder soOrder = (SoOrder) objectMap.get("soOrder");
		//本次审核通过或驳回
		AuditState state = auditContext.getState();
		//审核
		//审核意见
		String remark = auditContext.getremark();
		auditSend(state, auditLog, soOrder, remark);
	}

	/*进行发送消息*/
	private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark) {

		if (soOrder == null || auditLog == null) {

			return;
		}
		switch (state.getValue()) {
			case 0://驳回审核
				if (soOrder.getOwner() != null) {

					String content = String.format("【分期审核提醒】您好，您有1个订单提交的分期申请审核不通过，订单编号为【%s】，原因为【%s】,请知悉", soOrder.getNo(),remark);
					SmsHelper.send(UserHelper.getEmployeTelById(soOrder.getOwnerId()), content);//订单业务员

				}
				break;
			case 1://通过审核
				if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
					//通过审核
					String content = String.format("【分期审核提醒】您好，您有1个订单提交的分期申请已审核通过，订单编号为【%s】，请知悉", soOrder.getNo());
					SmsHelper.send(UserHelper.getEmployeTelById(soOrder.getOwnerId()), content);//订单业务员

				} else {
					//通知下一级
					List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
					sendNextAudit(userIds, soOrder.getNo());//下一级审核

				}
				break;
		}

	}

	/*下一级审核*/
	private void sendNextAudit(List<Integer> userIds, String no) {
		for (Integer item:userIds
				) {
			String content = String.format("【分期待审核提醒】您好，有N个分期申请待您审核，请及时审核");
			SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
		}

	}





}
