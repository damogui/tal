
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="org.netsharp.communication.ServiceFactory"%>
<%@ page import="com.gongsibao.crm.base.ICustomerServiceConfigService"%>
<%@ page import="com.gongsibao.entity.crm.CustomerServiceConfig"%>
<%@ page import="org.netsharp.organization.entity.Employee"%>
<%@ page import="org.netsharp.organization.controller.LoginController"%>
<%@ page
	import="org.netsharp.organization.controller.dto.LoginResultDTO"%>
<%@ page import="org.netsharp.panda.core.HttpContext"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletRequest"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletResponse"%>
<%@ page import="org.netsharp.panda.core.comunication.HtmlWriter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息</title>

</head>
<body>
	<%
		String swtCustomerId = request.getParameter("swtCustomerId");
		String swtServiceId = request.getParameter("swtServiceId");
		
		String ts = request.getParameter("ts");

		String nopermissionUrl = "/nav/panda-bizbase/authorization/nopermission";
		ICustomerServiceConfigService configService = ServiceFactory.create(ICustomerServiceConfigService.class);
		CustomerServiceConfig entity = configService.bySwtServiceId(swtServiceId);
		if (entity == null) {

			response.sendRedirect(nopermissionUrl);
		} else {

			//登录
			HttpContext ctx = new HttpContext();
			{
				ctx.setRequest(new ServletRequest(request, response));
				ctx.setResponse(new ServletResponse(response));
				ctx.setContext(request.getServletContext());
				ctx.setWriter(new HtmlWriter(response.getWriter()));
			}
			HttpContext.setCurrent(ctx);

			Employee employee = entity.getEmployee();
			LoginController loginController = new LoginController();
			LoginResultDTO result = loginController.login(employee.getLoginName(), employee.getPwd());
			if (result.getResult() != 1) {
				response.sendRedirect(nopermissionUrl);
			}
			//response.sendRedirect("/panda/crm/customer/all/form?swtCustomerId=" + swtCustomerId+"&swtServiceId="+employee.getId());
		}
	%>
</body>

<div>
	swtCustomerId：<%=swtCustomerId%></div>
<div>
	swtServiceId：<%=swtServiceId%></div>
<div>
	ts：<%=ts%></div>
</html>