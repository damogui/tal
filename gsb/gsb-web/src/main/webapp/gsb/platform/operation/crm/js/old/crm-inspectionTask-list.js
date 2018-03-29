System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyAllTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	//rowToolBar-行跟进
	follow : function() {
		var me = this;
		var row = this.getSelectedItem();
		var taskId = row.id;
		var url = "/panda/crm/my/task/followUp/from?fk=taskId:"+taskId;
		IMessageBox.open("跟进商机", url, 700, 400, function() {
			me.reload();
		});
	},//rowToolBar-行退回
	backTaskPopup : function() {
		var me = this;
		var row = this.getSelectedItem();
		var taskId = row.id;
		var intenCategory = row.intentionCategory;
		var ownerId = row.ownerId;
		var customerId = row.customer_id;
		var content = '<p style="padding-left:150px;">商机将会释放至【公海】，进行【二次分配】</p>'
			    + '<p style="padding-left:50px;">&nbsp;释放原因：</p>'
				+ '<p style="padding-left:50px;">'
				+ '<textarea collected="true" controltype="TextArea" id="txtNote" style="width:445px;height:100px;" '
				+ ' class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">'
				+ '</textarea></p>';
				
		//判断客户质量AB需要提示
		if(intenCategory.indexOf("A") > -1 || intenCategory.indexOf("B") > -1 || intenCategory.indexOf("X") > -1){
			content += '<p id="prompt" style="padding-left:30px;color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：请慎用！执行释放后该商机将不会再分配给你，'
				+ '如果只是需要将<br/>商机转给同事或者下属，请使用【商机转移】功能！</p>';
		};
		layer.open({
			type : 1,
			title : '释放商机',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '600px', '360px'],
			content : content,
			btn : [ '确定', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入释放原因');
					return false;
				}
				me.operatTaskReleasePopup(taskId,customerId,ownerId,getNote);
			},
			btn2 : function(index, layero) {
			}
		});
	},operatTaskReleasePopup : function(taskId,customerId,ownerId,getNote) {
		var me = this;
		this.invokeService("operatTaskRelease", [taskId,customerId,ownerId,getNote],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	}
});
