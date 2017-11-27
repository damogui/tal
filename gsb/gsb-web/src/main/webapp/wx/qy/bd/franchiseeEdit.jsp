<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
	
	<div class="weui-cells weui-cells_form">
	  <div class="weui-cells__title">基本信息</div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">公司名称</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="name" type="text" placeholder="请输入公司名称">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd">
	      <label class="weui-label">法人</label>
	    </div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="legalPerson" type="text" placeholder="请输入法人">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">人数</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="employeeCount" type="number">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">年收入(万)</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="annualIncome" type="number">
	    </div>
	    <div class="weui-cell__ft">万</div>
	  </div>
	  <div class="weui-cell weui-cell_access">
	    <div class="weui-cell__hd"><label for="city-picker" class="weui-label">地区</label></div>
	    <div class="weui-cell__bd">
	      <input type="text"  class="weui-input" id='city-picker'/>
	    </div>
	    <div class="weui-cell__ft">请选择</div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">注册地址</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="registerAddress" type="text" placeholder="请输入注册地址">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">办公地址</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="workdAddress" type="text" placeholder="请输入办公地址">
	    </div>
	  </div>
	  <div class="weui-cell weui-cell_access">
	    <div class="weui-cell__hd"><label class="weui-label">经营范围</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" id="scopes" type="text" >
	    </div>
	    <div class="weui-cell__ft">请选择</div>
	  </div>
		 <div class="weui-cells__title">备注</div>
	      <div class="weui-cell">
	        <div class="weui-cell__bd">
	          <textarea class="weui-textarea" id="memoto" placeholder="请输入备注" rows="3"></textarea>
	          <div class="weui-textarea-counter"><span>0</span>/200</div>
	        </div>
	      </div>
	    <div class="weui-cells__title">主联系人</div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="linkmanName" type="text" placeholder="请输入姓名">
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
		      <input class="weui-input" id="post" type="text" placeholder="请输入职务">
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">微信号</label></div>
		    <div class="weui-cell__bd">
		      <input class="weui-input" id="weixin" type="text" placeholder="请输入微信号">
		    </div>
		  </div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:ctrl.save();">提交</a>
    </div>
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/enum.items.js"></script>
	<script src="/wx/js/city-picker.js"></script>
	<script src="/wx/qy/bd/js/franchisee.form.ctrl.js"></script>
	<script>
	
	  var ctrl = new org.netsharp.we.core.franchiseeFormCtrl();
	  $(function() {
		  ctrl.init();
	  });
    </script>
</body>
</html>