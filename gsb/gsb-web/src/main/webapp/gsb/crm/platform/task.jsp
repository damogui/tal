<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:include page="/gsb/crm/base/task.jsp"></jsp:include>
<script src='/gsb/crm/platform/js/task.ctrl.js'></script>
<script>
	$(function(){

		var taskCtrl = new com.gongsibao.crm.web.PlatformTaskCtrl();
		taskCtrl.init();
	});
</script>
