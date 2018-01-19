//无法签单中的工具栏
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyAllTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	openMemberPopup : function() {
		var me = this;
		var row = this.getSelectedItem();
		if (row == null) {
			IMessageBox.info('请选择记录');
			return;
		}
		// 任务id
		var customerId = row.customer_id;
		var content = new System.StringBuilder();
		IMessageBox.confirm("您确定为该条记录开通会员吗？",function(r){
			//严格语法
			if(r===true){
				me.openMemberLayer(customerId);
			}
		});
	},openMemberLayer : function(customerId) {
		var me = this;
		this.invokeService("openMember", [customerId],function(data) {
			if(data===1){
				me.reload();
				IMessageBox.toast('开通成功');
				layer.closeAll();
			}else{
				IMessageBox.toast('开通失败,稍后再试');
			}
			return;
		});
	}
});
