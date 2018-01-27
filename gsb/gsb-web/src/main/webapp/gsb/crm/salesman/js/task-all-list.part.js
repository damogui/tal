com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/salesman/task/add";
		this.editUrl = "/panda/crm/salesman/task/edit";
		this.followUrl = '/panda/crm/salesman/task/followUp/from';
		this.addCustomerUrl = null;
	}
});
