System.Declare("com.gongsibao.workbench.SupplierWorkbench");
com.gongsibao.workbench.SupplierWorkbench = org.netsharp.panda.Workbench.Extends({
	ctor: function() {
		this.base();
	},
	openHomePage:function(){
		
		//打开服务商的首页，首页可按当前登录人的角色查看部门，业务员，服务商的统计信息
		this.openWorkspace("首页","https://www.baidu.com",'fa fa-home',false);
	}
});