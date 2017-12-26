<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>客户详情</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
	<div id="form" class="weui-cells weui-cells_form">
	
	  <div class="weui-cells__title">基本信息</div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">公司名称</label></div>
	    <div class="weui-cell__bd">
	      <p id="name"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd">
	      <label class="weui-label">法人</label>
	    </div>
	    <div class="weui-cell__bd">
	      <p id="legalPerson"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">人数</label></div>
	    <div class="weui-cell__bd">
	      <p id="employeeCount"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">年收入</label></div>
	    <div class="weui-cell__bd">
	      <p id="annualIncome"></p>
	    </div>
	  </div>
	  <div class="weui-cell ">
	    <div class="weui-cell__hd"><label for="city-picker" class="weui-label">地区</label></div>
	    <div class="weui-cell__bd">
	      <p id="district"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">注册地址</label></div>
	    <div id="registerAddress" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">办公地址</label></div>
	    <div id="workdAddress" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">经营范围</label></div>
	    <div id="businessScope" class="weui-cell__bd">
	    </div>
	  </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">备注</label></div>
        <div id="memoto" class="weui-cell__bd">

        </div>
      </div>

	    <div class="weui-cells__title">主联系人信息</div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
		    <div class="weui-cell__bd">
		      <p id="linkmanName"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
		    <div class="weui-cell__bd">
		     <a id="mobile"></a>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">职务</label></div>
		    <div class="weui-cell__bd">
		      <p id="post"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">微信号</label></div>
		    <div class="weui-cell__bd">
		      <p id="weixin"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">QQ</label></div>
		    <div class="weui-cell__bd">
		      <p id="qq"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">座机</label></div>
		    <div class="weui-cell__bd">
		      <p id="tel"></p>
		    </div>
		  </div>
	    <div class="weui-cells__title">最后跟进信息</div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">意向度</label></div>
		    <div class="weui-cell__bd">
		      <p id="intentionDegree"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">合作模式</label></div>
		    <div class="weui-cell__bd">
		      <p id="cooperativeMode"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">进度</label></div>
		    <div class="weui-cell__bd">
		      <p id="trackProgress"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">所属部门</label></div>
		    <div class="weui-cell__bd">
		      <p id="department"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">业务员</label></div>
		    <div class="weui-cell__bd">
		      <p id="owner"></p>
		    </div>
		  </div>
		  <div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">下次跟进</label></div>
		    <div class="weui-cell__bd">
		      <p id="nextTrackDate"></p>
		    </div>
		  </div>
		  
		  <div class="weui-cells__title">最后跟进内容</div>
	      <div class="weui-cell">
	        <div id="lastTrackContent" class="weui-cell__bd">
	        </div>
	      </div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toLinkmanList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">联系人信息</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toTrackList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">跟进信息</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
	</div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:ctrl.operation();">操作</a>
    </div>
    
    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_default" href="javascript:history.back();">返回</a>
    </div>
    
	<%@include file="/wx/qy/bd/include/footer.jsp" %>
	<script src="/wx/qy/bd/js/enum.items.js"></script>
	<script src="/wx/qy/bd/js/franchisee.detail.ctrl.js"></script>
	<script>

	  var ctrl = new org.netsharp.we.core.franchiseeDetailCtrl();
	  $(function(){
		  ctrl.init();
	  });
    </script>
</body>
</html>