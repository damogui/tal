<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:include page="/gsb/supplier/crm/base/task.jsp"></jsp:include>
<script src='/gsb/supplier/crm/salesman/js/task.ctrl.js'></script>
<script>
	$(function(){

		new com.gongsibao.crm.web.SalesmanTaskCtrl().init();
	});
</script>
