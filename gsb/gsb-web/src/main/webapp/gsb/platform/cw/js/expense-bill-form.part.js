System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.ExpenseBillFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    added: function (currentItem) {
    	
    	$('#loanAmount').numberbox('readonly',true);
    	$('#totalAmount').numberbox('setValue', 0);
    	$('#totalAmount').numberbox('readonly',true);
    	
    	//$('#amount').numberbox('setValue', 0);
    	//$('#amount').numberbox('setValue', 0);
    	 $('#amount').textbox({value: 0});
    	 $('#amount').textbox({readonly: true});
    },
    paymentMethodChange:function (el){
    	 if ($(el).val() == 2) {
    		 $('#companyName').textbox({disabled: false});
    		 $('#companyBank').textbox({disabled: false});
    		 $('#companyAccount').textbox({disabled: false});
         } else {
        	 $('#companyName').textbox({value: ""});
             $('#companyName').textbox({disabled: true});
             $('#companyBank').textbox({value: ""});
             $('#companyBank').textbox({disabled: true});
             $('#companyAccount').textbox({value: ""});
             $('#companyAccount').textbox({disabled: true});
         }
    },
    expenseChange : function (el){
    	 if ($(el).val() == 2) {
    		 $('#entertainCompany').textbox({disabled: false});
    		 $('#entertainCustomer').textbox({disabled: false});
    		 $('#entertainPlace').textbox({disabled: false});
    		 $('#entertainDate').textbox({disabled: false});
         } else {
        	 $('#entertainCompany').textbox({value: ""});
             $('#entertainCompany').textbox({disabled: true});
             $('#entertainCustomer').textbox({value: ""});
             $('#entertainCustomer').textbox({disabled: true});
             $('#entertainPlace').textbox({value: ""});
             $('#entertainPlace').textbox({disabled: true});
             $('#entertainDate').textbox({value: ""});
             $('#entertainDate').textbox({disabled: true});
         }
    },
    correctChange :function (el){ //冲正借款开关
    	var isCorrect = $(el).switchbutton("options").checked;
    	if(isCorrect){
    		 var loanAmount = $("#loanAmount").numberbox('getValue');
    	  	 var totalAmount = $("#totalAmount").numberbox('getValue');
    	  	 $('#amount').textbox('setValue',totalAmount - loanAmount);
    	}else{
    		 var totalAmount = $("#totalAmount").numberbox('getValue');
    	  	 $('#amount').textbox('setValue',totalAmount);
    	}
    },
    doSave: function (entity) {
        var me = this;
        IMessageBox.confirm('确定提交申请吗？', function (r) {
            if (r) {
                me.invokeService("saveExpense", [entity], function (data) {
                    IMessageBox.info('申请成功，请等待审核!', function (s) {
                        window.parent.layer.closeAll();
                        window.parent.controllerexpenseList.reload();
                    });
                });
            }
        });
    },
    validate: function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
        	 var rows = $("#datagridcostDetailItem").datagrid('getRows');
        	 var amount = $('#amount').numberbox('getValue');
        	 if(rows.length == 0 || amount == 0){
        		  IMessageBox.error("至少需要添加一条报销明细");
        		  return false;
        	 }
        	 return true; 
        }else{
        	return false;
        }
        
    }
});
//费用明细
com.gongsibao.cw.web.CostDetailListPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
        this.base();
        this.costType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$CostType');
        this.InvoiceType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$InvoiceType');
        this.TaxRateType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$TaxRateType');
    },
    invoiceTypeChange : function (el){
    	 if ($(el).val() == 2) { 
    		 $('#taxRate').combobox({disabled: false});
    		 $('#detailTaxation').combobox({disabled: false});
    	 }else{
    		 $('#taxRate').combobox('setValue', '3');
    		 $('#taxRate').combobox({disabled: true});
    		 $('#detailTaxation').val(0);
    		 $('#detailTaxation').numberbox({disabled: true});
    	 }
    },
    saveBefore:function (entity){
    	entity.pathName = entity.organization.pathName
    	entity.formType = 4;  //报销单
    },
    saveAfter: function () { 
    	this.sumAmount();
    },
    costTypeFormatter : function (value,row,index){
    	var me = this;
		return me.costType[value];
    },
    invoiceTypeFormatter : function (value,row,index){
    	var me = this;
    	return me.InvoiceType[value];
    },
    taxRateTypeFormatter : function (value,row,index){
    	var me = this;
    	var invoiceType =  $("#invoiceType").val();
    	if(invoiceType == 1){
    		return me.TaxRateType[value];
    	}else{
    		return 0;
    	}
    },
    taxRateTypeChange : function (el){
    	var detailMoney =  $("#detailMoney").val();
    	var taxRate = $(el).val();
    	var detailTaxation = (detailMoney * taxRate)/100;
    	$('#detailTaxation').numberbox('setValue', detailTaxation);
    },
    detailMoneyChange : function (el){
    	var invoiceType = $("#invoiceType").val();
    	if(invoiceType == 2){
    		var detailMoney =  $(el).val();
        	var taxRate = $("#taxRate").val();
        	var detailTaxation = (detailMoney * taxRate)/100;
        	$('#detailTaxation').numberbox('setValue', detailTaxation);
    	}
    },
	doRemove : function (){
		this.remove();
		this.sumAmount();
	},
    sumAmount : function (){ //计算明细金额
    	 var costRows = this.getGrid().datagrid('getRows');
         var costSumAmount = 0;
         var taxationSumAmount = 0 ;
         $(costRows).each(function (i, item) {
        	 costSumAmount += parseInt(item.detailMoney)/100;
        	 taxationSumAmount += parseInt(item.detailTaxation);
         });
         
         var subsidyRows = controllersubsidyItem.getGrid().datagrid('getRows');
         var subsidySumAmount = 0 ;
         $(subsidyRows).each(function (i, item) {
        	 subsidySumAmount += parseInt(item.subsidyAmount)/100;
         });
         
        $('#totalAmount').numberbox('setValue',costSumAmount + subsidySumAmount);
         
        //报销合计减去借款金额等于报销金额
        var loanAmount = $("#loanAmount").numberbox('getValue');
  	    var totalAmount = $("#totalAmount").numberbox('getValue');
  	    
  	    var isCorrect = $("#isOffset").switchbutton("options").checked;
	    if(isCorrect){
	    	$('#amount').textbox('setValue',totalAmount - loanAmount);
	    }else{
	    	$('#amount').textbox('setValue',totalAmount);
	    }
    }
	
});
//行程明细
com.gongsibao.cw.web.TripRecordListPart= org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
        this.base();
    },
    doRemove : function (){
		this.remove();
	}
});
//补助明细
com.gongsibao.cw.web.SubsidyRecordListPart= org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
		this.subsidyType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$SubsidyType');
		this.base();
	},
	dayChange : function (el){
		var countDay = $(el).val();
		var standard =$('#standard').numberbox('getValue');
		$('#subsidyAmount').numberbox('setValue', countDay * standard);
	},
	standardChange : function (el){
		var standard = $(el).val();
		var countDay =$('#countDay').numberbox('getValue');
		$('#subsidyAmount').numberbox('setValue', countDay * standard);
	},
	subsidyTypeFormatter : function (value,row,index){
		var me = this;
		return me.subsidyType[value];
	},
	saveAfter : function (){
		this.sumAmount();
	},
	doRemove : function (){
		this.remove();
		this.sumAmount();
	},
	sumAmount : function (){
		 var costRows = controllercostDetailItem.getGrid().datagrid('getRows');
         var costSumAmount = 0;
         $(costRows).each(function (i, item) {
        	 costSumAmount += parseInt(item.detailMoney)/100;
         });
         
         var subsidyRows = this.getGrid().datagrid('getRows');
         var subsidySumAmount = 0 ;
         $(subsidyRows).each(function (i, item) {
        	 subsidySumAmount += parseInt(item.subsidyAmount)/100;
         });
        $('#totalAmount').numberbox('setValue',costSumAmount + subsidySumAmount);
	   
	   var loanAmount = $("#loanAmount").numberbox('getValue');
	   var totalAmount = $("#totalAmount").numberbox('getValue');
	   
	   
	   var isCorrect = $("#isOffset").switchbutton("options").checked;
 	    if(isCorrect){
 	    	$('#amount').textbox('setValue',totalAmount - loanAmount);
 	    }else{
 	    	$('#amount').textbox('setValue',totalAmount);
 	    }
	}
});




