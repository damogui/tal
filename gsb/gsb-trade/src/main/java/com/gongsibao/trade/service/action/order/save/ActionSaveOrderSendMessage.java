package com.gongsibao.trade.service.action.order.save;

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

/**   
 * @ClassName:  ActionSaveOrderSendMessage   
 * @Description:TODO 发送消息
 * 执行顺序：10
 * @author: 韩伟
 * @date:   2018年3月2日 下午5:12:01   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionSaveOrderSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		SoOrder soOrder = (SoOrder) ctx.getItem();
		if (soOrder.getIsChangePrice()){
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
			auditSend(soOrder,tels);

		}
	}
	/*进行发送消息*/
	private void auditSend(SoOrder soOrder,List<String>tels) {

		if (soOrder == null ) {
			return;
		}
		for (String tel:
				tels) {
			if (!StringManager.isNullOrEmpty(tel)){
				String content = String.format("【改价待审核提醒】您好，【%s】提交1个改价订单待您审核，订单编号为【%s】，请及时审核",UserHelper.getEmployeeName(soOrder.getOwnerId()),soOrder.getNo());
				SmsHelper.send(tel, content);//电话和内容
			}

		}

	}

}
