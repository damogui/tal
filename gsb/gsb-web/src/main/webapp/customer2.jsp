
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
		String swtServiceId2 = java.net.URLDecoder.decode("%e5%88%98%e7%ab%8b%e4%b8%b9");
		
		String swtServiceId_md5 = org.netsharp.util.EncrypUtil.md5("刘立丹");
		
		String ts = request.getParameter("ts");

	%>
</body>

<div>
	swtCustomerId：<%=swtCustomerId%></div>
<div>
	swtServiceId：<%=swtServiceId%></div>
	<div>
	swtServiceId：<%=swtServiceId2%></div>
	
		<div>
	swtServiceId_md5：<%=swtServiceId_md5%></div>
<div>
	ts：<%=ts%></div>
</html>