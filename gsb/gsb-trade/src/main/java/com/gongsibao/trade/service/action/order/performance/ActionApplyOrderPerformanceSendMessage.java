package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.OrderPerformanceAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*创建订单业绩的通知*/
public class ActionApplyOrderPerformanceSendMessage  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub

		SoOrder entity = (SoOrder) ctx.getItem ();
		//发送消息待审核
		Map<String, Object> objectMap = ctx.getStatus();
		List<AuditLog> audits = (List<AuditLog>) objectMap.get("audits");
		if (audits.size()<1){
			return;
		}
		List<String> tels=new ArrayList<>();
		for (AuditLog  item:audits
			 ) {
			if (item.getLevel()==1){

				tels.add(UserHelper.getEmployeTelById(item.getCreatorId()));
			}

		}
		auditSend(entity,tels);
	}

	/*进行发送消息*/
	private void auditSend(SoOrder soOrder,List<String>tels) {

		if (soOrder == null ) {
			return;
		}
		for (String tel:
				tels) {
			if (!StringManager.isNullOrEmpty(tel)){
				String content = String.format("【订单业绩待审核提醒】您好，【业务员%s】提交1个订单业绩申请待您审核，订单编号为【%s】，请及时审核", UserHelper.getEmployeeName(soOrder.getOwnerId()), soOrder.getNo());
				SmsHelper.send(tel, content);//电话和内容
			}

		}

	}

}
