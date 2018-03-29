System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.AuditBillFormCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.paymentMethod = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$PaymentMethod');
    	this.loanBillType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$LoanBillType');
    	this.costType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$CostType');
    	this.auditDetailStatus = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$AuditDetailStatus');
    	this.service = 'com.gongsibao.cw.web.AuditBillFormController';
    },
    init:function(){
    	var me = this;
    	var formId = this.queryString('formId');
    	this.invokeService("getBillByFormId", [formId,1], function(data){  
    		 me.bindForm(data);
    		 me.bindCostTable(data);
    		 me.bindFileTable(data);
    		 me.bindAuditTable(data);
    	});
    },
    bindForm : function (loanData){ // 绑定form数据
    	var me = this;
    	$("#formId").val(loanData.id);
    	$("#formCode").text(loanData.code);
    	$("#create_time").text(loanData.createTime);
    	$("#amount").text(loanData.amount);
    	$("#books_name").text(loanData.setOfBooks.name);
    	$("#payment_method").text(me.paymentMethod[loanData.paymentMethod]);
    	$("#bill_type").text(me.loanBillType[loanData.type]);
    },
    bindCostTable : function (loanData){  //绑定明细数据
    	var me = this;
    	var costData = loanData.costDetailItem;
    	if(costData !=null && costData.length > 0){
    		for(var i =0;i<costData.length;i++){
    			var number = i+1;
    			var costItem = costData[i];
    			var cost_html = "<tr> ";
    		    cost_html +="<th scope='row'>"+number+"</th>";
    		    cost_html +="<td>"+costItem.organizationId+"</td>";
    		    cost_html +="<td>"+me.costType[costItem.costType]+"</td>";
    		    cost_html +="<td>"+costItem.detailMoney+"</td>";
    		    cost_html +="<td>"+costItem.memoto+"</td>";
    		    cost_html +="</tr>";
    		 $("#cost_date_table").append(cost_html);
    		}
    		
    	}else{
    		$("#cost_date_table").append("<tr><td colspan='4'style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	
    },
    bindFileTable : function (loanData){ //绑定附件数据
    	var fileData = loanData.files;
    	if(fileData !=null && fileData.length > 0){
    		for(var i =0;i<fileData.length;i++){
    			var number = i+1;
    			var fileItem = fileData[i];
    			var file_html = "<tr> ";
    			file_html +="<th scope='row'>"+number+"</th>";
    			file_html +="<td>"+fileItem.name+"</td>";
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
    			audit_html +="<td>"+auditItem.auditUserId+"</td>";
    			//获取审核记录id
    			if(auditItem.status ==1){
    				$("#audit_id").val(auditItem.id);
    			}
    			audit_html +="<td>"+me.auditDetailStatus[auditItem.status]+"</td>";
    			if(!System.isnull(auditItem.content) ){
    				audit_html +="<td>"+auditItem.content+"</td>";
    			}else{
    				audit_html +="<td></td>";
    			}
    			audit_html +="<td>"+auditItem.updateTime+"</td>";
    			audit_html +="</tr>";
    		 $("#audit_data_table").append(audit_html);
    		}
    	}else{
    		$("#audit_data_table").append(" <tr><td colspan='5' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    },
    saveAudit:function (){ //保存审核意见
    	var content = $("#content").val();
    	if(System.isnull(content)){
			IMessageBox.toast('审批意见不能为空!',2);
			return;
		}
    	var auditRecord = {};
    	auditRecord.status = $("#auditDetailStatus").val();
    	auditRecord.content =content;
    	auditRecord.id =  $("#audit_id").val();
    	auditRecord.formId = $("#formId").val();
    	this.invokeService("saveAudit", [auditRecord], function(data){  
    		IMessageBox.info('审批成功!',function(s){
    			window.parent.layer.closeAll();
    		});
    	});
    }
});

