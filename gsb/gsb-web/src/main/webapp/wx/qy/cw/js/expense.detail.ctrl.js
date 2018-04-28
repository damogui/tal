org.netsharp.we.core.ExpenseDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.formType = 4;
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.id = this.queryString('id');
    	this.oper = this.queryString('oper');
    },
    init:function(){
    	this.byId();
    	if(this.oper == "done"){
    		$("#auditDiv").hide();
    		$("#saveBtn").hide();
    	}
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getExpenseById', pars, function(result){
    		me.bindData(result);
    		if(result.paymentMethod == 1){
    			$("#payBank").attr("disabled","disabled");  
    			$("#payBankDiv").hide();
    		}else{
    			me.bindU8BankSelect(result.setOfBooksId);
    		}
    	});
    },
    bindData:function(entity){
    	$("#formId").val(entity.id);
    	$("#apply_user_id").val(entity.creatorId);
		$("#apply_department_id").val(entity.departmentId);
    	
    	
    	$("#bill_code").text(entity.code);
    	$("#create_time").text(entity.createTime);
    	$("#amount").text(entity.amount/100);
    	$("#expense_name").text(entity.expenseEmployee.name);
    	$("#creator").text(entity.creator);
    	$("#payeeName").text(entity.payeeName);
    	$("#books_name").text(entity.setOfBooks.name);
    	$("#payment_method").text(paymentMethodDict.byKey(entity.paymentMethod));
    	$("#expense_type").text(ExpenseTypeDict.byKey(entity.type));
    	$("#memoto_txt").text(entity.memoto);
    	
    	$("#payeeName").text(entity.payeeName);
    	$("#companyAccount").text((System.isnull(entity.companyAccount))?"无":entity.companyAccount);
    	$("#companyBank").text((System.isnull(entity.companyBank))?"无":entity.companyBank);
    	
    	$("#totalTaxation").text(entity.totalTaxation);
    	
    	$("#entertainDate").text((System.isnull(entity.entertainDate))?"无":entity.entertainDate);
		$("#entertainCompany").text((System.isnull(entity.entertainCompany))?"无":entity.entertainCompany);
		$("#entertainCustomer").text((System.isnull(entity.entertainCustomer))?"无":entity.entertainCustomer);
		$("#entertainPlace").text((System.isnull(entity.entertainPlace))?"无":entity.entertainPlace);
    	
    },
    bindU8BankSelect: function (setOfBooksId){
    	this.invokeService("getU8BankList", [setOfBooksId], function(data){  
    		for(var i =0;i<data.length;i++){
    			$("#payBank").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    		}
	   	});
    },
    toCostList : function (){  // 费用明细
    	var formId = this.queryString('id');
    	window.location.href = 'costList?formId=' + formId +"&formType="+this.formType;
    },
    toTripList : function (){  // 行程明细
    	var formId = this.queryString('id');
    	window.location.href = 'tripList?formId=' + formId +"&formType="+this.formType;
    },
    toSubsidyList : function (){
    	var formId = this.queryString('id');
    	window.location.href = 'subsidyList?formId=' + formId +"&formType="+this.formType;
    },
    toAuditList : function (){
    	var formId = this.queryString('id');
    	window.location.href = 'auditList?formId=' + formId +"&formType="+this.formType;
    },
    toFileList : function (){
    	var formId = this.queryString('id');
    	window.location.href = 'filesList?formId=' + formId +"&formType="+this.formType;
    },
    saveAudit:function (status){
    	var me = this;
    	var employeeId = me.queryString('employeeId');
    	var memoto = $("#memoto").val();
    	if(System.isnull(memoto)){
    		$.toptip('审批意见不能为空', 'warning');
			return;
		}
    	var auditRecord = {};
    	auditRecord.status = status;
    	auditRecord.memoto =memoto;
    	auditRecord.formId = $("#formId").val();
    	auditRecord.formType = 4;  //报销单
    	auditRecord.auditUserId = employeeId;
    	auditRecord.applyUserId = $("#apply_user_id").val();
    	auditRecord.applyDepartmentId = $("#apply_department_id").val();
    	this.invokeService("saveAudit", [auditRecord], function(data){  
    		$.toptip('提交成功', 'success');
    		setTimeout(function() {
    			window.location.href = 'todoList?employeeId=' + employeeId;
			}, 1000);
			
	   	});
    }
});


