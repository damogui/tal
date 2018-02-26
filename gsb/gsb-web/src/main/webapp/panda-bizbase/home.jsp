<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.netsharp.authorization.UserPermissionManager"%>
<%@ page import="org.netsharp.authorization.UserPermission"%>
<%@ page import="org.netsharp.organization.entity.Employee"%>
<%@ page import="org.netsharp.panda.core.HttpContext"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletRequest"%>
<%@ page import="org.netsharp.panda.core.comunication.ServletResponse"%>
<%@ page import="org.netsharp.panda.core.comunication.HtmlWriter"%>
<%
	HttpContext ctx = new HttpContext();
	{
		ctx.setRequest(new ServletRequest(request, response));
		ctx.setResponse(new ServletResponse(response));
		ctx.setContext(request.getServletContext());
		ctx.setWriter(new HtmlWriter(response.getWriter()));
	}
	HttpContext.setCurrent(ctx);
	UserPermission permission = UserPermissionManager.getUserPermission();
	Employee employee = permission.getEmployee();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href='/package/font-awesome/css/font-awesome.min.css'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link rel="stylesheet"
	href="http://cdn.admui.com/demo/iframe/1.0.0/themes/classic/base/css/index.css" />
<link rel="stylesheet"
	href="http://cdn.admui.com/demo/iframe/1.0.0/themes/classic/base/css/site.css" />
<link href='/panda-bizbase/home.css' rel='stylesheet' type='text/css' />
</head>
<body>
	<div class="page animation-fade page-index">

		<div class="page-content page-index">
			<div class="media account-info">
				<div class="media-left">
					<div class="avatar avatar-online">
						<img src="http://demo.admui.com/public/images/avatar.svg"> <i
							class="avatar avatar-busy"></i>
					</div>
				</div>
				<div class="media-body">
					<h4 class="media-heading">
						欢迎您，<%=employee.getName()%>
						<%
							if (employee.getPost() != null) {
						%>

						<%=employee.getPost().getName()%>

						<%
							}
						%>
					</h4>
					<p>
						<i class="icon icon-color wb-bell"></i> 这是您第<span
							style="color: red;"><%=employee.getLoginNum()%></span>
						次登录，上次登录日期：<%=employee.getLastLoginTime() != null ? employee.getLastLoginTime() : ""%>，详细信息请查看
						<a
							href="javascript:window.top.workbench.openWorkspace('操作日志','/panda/system/nlog/list?creatorId=<%=employee.getId()%>',null, true,'1',null,null,null);">日志</a>，如果不是您本人登录，请及时
						<a href="javascript:window.top.workbench.changePassword();">修改密码</a>。
					</p>
				</div>
			</div>

			<div class="row" style="height: 150px;">
				<div class="cell cell-12">
					<div class="easyui-panel" title="销售简报"
						data-options="fit:true,border:false"></div>
				</div>
			</div>
			<div class="row" style="height: 300px;">
				<div class="cell cell-6">
					<div class="easyui-panel" title="指标" style="padding: 10 20px;"
						data-options="fit:true,border:false"></div>
				</div>
				<div class="cell cell-6">
					<div class="easyui-panel" title="交易"
						data-options="fit:true,border:false"></div>
				</div>
			</div>
			<div class="row" style="height: 150px;">
				<div class="cell cell-12">
					<div class="easyui-panel" title="线索转化"
						data-options="fit:true,border:false"></div>
				</div>
			</div>
			<div class="row" style="height: 300px;">
				<div class="cell cell-6">
					<div class="easyui-panel" title="销售阶段转化分析"
						style="padding: 10 20px;" data-options="fit:true,border:false">

					</div>
				</div>
				<div class="cell cell-6">
					<div class="easyui-panel" title="将被收回的客户"
						data-options="fit:true,border:false"></div>
				</div>
			</div>

			<div class="row" style="height: 300px;">
				<div class="cell cell-6">
					<div class="easyui-panel" title="遗忘客户提醒" style="padding: 10 20px;"
						data-options="fit:true,border:false"></div>
				</div>
				<div class="cell cell-6">
					<div class="easyui-panel" title="客户资料需要完善"
						data-options="fit:true,border:false"></div>
				</div>
			</div>

			<!--        
         <div class="row" style="height:200px;">
        	<div class="cell cell-2">
        		<div class="test_cell">公众号</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">企业号</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">钉钉</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">微博</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">支付宝</div>
        	</div>
        	<div class="cell cell-2">
        		<div class="test_cell">地图</div>
        	</div>        	
        </div>
        
        
         <div class="row" style="height:200px;">
        	<div class="cell cell-4">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-4">
        		<div class="test_cell">B</div>
        	</div>
        	<div class="cell cell-4">
        		<div class="test_cell">C</div>
        	</div>       	
        </div>
         <div class="row" style="height:200px;">
        	<div class="cell cell-6">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-6">
        		<div class="test_cell">B</div>
        	</div>    	
        </div>   
         <div class="row" style="height:200px;">
        	<div class="cell cell-3">
        		<div class="test_cell">A</div>
        	</div>
        	<div class="cell cell-9">
        		<div class="test_cell">B</div>
        	</div>    	
        </div>  -->
		</div>
	</div>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</html>
