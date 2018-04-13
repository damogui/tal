<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>创建回款</title>
	<link href='/package/font-awesome/css/font-awesome.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/material/easyui.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/easyui.extend.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/color.css' rel='stylesheet' type='text/css' />
	<link href='/package/easyui/themes/icon.css' rel='stylesheet' type='text/css' />
	<link href='/panda-res/css/panda.form.css' rel='stylesheet' type='text/css' />
	<style>
		.form-panel td.label_td{width:170px;}
	</style>
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>	
	<script src='/package/qiniu/plupload.full.min.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/panda-res/js/panda.controls.js'></script>
	<script src='/gsb/platform/trade/js/order-pay.ctrl.js'></script>
</head>
     <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:155">
        	 <div class="formContent">
				  <table cellpadding="3" cellspacing="0" class="form-panel">
					  <tr>
				          <td class="label_td"><label>付款账套：</label></td>
				          <td class="control_td">
								<input id="setOfBooksId" class="easyui-combobox" data-options="editable:false,width:200,onChange:function(newValue,oldValue){payCtrl.setOfBooksIdChange(newValue,oldValue);}"/>
				          </td>
				          <td class="label_td"><label>付款方式：</label></td>
				          <td class="control_td">
				          		<input id="u8BankId" class="easyui-combobox nsInput" data-options="editable:false,width:200"/>
				          </td>
				          <td class="label_td"><label>付款账户名称：</label></td>
				          <td class="control_td">
				          		<input id="offlinePayerName" class="easyui-validatebox nsInput" style="width:200px;"/>
				          </td>
					  </tr>
				      <tr>
				          <td class="label_td"><label>付款账号：</label></td>
				          <td class="control_td">
				          		<input id="offlineBankNo" class="easyui-validatebox nsInput" style="width:200px;"/>
				          </td>
				          <td class="label_td"><label>一笔多单：</label></td>
				          <td class="control_td">
									<input id="payForOrderCount" class="easyui-switchbutton" data-options="width:60,height:28,onText:'是',offText:'否',onChange:function(checked){payCtrl.payForOrderCount=checked;}"/>
				          </td>
				          <td class="label_td"><label>付款金额：</label></td>
				          <td class="control_td">
					          		<input id="amount" class="easyui-numberbox nsInput"
				          			data-options="precision:2,width:200,min:1"/>
						  </td>
					</tr>
				      <tr>
				          <td class="label_td"><label>付款说明：</label></td>
				          <td colspan="3" class="control_td">
								<textarea id="offlineRemark" style="width:100%;height:50px;" class="easyui-validatebox nsInput"></textarea>
				          </td>
					</tr>
				</table>
        	 </div>
		</div>
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,tabHeight:30">
			    <div title="付款凭证">   
			          <table id="pay_voucher_grid"></table>
			    </div>   
			    <div title="关联订单">   
			         <table id="order_relevance_grid"></table>
			    </div>
			</div>
		</div>
</body>
<div id="upload_toolbar">
	<a href="#" id="btn_upload" class="easyui-linkbutton" data-options="iconCls:'fa fa-cloud-upload',plain:true">上传</a>
</div>

<script>

	var payCtrl = null;
	$(function(){
		
		payCtrl = new com.gongsibao.trade.web.OrderPayCtrl();
		payCtrl.init(); 
	});
</script>
</html>
