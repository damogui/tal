com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/salesman/task/add";
		this.editUrl = "/nav/gsb/crm/salesman/task";
		this.followUrl = '/panda/crm/salesman/task/follow';
		this.addCustomerUrl = null;
	}
});
