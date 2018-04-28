System.Declare("com.gongsibao.workbench.SupplierWorkbench");
com.gongsibao.workbench.SupplierWorkbench = org.netsharp.panda.Workbench.Extends({
	ctor: function() {
		this.base();
		this.context = null;
	},
	openHomePage:function(){
		
		//打开服务商的首页，首页可按当前登录人的角色查看部门，业务员，服务商的统计信息
		this.openWorkspace("首页","/nav/gsb/supplier/home",'fa fa-home',false);
	},
	switchReceiving:function(checked){
		
		var service = "com.gongsibao.workbench.supplier.SupplierWorkbench";
		this.invokeService(service,"setReceiving", [checked], function(data) {

			if(checked === true){

				layer.msg("接单已开启");
			}else{

				layer.msg("接单已关闭");
			}
		});
	}
});


$(function(){
	
	var orderTips = sessionStorage['orderTips'];
	if(orderTips == '0' || orderTips == undefined){
		
		layer.confirm('【订单管理】>【我的订单】>【创建订单】功能已关闭，请从商机创建订单。', {title:'重要提醒',closeBtn:false,shade:false,
			  btn: ['我知道了']
			}, function(){

				sessionStorage['orderTips'] = '1';
				layer.closeAll();
		});
		
	}
	
});
