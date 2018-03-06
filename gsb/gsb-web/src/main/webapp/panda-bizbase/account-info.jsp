<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
<div class="media account-info">
    <div class="media-left">
        <div class="avatar avatar-online">
            <img src="http://demo.admui.com/public/images/avatar.svg">
            <i class="avatar avatar-busy"></i>
        </div>
    </div>
    <div class="media-body">
        <h4 class="media-heading">  欢迎您，<%=employee.getName() %> 
        	<%
        		if(employee.getPost() != null){
        	%>
        	
        	<%=employee.getPost().getName() %>
        	
        	<%}%>
        </h4>
        <p>
            <i class="icon icon-color wb-bell"></i> 这是您第 <%=employee.getLoginNum()%> 次登录，上次登录日期：<%=employee.getLastLoginTime()!=null?employee.getLastLoginTime():"" %>，详细信息请查看
            <a href="javascript:window.top.workbench.openWorkspace('操作日志','/panda/system/nlog/list?creatorId=<%=employee.getId()%>','fa fa-list-ul', true,'1',null,null,null);">日志</a>，如果不是您本人登录，请及时  <a href="javascript:window.top.workbench.changePassword();">修改密码</a>。
        </p>
    </div>
    <div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div id="briefing" class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
	        		<p>
	        			<span></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        			<span class="padding-15"></span>
	        		</p>
			    </div>
        	</div>	
        </div>
</div>