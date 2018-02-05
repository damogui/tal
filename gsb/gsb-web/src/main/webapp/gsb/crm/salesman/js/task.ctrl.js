System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.SalesmanTaskCtrl = com.gongsibao.crm.web.TaskCtrl.Extends({
	ctor : function() {
		
		this.taskUrl = '/panda/crm/salesman/task/edit';
		this.customerUrl = '/panda/crm/salesman/customer/edit';
	}
});