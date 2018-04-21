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
        <p class="weui-grid__label">我的待办</p>
      </a>
      
      <a href="franchiseeEdit?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-user-plus"></i>
        </div>
        <p class="weui-grid__label">我的已办</p>
      </a>
      <a href="awaitTrackCustomerList?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-user"></i>
        </div>
        <p class="weui-grid__label">借款申请</p>
      </a>


      <a href="trackList?employeeId=<%=employeeId %>" class="weui-grid js_grid">
        <div class="weui-grid__icon" style="color:#4C84C4;">
          <i class="fa fa-list-ol"></i>
        </div>
        <p class="weui-grid__label">报销申请</p>
      </a>

    </div>
</body>
</html>