<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单详情</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style>
		.control_td{
			min-width:120px;
		}
		
		.form-panel td.label_td {
		
			line-height: 25px !important;
		}
	</style>
</head>
    <body class="easyui-layout">
        <div data-options="region:'center',split:false,collapsible:false,closed:false" style="height:100%;" >
            <div id="tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,plain:false,tabPosition:'top',tabWidth:0,tabHeight:35">
			    <div title="订单详情">
			        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:200">
			            <div id="partsoOrder" style=" border:0px;padding:20px;">
	                        <table cellpadding="3" cellspacing="0" class="form-panel">
	                            <tr>
	                                <td class="label_td"><label>订单编号：</label></td>
	                                <td class="control_td" id="no"></td>
	                                <td class="label_td"><label>订单金额：</label></td>
	                                <td class="control_td" id="payablePrice"></td>
	                                <td class="label_td"><label>已付金额：</label></td>
	                                <td class="control_td" id="paidPrice"></td>
								</tr>
	                            <tr>
	                                <td class="label_td"><label>客户名称：</label></td>
	                                <td class="control_td" id="accountName"></td>
	                                <td class="label_td"><label>手机：</label></td>
	                                <td class="control_td" id="accountMobile"></td>
	                                <td class="label_td"><label>下单时间：</label></td>
	                                <td class="control_td" id="addTime"></td>
								</tr>
	                            <tr>
	                                <td class="label_td"><label>订单来源：</label></td>
	                                <td class="control_td" id="platformSource"></td>
	                                <td class="label_td"><label>付款状态：</label></td>
	                                <td class="control_td" id="payStatus"></td>
	                                <td class="label_td"><label>分期次数：</label></td>
	                                <td class="control_td" id="installmentCount"></td>
								</tr>
	                            <tr>
	                                <td class="label_td"><label>渠道订单号：</label></td>
	                                <td class="control_td" id="channelOrderNo"></td>
	                                <td class="label_td"><label></label></td>
	                                <td class="control_td"></td>
	                                <td class="label_td"><label></label></td>
	                                <td class="control_td"></td>
								</tr>
	                            <tr>
	                                <td class="label_td"><label>备注：</label></td>
	                                <td colspan="5" class="control_td" id="remark"></td>
								</tr>
							</table>
						</div>
					</div>
					<div id="center" data-options="region:'center'">
						<div id="detail_tabs" style="height:100%;">   
						    <div title="产品信息">   
						          <table id="order_product_grid"></table>
						    </div>   
						    <div title="回款记录">   
						         <table id="order_payment_grid"></table>
						    </div>   
						    <div title="退款记录">   
						         <table id="order_refund_grid"></table>
						    </div>
						    <div title="改价记录">   
						         <table id="order_change_price_grid"></table>
						    </div>  
						    <div title="优惠明细">   
						         <table id="order_discount_grid"></table>
						    </div>
						    <div title="流转日志">   
						         <table id="order_follow_grid"></table>
						    </div>
						</div>
					</div>
			    </div>
			     
			    <div title="合同信息">
			         
			    </div>
			     
			    <div title="任务信息">
			         
			    </div>
			</div>
    	</div>
    
</body>

<script src='/package/easyui/jquery.min.js'></script>
<script src='/package/layer/layer.js'></script>
<script src='/package/easyui/jquery.easyui.min.js'></script>
<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
<script src='/package/easyui/jquery.easyui.extend.js'></script>
<script src='/panda-res/js/system.js'></script>
<script src='/panda-res/js/panda.core.js'></script>
<script src='/gsb/trade/js/order.detail-ctrl.js'></script>
<script>
	$(function(){
		
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		
		var formCtrl = new com.gongsibao.trade.web.OrderFormCtrl();
		formCtrl.init();
	});
</script>
</html>
