System.Declare("com.gongsibao.crm.web");
//所有任务列表基类
com.gongsibao.crm.web.BaseTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = null;
		this.editUrl = null;
		this.followUrl = null;
		this.addCustomerUrl = null;
	},
	add:function(){
		var row = this.getSelectedItem();
		if(row){

			var customerId = row.customerId;
			var url = this.addUrl+"?fk=customerId:"+customerId;
			window.open(url);
		}
	},
	detail:function(id){
		
		this.edit(id);
	},
	doubleClickRow : function(index, row) {
		
	},
	edit : function(id) {
		
		var url = this.editUrl+"?id="+id;
		window.open(url);
	},
	allocation:function(id){
		//任务分配
		var me = this;
		me.doAllot(id);
	},
	follow : function(id) {
		//任务跟进 
		var me = this;
		var url = this.followUrl +"?fk=taskId:"+id;
		IMessageBox.open("任务跟进", url, 700, 450, function() {
			me.reload();
		});
	},
	doAllot : function(taskId) {
		
		var me = this;
		var supplierOption = getSupplierOption();
		var departmentOption = getDepartmentOption();
		var employeeOption = getEmployeeOption();
		PandaHelper.openDynamicForm({
			title:'任务分配',
			width:450,
			height:300,
			items:[{id:'allot_supplier_name',
				title:'服务商',
				type:'combogrid',
	            className:'',
				option:supplierOption},
				
				{id:'allot_department_name',
					title:'部门',
					type:'combogrid',
		            className:'',
					option:departmentOption},
					
				{id:'allot_employee_name',
					title:'业务员',
					type:'combogrid',
		            className:'',
					option:employeeOption}
			],
			callback:function(index, layero){
				
				var supplierId = $('#allot_supplier_name').combogrid('getValue');
				var departmentId = $('#allot_department_name').combogrid('getValue');
				var toUserId = $('#allot_employee_name').combogrid('getValue');
				
				if (System.isnull(supplierId) && System.isnull(departmentId) && System.isnull(toUserId)) {
					
					IMessageBox.info('请选择');
					return;
				}
				var allocationType = 2;//手动分配
				me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId,allocationType],function(data) {
					me.reload();
					IMessageBox.toast('分配成功');
					layer.closeAll();
					return;
				});
			}
		});
	},

	batchAllocation:function(){
		
		//任务批量分配
		var me = this;
		var id = this.getSelectionIds();
		if(id == "" || id == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doAllot(id);
	},
	regain : function(){
		
		//任务收回
		var me = this;
		var id = this.getSelectionIds();
		if(id == "" || id == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doRollBack(id,1,null);
	},
	rollback : function(id){
		
		//任务退回
		var me = this;
		//这里先要取消所有行，再选中1行
		$("#" + this.context.id).datagrid('unselectAll');
		$("#" + this.context.id).datagrid('selectRecord',id);
		var row = this.getSelectedItem();
		var intenCategory = row.intentionCategory;
		me.doRollBack(id,2,intenCategory);
	},
	doRollBack : function(id,type,intenCategory) {
		//收回和退回公用一个方法 type:1-收回、2-退回
		var me = this;
		var setTitle ='';
		if(type==1){
			setTitle = "收回";
		}else{
			setTitle = "退回";
		}
		var content = new System.StringBuilder();
		content.append('<p style="padding-left:150px;">任务将会退回至【公海】，进行【二次分配】</p>');
		content.append('<p style="padding-left:50px;">&nbsp;'+ setTitle +'原因：</p>');
		content.append('<p style="padding-left:50px;">');
		content.append(' 	<textarea collected="true" controltype="TextArea" id="txtNote" style="width:445px;height:100px;"');
		content.append(' 		  class="easyui-validatebox validatebox-text" data-options="validateOnCreate:false,validateOnBlur:true">');
		content.append('</textarea>');
		content.append('</p>');
		
		//判断客户质量AB需要提示
		if(type == 2){
			if(intenCategory.indexOf("A") > -1 || intenCategory.indexOf("B") > -1 || intenCategory.indexOf("X") > -1){
				content.append('<p id="prompt" style="padding-left:30px;color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：请慎用！执行退回后该任务将不会再分配给你，');
				content.append('如果只是需要将<br/>任务转给同事或者下属，请使用【任务转移】功能！</p>');
			}
		}
		layer.open({
			type : 1,
			title : setTitle +'任务',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			area : [ '600px', '360px'],
			content : content.toString(),
			btn : [ '确定', '取消' ],// 可以无限个按钮
			btn1 : function(index, layero) {
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入原因');
					return false;
				}
				me.doRollBackService(id,getNote,type);
			},
			btn2 : function(index, layero,type) {
			}
		});
	},
	doRollBackService : function(id,getNote,type) {
		var me = this;
		var functionName = "rollback"
		if(type == 1){
			functionName = "regain";
		}
		this.invokeService(functionName, [id,getNote],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	},
	batchTransfer : function(){
		//任务批量转移
		var me = this;
		var id = this.getSelectionIds();
		if(id == "" || id == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doTransfer(id);
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
	doTransfer : function(taskId) {
		
		var me = this;
		var supplierOption = getSupplierOption();
		var departmentOption = getDepartmentOption();
		var employeeOption = getEmployeeOption();
		
		PandaHelper.openDynamicForm({
			title:'任务转移',
			width:450,
			height:300,
			items:[{id:'allot_supplier_name',
				title:'服务商',
				type:'combogrid',
	            className:'',
				option:supplierOption},
				
				{id:'allot_department_name',
					title:'部门',
					type:'combogrid',
		            className:'',
					option:departmentOption},
					
				{id:'allot_employee_name',
					title:'业务员',
					type:'combogrid',
		            className:'',
					option:employeeOption}
			],
			callback:function(index, layero){
				
				var supplierId = $('#allot_supplier_name').combogrid('getValue');
				var departmentId = $('#allot_department_name').combogrid('getValue');
				var toUserId = $('#allot_employee_name').combogrid('getValue');
				if (System.isnull(supplierId) && System.isnull(departmentId) && System.isnull(toUserId)) {
					
					IMessageBox.info('请选择');
					return;
				}
				me.doTransferService(taskId,supplierId,departmentId,toUserId);
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
	openMember : function(customerId){

		var me = this;
		IMessageBox.confirm("您确定要开通会员吗？",function(r){
			
			if(r===true){

				me.invokeService("openMember", [customerId],function(data) {
					if(data){
						IMessageBox.toast('开通成功');
						me.reload();
						layer.closeAll();
					}else{
						IMessageBox.toast('开通失败,稍后再试');
					}
				});
			}
		});
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
	remark:function(id){
		//state:0  ；只更新内容
		var me = this;
		me.doAbnormalPopup(id,0,1);
	},
	doAbnormalPopup : function(id,state,type) {
		var me = this;
		PandaHelper.openDynamicForm({
			title:'抽查',
			width:450,
			height:300,
			items:[{id:'txtNote',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:''}
			],
			callback:function(index, layero){
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入内容');
					return false;
				}
				me.doAbnormal(id,state,getNote,type);
			}
		});
	},
	doAbnormal : function(id,state,getNote,type) {
		var me = this;
		this.invokeService("abnormal", [id,state,getNote,type],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	}
});


function getSupplierOption(){
	
	var supplierOption = {columns : [ [ {
			field : 'name',
			title : '名称',
			width : 100
		}] ],
		url : '\/panda\/rest\/reference?code=CRM_Supplier&filter=',
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
			
		}};
	
	return supplierOption;
}

function getDepartmentOption(){
	
	var departmentOption = {columns : [ [ {
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
			
		}};
	return departmentOption;
}

function getEmployeeOption(){
	
	var employeeOption = {columns : [ [ {
			field : 'name',
			title : '名称',
			width : 100
		}] ],
		url : '\/panda\/rest\/reference?code=Employee&filter=',
		idField : 'id',
		textField : 'name',
		width : 300,
		fitColumns : true,
		panelWidth : 450,
		panelHeight : 310,
		pagination : true,
		pageSize : 10,
		mode : 'remote',
		multiple : false};
	
	return employeeOption;
}