//附件
com.gongsibao.cw.web.AttachmentListPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
        this.base();
    },
    initUpload: function () {

        var upload = new org.netsharp.controls.ExpenseFileUpload();
        upload.parent = this;
        upload.init();
    },
    appendRow: function (path, file) {

        var row = new Object();
        row.name = file.name;
        row.url = path;
        row.tabName = 'cw_expense';//要放到后台处理
        $('#' + this.context.id).datagrid('appendRow', row);
    },
    onload: function () {

        this.resize();
        this.initUpload();
    },
    urlFormatter: function (value, row, index) {

        var str = '<a class="grid-btn" href="javascript:window.open(\'' + row.url + '\');">查看</a> \
		   <a class="grid-btn" href="javascript:controllerfiles.remove(' + index + ');">删除</a>';
        return str;
    },
    remove: function (index) {

        $('#' + this.context.id).datagrid('deleteRow', index);
    }
});

org.netsharp.controls.ExpenseFileUpload = org.netsharp.controls.OSSUpload.Extends({
    ctor: function () {
        this.base();
        this.multi_selection = true;
        this.parent = null;
    },
    getButtonId: function () {

        return "controllerfilesuploadAdd";
    },
    preview: function (path, file) {

        if (System.isnull(path)) {
            return;
        }
        this.parent.appendRow(path, file);
    }
});

