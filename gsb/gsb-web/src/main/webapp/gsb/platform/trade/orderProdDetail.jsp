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
		
/* 		.toolbar a{
			background-color: #1E7CB5;
		    color: #fff;
		    font-size: 12px;
		} */
		.toolbar{
			padding:1px 0;
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
		
		table.form-panel td.control_td {
			
			min-width: 150px;
		}
		
		.file-preview-item{
			float:left;
			width:250px;
			margin:15px;
		}
		
		.file-preview-item p{
		
			margin:5px 0;
		}
		
		.btn_top{
		
			position: absolute; 
			left: 0px; 
			top: 0px;
			border-radius: 3px;
			padding:4px 12px;
			cursor: pointer;
			color:transparent;
		}
		
		.btn_top:hover{
		    color: #fff;
    		background-color: #1E7CB5;
		}
		
		.file-preview-item img{
			width:250px;
			height:150px;
			border: 1px solid #eee;
		}
		
		.file-preview-item img:hover{
			border: 1px solid #1E7CB5;
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
	<script src='/gsb/platform/trade/js/prod/select-company.ctrl.js'></script>
	
	<script src='/gsb/platform/trade/js/prod/prod-main.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-trace.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-principal.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-file.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-company.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-customer.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-order.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-file-preview.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/prod/prod-trail.ctrl.js'></script>
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
<!--					   			 	<td><div class="datagrid-btn-separator"></div></td>
 					   			 	<td>
					   			 		<a id="btn7" href="javascript:traceCtrl.setAccount();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-user-o'">帐号密码</a>
					   			 	</td>
					   			 	<td><div class="datagrid-btn-separator"></div></td>
					   			 	<td>
					   			 		<a id="btn8" href="javascript:traceCtrl.setQualification();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-file-o'">资质维护</a>
					   			 	</td> -->
			   			 	</tr>
		   			 	</table>
		   			</div>
		   			<div style="padding-left:30px;height: 162px;background-color: #fff;">
			   			<p>订单用时： <span id="processdDays"></span> / <span id="needDays"></span> 天</p>
			   			<p style="font-size:24px;margin: 10px 0;"><span id="processStatus" style="color:#009688;"></span> <span id="nodeDayCount"></span> / <span id="weekdayCount"></span>天</p>
			   			
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

			      <table cellpadding="3" cellspacing="0" class="form-panel" style="margin:10px;">
				    <tr>
				          <td class="label_td"><label>订单编号：</label></td>
				          <td class="control_td" id="order_info_order_no"></td>
				          <td class="label_td"><label>业务员：</label></td>
				          <td class="control_td" id="order_info_salesman"></td>
				          <td class="label_td"><label>地区：</label></td>
				          <td class="control_td" id="order_info_city"></td>
				   </tr>
				   <tr>
				          <td class="label_td"><label>支付金额：</label></td>
				          <td class="control_td" id="order_info_payablePrice"></td>
				          <td class="label_td"><label>下单时间：</label></td>
				          <td class="control_td" id="order_info_createTime"></td>
				          <td class="label_td"><label>付款状态：</label></td>
				          <td class="control_td" id="order_info_payStatus"></td>
				   </tr>
				   <tr>
				          <td colspan="6" class="control_td">
				          	<table id="service_item_grid"></table>
				          </td>
				   </tr>
				   <tr>
				          <td colspan="6" class="control_td" style="padding-top:10px;">
				          	<table id="other_prod_grid"></table>
				          </td>
				   </tr>  
				</table>
		    </div>
		    <div title="客户信息">
			      <table cellpadding="3" cellspacing="0" class="form-panel" style="margin:10px;">
				    <tr>
				          <td class="label_td"><label>联系人姓名：</label></td>
				          <td class="control_td" id="customer_name"></td>
				          <td class="label_td"><label>联系人手机：</label></td>
				          <td class="control_td" id="customer_mobile"></td>
				          <td class="label_td"><label>微信：</label></td>
				          <td class="control_td" id="customer_weixin"></td>
				   </tr>
				      <tr>
				          <td class="label_td"><label>固定电话：</label></td>
				          <td class="control_td" id="customer_telephone"></td>
				          <td class="label_td"><label>QQ：</label></td>
				          <td class="control_td" id="customer_qq"></td>
				          <td class="label_td"><label>邮箱：</label></td>
				          <td class="control_td" id="customer_email"></td>
				   </tr>
				   <tr>
				          <td class="label_td"><label>收货地址：</label></td>
				          <td colspan="5" class="control_td" id="customer_address"></td>
				   </tr>
				</table>
		    </div>  
		    <div title="企业信息">
		        <div class="toolbar" style="border-bottom: 1px solid #eee;border-top-width:0px;border-top: 1px solid #eee;background-color: #fff;">
		       		<a id="btn_add_relevance_company" href="javascript:companyCtrl.addRelevanceCompany();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus'">添加关联公司</a>
		       		<a id="btn_edit_company" href="javascript:companyCtrl.editCompany();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-edit'">编辑公司信息</a>
		       		<a id="btn_cancel_relevance_company" href="javascript:companyCtrl.cancelRelevanceCompany();" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-trash-o'">取消关联<span style="color:red;">(慎用)</span></a>
		        </div>
		       <p style="padding-left:30px;">
		       
		       		订单明细（产品）关联公司：<span id="relevance_companyName"></span>
		       </p>
		    </div>
		    <div title="材料预览">   
		       <div id="file_preview_panel" style="width:100%;">
		       </div>
		    </div>
<!--  		    <div title="自动进度">   
		         <table id="order_prod_trail_grid">7</table>
		    </div> -->
		</div>
</body>

<script>

	var prodMainCtrl = null;
	var traceCtrl = null;
	var principalCtrl = null;
	var companyCtrl = null;
	var filePreviewCtrl = null;
 	$(function(){
		
 		prodMainCtrl = new com.gongsibao.trade.web.ProdMainCtrl();
 		prodMainCtrl.init();
	});
</script>
</html>
