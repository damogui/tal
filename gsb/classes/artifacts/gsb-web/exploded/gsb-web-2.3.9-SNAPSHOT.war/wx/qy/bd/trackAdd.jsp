<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>客户跟进</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>

		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">意向度</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="intentionDegree" type="text" placeholder="请选择意向度">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">进度</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="trackProgress" type="text" placeholder="请选择意进度">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">预计签单时间</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="expectedSign" type="text" placeholder="预计签单时间">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">下次拜访时间</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" type="text" id="nextTrackDate" placeholder="请选择下次跟进时间">
		      
		    </div>
		  </div>
		  
	      <div class="weui-cell">
	        <div class="weui-cell__bd">
	          <textarea class="weui-textarea" id="content" placeholder="请输入跟进内容" rows="8"></textarea>
	          <div class="weui-textarea-counter"><span>0</span>/1000</div>
	        </div>
	      </div>
	</div>
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:ctrl.save();">提交</a>
    </div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/enum.items.js"></script>
	<script src="/wx/qy/bd/js/track.form.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.trackFormCtrl();
	  $(function(){
		  ctrl.init();
	  });
    </script>
	<script>

    </script>
</body>
</html>