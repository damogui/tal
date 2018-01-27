com.gongsibao.crm.web.NCustomerAllListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		window.open("/panda/crm/salesman/customer/add");
	},
	detail:function(id){
		
		this.edit(id);
	},
	edit : function(id) {

		window.open("/panda/crm/salesman/customer/edit?id="+id);
	},
	addTask:function(){

		var row = this.getSelectedItem();
		if(row == null){
			return;
		}
		var customerId = row.id;
    	var url='/panda/crm/salesman/task/add?fk=customerId:'+customerId;
    	layer.open({
  		  type: 2,
  		  title: '新增任务',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['90%','90%'],
  		  content: url,
  		  cancel: function(){ 

		  }
  	    });
	}
});
