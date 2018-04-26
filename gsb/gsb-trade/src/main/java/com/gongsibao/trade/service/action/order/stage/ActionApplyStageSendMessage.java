package com.gongsibao.trade.service.action.order.stage;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
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

/**   
 * @ClassName:  ActionApplyStageSendMessage   
 * @Description:TODO 申请分期发送通知消息
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:52:47   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionApplyStageSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
		SoOrder order = (SoOrder) ctx.getItem();
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
		auditSend(order,tels);
	}
	/*进行发送消息*/
	private void auditSend(SoOrder order, List<String>tels) {

		if (order == null ) {
			return;
		}
		for (String tel:
				tels) {
			if (!StringManager.isNullOrEmpty(tel)){
				String content = String.format("【分期待审核提醒】您好，【%S】提交1个分期申请待您审核，订单编号为【%s】，请及时审核", SessionManager.getUserName(), order.getNo());
				SmsHelper.send(tel, content);//电话和内容
			}

		}

	}


}
