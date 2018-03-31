System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.ExpenseBillFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    added: function (currentItem) {
    	$('#amount').numberbox('setValue', 0);
    	$('#amount').numberbox('readonly',true);
    	//$('#subsidyAmount').numberbox('setValue', 0);
    	//$('#subsidyAmount').numberbox('readonly',true);
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
    doSave: function (entity) {
        var me = this;
        IMessageBox.confirm('确定提交申请吗？', function (r) {
            if (r) {
                me.invokeService("saveExpense", [entity], function (data) {
                    IMessageBox.info('申请成功，请等待审核!', function (s) {
                        window.parent.layer.closeAll();
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
    },
    saveAfter: function () { //计算明细金额
        var rows = this.getGrid().datagrid('getRows');
        var totalAmount = 0;

        $(rows).each(function (i, item) {

            totalAmount += parseInt(item.detailMoney)/100;

        });
        var amount =$('#amount').numberbox('getValue');
        $('#amount').numberbox('setValue',parseInt(amount)+totalAmount);
    }
});
//行程明细
com.gongsibao.cw.web.TripRecordListPart= org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
        this.base();
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
		 var rows = this.getGrid().datagrid('getRows');
	     var totalAmount = 0;
	     $(rows).each(function (i, item) {
            totalAmount += parseInt(item.subsidyAmount)/100;

        });
	   var amount =  $('#amount').numberbox('getValue');
	   $('#amount').numberbox('setValue',parseInt(amount)+totalAmount);
	   
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

