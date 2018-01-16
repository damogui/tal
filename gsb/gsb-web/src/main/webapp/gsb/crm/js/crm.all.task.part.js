System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.NCustomerFormPart = org.netsharp.panda.commerce.DetailPart.Extends( {	
	ctor: function() {
		this.base();
	},
	save : function(){
		var id = this.queryString("id");
		this.invokeService("updateNcustomerTask",[id],function(data){
			alert(data);
		});
	},
	onload: function () {
        var id = this.queryString("id");
        alert(id);
    }
});

System.Declare("com.gongsibao.controls");
com.gongsibao.controls.CityComboBox = org.netsharp.controls.PccBox.Extends({
	ctor: function() {
		this.base();
		this.service = 'com.gongsibao.controls.CityComboBoxController';
	}
});