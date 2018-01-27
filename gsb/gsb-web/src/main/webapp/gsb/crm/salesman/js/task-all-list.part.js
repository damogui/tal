com.gongsibao.crm.web.TaskAllListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
		this.followUrl = '/panda/crm/salesman/task/followUp/from';
	},
	add:function(){
		
		window.open("/panda/crm/salesman/task/add");
	},
	edit : function(id) {
		
		var url = "/panda/crm/salesman/task/edit?id="+id;
		window.open(url);
	},
	takeBack:function(){
		
		alert('收回');
	}
});
