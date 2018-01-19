//无法签单中的工具栏
System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.CheckAbmormalPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	checkAbmormal : function() {
		var me = this;
		var row = this.getSelectedItem();
		if (row == null) {
			IMessageBox.info('请选择记录');
			return;
		}
		// 支付id
		var taskId = row.id;
		var content = '<br/><p style="padding-left:50px;">&nbsp;抽查状态：<select class="easyui-validatebox nsInput" id="selStatus">' 
			+ '<option value="1">未抽查</option>' 
			+ '<option value="2">抽查正常</option>'
			+ '<option value="3">抽查异常</option>'
			+ '<option value="4">异常已处理</option>'
			+ '</select> </p>'
				+ '<p style="padding-left:50px;">&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：'
				+ '<textarea collected="true" controltype="TextArea" id="txtNote" style="width:187px;height:60px;" '
				+ ' class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">'
				+ '</textarea></p>';
		layer.open({
			type : 1,
			title : '抽查标记',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '500px', '300px' ],
			content : content,
			btn : [ '保存', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入备注');
					return false;
				}
				var selectValue = $("#selStatus option:selected").val();
				me.updataTaskInspectionState(taskId,selectValue,getNote);
			},
			btn2 : function(index, layero) {
			}
		});
	},updataTaskInspectionState : function(taskId,selectValue,getNote) {
		alert(taskId +"======="+selectValue+"===="+getNote);
		var me = this;
		this.invokeService("taskInspectionState", [taskId,selectValue,getNote],function(data) {
			alert(123);
			me.reload();
			IMessageBox.toast('标记成功');
			layer.closeAll();
			return;
		});
	}
});
