com.gongsibao.crm.web.NCustomerAllListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
		this.isPlatform = 0;
		this.addUrl = "/panda/crm/salesman/customer/add";
		this.editUrl = "/panda/crm/salesman/customer/edit";
		this.addTaskUrl="/panda/crm/salesman/task/add";
	}
});
