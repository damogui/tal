System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.ProdMapDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {
	
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