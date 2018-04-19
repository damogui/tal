System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.LoanAuditBillFormCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.paymentMethod = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$PaymentMethod');
    	this.loanBillType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$LoanBillType');
    	this.auditDetailStatus = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$AuditDetailStatus');
    	this.service = 'com.gongsibao.cw.web.AuditBillFormController';
    	
    },
    init:function(){
    	var me = this;
    	this.formId = this.queryString('formId');
    	this.formType = this.queryString("formType");
    	this.invokeService("getBillByFormId", [this.formId,this.formType], function(data){  
    		 me.bindForm(data);
    		 me.bindAuditTable(data);
    		 me.bindFileTable(data);
    		 me.bindU8BankSelect(data.setOfBooksId);
    		 //禁用支付方式
    		 if(data.paymentMethod == 1){
	    		$("#bankItem").combobox({ disabled: true });  
	    	 }else{
	    	 	$("#bankItem").combobox({ disabled: false });  
	    	 }
    		 $("#paymentMethodId").val(data.paymentMethod);
    	});
    },
    bindForm : function (billData){ // 绑定form数据
    	var me = this;
    	$("#formId").val(billData.id);
    	$("#apply_user_id").val(billData.creatorId);
		$("#apply_department_id").val(billData.departmentId);
    	$("#bill_code").text(billData.code);
    	$("#create_time").text(billData.createTime);
    	$("#amount").text(billData.amount);
    	
    	$("#borrower_name").text(billData.borrowerEmployee.name);
    	$("#creator").text(billData.creator);
    	$("#payeeName").text(billData.payeeName);
    	$("#books_name").text(billData.setOfBooks.name);
    	$("#payment_method").text(me.paymentMethod[billData.paymentMethod]);
    	
    	$("#bill_type").text(me.loanBillType[billData.type]);
    	$("#memoto_txt").text(billData.memoto);
    	
    	$("#companyAccount").text((System.isnull(billData.companyAccount))?"无":billData.companyAccount);
    	$("#companyBank").text((System.isnull(billData.companyBank))?"无":billData.companyBank);
    	
    	//判断是否财务办理
    	if(billData.status == 4){
    		$("#audit_panel").hide();
    		$("#finance_panel").show();
    	}else{
    		$("#audit_panel").show();
    		$("#finance_panel").hide();
    	}
    	
    },
    bindFileTable : function (loanData){ //绑定附件数据
    	var me = this;
    	var fileData = loanData.files;
    	if(fileData !=null && fileData.length > 0){
    		for(var i =0;i<fileData.length;i++){
    			var number = i+1;
    			var fileItem = fileData[i];
    			var file_html = "<tr> ";
    			file_html +="<th scope='row'>"+number+"</th>";
    			file_html +="<td> <a class=\"grid-btn\" href=\"javascript:window.open('"+fileItem.url+"');\">"+fileItem.name+"</a></td>";
    			file_html +="</tr>";
    			$("#file_data_table").append(file_html);
    		}
    	}else{
    		$("#file_data_table").append("<tr><td colspan='2' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	
    },
    bindAuditTable : function (loanData){ //审核记录
    	var me = this;
    	var auditData = loanData.auditItem;
    	if(auditData !=null && auditData.length > 0){
    		for(var i =0;i<auditData.length;i++){
    			
    			var number = i+1;
    			var auditItem = auditData[i];
    			var audit_html = "<tr> ";
    			audit_html +="<th scope='row'>"+number+"</th>";
    			audit_html +="<td>"+auditItem.employee.name+"</td>";
    			//获取审核记录id
    			if(auditItem.status ==1){
    				$("#audit_id").val(auditItem.id);
    			}
    			audit_html +="<td>"+me.auditDetailStatus[auditItem.status]+"</td>";
    			if(!System.isnull(auditItem.memoto) ){
    				audit_html +="<td>"+auditItem.memoto+"</td>";
    			}else{
    				audit_html +="<td></td>";
    			}
    			if(!System.isnull(auditItem.updateTime) ){
    				audit_html +="<td>"+auditItem.updateTime+"</td>";
    			}else{
    				audit_html +="<td></td>";
    			}
    			audit_html +="</tr>";
    		 $("#audit_data_table").append(audit_html);
    		}
    	}else{
    		$("#audit_data_table").append(" <tr><td colspan='5' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    },
    bindU8BankSelect: function (setOfBooksId){
    	this.invokeService("getU8BankList", [setOfBooksId], function(data){  
    		var dataArr =[];  
    		for(var i =0;i<data.length;i++){
    			dataArr.push({"text":data[i].name,"id":data[i].id});  
    		}
    		 $("#bankItem").combobox("loadData", dataArr);  
	   	});
    },
    saveAudit:function (){ //保存审核意见
    	var memoto = $("#memoto").val();
    	if(System.isnull(memoto)){
			IMessageBox.toast('审批意见不能为空!',2);
			return;
		}
    	var auditRecord = {};
    	auditRecord.status = $("input[name='auditDetailStatus']:checked").val();
    	auditRecord.memoto =memoto;
    	auditRecord.id =  $("#audit_id").val();
    	auditRecord.formId = $("#formId").val();
    	auditRecord.formType = this.formType;
    	auditRecord.applyUserId = $("#apply_user_id").val();
    	auditRecord.applyDepartmentId = $("#apply_department_id").val();
    	
    	this.invokeService("saveAudit", [auditRecord], function(data){  
    		IMessageBox.info('操作成功!',function(s){
    			window.parent.layer.closeAll();
    			window.parent.controllerbillAuditDTOList.reload();
    		});
    	});
    },
    saveFinance:function (){
    	var paymentMethodId = $("#paymentMethodId").val();
    	if(paymentMethodId == 2){
    		var bankId = $('#bankItem').combobox('getValue');
        	if(System.isnull(bankId)){
        		IMessageBox.toast('请选择支付方式!',2);
    			return;
        	}
    	}
    	var memoto = $("#finance_memoto").val();
    	if(System.isnull(memoto)){
			IMessageBox.toast('财务办理意见不能为空!',2);
			return;
		}
    	var auditRecord = {};
    	auditRecord.bankId =bankId;
    	auditRecord.status = $("input[name='financeStatus']:checked").val();
    	auditRecord.memoto =memoto;
    	auditRecord.formId = $("#formId").val();
    	auditRecord.formType = this.formType;
    	auditRecord.applyUserId = $("#apply_user_id").val();
    	auditRecord.applyDepartmentId = $("#apply_department_id").val();
    	
    	this.invokeService("saveFinance", [auditRecord], function(data){  
    		IMessageBox.info('操作成功!',function(s){
    			window.parent.layer.closeAll();
    			window.parent.controllerbillAuditDTOList.reload();
    		});
    	});
    }
    
});

