<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>合同审核</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
</head>
    <body class="easyui-layout">
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" class="easyui-tabs" style="height:100%;" data-options="fit:true,tabHeight:30">
			    <div title="合同信息">   
			         	这里可以嵌套一个iframe
			    </div>   
			    <div title="审批进度">   
			         <table id="audit_progress_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script src='/gsb/platform/trade/js/audit/audit-base.ctrl.js'></script>
<script src='/gsb/platform/trade/js/audit/audit/audit-contract.ctrl.js'></script>
<script>

	var auditContractCtrl = null;
	$(function(){
		
		auditContractCtrl = new com.gongsibao.trade.web.AuditContractCtrl();
		auditContractCtrl.init(); 
	});
</script>
</html>
