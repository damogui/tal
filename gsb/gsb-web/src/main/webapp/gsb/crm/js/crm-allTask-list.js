//无法签单中的工具栏
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.MyAllTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},//listToolBar-开通会员
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
	},
	openMemberLayer : function(customerId) {
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
	},//rowToolBar-行跟进
	followUpPopup : function() {
		var me = this;
		var row = this.getSelectedItem();
		var taskId = row.id;
		var url = "/panda/crm/my/task/followUp/from?fk=taskId:"+taskId;
		IMessageBox.open("跟进任务", url, 700, 400, function() {
			me.reload();
		});
	},//rowToolBar-行退回
	backTaskPopup : function() {
		var me = this;
		var row = this.getSelectedItem();
		var taskId = row.id;
		var intenCategory = row.intentionCategory;
		var salesmanId = row.salesmanId;
		var customerId = row.customer_id;
		var content = '<p style="padding-left:150px;">任务将会退回至【公海】，进行【二次分配】</p>'
			    + '<p style="padding-left:50px;">&nbsp;退回原因：</p>'
				+ '<p style="padding-left:50px;">'
				+ '<textarea collected="true" controltype="TextArea" id="txtNote" style="width:445px;height:100px;" '
				+ ' class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">'
				+ '</textarea></p>';
				
		//判断客户质量AB需要提示
		if(intenCategory.indexOf("A") > -1 || intenCategory.indexOf("B") > -1){
			content += '<p id="prompt" style="padding-left:30px;color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：请慎用！执行退回后该任务将不会再分配给你，'
				+ '如果只是需要将<br/>任务转给同事或者下属，请使用【任务转移】功能！</p>';
		};
		layer.open({
			type : 1,
			title : '释放任务',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '600px', '360px'],
			content : content,
			btn : [ '确定', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入退回原因');
					return false;
				}
				me.operatBackTaskPopup(taskId,customerId,salesmanId,getNote);
			},
			btn2 : function(index, layero) {
			}
		});
	},operatBackTaskPopup : function(taskId,customerId,salesmanId,getNote) {
		var me = this;
		this.invokeService("operatBackTask", [taskId,customerId,salesmanId,getNote],function(data) {
			alert(123);
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	}
});
