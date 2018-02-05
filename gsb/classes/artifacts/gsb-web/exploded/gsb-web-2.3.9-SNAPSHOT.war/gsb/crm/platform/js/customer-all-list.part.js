com.gongsibao.crm.web.NCustomerAllListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/platform/customer/add";
		this.editUrl = "/panda/crm/platform/customer/edit";
		this.addTaskUrl="/panda/crm/platform/task/add";
	}
});
