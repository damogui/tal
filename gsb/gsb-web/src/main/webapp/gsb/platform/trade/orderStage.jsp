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
        <div data-options="region:'center',split:false,collapsible:false,closed:false" style="height:100%;" >
            <div id="tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,plain:false,tabPosition:'top',tabWidth:0,tabHeight:35">
			    <div title="订单信息">
			        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:200">
						<jsp:include page="/gsb/platform/trade/orderInfo.jsp"></jsp:include>
					</div>
					<div id="center" data-options="region:'center'">
						<div id="detail_tabs">   
						    <div title="分期信息">   
						        <div style=" border:0px;padding:20px;">
								   <table cellpadding="3" cellspacing="0" class="form-panel">
								       <tr>
								           <td class="label_td"><label>分期期数：</label></td>
								           <td class="control_td"></td>
								           <td class="label_td"><label>分期金额：</label></td>
								           <td class="control_td"></td>
									   </tr>
								       <tr>
								           <td class="label_td"><label>一期付款：</label></td>
								           <td class="control_td"></td>
								           <td class="label_td"><label>付款比例：</label></td>
								           <td class="control_td"></td>
									   </tr>
								       <tr>
								           <td class="label_td"><label>二期付款：</label></td>
								           <td class="control_td"></td>
								           <td class="label_td"><label>付款比例：</label></td>
								           <td class="control_td"></td>
									   </tr>
								       <tr>
								           <td class="label_td"><label>三期付款：</label></td>
								           <td class="control_td"></td>
								           <td class="label_td"><label>付款比例：</label></td>
								           <td class="control_td"></td>
									   </tr>
								       <tr>
								           <td class="label_td"><label>四期付款：</label></td>
								           <td class="control_td"></td>
								           <td class="label_td"><label>付款比例：</label></td>
								           <td class="control_td"></td>
									   </tr>
									</table>
								</div>
						    </div>
						</div>
					</div>
			    </div>
			</div>
    	</div>
    
</body>

<script>
	$(function(){
		
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});
		//var formCtrl = new com.gongsibao.trade.web.OrderFormCtrl();
		//formCtrl.init();
	});
</script>
</html>
