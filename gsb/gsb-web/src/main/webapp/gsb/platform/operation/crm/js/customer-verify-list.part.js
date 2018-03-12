//无法签单中的工具栏
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.NCustomerVerifyListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		//关闭窗口，直接进行新增
		var index = parent.layer.getFrameIndex(window.name); 
		parent.layer.close(index);
	},
	binding : function(id) {

		//这里应该跳转到客户修改页面
		//window.parent.location.href = '/panda/operation/customer/edit';
		window.parent.controllernCustomer.bindCustomer(id);
		
		var index = parent.layer.getFrameIndex(window.name); 
		parent.layer.close(index);
		//后续怎么操作？这时候不知道输入具体是哪个字段
	}
});
