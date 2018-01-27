System.Declare("com.gongsibao.crm.web.department");
com.gongsibao.crm.web.department.DepartmentAllTaskListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		
		this.base();
		this.followUrl = '/panda/crm/department/task/followUp/from';
	},
	add:function(){
		
		window.open("/panda/crm/department/task/add");
	},
	edit : function(id) {
		
		var url = "/panda/crm/department/task/edit?id="+id;
		window.open(url);
	}
});
