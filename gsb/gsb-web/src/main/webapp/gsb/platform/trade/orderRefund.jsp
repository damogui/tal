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
	<style type="text/css">
	
		.label_td{width:100px;}
		.no-border{
		  border-width: 0px;
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
    <script src='/package/easyui/datagrid-cellediting.js'></script>
	<script src='/gsb/platform/trade/js/order-allot.ctrl.js'></script>
	<script src='/gsb/platform/trade/js/order.refund-ctrl.js'></script>
</head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:425">

		    <div class="easyui-panel" title="订单信息" data-options="height:200,bodyCls:'no-border'" > 
		        <jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
			</div>
			<div class="easyui-panel" title="退款信息" data-options="height:210,bodyCls:'no-border'" style="padding:10px;">
				  <table cellpadding="3" cellspacing="0" class="form-panel">
				      <tr>
				          <td class="label_td"><label style="color:Red">*</label><label>退款账套：</label></td>
				          <td class="control_td">
								<input id="setOfBooksId" class="easyui-combobox" 
								data-options="editable:false,width:180,onChange:function(newValue,oldValue){refundCtrl.setOfBooksIdChange(newValue,oldValue);}"/>
				          </td>
				          <td class="label_td"><label style="color:Red">*</label><label>付款方式：</label></td>
				          <td class="control_td">
				          		<input id="u8BankId" class="easyui-combobox nsInput" data-options="editable:false,width:180"/>
				          </td>
				          <!-- <td class="label_td"><label style="color:Red">*</label><label>退款类别：</label></td>
				          <td class="control_td">
								<input id="refundType" class="easyui-combobox" 
								data-options="editable:false,width:150,onChange:function(newValue,oldValue){refundCtrl.refundTypeChange(newValue,oldValue);},
								method:'get',url:'/panda/rest/enum?name=com.gongsibao.entity.trade.dic.RefundType'"/> 
				          </td> -->
				          <td class="label_td" style="width:120px;"><label style="color:Red">*</label><label>退款账户名称：</label></td>
				          <td class="control_td">
				          		<input id="payerName" class="easyui-validatebox nsInput" style="width:180px"
				          			data-options="required:true"/></td>
				   </tr>
				     <tr>
				          <td class="label_td"><label style="color:Red">*</label><label>退款账号：</label></td>
				          <td class="control_td">
				          		<input id="bankNo" class="easyui-validatebox nsInput"   style="width:180px"
									data-options="width:150,required:true"/></td>
				          <td class="label_td"><label style="color:Red">*</label><label>退款金额：</label></td>
				          <td class="control_td">
				          		<input type="hidden" id ="refundPrice_hidd">
				          		<input id="amount" class="easyui-numberbox nsInput"
				          			data-options="precision:2,width:180,min:1,required:false,disabled:true,onChange:function(newValue,oldValue){refundCtrl.amountChange(newValue,oldValue);}"/>
				          </td>
				          <td class="label_td"><label>可退款金额：</label></td>
				          <td class="control_td">
				          		<input id="ableRefundAmount" class="easyui-numberbox nsInput"
				          			data-options="precision:2,width:180,min:1,required:false,disabled:true"/>
				          </td>
					</tr>
				      <tr>
				          <td class="label_td"><label style="color:Red">*</label><label>退款说明：</label></td>
				          <td colspan="5" class="control_td">
								<textarea id="refundRemark" style="width:100%;height:50px;" class="easyui-validatebox nsInput" data-options="width:150,required:true"></textarea>
				          </td>
					</tr>
				</table>
			</div>
		</div>
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" style="height:100%;">   
			    <div title="退款产品">   
			          <table id="order_product_grid"></table>
			    </div>   
			    <div title="退款业绩分配">   
			         <table id="order_refund_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script>

	var refundCtrl = null;
	$(function(){
		
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		
		refundCtrl = new com.gongsibao.trade.web.OrderRefundCtrl();
 		refundCtrl.init(); 
	});
</script>
</html>
