<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单结转</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style>
		tr.stage{display: none;}
	</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/gsb/platform/trade/js/order/order-carryover.ctrl.js'></script>
</head>
    <body class="easyui-layout" style="background-color:#fff;">
    
    	<div class="easyui-panel" title="订单信息" data-options="height:200,bodyCls:'no-border'" > 
		        <jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
		</div>
		<div class="easyui-panel" title="结转信息" data-options="height:210,bodyCls:'no-border'" style="padding:10px;">

          <form id="form1" style="padding-top: 0px;">
		   <table cellpadding="3" cellspacing="0" class="form-panel">
		      <tr>
		          <td class="label_td"><label style="color:Red">*</label><label>来源订单号：</label></td>
		          <td class="control_td">
		          		<input id="formOrderNo" class="easyui-validatebox nsInput"
		          			data-options="required:true,disabled:true"/></td>
		          <td class="label_td"><label style="color:Red">*</label><label>去向订单号：</label></td>
		          <td class="control_td">
		          		<!-- 存储临时来源订单值 -->
					<input type="hidden" id ="payablePrice_hidden">
					<input type="hidden" id ="paidPrice_hidden">
					<input type="hidden" id ="refundPrice_hidden">
					<input type="hidden" id ="carryAmount_hidden">
					<input type="hidden" id ="carryIntoAmount_hidden"> 
					<!-- 存储临时去向订单值 -->
					<input type="hidden" id ="toOrderId_hidden">
					<input type="hidden" id ="toPayAmount_hidden">  		          		
					<input id="toOrderNo" class="easyui-validatebox nsInput" onblur="carryoverCtrl.toOrderNoBlur();"
							data-options="width:150,required:true"/></td>
		          <td class="label_td"><label style="color:Red">*</label><label>结转金额：</label></td>
		          <td class="control_td">
		          		<input id="amount" class="easyui-numberbox nsInput"
		          			data-options="precision:2,width:150,min:1,required:true,onChange:function(newValue,oldValue){carryoverCtrl.amountChange(newValue,oldValue);}"/>
		          </td>
			</tr>
		      <tr>
		          <td class="label_td"><label style="color:Red">*</label><label>结转说明：</label></td>
		          <td colspan="5" class="control_td">
						<textarea id="carryRemark" style="width:100%;height:50px;" class="easyui-validatebox nsInput" data-options="width:150,required:true"></textarea>
		          </td>
			</tr>
		</table>
		</form>
		</div>
</body>

<script>

	var stagetrl = null;
	$(function(){
		
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);

		carryoverCtrl = new com.gongsibao.trade.web.OrderCarryoverCtrl();
		carryoverCtrl.init();
	});
</script>
</html>
