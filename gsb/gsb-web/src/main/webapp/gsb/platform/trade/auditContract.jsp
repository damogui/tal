<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>合同审核</title>
	<jsp:include page="/gsb/platform/trade/meta.jsp"></jsp:include>
</head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:375">
        	 <div class="formContent">
		        <fieldset style="margin-bottom:0px;">
		        	<legend>订单信息</legend>
					<jsp:include page="/gsb/platform/trade/orderInfo.jsp"></jsp:include>
				</fieldset>
		        <fieldset style="margin-bottom:0px;">
		        	<legend>退款信息</legend>
					  <table cellpadding="3" cellspacing="0" class="form-panel">
					      <tr>
					          <td class="label_td"><label style="color:Red">*</label><label>退款账套：</label></td>
					          <td class="control_td" colspan="3">
									<input id="setOfBooksId" class="easyui-combobox" 
									data-options="editable:false,width:415"/>
					          </td>
					          <td class="label_td"><label style="color:Red">*</label><label>退款类别：</label></td>
					          <td class="control_td">
									<input id="refundType" class="easyui-combobox" 
									data-options="editable:false,width:150,onChange:function(newValue,oldValue){refundCtrl.refundTypeChange(newValue,oldValue);},
									method:'get',url:'/panda/rest/enum?name=com.gongsibao.entity.trade.dic.RefundType'"/> 
					          </td>
					   </tr>
					      <tr>
					          <td class="label_td"><label style="color:Red">*</label><label>退款账户名称：</label></td>
					          <td class="control_td">
					          		<input id="payerName" class="easyui-validatebox nsInput" style="width:150px"
					          			data-options="required:true"/></td>
					          <td class="label_td"><label style="color:Red">*</label><label>退款账号：</label></td>
					          <td class="control_td">
					          		<input id="bankNo" class="easyui-validatebox nsInput"   style="width:150px"
										data-options="width:150,required:true"/></td>
					          <td class="label_td"><label style="color:Red">*</label><label>退款金额：</label></td>
					          <td class="control_td">
					          		<input id="amount" class="easyui-numberbox nsInput"
					          			data-options="precision:2,width:150,min:1,required:true"/>
					          </td>
						</tr>
					      <tr>
					          <td class="label_td"><label style="color:Red">*</label><label>退款说明：</label></td>
					          <td colspan="5" class="control_td">
									<textarea id="refundRemark" style="width:100%;height:50px;" class="easyui-validatebox nsInput" data-options="width:150,required:true"></textarea>
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
			         <table id="order_refund_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script src='/gsb/platform/trade/js/audit.contract-ctrl.js'></script>
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
