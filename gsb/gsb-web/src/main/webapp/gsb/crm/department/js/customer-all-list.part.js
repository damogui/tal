System.Declare("com.gongsibao.crm.web.department");
com.gongsibao.crm.web.department.DepartmentAllCustomerListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = "/panda/crm/department/customer/add";
		this.editUrl = "/panda/crm/department/customer/edit";
		this.addTaskUrl="/panda/crm/department/task/add";
	}
});