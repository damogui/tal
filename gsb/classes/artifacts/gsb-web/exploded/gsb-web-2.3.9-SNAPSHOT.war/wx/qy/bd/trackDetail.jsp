<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>跟进详情</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>

	<div class="weui-cells weui-cells_form">
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">意向度</label></div>
	    <div class="weui-cell__bd">
	      <p id="intentionDegree"></p>
	    </div>
	  </div>
		  
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">进度</label></div>
	    <div class="weui-cell__bd">
	      <p id="trackProgress"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">预计签单时间</label></div>
	    <div class="weui-cell__bd">
	      <p id="expectedSign"></p>
	    </div>
	  </div>

	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">下次拜访</label></div>
	    <div class="weui-cell__bd">
			<p id="nextTrackDate"></p>
		</div>
	  </div>

	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">跟进内容</label></div>
	    <div id="content" class="weui-cell__bd">
			
		</div>
	  </div>

	</div>
    <div class="weui-btn-area">
       <a class="weui-btn weui-btn_default" href="javascript:history.back();">返回</a>
    </div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/enum.items.js"></script>
	<script src="/wx/qy/bd/js/track.detail.ctrl.js"></script>

	<script>
	  var ctrl = new org.netsharp.we.core.trackDetailCtrl();
	  $(function(){
		  ctrl.init();
          $(window).on('popstate', function () {
        	  
       	   	  alert(1);
       	   	  //sessionStorage.goBack==1;
          });
	  }); 
    </script>
</body>
</html>