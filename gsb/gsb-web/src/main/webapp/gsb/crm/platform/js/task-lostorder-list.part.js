com.gongsibao.crm.web.TaskLostOrderListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
	},
	verified:function(id){
		
		alert('属实');
	},
	untrue:function(id){
		
		alert('不属实');
	},
	submitRemark:function(id){
		
		alert('备注');
	}
});
