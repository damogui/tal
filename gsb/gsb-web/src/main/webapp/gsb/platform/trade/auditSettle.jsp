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

        <%@ include file="/gsb/supplier/settle/include/settleData.jsp"%>
    </body>
    <script src='/gsb/platform/trade/js/audit/audit-base.ctrl.js'></script>
    <script src='/gsb/platform/trade/js/audit/audit-settle.ctrl.js'></script>
    <script type="text/javascript">
        var auditSettleCtrl = null;
        $(function(){
            auditSettleCtrl = new com.gongsibao.trade.web.AuditSettleCtrl();
            auditSettleCtrl.init();
        });
    </script>
</html>
