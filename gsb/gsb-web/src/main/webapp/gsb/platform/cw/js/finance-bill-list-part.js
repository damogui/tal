System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.FinanceBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
    	this.base();
    	this.loanAuditUrl = '/nav/gsb/platform/cw/loan_audit_bill_form';
   	    this.exppenseAuditUrl = '/nav/gsb/platform/cw/expense_audit_bill_form';
   	    this.paymentAuditUrl = "/nav/gsb/platform/cw/payment_audit_bill_form";
    	this.formType = PandaHelper.Enum.get('com.gongsibao.entity.cw.dict.FinanceDict$FormType');
    },
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var formType = row.formTypeValue;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showAudit('+formId+','+formType+');">财务办理</a>';
    	return opeHtml;
    },
    formTypeFormatter:function (value,row,index){
    	return this.formType[value];
    },
    showAudit:function (formId,formType){ //打开审核页面
    	var contentUrl ;
    	var title = "";
    	if(formType == 3){
    		title = "借款单";
    		contentUrl = this.loanAuditUrl +"?formId="+formId +"&formType="+formType;
    	}
    	if(formType == 4){
    		title = "报销单";
    		contentUrl = this.exppenseAuditUrl +"?formId="+formId +"&formType="+formType;
    	}
    	if(formType == 5){
    		title = "付款单";
    		contentUrl = this.paymentAuditUrl +"?formId="+formId +"&formType="+formType;
    	}
    	layer.open({
    		id: "financeBillIframe",
            type: 2,
            title: '财务办理',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['90%','90%'],
            content: contentUrl,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
            	document.getElementById('financeBillIframe').firstElementChild.contentWindow.auditBillFormCtrl.saveFinance();
            }
        });
    }
});

