System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.CustomerFormPart = org.netsharp.panda.commerce.FormPart.Extends( {});

com.gongsibao.crm.web.NCustomerFollowStatusPart = org.netsharp.panda.commerce.DetailPart.Extends( {	
	saveAfter:function(entity){
		entity.id=null;
		var me = this;
		this.invokeService("save", [entity], function(data) {
			alert(123);
			//me.parent.byId(entity.customerId);
		});
		
	}
});