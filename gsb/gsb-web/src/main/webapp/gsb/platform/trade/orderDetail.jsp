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
	
	<script src='/package/easyui/jquery.min.js'></script>
	<script src='/package/layer/layer.js'></script>
	<script src='/package/easyui/jquery.easyui.min.js'></script>
	<script src='/package/easyui/locale/easyui-lang-zh_CN.js'></script>
	<script src='/package/easyui/jquery.easyui.extend.js'></script>
	<script src='/panda-res/js/system.js'></script>
	<script src='/panda-res/js/panda.core.js'></script>
	<script src='/panda-res/js/panda.js'></script>
	<script src='/gsb/platform/trade/js/order/order-detail.ctrl.js'></script>
</head>
    <body class="easyui-layout">
        <div data-options="region:'center',split:false,collapsible:false,closed:false" style="height:100%;" >
            <div id="tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,plain:false,tabPosition:'top',tabWidth:0,tabHeight:30">
			    <div title="订单详情">
			        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:200">
						<jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
					</div>
					<div id="center" data-options="region:'center'" style="border-top: 1px #eee solid;">
						<div id="detail_tabs" style="height:100%;">   
						    <div title="产品信息">   
						          <table id="order_product_grid"></table>
						    </div>   
						    <div title="订单业绩分配">
						         <table id="order_per"></table>
						    </div>
                            <div title="回款记录">
                                <table id="order_payment_grid"></table>
                            </div>
                            <div title="回款业绩记录">
                                <table id="order_pay_per"></table>
                            </div>
                            <div title="退款记录">
						         <table id="order_refund_grid"></table>
						    </div>
						    <div title="改价记录">   
						         <table id="order_change_price_grid"></table>
						    </div>
						    <div title="分期信息">   
						         <table id="order_stage_grid"></table>
						    </div>   
						    <%--<div title="优惠明细">   --%>
						         <%--<table id="order_discount_grid"></table>--%>
						    <%--</div>--%>
							<div title="签单公司">
								<table id="order_bill_company"></table>
							</div>
						    <div title="流转日志">   
						         <table id="order_follow_grid"></table>
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
		
		var formCtrl = new com.gongsibao.trade.web.OrderFormCtrl();
		formCtrl.init();
	});
</script>
</html>
