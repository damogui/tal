<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回款统计</title>
<link
	href='/package/font-awesome/css/font-awesome.min.css?v=20180413104131'
	rel='stylesheet' type='text/css' />
<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet'
	type='text/css' />
<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet'
	type='text/css' />
<link href='/package/easyui/themes/color.css' rel='stylesheet'
	type='text/css' />
<link href='/package/easyui/themes/icon.css' rel='stylesheet'
	type='text/css' />
<style>
.datagrid-ftable {
	color: red;
}
</style>
</head>
<body class="easyui-layout">
	<div id="center"
		data-options="region:'center',split:false,collapsible:false,closed:false">
		<div id="content" class="page-content">
			<form id="queryFrom">
				<ul class="query-panel">
					<li class="item">
						<div class="label">起始时间：</div>
						<div class="ctrl">
							<table cellpadding="0" cellspacing="0" id="createTime">
								<tr>
									<td style="width: 90px;"><input type="text"
										id="start_date" style="width: 92px;" class="easyui-datebox" /></td>
									<td><label style="margin: 0 2px;"> - </label></td>
									<td style="width: 90px;"><input type="text" id="end_date"
										style="width: 92px;" class="easyui-datebox" /></td>
								</tr>
							</table>
						</div>
					</li>
					<li class="item btn"><a
						href="javascript:payReportCtrl.query();"
						class="easyui-linkbutton btn"
						data-options="plain:false,iconCls:'fa fa-search'"> 查 询 </a> <a
						href="javascript:payReportCtrl.downloadExl();"
						class="easyui-linkbutton btn"
						data-options="plain:false,iconCls:'fa fa-file-excel-o'"> 导出</a></li>
					<li class="item">
					
					</li>
				</ul>
			</form>
			<table id="datagrid"></table>
		</div>
	</div>
<a href="" download="这里是下载的文件名.xlsx" id="hf"></a>
</body>
<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>
<script src="/package/js-xlsx/xlsx.full.min.js"></script>
<script src='/panda-res/js/system.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/panda-res/js/panda.js'></script>
<script src='/gsb/platform/report/js/pay-report.ctrl.js'></script>
<script>



var tmpDown; //导出的二进制对象
	var payReportCtrl = null;
	$(function() {

		payReportCtrl = new com.gongsibao.report.web.PayReportCtrl();
		payReportCtrl.init();
	});
</script>
</html>