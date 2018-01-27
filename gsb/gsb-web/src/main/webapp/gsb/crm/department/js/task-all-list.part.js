System.Declare("com.gongsibao.crm.web.department");
com.gongsibao.crm.web.department.DepartmentAllTaskListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		
		this.base();
		this.addUrl = "/panda/crm/department/task/add";
		this.editUrl = "/panda/crm/department/task/edit";
		this.followUrl = '/panda/crm/department/task/followUp/from';
		this.addCustomerUrl = null;
	}
});
