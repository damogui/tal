System.Declare("com.gongsibao.crm.web");
//所有任务列表基类
com.gongsibao.crm.web.BaseTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	add:function(){
		
		window.open("/panda/operation/customer/add");
	},
	detail:function(id){
		
		this.edit(id);
	},
	doubleClickRow : function(index, row) {

		this.edit(row.id);
	},
	edit : function(id) {
		
		var url = "/panda/operation/task/edit?id="+id;
		window.open(url);
	},
	allocation:function(id){
		//任务分配
		var me = this;
		me.doAllot(id);
	},
	doAllot : function(id) {
		var builder = new System.StringBuilder();
		builder.append('<div style="margin:10px;">');
		builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
		builder.append(' 		<tr><td class="title">服务商</td><td><input id="supplier_name"/></td></tr>');
		builder.append(' 		<tr><td class="title">部门</td><td><input id="department_name"/></td></tr>');
		builder.append(' 		<tr><td class="title">业务员</td><td><input id="employee_name"/></td></tr>');
		builder.append('	</table>');
		builder.append('</div>');

		var me = this;
		layer.open({
					type : 1,
					title : '任务分配',
					fixed : false,
					maxmin : false,
					shadeClose : false,
					zIndex : 100000,
					area : [ '450px', '300px' ],
					content : builder.toString(),
					btn : [ '提交', '取消' ],
					success : function(layero, index) {
						var options = {
							columns : [ [ {
								field : 'name',
								title : '名称',
								width : 100
							}] ],
							url : '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
							idField : 'id',
							textField : 'name',
							width : 300,
							fitColumns : true,
							panelWidth : 450,
							panelHeight : 310,
							pagination : true,
							pageSize : 10,
							mode : 'remote',
							multiple : false,
							onChange : function(newValue, oldValue) {
								
							}
						};
						$('#department_name').combogrid(options);
						// 服务商下拉参照配置
						options.columns = [ [ {
							field : 'name',
							title : '名称',
							width : 150
						} ] ];
						options.url = '\/panda\/rest\/reference?code=CRM_Supplier&filter=';
						options.textField = 'name';
						options.onChange = function(newValue, oldValue) {
						};
						$('#supplier_name').combogrid(options);
						// 业务员下拉参照配置
						options.columns = [ [ {
							field : 'name',
							title : '姓名',
							width : 150
						} ] ];
						options.url = '\/panda\/rest\/reference?code=Employee&filter=';
						options.textField = 'name';
						options.onChange = function(newValue, oldValue) {
						};
						$('#employee_name').combogrid(options);
					},
					btn1 : function(index, layero) {
						var supplierId = $('#supplier_name').combogrid('getValue');
						var departmentId = $('#department_name').combogrid('getValue');
						var toUserId = $('#employee_name').combogrid('getValue');
						if (System.isnull(supplierId) && System.isnull(departmentId) && System.isnull(toUserId)) {
							IMessageBox.info('请选择');
							return;
						}
						var allocationType = 2;//手动分配
						var taskId = id;
						me.doAllotService(taskId,supplierId,departmentId,toUserId,allocationType);
					}
				});
	},
	doAllotService : function(taskId,supplierId,departmentId,toUserId,allocationType) {
		var me = this;
		this.invokeService("allocation", [taskId,supplierId,departmentId,toUserId,allocationType],function(data) {
			me.reload();
			IMessageBox.toast('分配成功');
			layer.closeAll();
			return;
		});
	},
	batchAllocation:function(){
		//任务批量分配
		var row = this.getSelectedItem();
		alert(row);
	},
	rollback : function(id){
		//任务退回
		var me = this;
		me.doRollBack(id);
	},
	doRollBack : function(id) {
		var me = this;
		var row = this.getSelectedItem();
		var taskId = id;
		var intenCategory = row.intentionCategory;
		var content = '<p style="padding-left:150px;">任务将会退回至【公海】，进行【二次分配】</p>'
			    + '<p style="padding-left:50px;">&nbsp;退回原因：</p>'
				+ '<p style="padding-left:50px;">'
				+ '<textarea collected="true" controltype="TextArea" id="txtNote" style="width:445px;height:100px;" '
				+ ' class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">'
				+ '</textarea></p>';
				
		//判断客户质量AB需要提示
		if(intenCategory.indexOf("A") > -1 || intenCategory.indexOf("B") > -1 || intenCategory.indexOf("X") > -1){
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
				me.doRollBackService(taskId,getNote);
			},
			btn2 : function(index, layero) {
			}
		});
	},
	doRollBackService : function(taskId,getNote) {
		var me = this;
		this.invokeService("rollback", [taskId,getNote],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	},
	transfer : function(id){
		//任务转移
		var me = this;
		var row = this.getSelectedItem();
		if (row == null) {
			IMessageBox.info('请选择记录');
			return;
		}
		me.doTransfer(id);
	},
	doTransfer : function(id) {
		var builder = new System.StringBuilder();
		builder.append('<div style="margin:10px;">');
		builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
		builder.append(' 		<tr><td class="title">服务商</td><td><input id="supplier_name"/></td></tr>');
		builder.append(' 		<tr><td class="title">部门</td><td><input id="department_name"/></td></tr>');
		builder.append(' 		<tr><td class="title">业务员</td><td><input id="employee_name"/></td></tr>');
		builder.append('	</table>');
		builder.append('</div>');
		var me = this;
		layer.open({
					type : 1,
					title : '任务转移',
					fixed : false,
					maxmin : false,
					shadeClose : false,
					zIndex : 100000,
					area : [ '450px', '300px' ],
					content : builder.toString(),
					btn : [ '提交', '取消' ],
					success : function(layero, index) {
						var options = {
							columns : [ [ {
								field : 'name',
								title : '名称',
								width : 100
							}] ],
							url : '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter=',
							idField : 'id',
							textField : 'name',
							width : 300,
							fitColumns : true,
							panelWidth : 450,
							panelHeight : 310,
							pagination : true,
							pageSize : 10,
							mode : 'remote',
							multiple : false,
							onChange : function(newValue, oldValue) {
								
							}
						};
						$('#department_name').combogrid(options);
						// 服务商下拉参照配置
						options.columns = [ [ {
							field : 'name',
							title : '名称',
							width : 150
						} ] ];
						options.url = '\/panda\/rest\/reference?code=CRM_Supplier&filter=';
						options.textField = 'name';
						options.onChange = function(newValue, oldValue) {
						};
						$('#supplier_name').combogrid(options);
						// 业务员下拉参照配置
						options.columns = [ [ {
							field : 'name',
							title : '姓名',
							width : 150
						} ] ];
						options.url = '\/panda\/rest\/reference?code=Employee&filter=';
						options.textField = 'name';
						options.onChange = function(newValue, oldValue) {
						};
						$('#employee_name').combogrid(options);
					},
					btn1 : function(index, layero) {
						var supplierId = $('#supplier_name').combogrid('getValue');
						var departmentId = $('#department_name').combogrid('getValue');
						var toUserId = $('#employee_name').combogrid('getValue');
						if (System.isnull(supplierId) && System.isnull(departmentId) && System.isnull(toUserId)) {
							IMessageBox.info('请选择');
							return;
						}
						var taskId = id;
						me.operatTaskTransfer(taskId,supplierId,departmentId,toUserId);
					}
				});
	},
	doTransferService : function(taskId,supplierId,departmentId,toUserId) {
		var me = this;
		this.invokeService("transfer", [taskId,supplierId,departmentId,toUserId],function(data) {
			me.reload();
			IMessageBox.toast('转移成功');
			layer.closeAll();
			return;
		});
	},
	openMember : function(){
		//开通会员
		var me = this;
		var row = this.getSelectedItem();
		if (row == null) {
			IMessageBox.info('请选择记录');
			return;
		}
		// 任务id
		var customerId = row.customer_id;
		var isMember = row.customer_isMember;
		if(isMember){
			IMessageBox.info('您已开通会员');
			return;
		}
		IMessageBox.confirm("您确定为该条记录开通会员吗？",function(r){
			//严格语法
			if(r===true){
				me.doOpenMember(customerId);
			}
		});
	},
	doOpenMember : function(customerId) {
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
	},
});
