package com.gongsibao.trade.service.action.order.save;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditOrderService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;

/**   
 * @ClassName:  ActionSaveOrderChangePriceAudit   
 * @Description:TODO 改价、分期进行审核
 * 执行顺序：8
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:11:39   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderChangePriceAudit implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		SoOrder soOrder = (SoOrder) ctx.getItem();
		
		if (soOrder.getIsChangePrice() || soOrder.getIsInstallment()) {
			
			AbstractAuditService auditLogService = AuditServiceFactory.create(AuditOrderService.class);
			
			//后期发送通知用
			List<AuditLog> auditLogList = auditLogService.execute(soOrder.getId());//改价
			
			// 推送消息
			Map<String, Object> statusMap = new HashMap<String, Object>();
			statusMap.put ("audits", auditLogList);
			ctx.setStatus (statusMap);
		}
	}

}
