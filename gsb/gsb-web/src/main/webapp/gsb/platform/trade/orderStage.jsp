<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单分期</title>
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
	<script src='/gsb/platform/trade/js/order.stage-ctrl.js'></script>
</head>
    <body class="easyui-layout">
	    <div class="easyui-panel" title="订单信息"  data-options="height:220" style="padding:10px;"> 
	        <jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
		</div>
	       <div id="stage_panel" class="easyui-panel" title="分期信息"  style="padding:10px;"> 
	      	<form id="form1" style="padding-top: 0px;">
			   <table cellpadding="3" cellspacing="0" class="form-panel">
			       <tr>
			           <td class="label_td"><label>分期期数：</label></td>
			           <td class="control_td">
	                                 <select id="stageNum" class="easyui-combobox" 
	                                 data-options="value:'',width:100,editable:false,onChange:function(newValue,oldValue){stagetrl.stageNumChange(newValue,oldValue);}">
	                                     <option value="2">二期</option>
	                                     <option value="3">三期</option>
	                                     <option value="4">四期</option>
							</select>
			           </td>
			           <td class="label_td"><label>分期金额：</label></td>
			           <td class="control_td">
			           		<input id="stageAmount" class="easyui-numberbox" data-options="precision:2,width:100,min:1,disabled:true"/>
			           </td>
				   </tr>
			       <tr class="stage" num="1">
			           <td class="label_td"><label>一期付款：</label></td>
			           <td class="control_td">
			           		<input id="stageAmount1" class="easyui-numberbox" 
			           		data-options="precision:2,width:100,min:1,required:true,onChange:function(newValue,oldValue){stagetrl.stageAmountChange(newValue,oldValue,1);}"/>
			           </td>
			           <td class="label_td"><label>付款比例：</label></td>
			           <td class="control_td">
			           		<input id="stagePercentage1" class="easyui-numberbox" data-options="precision:4,width:100,min:0,max:100,suffix:'%',disabled:true"/>
			           </td>
				   </tr>
			       <tr class="stage" num="2">
			           <td class="label_td"><label>二期付款：</label></td>
			           <td class="control_td">
			           		<input id="stageAmount2" class="easyui-numberbox" 
			           		data-options="precision:2,width:100,min:1,required:true,onChange:function(newValue,oldValue){stagetrl.stageAmountChange(newValue,oldValue,2);}"/>
			           </td>
			           <td class="label_td"><label>付款比例：</label></td>
			           <td class="control_td">
			           		<input id="stagePercentage2" class="easyui-numberbox" data-options="precision:4,width:100,min:0,max:100,suffix:'%',disabled:true"/>
			           </td>
				   </tr>
			       <tr class="stage" num="3">
			           <td class="label_td"><label>三期付款：</label></td>
			           <td class="control_td">
			           		<input id="stageAmount3" class="easyui-numberbox" 
			           		data-options="precision:2,width:100,min:1,required:true,onChange:function(newValue,oldValue){stagetrl.stageAmountChange(newValue,oldValue,3);}"/>
			           </td>
			           <td class="label_td"><label>付款比例：</label></td>
			           <td class="control_td">
			           		<input id="stagePercentage3" class="easyui-numberbox" data-options="precision:4,width:100,min:0,max:100,suffix:'%',disabled:true"/>
			           </td>
				   </tr>
			       <tr class="stage" num="4">
			           <td class="label_td"><label>四期付款：</label></td>
			           <td class="control_td">
			           		<input id="stageAmount4" class="easyui-numberbox" 
			           		data-options="precision:2,width:100,min:1,required:true,onChange:function(newValue,oldValue){stagetrl.stageAmountChange(newValue,oldValue,4);}"/>
			           </td>
			           <td class="label_td"><label>付款比例：</label></td>
			           <td class="control_td">
			           		<input id="stagePercentage4" class="easyui-numberbox" data-options="precision:4,width:100,min:0,max:100,suffix:'%',disabled:true"/>
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

		stagetrl = new com.gongsibao.trade.web.OrderStageCtrl();
		stagetrl.init();
	});
</script>
</html>
