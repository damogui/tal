<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>审批记录</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>

<div class="weui_body">
	<div id="list">
		<div id="nodata"  class="weui-loadmore weui-loadmore_line" style="display:none;">
		  <span class="weui-loadmore__tips">暂无数据</span>
		</div>
	</div>
</div>
<div id="list" ></div>
<div class="weui-btn-area" id="btnBack" >
  <a class="weui-btn weui-btn_default" href="javascript:history.back();">返回</a>
</div>

	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/cw/js/enum.items.js"></script>
	<script src="/wx/qy/cw/js/trip.list.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.tripListCtrl();
	  $(function(){
		  ctrl.init();
	  });
	  
	  //解决微信IOS里返回不加载问题问题
	  window.addEventListener("pageshow", function() {
	  	
	    if(sessionStorage.goBack==1){
	  	  
 	      location.reload();
 	      sessionStorage.setItem('goBack',0);
	    }
      });
	</script>
</body>
</html>