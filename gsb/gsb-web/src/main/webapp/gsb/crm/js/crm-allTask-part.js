//任务中的子页面工具栏中的跟进操作（沟通日志的跟进操作）
System.Declare("com.gongsibao.crm.web"); 
com.gongsibao.crm.web.NCustomerFollowPart = org.netsharp.panda.commerce.DetailPart.Extends( {
	saveAfter:function(entity){
		var id = this.queryString("id");
		entity.id=null;
		entity.customerId=id;
		var me = this;
		this.invokeService("save", [entity], function(data) {
			me.parent.byId(entity.customerId);
		});
	}
});
com.gongsibao.crm.web.NProdMapDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
	productChange:function(newValue,oldValue){
		//为空时，重置查询条件：q
		if(System.isnull(newValue)){
			var options = $('#product_name').combogrid('options');
			var qp = options.queryParams;
		}
	}
});
System.Declare("com.gongsibao.controls");
com.gongsibao.controls.CityComboBox = org.netsharp.controls.PccBox.Extends({
	ctor: function() {
		this.base();
		this.service = 'com.gongsibao.controls.CityComboBoxController';
	}
});

