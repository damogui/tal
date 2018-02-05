System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.PlatformTaskCtrl = com.gongsibao.crm.web.TaskCtrl.Extends({
	ctor : function() {
		
		this.taskUrl = '/panda/crm/platform/task/edit';
		this.customerUrl = '/panda/crm/platform/customer/edit';
	}
});