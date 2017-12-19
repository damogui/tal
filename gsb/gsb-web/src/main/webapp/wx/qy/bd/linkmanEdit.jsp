<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>联系人信息</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>

	<div class="weui-cells weui-cells_form">

		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="name" type="text" placeholder="请输入姓名">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="mobile" type="text" placeholder="请输入手机号">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">职务</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" type="text" id="post" placeholder="请输入职务">
		      
		    </div>
		  </div>
		  
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">微信号</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" type="text" id="weixin" placeholder="请输入微信号">
		      
		    </div>
		  </div>
		  
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">QQ</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="qq" type="text" placeholder="请输入QQ">
		    </div>
		  </div>
		  
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">座机</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="tel" type="text" placeholder="请输入座机">
		    </div>
		  </div>
		  
	      <div class="weui-cell weui-cell_switch">
	        <div class="weui-cell__bd">主联系人</div>
	        <div class="weui-cell__ft">
	          <label for="main" class="weui-switch-cp">
	            <input id="main" class="weui-switch-cp__input" type="checkbox">
	            <div class="weui-switch-cp__box"></div>
	          </label>
	        </div>
	      </div>
	</div>
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:ctrl.save();">提交</a>
    </div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/linkman.form.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.linkmanFormCtrl();
	  $(function(){
		  ctrl.init();
	  });
    </script>
</body>
</html>