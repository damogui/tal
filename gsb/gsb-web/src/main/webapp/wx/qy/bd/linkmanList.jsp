<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>联系人信息</title>
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

<div class="weui-btn-area">
  <a class="weui-btn weui-btn_primary" href="javascript:ctrl.toAdd();" id="show-actions">新增联系人</a>
</div>
	<textarea id="template" class="template">
	   {#foreach $T as record}
			<div class="weui-form-preview">
			  <div class="weui-form-preview__hd">
			    <label class="weui-form-preview__label">姓名</label>
			    <em class="weui-form-preview__value">{$T.record.name}</em>
			  </div>
			  <div class="weui-form-preview__bd">
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">手机号</label>
			      <span class="weui-form-preview__value">{$T.record.mobile}</span>
			    </div>
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">职务</label>
			      <span class="weui-form-preview__value">{$T.record.post}</span>
			    </div>
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">微信号</label>
			      <span class="weui-form-preview__value">{$T.record.weixin}</span>
			    </div>
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">QQ</label>
			      <span class="weui-form-preview__value">{$T.record.qq}</span>
			    </div>
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">座机</label>
			      <span class="weui-form-preview__value">{$T.record.tel}</span>
			    </div>
			    <div class="weui-form-preview__item">
			      <label class="weui-form-preview__label">主联系人</label>
			      <span class="weui-form-preview__value">
			      	{#if $T.record.main==true} 是 {#else} 否 {#/if}
			      </span>
			    </div>
			  </div>
			  <div class="weui-form-preview__ft">
			    <a class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:ctrl.toFranchiseeDetail({$T.record.franchiseeId});">返回</a>
			    <a class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:ctrl.toEdit({$T.record.id});">修改</a>
			  </div>
			</div>
       {#/for}
	</textarea>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/linkman.list.ctrl.js"></script>
	<script>
	  var ctrl = new org.netsharp.we.core.linkmanListCtrl();
	  $(function(){
		  ctrl.init();
	  });
	</script>

</body>
</html>