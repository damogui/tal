<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>报销详情</title>
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
	    <div class="weui-cell__hd"><label for="" class="weui-label">报销合计</label></div>
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
	    <div id="expense_type" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">经办人</label></div>
	    <div id="creator" class="weui-cell__bd">
	    </div>
	  </div>
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">报销人</label></div>
	    <div id="expense_name" class="weui-cell__bd">
	    </div>
	  </div>
	  
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">税费合计</label></div>
	    <div id="totalTaxation" class="weui-cell__bd">
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
	  <div class="weui-cells__title">招待信息</div>
	   <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">招待时间</label></div>
        <div id="entertainDate" class="weui-cell__bd">
        </div>
      </div>
       <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">招待公司名</label></div>
        <div id="entertainCompany" class="weui-cell__bd">
        </div>
      </div>
       <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">招待客户姓名</label></div>
        <div id="entertainCustomer" class="weui-cell__bd">
        </div>
      </div>
       <div class="weui-cell">
        <div class="weui-cell__hd"><label class="weui-label">招待地点</label></div>
        <div id="entertainPlace" class="weui-cell__bd">
        </div>
      </div>


	    <div class="weui-cells__title">备注信息</div>
		<div class="weui-cell">
		    <div class="weui-cell__hd"><label class="weui-label">备注</label></div>
		    <div class="weui-cell__bd" id="memoto_txt">
		    </div>
		 </div>
		 
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toCostList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">费用明细</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toTripList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">行程明细</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toSubsidyList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">补助明细</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toAuditList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">审批记录</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
		<div class="weui-panel__ft">
		    <a href="javascript:ctrl.toFileList();" class="weui-cell weui-cell_access weui-cell_link">
		      <div class="weui-cell__bd">附件明细</div>
		      <span class="weui-cell__ft"></span>
		    </a>    
	  	</div>
	  	
	    <div class="weui-cells__title">审批信息</div>
	  	<div class="weui-cells weui-cells_checkbox">
		  <label class="weui-cell weui-check__label" for="agree">
		    <div class="weui-cell__hd">
		      <input type="radio" class="weui-check" name="auditDetailStatus" value="2"  id="agree" checked="checked">
		      <i class="weui-icon-checked"></i>
		    </div>
		    <div class="weui-cell__bd">
		      <p>通过</p>
		    </div>
		  </label>
		  <label class="weui-cell weui-check__label" for="reject">
		    <div class="weui-cell__hd">
		      <input type="radio" name="auditDetailStatus" class="weui-check" value="3"  id="reject">
		      <i class="weui-icon-checked"></i>
		    </div>
		    <div class="weui-cell__bd">
		      <p>驳回</p>
		    </div>
		  </label>
		
		</div>
		
		
		<div class="weui-cell weui-cell_select" id="payBankDiv" >
		    <div class="weui-cell__bd" >
			    <select class="weui-select" name="payBank" id="payBank" >
	          </select>
		    </div>
		 </div>
	    <div class="weui-cells weui-cells_form" >
		  <div class="weui-cell">
		    <div class="weui-cell__bd" >
		      <textarea class="weui-textarea" placeholder="输入审批意见" rows="3" id="memoto" ></textarea>
		    </div>
		  </div>
		</div>
	</div>

    <div class="weui-btn-area">
      <a class="weui-btn weui-btn_primary" href="javascript:ctrl.saveAudit();">提交</a>
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