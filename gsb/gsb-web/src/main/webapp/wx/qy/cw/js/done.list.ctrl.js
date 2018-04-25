org.netsharp.we.core.DoneListCtrl = org.netsharp.we.core.TodoListCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.oper = "done";
    },
    urlHandle:function (item){
    	var billUrl = "";
    	var employeeId = this.queryString('employeeId');
    	if(item.formType == 3 ){
			billUrl = "loanDetail?employeeId="+employeeId+"&id="+item.formId+"&oper="+this.oper;
		}
		if(item.formType == 4 ){
			billUrl = "expenseDetail?employeeId="+employeeId+"&id="+item.formId+"&oper="+this.oper;;
		}
    	return billUrl;
    }
});