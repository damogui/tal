<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>借款详情</title>
	<%@include file="/wx/qy/bd/include/header.jsp" %>
</head>
<body>
	<div id="form" class="weui-cells weui-cells_form">
	
	  <div class="weui-cells__title">基本信息</div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">单据号</label></div>
	    <div class="weui-cell__bd">
	      <p id="bill_code"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd">
	      <label class="weui-label">创建时间</label>
	    </div>
	    <div class="weui-cell__bd">
	      <p id="create_time"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">借款总额</label></div>
	    <div class="weui-cell__bd">
	      <p id="amount"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label for="" class="weui-label">付款单位</label></div>
	    <div class="weui-cell__bd">
	      <p id="books_name"></p>
	    </div>
	  </div>
	  <div class="weui-cell ">
	    <div class="weui-cell__hd"><label for="city-picker" class="weui-label">收款方式</label></div>
	    <div class="weui-cell__bd">
	      <p id="payment_method"></p>
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">单据类型</label></div>
	    <div id="bill_type" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">经办人</label></div>
	    <div id="creator" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">借款人</label></div>
	    <div id="borrower_name" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cells__title">收款信息</div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">收款人</label></div>
	    <div id="payeeName" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">开户行</label></div>
	    <div id="companyBank" class="weui-cell__bd">
	    </div>
	  </div>
      <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">银行账号</label></div>
        <div id="companyAccount" class="weui-cell__bd">

        </div>
      </div>




	    <div class="weui-cells__title">备注信息</div>
		<div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">备注</label></div>
		    <div class="weui-cell__bd" id="memoto_txt">
		    </div>
		 </div>

	    <div class="weui-cells__title">审批信息</div>
		 
		 
		 
		 
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
	<script src="/wx/qy/cw/js/enum.items.js"></script>
	<script src="/wx/qy/cw/js/expense.detail.ctrl.js"></script>
	<script>

	  var ctrl = new org.netsharp.we.core.ExpenseDetailCtrl();
	  $(function(){
		  ctrl.init();
	  });
    </script>
</body>
</html>