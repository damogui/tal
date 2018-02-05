System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.DepartmentTaskCtrl = com.gongsibao.crm.web.TaskCtrl.Extends({
	ctor : function() {
		
		this.taskUrl = '/panda/crm/department/task/edit';
		this.customerUrl = '/panda/crm/department/customer/edit';
	}
});