<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-bizbase/home.css' rel='stylesheet' type='text/css' />
</head>
<body>
	<div class="page-content page-index">
		<jsp:include page="/panda-bizbase/account-info.jsp"></jsp:include>
		<jsp:include page="portal-statistic.jsp"></jsp:include>
	</div>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/panda-res/js/system.js'></script>
<script type="text/javascript">
$(function(){
	var serviceLocator = new org.netsharp.core.JServiceLocator();
	var service = "com.gongsibao.crm.web.home.PortalStatistic";
	serviceLocator.invoke(service, 'getNewTasksCount', null, function(data){
		alert(data);
	}, null, true);
})
</script>
</html>
