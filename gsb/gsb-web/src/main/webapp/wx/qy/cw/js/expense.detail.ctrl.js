org.netsharp.we.core.ExpenseDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.TodoListController';
    	this.id = this.queryString('id');
    },
    init:function(){
    	
    	this.byId();
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getExpenseById', pars, function(result){
    		me.bindData(result);
    	});
    },
    bindData:function(entity){
    	
    	
    	
    },
    saveAudit:function (){
    	
    }
});


