com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/platform/customer/add";
		this.editUrl = "/nav/gsb/crm/platform/task";
		this.followUrl = '/panda/crm/platform/task/follow/from';
		this.addCustomerUrl = null;
	}
});
