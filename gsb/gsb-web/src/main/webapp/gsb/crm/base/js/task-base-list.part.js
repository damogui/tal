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
						me.operatTaskTransfer(taskId,supplierId,departmentId,toUserId,allocationType);
					}
				});
	},
	operatTaskTransfer : function(taskId,supplierId,departmentId,toUserId,allocationType) {
		alert(taskId +"|"+supplierId+"|"+departmentId+"|"+toUserId+"|"+allocationType);
		var me = this;
		this.invokeService("allocation", [taskId,supplierId,departmentId,toUserId,allocationType],function(data) {
			me.reload();
			IMessageBox.toast('分配成功');
			layer.closeAll();
			return;
		});
	},
	batchAllocation:function(){
		
		alert("批量分配-未实现");
	}
});
