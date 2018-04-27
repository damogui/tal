System.Declare("com.gongsibao.cw.web");
//全部订单
com.gongsibao.cw.web.AllBillsListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	   this.pritBillUrl = "/nav/gsb/platform/cw/print_bill_template";
	},
	query : function() {

		this.queryModel.collectControl();
		var qpc = this.queryModel.getQueryParameters();
		var queryParams= this.queryModel.getFilterParameters();
		var filters = [];
		if(qpc === false){
			return;
		}
		for ( var i = 0; i < qpc.length; i++) {
			var filter  = qpc[i].Filter;
			if(filter.indexOf("amount>=")!= -1){
				var startAmount = qpc[i].Value*100;
				filters.push("amount>="+startAmount);
			}else if(filter.indexOf("amount<=")!= -1){
				var endAmount = qpc[i].Value*100 ;
				filters.push("amount<="+endAmount);
			}else{
				filters.push(qpc[i].Filter);
			}
		}
		var filter = filters.join(" AND ");
		this.doQuery(filter,queryParams);
		this.logQuery(filter);
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
    		if(data != null){
    			IMessageBox.toast(data.msg,2);
    		}else{
    			IMessageBox.toast("凭证生成失败！",2);
    		}
    	});
    }
});