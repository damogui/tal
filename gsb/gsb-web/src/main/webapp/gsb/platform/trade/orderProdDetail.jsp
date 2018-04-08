<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单详情</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<style>
		a{
			text-decoration: none;
		}
		
		.text-default {
		    color: #404040;
		}
		.text-muted {
		    color: #909FA7;
		}
		.text-warning {
		    color: #fbc02d;
		}
		
		.text-purple {
		    color: #ae81ff;
		}
		
		.text-success {
		    color: #4CAF50;
		}
		
		.text-primary {
		    color: #1E7CB5;
		}

		.orderProd span{
		
			margin:0 5px;
		}
		
		.datagrid-pager{
			border-top-width:0px !important;
		}
		
		.principal-panel .datagrid-wrap{
			border-width:0px !important;
		}
	</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	
	<script src='/gsb/platform/trade/js/prod/select-salesman.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-main.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-trace.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-principal.ctrl.js'></script>
	
</head>
    <body class="easyui-layout">
		<div class="easyui-panel" title="订单信息" style="height:70px;padding-left:10px;" data-options="tools:[{
					iconCls:'fa fa-chevron-left',
					title:'上一条',
					handler:function(){alert('上一条')}
				},{
					iconCls:'fa fa-chevron-right',
					title:'下一条',
					handler:function(){alert('下一条')}
				}]">
		   
		    <p class="orderProd">订单号：<span id="orderNo"></span> 订单明细：<span id="orderProdNo"></span> 公司：<span id="companyName"></span>  办理名称：<span id="handleName"></span>  申请号：<span id="applyNo"></span></p>
		</div>
		<div class="easyui-panel" style="height:210px;border-top-width: 0px;">
			<div style="width:50%;float:left;">
				<div class="easyui-panel" style="height:209px;border-top-width: 0px;border-bottom-width: 0px;">

		   			 <div class="toolbar" style="border-bottom: 1px solid #eee;border-top-width:0px;background-color: #fff;">
		   			 	<table cellspacing="0" cellpadding="0">
			   			 	<tbody>
				   			 	<tr>
					   			 	<td>
		   								<a id="btn_processStatus" href="javascript:traceCtrl.updateProcessStatus();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-refresh'">更改状态</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn_remark" href="javascript:traceCtrl.remark();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-edit'">备注</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
		   								<a id="btn_upload" href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-cloud-upload'">上传</a>
					   			 	</td>
					   			 	
					   				<td><div class="datagrid-btn-separator"></div></td>
 					   			 	<td>
					   			 		<a id="btn3" href="javascript:traceCtrl.markComplaint();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-paint-brush'">标记投诉</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn4" href="javascript:traceCtrl.remindCustomer();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-meh-o'">提示客户</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn5" href="javascript:traceCtrl.markAbnormal();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-fire'">标记异常</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn6" href="javascript:traceCtrl.sendExpress();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-send mr-sm'">发快递</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
<!-- 					   			 	<td>
					   			 		<a id="btn7" href="javascript:traceCtrl.setAccount();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-user-o'">帐号密码</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn8" href="javascript:traceCtrl.setQualification();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-file-o'">资质维护</a>
					   			 	</td> -->
				   			 	</tr>
			   			 	</tbody>
		   			 	</table>
		   			</div>
		   			<div style="padding-left:30px;height: 162px;background-color: #fff;">
			   			<p>订单用时： <span id="processdDays"></span> / <span id="needDays"></span> 天</p>
			   			<p style="font-size:24px;"><span id="processStatus" style="color:#009688;"></span> <span id="nodeDayCount"></span> / <span id="weekdayCount"></span>天</p>
			   			
						<p id="handle_panel">办理名称：<span id="editHandleName" style="color:#428bca">点击编辑</span></p>
						<p id="applyNo_panel">申请号：<span id="editApplyNo" style="color:#428bca">点击编辑</span></p>
		   			</div>
				</div>
			</div>
			<div class="principal-panel" style="width:50%;float:left;">
		   		<table id="order_prod_principal_grid"></table>
			</div>
		</div>
		<div id="detail_tabs" class="easyui-tabs" tabHeight="30" style="height:100%;">   
		    <div title="跟进记录">   
		          <table id="order_prod_trace_grid"></table>
		    </div>   
		    <div title="材料信息">   
		         <table id="order_prod_trace_file_grid"></table>
		    </div>   
		    <div title="订单信息">   
		         
		    </div>
		    <div title="客户信息">   
		        
		    </div>  
		    <div title="企业信息">
		       
		    </div>
		    <div title="材料预览">   
		       
		    </div>
<!-- 		    <div title="自动进度">   
		         <table id="order_prod_trail_grid">7</table>
		    </div> -->
		</div>
</body>

<script>

	var prodMainCtrl = null;
	var traceCtrl = null;
	var principalCtrl = null;
 	$(function(){
		
 		prodMainCtrl = new com.gongsibao.trade.web.ProdMainCtrl();
 		prodMainCtrl.init();
	});
</script>
</html>
