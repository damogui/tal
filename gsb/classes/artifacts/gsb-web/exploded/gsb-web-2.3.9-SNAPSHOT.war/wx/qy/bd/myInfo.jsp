<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>基本信息</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
<div class="weui-form-preview">

  <div class="weui-form-preview__bd">
    <div class="weui-form-preview__item">
      <label class="weui-form-preview__label">姓名</label>
      <span class="weui-form-preview__value" id="name"></span>
    </div>
    <div class="weui-form-preview__item">
      <label class="weui-form-preview__label">手机号</label>
      <span class="weui-form-preview__value" id="mobile"></span>
    </div>
    <div class="weui-form-preview__item">
      <label class="weui-form-preview__label">入职时间</label>
      <span class="weui-form-preview__value" id="entryDate"></span>
    </div>
    <div class="weui-form-preview__item">
      <label class="weui-form-preview__label">岗位</label>
      <span class="weui-form-preview__value" id="post"></span>
    </div>
  </div>
</div>

	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/myinfo.detail.ctrl.js"></script>
	<script>

	  var ctrl = new org.netsharp.we.core.myInfoDetailCtrl();
	  $(function(){
		  ctrl.init();
	  });
    </script>
</body>
</html>