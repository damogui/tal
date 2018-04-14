<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>发票审核</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
</head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:220">
        	 <div class="formContent">
		        <fieldset style="margin-bottom:0px;">
		        	<legend>订单信息</legend>
					<jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
				</fieldset>
        	 </div>
		</div>
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,tabHeight:30">
			    <div title="发票信息">
			    	这里可以嵌套一个iframe
			    </div>   
			    <div title="审批进度">   
			         <table id="audit_progress_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script src='/gsb/platform/trade/js/audit/audit-base.ctrl.js'></script>
<script src='/gsb/platform/trade/js/audit/audit-invoice.ctrl.js'></script>
<script>

	var auditInvoiceCtrl = null;
	$(function(){
		
		auditInvoiceCtrl = new com.gongsibao.trade.web.AuditInvoiceCtrl();
		auditInvoiceCtrl.init(); 
	});
</script>
</html>
