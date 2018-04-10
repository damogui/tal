System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.LoansBillFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    added: function (currentItem) {
    	$('#amount').numberbox('setValue', 0);
    	$('#amount').numberbox('readonly',true);
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
    doSave: function (entity) {
        var me = this;
        IMessageBox.confirm('确定提交申请吗？', function (r) {
            if (r) {
                me.invokeService("saveLoan", [entity], function (data) {
                    IMessageBox.info('申请成功，请等待审核!', function (s) {
                        window.parent.layer.closeAll();
                        window.parent.controllerloanList.reload();
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
        		  IMessageBox.error("至少需要添加一条借款明细");
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
    },
    costTypeFormat : function (value,row,index){
    	return this.costType[value];
    },
    saveBefore:function (entity){
    	entity.pathName = entity.organization.pathName
    	entity.detailMoney = parseInt(entity.detailMoney)/100; 
    	entity.formType = 1;  //借款单
    },
    saveAfter: function () { 
    	this.sumAmount();
    },
    doRemove : function (){
    	this.remove();
    	this.sumAmount();
    },
    sumAmount : function (){//计算明细金额
    	 var rows = this.getGrid().datagrid('getRows');
         var totalAmount = 0;
         $(rows).each(function (i, item) {
             totalAmount += parseInt(item.detailMoney);
         });
         $('#amount').numberbox('setValue', totalAmount);
    }
});

//附件
com.gongsibao.cw.web.AttachmentListPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
        this.base();
    },
    initUpload: function () {

        var upload = new org.netsharp.controls.LoanFileUpload();
        upload.parent = this;
        upload.init();
    },
    appendRow: function (path, file) {

        var row = new Object();
        row.name = file.name;
        row.url = path;
        row.tabName = 'cw_loan';//要放到后台处理
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

org.netsharp.controls.LoanFileUpload = org.netsharp.controls.OSSUpload.Extends({
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

