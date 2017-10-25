<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>附件</title>
	<link href="/package/easyui/themes/gray/easyui.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/color.css" rel="stylesheet" type="text/css" />
	<!-- <link href='/panda-res/css/panda.css?v=20151025' rel='stylesheet' type='text/css' /> -->
	<link href='/package/uploadify/uploadify.css' rel='stylesheet' type='text/css' />
	
	<style>
		
		.order_info {
			width: 100%;
			margin: 5px 0 5px 0;
			border: 0
		}
		
		.order_info hr {
			width: 100%;
			border: #ccc 1px dotted
		}
		
		.order_info td {
			padding: 4px 0
		}
		
		.order_info td.label {
			width: 120px;
			text-align: right;
			pading-right: 5px
		}
		
		.order_info td.label span {
			color: red;
			margin-right: 2px
		}
		
		.uploadify{
			margin-bottom:0px;
		}
		
		.uploadify-queue{
			display:none;
		}
		#toolbar {
		    background-color: #f4f4f4;
		    padding-top: 2px;
		    padding-bottom: 2px;
		    border-bottom: 1px solid #ddd;
		    width: 100%;
		}
	</style>
</head>
<body id="bodyLayout" class="easyui-layout">
    <div id="toolbar" class="toolbar" style="height:32px;">
    	<div style="float:left;"><input type="file" name="uploadify" id="uploadify" /></div>
    	<div style="float:left;">
        	<a iconCls="icon-remove" onclick="listController.remove();" plain="true" id="controllershopListdetail" class="easyui-linkbutton">删除</a>
    	</div>
	</div>

	<table id="attachment_grid"></table>
	
 	<%
		String version = "0.1";
	 %>
 	<script src="/package/easyui/jquery.min.js?v=<%=version%>"></script>
	<script src="/package/easyui/jquery.easyui.min.js?v=<%=version%>"></script>
	<script src="/package/easyui/locale/easyui-lang-zh_CN.js?v=<%=version%>"></script>
	<script src='/package/easyui/jquery.easyui.patch.js?v=<%=version%>'></script>
	<script src="/package/easyui/jquery.easyui.extend.js?v=<%=version%>"></script>
	<script src="/panda-res/js/system.js?v=<%=version%>"></script>
	<script src="/panda-res/js/panda.core.js?v=<%=version%>"></script>
	<script src='/package/uploadify/jquery.uploadify.min.js?v=<%=version%>'></script>
	<script src="/panda-platform/attachment/attachmentListPart.js?v=<%=version%>"></script>
	<script>
    	var listController = new org.netsharp.core.attachmentListController();
		$(function(){

			listController.init();
		});
    </script>
</body>
</html>