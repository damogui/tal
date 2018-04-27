com.gongsibao.crm.web.DepartmentAllCustomerListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
		this.isPlatform = 0;
		this.addUrl = "/panda/crm/department/customer/add";
		this.editUrl = "/panda/crm/department/customer/edit";
		this.addTaskUrl="/panda/crm/department/task/add";
	}
});