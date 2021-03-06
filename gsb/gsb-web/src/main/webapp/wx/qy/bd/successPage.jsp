<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>成功提示</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
	<div class="weui-msg">
	  <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
	  <div class="weui-msg__text-area">
	    <h2 class="weui-msg__title">操作成功</h2>
	    <p class="weui-msg__desc">内容详情，可根据实际需要安排，如果换行则不超过规定长度，居中展现<a href="javascript:void(0);">文字链接</a></p>
	  </div>
	  <div class="weui-msg__opr-area">
	    <p class="weui-btn-area">
	      <a href="javascript:;" class="weui-btn weui-btn_primary">继续添加</a>
	      <a href="javascript:;" class="weui-btn weui-btn_default">返回</a>
	    </p>
	  </div>
	</div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
</body>
</html>