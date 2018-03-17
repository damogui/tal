<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>退款审核</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
</head>
    <body class="easyui-layout">
        <div data-options="region:'north',split:false,collapsible:false,closed:false,height:375">
        	 <div class="formContent">
		        <fieldset style="margin-bottom:0px;">
		        	<legend>订单信息</legend>
					<jsp:include page="/gsb/platform/trade/include/orderInfo.jsp"></jsp:include>
				</fieldset>
		        <fieldset style="margin-bottom:0px;">
		        	<legend>退款信息</legend>
					  <table cellpadding="3" cellspacing="0" class="form-panel">
					      <tr>
					          <td class="label_td"><label>退款账套：</label></td>
					          <td class="control_td" colspan="3"></td>
					          <td class="label_td"><label>退款类别：</label></td>
					          <td class="control_td"></td>
					   </tr>
					      <tr>
					          <td class="label_td"><label>退款账户名称：</label></td>
					          <td class="control_td"></td>
					          <td class="label_td"><label>退款账号：</label></td>
					          <td class="control_td"></td>
					          <td class="label_td"><label>退款金额：</label></td>
					          <td class="control_td"></td>
						</tr>
					      <tr>
					          <td class="label_td"><label>退款说明：</label></td>
					          <td colspan="5" class="control_td">
									
					          </td>
						</tr>
					</table>
				</fieldset>
        	 </div>
		</div>
		<div id="center" data-options="region:'center'">
			<div id="detail_tabs" style="height:100%;">   
			    <div title="退款产品">   
			          <table id="audit_product_grid"></table>
			    </div>   
			    <div title="退款业绩分配">   
			         <table id="audit_refund_grid"></table>
			    </div>
			    <div title="审批进度">   
			         <table id="audit_progress_grid"></table>
			    </div>
			</div>
		</div>
</body>

<script src='/gsb/platform/trade/js/audit-base.ctrl.js'></script>
<script src='/gsb/platform/trade/js/audit-refund.ctrl.js'></script>
<script>

	var auditRefundCtrl = null;
	$(function(){
		
		auditRefundCtrl = new com.gongsibao.trade.web.AuditRefundCtrl();
		auditRefundCtrl.init(); 
	});
</script>
</html>
