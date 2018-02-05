<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:include page="/gsb/crm/base/task.jsp"></jsp:include>
<script src='/gsb/crm/department/js/task.ctrl.js'></script>
<script>
	$(function(){

		new com.gongsibao.crm.web.DepartmentTaskCtrl().init();
	});
</script>