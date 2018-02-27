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
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="销售简报" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
        <div class="row" style="height:300px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="指标" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="交易" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
		<div class="row" style="height:150px;">
        	<div class="cell cell-12">
	        	<div class="easyui-panel" title="线索转化" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>

        <div class="row" style="height:300px;">
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="转化分析" style="padding:10 20px;" data-options="fit:true,border:false">
			    </div>
        	</div>
        	<div class="cell cell-6">
	        	<div class="easyui-panel" title="抽查异常" data-options="fit:true,border:false">
			    </div>
        	</div>
        </div>
	</div>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>

</html>
