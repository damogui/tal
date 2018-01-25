//无法签单中的工具栏
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyCustomerListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	add : function(){
		var url='/panda/operation/customer/add?openType=0';
		layer.open({
	  		  type: 2,
	  		  title: '添加客户',
	  		  fixed: false,
	  		  maxmin: true,
	  		  shadeClose:true,
	  		  area: ['90%','90%'],
	  		  content: url,
	  		  cancel: function(){ 

			  }
	  	    });
	},
	detail : function(id){ 
		var url='/operation/customer/edit?id='+id;
		layer.open({
	  		  type: 2,
	  		  title: '查看',
	  		  fixed: false,
	  		  maxmin: true,
	  		  shadeClose:true,
	  		  area: ['90%','90%'],
	  		  content: url,
	  		  cancel: function(){ 

			  }
	  	    });
	},
	edit : function(id){
		alert(id);
		var url='/operation/customer/edit?id='+id;
		layer.open({
	  		  type: 2,
	  		  title: '查看',
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
