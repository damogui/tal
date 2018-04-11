package com.gongsibao.crm.service.action.customer.member;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.utils.sms.SmsHelper;

/**
 * @author hw
 * 开通会员：发送信息
 */
public class ActionMemberSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		Object object = ctx.getStatus().get("isSendSms");
		if(object != null){

			Boolean isSendSms = (Boolean)object;
			if(isSendSms){

				Account account = (Account)ctx.getStatus().get("account");
				String pwd = (String)ctx.getStatus().get("pwd");
				String content = String.format("您好，公司宝业务员“%s”已为您开通会员，账号是您当前手机号，密码：“%s”。后续所有服务通知会发送给您，此消息为系统发息为系统发送，请勿回复，有问题请联系您对应的业务员。", SessionManager.getUserName(),pwd);
				SmsHelper.send(account.getMobilePhone(), content);
			}
		}
	}
}