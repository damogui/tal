System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.DoneBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
      this.base();
      this.loanAuditUrl = '/nav/gsb/platform/cw/loan_audit_bill_form';
   	  this.exppenseAuditUrl = '/nav/gsb/platform/cw/expense_audit_bill_form';
   	  this.paymentAuditUrl = "/nav/gsb/platform/cw/payment_audit_bill_form";
   	  this.oper = "done"; 
    },
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var formType = row.formTypeValue;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showBill('+formId+','+formType+');">查看</a>';
    	return opeHtml;
    },
    showBill:function (formId,formType){ //打开单据详情
    	var contentUrl ;
    	var title = "";
    	if(formType == 3){
    		title = "借款单";
    		contentUrl = this.loanAuditUrl +"?formId="+formId +"&formType="+formType+"&oper="+this.oper;
    	}
    	if(formType == 4){
    		title = "报销单";
    		contentUrl = this.exppenseAuditUrl +"?formId="+formId +"&formType="+formType+"&oper="+this.oper;
    	}
    	if(formType == 5){
    		title = "付款单";
    		contentUrl = this.paymentAuditUrl +"?formId="+formId +"&formType="+formType+"&oper="+this.oper;
    	}
    	layer.open({
    		id: "auditBillIframe",
            type: 2,
            title: '查看'+title,
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: contentUrl,
            btn : [ '取消' ],
            success: function (layero, index) {
           	
            }
        });
    }
});

