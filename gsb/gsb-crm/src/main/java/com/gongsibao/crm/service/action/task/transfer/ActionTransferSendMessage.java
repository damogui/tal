package com.gongsibao.crm.service.action.task.transfer;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.ea.base.IEaMessageService;

/**
 * @author hw
 * 转移:发送消息
 * 文案：【**】把客户**转移给【**】，请及时跟进。
 */
public class ActionTransferSendMessage implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		
		//formUser
		
		
		
		//customer
		
		//toUser
		
//		String content = String.format("【%s】把客户%s转移给【%s】，请及时跟进。", args);

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();

//		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
//		Employee owner = employeeService.byId(ownerId);
//
//		for (String franchiseeId : ids) {
//
//			List<String> ss = new ArrayList<String>();
//			ss.add("【分配提醒】" + executor + "分配了1个客户给" + owner.getName());
//			ss.add("请及时跟进");
//			ss.add("<a href=\"http://netsharp.gongsibao.com/nav/wx/qy/bd/franchiseeDetail?id=" + franchiseeId + "\">查看详情</a>");
//			String content = StringManager.join("，", ss);
//			List<String> ls = new ArrayList<String>();
//			//ls.add(UserPermissionManager.getUserPermission().getEmployee().getMobile());
//			ls.add(owner.getMobile());
//			eMessageService.send("BD", content, StringManager.join("|", ls));
//		}
	}

}
