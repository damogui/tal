com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/platform/task/add";
		this.editUrl = "/nav/gsb/platform/operation/crm/task";
		this.followUrl = '/panda/crm/platform/task/follow/from';
		this.addCustomerUrl = '/panda/crm/platform/customer/add';
	}
});
