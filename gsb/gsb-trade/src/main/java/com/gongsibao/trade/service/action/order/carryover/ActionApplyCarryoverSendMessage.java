package com.gongsibao.trade.service.action.order.carryover;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*结转申请发送消息*/
public class ActionApplyCarryoverSendMessage  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		NOrderCarryover carryOver = (NOrderCarryover) ctx.getItem();
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
		auditSend(carryOver,tels);
		
	}

	/*进行发送消息*/
	private void auditSend(NOrderCarryover carryOver,List<String>tels) {

		if (carryOver == null ) {
			return;
		}
		for (String tel:
				tels) {
			if (!StringManager.isNullOrEmpty(tel)){
				String content = String.format("【结转待审核提醒】您好，【%S】提交1个结转申请待您审核，订单编号为【%s】，请及时审核", SessionManager.getUserName(), AuditHelper.getOrderNoById(carryOver.getFormOrderId()));
				SmsHelper.send(tel, content);//电话和内容
			}

		}

	}

}
