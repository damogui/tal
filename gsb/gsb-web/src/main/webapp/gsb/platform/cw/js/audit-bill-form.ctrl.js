System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.AuditBillFormCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.paymentMethod = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$PaymentMethod');
    	this.subsidyType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$SubsidyType');
    	this.loanBillType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$LoanBillType');
    	this.expenseBillType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$ExpenseBillType');
    	
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
    		if(me.formType == 4){ //报销单据
    			me.bindCostTable(data);
    			me.bindTripTable(data);
    			me.bindSubsidyTable(data);
    		}
    		me.bindFileTable(data);
    		me.bindU8BankSelect(data.setOfBooksId);
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
    	$("#books_name").text(billData.setOfBooks.name);
    	$("#payment_method").text(me.paymentMethod[billData.paymentMethod]);
    	if(me.formType == 3){
    		$("#bill_type").text(me.loanBillType[billData.type]);
    	}
    	if(me.formType == 4){
    		$("#bill_type").text(me.expenseBillType[billData.type]);
    	}
    	$("#memoto_txt").text(billData.memoto);
    	//判断是否财务办理
    	if(billData.status == 4){
    		$("#audit_panel").hide();
    		$("#finance_panel").show();
    	}else{
    		$("#audit_panel").show();
    		$("#finance_panel").hide();
    	}
    	//判断支付方式
    	if(billData.paymentMethod == 2){
    		$("#receiver_div").show();
    		$("#companyName").text(billData.companyName);
    		$("#companyBank").text(billData.companyBank);
    		$("#companyAccount").text(billData.companyAccount);
    	}
    	
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
    		    cost_html +="<td>"+costItem.pathName+"</td>";
    		    cost_html +="<td>"+costItem.costTypeName+"</td>";
    		    cost_html +="<td>"+costItem.detailMoney+"</td>";
    		    cost_html +="<td>"+costItem.memoto+"</td>";
    		    cost_html +="</tr>";
    		 $("#cost_date_table").append(cost_html);
    		}
    		
    	}else{
    		$("#cost_date_table").append("<tr><td colspan='5'style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	
    },
    bindFileTable : function (loanData){ //绑定附件数据
    	var me = this;
    	var table = $('<table>').addClass("table table-bordered");   
    	table.append('<thead><tr class="active"></tr></thead> ');
    	var theadTr = table.children("thead").find("tr");
    	theadTr.append('<th style="width: 10%">序号</th>');
    	theadTr.append('<th style="width: 90%">名称</th>');
    	
    	var fileData = loanData.files;
    	if(fileData !=null && fileData.length > 0){
    		for(var i =0;i<fileData.length;i++){
    			var number = i+1;
    			var fileItem = fileData[i];
    			var file_html = "<tr> ";
    			file_html +="<th scope='row'>"+number+"</th>";
    			file_html +="<td> <a class=\"grid-btn\" href=\"javascript:window.open('"+fileItem.url+"');\">"+fileItem.name+"</a></td>";
    			file_html +="</tr>";
    			table.append(file_html);
    		}
    	}else{
    		table.append("<tr><td colspan='2' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	me.addTab({
    		divId:"tab_file",
    		title:"附件",
    		content:table
    	});
    	
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
    bindTripTable : function (billData){  //行程记录
    	var me = this;
    	var table = $('<table>').addClass("table table-bordered");   
    	table.append('<thead><tr class="active"></tr></thead> ');
    	var theadTr = table.children("thead").find("tr");
    	theadTr.append('<th style="width: 10%">序号</th>');
    	theadTr.append('<th style="width: 10%">出发地</th>');
    	theadTr.append('<th style="width: 10%">目的地</th>');
    	theadTr.append('<th style="width: 10%">开始时间</th>');
    	theadTr.append('<th style="width: 10%">结束时间</th>');
    	theadTr.append('<th style="width: 30%">备注</th>');
    
    	var tripData =   billData.tripItem;  
    	if(tripData !=null && tripData.length > 0){
    		for(var i =0;i<tripData.length;i++){
    			var number = i+1;
    			var tripItem = tripData[i];
    			var trip_html = "<tr> ";
    			trip_html +="<th scope='row'>"+number+"</th>";
    			trip_html +="<td>"+tripItem.origin+"</td>";
    			trip_html +="<td>"+tripItem.destination+"</td>";
    			trip_html +="<td>"+tripItem.startTime+"</td>";
    			trip_html +="<td>"+tripItem.endTime+"</td>";
    			trip_html +="<td>"+tripItem.memoto+"</td>";
    			trip_html +="</tr>";
    			table.append(trip_html);
    		}
    	}else{
    		table.append("<tr><td colspan='6' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	me.addTab({
    		divId:"tab_trip",
    		title:"行程明细",
    		content:table
    	});
    	
    },
    bindSubsidyTable : function (billData){  //补助记录
    	var me = this;
    	var table = $('<table>').addClass("table table-bordered");   
    	table.append('<thead><tr class="active"></tr></thead> ');
    	var theadTr = table.children("thead").find("tr");
    	theadTr.append('<th style="width: 10%">序号</th>');
    	theadTr.append('<th style="width: 10%">补贴类型</th>');
    	theadTr.append('<th style="width: 10%">补贴天数	</th>');
    	theadTr.append('<th style="width: 10%">补贴标准	</th>');
    	theadTr.append('<th style="width: 10%">金额	</th>');
    	theadTr.append('<th style="width: 30%">备注</th>');
    	var subsidyData =   billData.subsidyItem;  
    	if(subsidyData !=null && subsidyData.length > 0){
    		for(var i =0;i<subsidyData.length;i++){
    			var number = i+1;
    			var subsidyItem = subsidyData[i];
    			var subsidy_html = "<tr> ";
    			subsidy_html +="<th scope='row'>"+number+"</th>";
    			subsidy_html +="<td>"+me.subsidyType[subsidyItem.type]+"</td>";
    			subsidy_html +="<td>"+subsidyItem.countDay+"</td>";
    			subsidy_html +="<td>"+subsidyItem.subsidyAmount+"</td>";
    			subsidy_html +="<td>"+subsidyItem.standard+"</td>";
    			subsidy_html +="<td>"+subsidyItem.memoto+"</td>";
    			subsidy_html +="</tr>";
    			table.append(subsidy_html);
    		}
    	}else{
    		table.append("<tr><td colspan='6' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    	me.addTab({
    		divId:"tab_subsidy",
    		title:"补助明细",
    		content:table
    	});
    	
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
    addTab : function (options){  //添加选项卡
    	var li = $("<li />").append($("<a />",{
    			"href": "#" + options.divId,
    	        "text": options.title,
    	        "click": function () {
    	            $(this).tab("show");
    	        }
    		})
    	);
    	$("#tab_header").append(li);
    	var div = $("<div />", {
            "id": options.divId,
            "class": "tab-pane fade",
        });
       if(typeof options.content == "string" ){
    	   div.append(options.content);
       }else{
    	   div.html(options.content)
       }	
       $("#tab_content").append(div);

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
    	var bankId = $('#bankItem').combobox('getValue');
    	if(System.isnull(bankId)){
    		IMessageBox.toast('请选择支付方式!',2);
			return;
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

