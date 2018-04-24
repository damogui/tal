<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>跟进记录</title>
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
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/cw/js/file.list.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.fileListCtrl();
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