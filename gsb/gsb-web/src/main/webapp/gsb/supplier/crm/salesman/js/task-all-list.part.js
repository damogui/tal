com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/salesman/task/add";
		this.editUrl = "/nav/gsb/supplier/crm/salesman/task";
		this.followUrl = '/panda/crm/salesman/task/follow';
		this.addCustomerUrl = '/panda/crm/salesman/customer/add';
	}
});
