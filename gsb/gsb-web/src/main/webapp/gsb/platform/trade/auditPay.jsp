<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>回款审核</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
      <style  type="text/css">


      </style>
</head>
    <body class="easyui-layout">


    <div data-options="region:'north',split:false,collapsible:false,closed:false,height:200">
        <div class="formContent">
            <table cellpadding="3" cellspacing="0" class="form-panel">
                <tr>
                    <td class="label_td"><label>付款账套：</label></td>
                    <td class="control_td"  id="setOfBooksId">

                    </td>
                    <td class="label_td"><label>付款方式：</label></td>
                    <td class="control_td" id="u8BankId">

                    </td>
                </tr>
                <tr>
                    <td class="label_td"><label>付款账户名称：</label></td>
                    <td class="control_td" id="offlinePayerName">

                    </td>
                    <td class="label_td"><label>付款账号：</label></td>
                    <td class="control_td" id="offlineBankNo">

                    </td>
                </tr>
                <tr>
                    <td class="label_td"><label>一笔多单：</label></td>
                    <td class="control_td" id="payForOrderCount">

                    </td>
                    <td class="label_td"><label>付款金额：</label></td>
                    <td class="control_td"  id="amount">

                    </td>
                </tr>
                <tr>
                    <td class="label_td"><label>付款说明：</label></td>
                    <td colspan="3" class="control_td" id="offlineRemark">

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
            <div title="审批进度">
                <table id="audit_progress_grid"></table>
            </div>
        </div>
    </div>
</body>

<script src='/gsb/platform/trade/js/audit/audit-base.ctrl.js'></script>
<script src='/gsb/platform/trade/js/audit/audit-pay.ctrl.js'></script>
<script>

	var auditPayCtrl = null;
	$(function(){
		
		auditPayCtrl = new com.gongsibao.trade.web.AuditPayCtrl();
		auditPayCtrl.init(); 
	});
</script>
</html>
