<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>申请退款</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style type="text/css">.label_td{width:100px;}</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/gsb/platform/trade/js/order.detail-ctrl.js'></script>
</head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:400">
        	 <div class="formContent">
		        <fieldset style="margin-bottom:0px;">
		        	<legend>订单信息</legend>
					<jsp:include page="/gsb/platform/trade/orderInfo.jsp"></jsp:include>
				</fieldset>
		        <fieldset style="margin-bottom:0px;">
		        	<legend>退款信息</legend>
					  <table cellpadding="3" cellspacing="0" class="form-panel">
					      <tr>
					          <td class="label_td"><label>是否结转：</label></td>
					          <td class="control_td"><input class="easyui-switchbutton" data-options="onText:'是',offText:'否'"></td>
					          <td class="label_td"><label>结转去向订单号：</label></td>
					          <td class="control_td"><input class="easyui-validatebox nsInput" style="width:150px"></td>
					          <td class="label_td"><label></label></td>
					          <td class="control_td"></td>
					   </tr>
					      <tr>
					          <td class="label_td"><label>退款账套：</label></td>
					          <td class="control_td">
									<select id="cc" class="easyui-combobox" data-options="editable:false,width:150">
									    <option value="aa">aitem1</option>   
									    <option>bitem2</option>   
									    <option>bitem3</option>   
									    <option>ditem4</option>   
									    <option>eitem5</option>   
									</select>  
					          </td>
					          <td class="label_td"><label>退款支付方式：</label></td>
					          <td class="control_td">
									<select id="cc3" class="easyui-combobox" data-options="editable:false,width:150">
									    <option value="aa">aitem1</option>   
									    <option>bitem2</option>   
									    <option>bitem3</option>   
									    <option>ditem4</option>   
									    <option>eitem5</option>   
									</select>  
					          </td>
					          <td class="label_td"><label>退款类别：</label></td>
					          <td class="control_td">
									<select id="cc3" class="easyui-combobox" data-options="editable:false,width:150">
									    <option value="aa">aitem1</option>   
									    <option>bitem2</option>   
									    <option>bitem3</option>   
									    <option>ditem4</option>   
									    <option>eitem5</option>   
									</select>  
					          </td>
					   </tr>
					      <tr>
					          <td class="label_td"><label>退款账户名称：</label></td>
					          <td class="control_td"><input class="easyui-validatebox nsInput" style="width:150px;"/></td>
					          <td class="label_td"><label>退款账号：</label></td>
					          <td class="control_td"><input class="easyui-validatebox nsInput" style="width:150px;"/></td>
					          <td class="label_td"><label>退款金额：</label></td>
					          <td class="control_td">
					          	<input class="easyui-validatebox nsInput" style="width:150px;"/>
					          </td>
						</tr>
					      <tr>
					          <td class="label_td"><label>退款说明：</label></td>
					          <td colspan="5" class="control_td">
									<textarea collected="true" controlType="TextArea" id="momo" style="width:100%;height:50px;" class="easyui-validatebox nsInput"></textarea>
					          </td>
						</tr>
					</table>
				</fieldset>
        	 </div>
		</div>
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" style="height:100%;">   
			    <div title="退款产品">   
			          <table id="order_product_grid"></table>
			    </div>   
			    <div title="退款业绩分配">   
			         <table id="order_payment_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script>
	$(function(){
		
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		
		var formCtrl = new com.gongsibao.trade.web.OrderFormCtrl();
		formCtrl.init();
	});
</script>
</html>
