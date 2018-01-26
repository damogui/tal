System.Declare("com.gongsibao.crm.web");
//所有任务列表基类
com.gongsibao.crm.web.BaseTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		window.open("/panda/operation/customer/add");
	},
	detail:function(id){
		
		this.edit(id);
	},
	doubleClickRow : function(index, row) {

		this.edit(row.id);
	},
	edit : function(id) {
		
		var url = "/panda/operation/task/edit?id="+id;
		window.open(url);
	},
	allocation:function(id){
		
		alert("未实现");
	},
	batchAllocation:function(){
		
		alert("未实现");
	}
});
