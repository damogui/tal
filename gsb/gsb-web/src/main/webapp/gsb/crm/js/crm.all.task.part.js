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