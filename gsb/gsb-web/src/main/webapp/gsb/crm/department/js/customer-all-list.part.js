System.Declare("com.gongsibao.crm.web.department");
com.gongsibao.crm.web.department.DepartmentAllCustomerListPart = com.gongsibao.crm.web.BaseCustomerListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		window.open("/panda/crm/department/customer/add");
	},
	detail:function(id){
		
		this.edit(id);
	},
	edit : function(id) {

		window.open("/panda/crm/department/customer/edit?id="+id);
	},
	addTask:function(){

		var row = this.getSelectedItem();
		if(row == null){
			return;
		}
		var customerId = row.id;
    	var url='/panda/crm/department/task/add?fk=customerId:'+customerId;
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