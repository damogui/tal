<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String employeeId = request.getParameter("employeeId");
%>
<!DOCTYPE html>
<html>
<head>
	<title>工作台</title>
	<link rel="stylesheet" href='/package/font-awesome/css/font-awesome.min.css'/>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>

    <div class="weui-grids">
      <a href="franchiseeList?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-user"></i>
        </div>
        <p class="weui-grid__label">我的客户</p>
      </a>
      <a href="franchiseeEdit?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-user-plus"></i>
        </div>
        <p class="weui-grid__label">添加客户</p>
      </a>
      <a href="awaitTrackCustomerList?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-user"></i>
        </div>
        <p class="weui-grid__label">待跟进客户</p>
      </a>

      <a href="javascript:;" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-bar-chart"></i>
        </div>
        <p class="weui-grid__label">日统计</p>
      </a>
      <a href="javascript:;" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-bar-chart"></i>
        </div>
        <p class="weui-grid__label">周统计</p>
      </a>
      <a href="javascript:;" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-bar-chart"></i>
        </div>
        <p class="weui-grid__label">月统计</p>
      </a>
      <a href="" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-area-chart"></i>
        </div>
        <p class="weui-grid__label">客户统计</p>
      </a>
      <a href="trackList?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-list-ol"></i>
        </div>
        <p class="weui-grid__label">跟进记录</p>
      </a>
      <a href="myInfo?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-vcard-o"></i>
        </div>
        <p class="weui-grid__label">个人信息</p>
      </a>
    </div>
</body>
</html>