<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>审批表单</title>
	<jsp:include page="/gsb/platform/trade/include/meta.jsp"></jsp:include>
	<link href='/package/bootstrap/css/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<link href='/package/bootstrap/css/bootstrap-theme.min.css' rel='stylesheet' type='text/css' />
</head>
<body >

<div class="panel panel-default">
    <div class="panel-body">
		<form class="form-horizontal" role="form"> 
		   <input type="hidden" id="formId"  value=""  > 
		   <input type="hidden" id="apply_user_id"  value=""  >
	  	   <input type="hidden" id="apply_department_id"  value=""  >
		   <h5 class="page-header" style="margin-top: 0px;" ><span class="glyphicon glyphicon-list-alt" ></span> 单据信息</h5>
		   <div class="row">
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">单据号:</label>   
					        <div class="col-sm-8">  
					           <p class="form-control-static" id="bill_code" ></p>
					       </div>  
				       </div>  
		   		 	
		   		 </div>
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">创建时间:</label>   
					        <div class="col-sm-8">  
					          <p class="form-control-static" id="create_time" ></p>
					       </div>  
				       </div>  
		   		</div>	
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-6 control-label">总额:</label>   
					        <div class="col-sm-6">  
					           <p class="form-control-static" id="amount" ></p>
					       </div>  
				      </div>  	
		   		</div>
		   </div>
		   <div class="row">
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">付款单位:</label>   
					        <div class="col-sm-8">  
					           <p class="form-control-static" id="books_name" ></p>
					       </div>  
				       </div>  
		   		 	
		   		 </div>
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">收款方式:</label>   
					        <div class="col-sm-8">  
					          <p class="form-control-static" id="payment_method" ></p>
					       </div>  
				       </div>  
		   		</div>	
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-6 control-label">单据类型:</label>   
					        <div class="col-sm-6">  
					           <p class="form-control-static" id="bill_type" ></p>
					       </div>  
				      </div>  	
		   		</div>
		   </div>
		   
		    <div class="row" id="receiver_div"  style="display: none" >
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">公司名称:</label>   
					        <div class="col-sm-8">  
					           <p class="form-control-static" id="companyName" ></p>
					       </div>  
				       </div>  
		   		 	
		   		 </div>
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-4 control-label">公司开户行:</label>   
					        <div class="col-sm-8">  
					          <p class="form-control-static" id="companyBank" ></p>
					       </div>  
				       </div>  
		   		</div>	
		   		 <div class="col-sm-4"> 
		   		 	 <div class="form-group">   
					        <label for="firstname" class="col-sm-6 control-label">公司银行账号:</label>   
					        <div class="col-sm-6">  
					           <p class="form-control-static" id="companyAccount" ></p>
					       </div>  
				      </div>  	
		   		</div>
		   </div>
	        <div class="row">
	        	<label style="width: 11%;" class="col-sm-4 control-label">备注:</label>   
		        <div class="col-sm-8">  
		           <p class="form-control-static" id="memoto_txt" ></p>
		       </div>  
	        </div>
	        
	         <div class="panel panel-default">
	         	 <div class="panel-body">
			        <ul id="tab_header" class="nav nav-tabs">
						<li class="active"><a href="#tab_cost_data" data-toggle="tab">单据明细</a></li>
					</ul>
					<div id="tab_content" class="tab-content" style="margin-top: 10px;">
						<div class="tab-pane fade in active" id="tab_cost_data">
							 <table class="table table-bordered" id="cost_date_table" >
						      <thead>
						        <tr class="active">
						          <th style="width: 10%">序号</th>
						          <th style="width: 20%">费用归属部门</th>
						          <th style="width: 20%">费用类型</th>
						          <th style="width: 20%">金额</th>
						          <th style="width: 30%">说明</th>
						        </tr>
						      </thead>
						    </table>
						</div>
					</div>
				</div>
	        </div>
	  	    
	  	    <h5 class="page-header "> <span class="glyphicon glyphicon-list-alt" ></span> 审批记录</h5>
	  	    
	  	    
	  	     <table class="table table-bordered" id="audit_data_table" >
		      <thead>
		        <tr class="active">
		          <th style="width: 10%">序号</th>
		          <th style="width: 10%">审批人</th>
		          <th style="width: 20%">审批结果</th>
		          <th style="width: 40%">审批意见</th>
		          <th style="width: 20%">审批时间</th>
		        </tr>
		      </thead>
		     
		    </table>
			
	     </form>  
   </div>
</div>
</body>

<script src='/package/bootstrap/js/bootstrap.min.js'></script>
<script src='/gsb/platform/cw/js/audit-bill-form.ctrl.js'></script>
<script src='/gsb/platform/cw/js/audit-bill-see.ctrl.js'></script>

<script>
	var auditBillSeeCtrl = null;
	$(function(){
		auditBillSeeCtrl = new com.gongsibao.cw.web.AuditBillSeeCtrl();
		auditBillSeeCtrl.init(); 
	});
</script>
</html>
