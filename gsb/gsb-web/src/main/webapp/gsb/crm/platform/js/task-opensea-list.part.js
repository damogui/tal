com.gongsibao.crm.web.TaskOpenSeaListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
	},
	batchAllocation:function(){
		
		alert("未实现");
	},
	detail:function(id){
		
		var url = "/panda/operation/task/edit?id="+id;
		window.open(url);
	}
});
