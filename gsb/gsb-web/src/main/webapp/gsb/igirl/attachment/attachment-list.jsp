<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>附件</title>
	<link href="/package/font-awesome/css/font-awesome.min.css" rel='stylesheet' type='text/css' />
	<link href="/package/easyui/themes/material/easyui.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/color.css" rel="stylesheet" type="text/css" />
	<link href="/package/easyui/themes/easyui.extend.css" rel="stylesheet" type="text/css"/>
	<link href="/panda-res/css/panda.form.css" rel="stylesheet" type="text/css"/>
</head>
<body id="bodyLayout" class="easyui-layout">

    <div id="toolbar" class="toolbar">
		<a iconCls="fa fa-cloud-upload" plain="true" id="btn_upload" class="easyui-linkbutton">上传</a>
		<div id="p" style="width:100%;"></div>
		</div>

	<div id="tdiv"><table id="attachment_grid"></table></div>
	
 	<script src="/package/easyui/jquery.min.js"></script>
	<script src="/package/easyui/jquery.easyui.min.js"></script>
	<script src="/package/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script src='/package/easyui/jquery.easyui.patch.js'></script>
	<script src="/package/easyui/jquery.easyui.extend.js"></script>
	<script src='/package/qiniu/plupload.full.min.js'></script>
	<script src="/panda-res/js/system.js"></script>
	<script src="/package/layer/layer.js"></script>
	<script src="/panda-res/js/panda.core.js"></script>
	<script src="/panda-res/js/panda.controls.js"></script>
	<script src="/gsb/igirl/attachment/attachmentListPart.js"></script>
	<script>
    var listController = new org.netsharp.core.attachmentListController();
		$(function(){
		  $('#p').progressbar({
		        value: 0
		    });
		  if(System.Url.getParameter("foreignKey")==""){
			    $("#tdiv").hide();
		    }else{
		    	$("#tdiv").show();
		    }
		  listController.init();
		});
    </script>
</body>
</html>