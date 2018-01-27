com.gongsibao.crm.web.TaskLostOrderListPart = com.gongsibao.crm.web.BaseTaskListPart.Extends({
	ctor : function() {
		this.base();
	},
	verified:function(id){
		//state :1-"未抽查",2-"抽查正常",3-"抽查异常",4-"异常已处理"
		//type :1-"抽查",2-"处理"
		var me = this;
		me.doAbnormalPopup(id,2,1);
	},
	untrue:function(id){
		var me = this;
		me.doAbnormalPopup(id,3,1);
	},
	submitRemark:function(id){
		//state:0  ；只更新内容
		var me = this;
		me.doAbnormalPopup(id,0,1);
	},doAbnormalPopup : function(id,state,type) {
		var me = this;
		var content = '<p style="padding-left:50px;">&nbsp;抽查记录：</p>'
				+ '<p style="padding-left:50px;">'
				+ '<textarea collected="true" controltype="TextArea" id="txtNote" style="width:445px;height:100px;" '
				+ ' class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">'
				+ '</textarea></p>';
		layer.open({
			type : 1,
			title : '抽查记录内容',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '600px', '360px'],
			content : content,
			btn : [ '确定', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入抽查记录');
					return false;
				}
				me.doAbnormal(id,state,getNote,type);
			},
			btn2 : function(index, layero) {
			}
		});
	},doAbnormal : function(id,state,getNote,type) {
		var me = this;
		this.invokeService("abnormal", [id,state,getNote,type],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	}
});
