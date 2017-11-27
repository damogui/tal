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
	<textarea id="template" class="template">
		<div class="weui-cells">
		  {#foreach $T as record}
			  <a class="weui-cell weui-cell_access" href="trackDetail?id={$T.record.id}">
			    <div class="weui-cell__bd">
			      <p>{$T.record.content}</p>
			      <p style="color:#cecece;">{$T.record.createTime}</p>
			    </div>
			    <div class="weui-cell__ft"></div>
			  </a>
		   {#/for}
		</div>
      
	</textarea>

	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/track.list.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.trackListCtrl();
	  $(function(){
		  ctrl.init();
	  });
	</script>
</body>
</html>