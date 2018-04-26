package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*创建回款通知*/
public class ActionApplyPaySendMessage  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub

		Pay pay = (Pay) ctx.getItem();
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
		auditSend(pay,tels);
		
	}

	/*进行发送消息*/
	private void auditSend(Pay pay,List<String>tels) {

		if (pay == null ) {
			return;
		}
		for (String tel:
				tels) {
			if (!StringManager.isNullOrEmpty(tel)){
				String content = String.format("【回款待审核提醒】您好，【%s】提交1个回款申请待您审核，订单编号为【%s】，请及时审核",SessionManager.getUserName(),pay.getOrderNo());
				SmsHelper.send(tel, content);//电话和内容
			}

		}

	}

}
