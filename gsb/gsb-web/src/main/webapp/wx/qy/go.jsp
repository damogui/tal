<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.netsharp.communication.ServiceFactory"%>
<%@page import="org.netsharp.wx.qy.user.UserInfoRequest" %>
<%@page import="org.netsharp.wx.qy.user.UserInfoResponse" %>
<%@page import="org.netsharp.util.JsonManage" %>
<%@page import="org.netsharp.wx.qy.accesstoken.AccessToken" %>
<%@page import="org.netsharp.wx.qy.accesstoken.AccessTokenManage" %>
<%@page import="org.netsharp.organization.base.IEmployeeService" %>
<%@page import="org.netsharp.organization.entity.Employee" %>
<%@page import="org.netsharp.wx.ea.base.IWxeaAppService" %>
<%@page import=" org.netsharp.wx.ea.entity.WxeaApp" %>
<%@ page import="org.netsharp.panda.core.HttpContext"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletRequest"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletResponse"%>
<%@ page import="org.netsharp.panda.core.comunication.HtmlWriter"%>
<%@ page import="org.netsharp.organization.controller.LoginController"%>
<%@ page import="org.netsharp.organization.controller.dto.LoginResultDTO"%>
<%
	String code = request.getParameter("code");
	String state = request.getParameter("state");
	String toUrl = request.getParameter("toUrl");
	
	IWxeaAppService wxeaAppService = ServiceFactory.create(IWxeaAppService.class);
	WxeaApp app = wxeaAppService.byCode(state);
	
	AccessToken token = AccessTokenManage.get(app.getCorpid(),app.getCorpsecret());
	UserInfoRequest userInfoRequest = new UserInfoRequest();{
		userInfoRequest.setCode(code);
		userInfoRequest.setToken(token);
	}
	UserInfoResponse userInfoResponse = userInfoRequest.getResponse();
	String wxUserId = userInfoResponse.getUserId();
	
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
	Employee employee = employeeService.byPhone(wxUserId);
	
	//自动登录
	HttpContext ctx = new HttpContext();
	{
		ctx.setRequest(new ServletRequest(request, response));
		ctx.setResponse(new ServletResponse(response));
		ctx.setContext(request.getServletContext());
		ctx.setWriter(new HtmlWriter(response.getWriter()));
	}
	
	HttpContext.setCurrent(ctx);
	LoginController loginController = new LoginController();
	LoginResultDTO result = loginController.login(employee.getLoginName(), employee.getPwd());
	
	Cookie cookie = new Cookie("QY_WX_EMPLOYEEID", employee.getId().toString());
	String url = toUrl+"?employeeId="+employee.getId();
%>
<script>
	window.location.href='<%=url%>';
</script>