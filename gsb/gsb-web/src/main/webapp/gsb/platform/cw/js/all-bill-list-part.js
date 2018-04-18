System.Declare("com.gongsibao.cw.web");
//全部订单
com.gongsibao.cw.web.AllBillsListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	   this.pritBillUrl = "/nav/gsb/platform/cw/print_bill_template";
	},
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var formType = row.formTypeValue;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.createVoucher('+formId+','+formType+');">生成凭证</a>';
    	opeHtml += '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showPritBiil('+formId+','+formType+');">单据打印</a>';
    	return opeHtml;
    },
    showPritBiil:function (formId,formType){
		var pritUrl = this.pritBillUrl +"?formId="+formId +"&formType="+formType;
		window.open(pritUrl);
    },
    createVoucher : function (formId,formType){  
    	this.invokeService("createVoucher", [formId,formType], function(data){  
    		
    	});
    }
});