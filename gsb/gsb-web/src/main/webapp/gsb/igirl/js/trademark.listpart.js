//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.TradeMarkListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	autoSubmit:function(){
		this.datagrid.datatrid('select')
	}

});
