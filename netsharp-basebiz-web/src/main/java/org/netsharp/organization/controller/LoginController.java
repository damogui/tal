package org.netsharp.organization.controller;

import org.netsharp.application.Application;
import org.netsharp.authorization.LoginManager;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.controller.dto.LoginResultDTO;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.anno.Authorization;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.qy.accesstoken.AccessToken;
import org.netsharp.wx.qy.accesstoken.AccessTokenManage;
import org.netsharp.wx.qy.user.UserLoginRequest;
import org.netsharp.wx.qy.user.UserLoginResponse;

public class LoginController {

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);

	// 微信扫码登录
	@Authorization(is = false)
	public int wxLogin(String authCode) {

		if (StringManager.isNullOrEmpty(authCode)) {
			return 0;
		}
		String ps = "{\"auth_code\":\"" + authCode + "\"}";
		IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
		WxeaApp wxpa = service.byCode("WeChat");

		UserLoginRequest loginRequest = new UserLoginRequest();
		{
			AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
			loginRequest.setToken(token);
			loginRequest.setJson(ps);

		}
		try {
			UserLoginResponse response = loginRequest.getResponse();

			if (response.getUser_info() != null && response.getUser_info().getUserid() != null) {

				IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
				Employee emp = employeeService.byId(response.getUser_info().getUserid());
				if (emp != null && !emp.getDisabled()) {
					UserPermissionManager.addSession(emp);
					return 1;
				}
				return 0;
			}
			return 0;
		} catch (Exception e) {

			return 0;
		}

	}

	// 登录
	@Authorization(is = false)
	public LoginResultDTO login(String userName, String password) {

		LoginResultDTO result = new LoginResultDTO();
		
		if (StringManager.isNullOrEmpty(userName) || StringManager.isNullOrEmpty(password)) {
			return result;
		}

		Employee employee = employeeService.login(userName, password);
		if (employee == null) {
			
			return result;
		} else if (employee.getDisabled()) {
			
			result.setResult(2);
			return result;
		} else if (employee.getPwd().equals(EncrypUtil.md5(Application.getContext().getDefaultPassword()))) {
			
			UserPermissionManager.addSession(employee);
			//return 3;
			result.setData(employee.getTicket());
			result.setResult(1);
			return result;
		} else {
			
			UserPermissionManager.addSession(employee);
			result.setData(employee.getTicket());
			result.setResult(1);
			return result;
		}
	}

	@Authorization(is = false)
	public int logout() {
		LoginManager manager = new LoginManager();
		manager.logout();
		return 1;
	}
}
