<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>结转审核</title>
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
			<div id="detail_tabs" style="height:100%;">   
			    <div title="结转信息">   
			          	<!-- 这里可以嵌套一个iframe -->
			          	<table cellpadding="3" cellspacing="0" class="form-panel" id="carryover_info_grid"></table>
			    </div>   
			    <div title="审批进度">   
			         <table id="audit_progress_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script src='/gsb/platform/trade/js/audit/audit-base.ctrl.js'></script>
<script src='/gsb/platform/trade/js/audit/audit-carryover.ctrl.js'></script>
<script>

	var auditCarryoverCtrl = null;
	$(function(){
		
		auditCarryoverCtrl = new com.gongsibao.trade.web.AuditCarryoverCtrl();
		auditCarryoverCtrl.init(); 
	});
</script>
</html>
