org.netsharp.we.core.LoanDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.id = this.queryString('id');
    },
    init:function(){
    	
    	this.byId();
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getLoanById', pars, function(result){
    		me.bindData(result);
    		me.bindU8BankSelect(result.setOfBooksId);
    	});
    },
    bindData:function(entity){
    	$("#bill_code").text(entity.code);
    	$("#create_time").text(entity.createTime);
    	$("#amount").text(entity.amount/100);
    	$("#borrower_name").text(entity.borrowerEmployee.name);
    	$("#creator").text(entity.creator);
    	$("#payeeName").text(entity.payeeName);
    	$("#books_name").text(entity.setOfBooks.name);
    	$("#payment_method").text(paymentMethodDict.byKey(entity.paymentMethod));
    	$("#bill_type").text(loanBillTypeDict.byKey(entity.type));
    	$("#memoto_txt").text(entity.memoto);
    	$("#companyAccount").text((System.isnull(entity.companyAccount))?"无":entity.companyAccount);
    	$("#companyBank").text((System.isnull(entity.companyBank))?"无":entity.companyBank);
    	
    },
    bindU8BankSelect: function (setOfBooksId){
    	this.invokeService("getU8BankList", [setOfBooksId], function(data){  
    		for(var i =0;i<data.length;i++){
    			$("#payBank").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
    		}
	   	});
    },
    saveAudit:function (){
    	$.toptip('审批意见不能为空', 'warning');
    }
});


