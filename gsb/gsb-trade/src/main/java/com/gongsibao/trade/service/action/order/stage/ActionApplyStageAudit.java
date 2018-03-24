package com.gongsibao.trade.service.action.order.stage;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.StageAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;

/**   
 * @ClassName:  ActionApplyStageAudit   
 * @Description:TODO 申请分期提交审核
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:52:22   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionApplyStageAudit implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		// 分期审核：（只能1次分期，所以审核中的formId用订单id即可）
		AbstractAuditLogService auditLogService = AuditFactory.getAudit(StageAudit.class);
		//后期发送通知用
		List<AuditLog> auditLogList = auditLogService.execute(order.getId());
	}

}
