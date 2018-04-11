System.Declare("com.gongsibao.crm.web");
//所有商机列表基类
com.gongsibao.crm.web.BaseTaskListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
		this.addUrl = null;
		this.editUrl = null;
		this.followUrl = null;
		
		this.addCustomerUrl = null;
	},
	addCustomer:function(){
		
		window.open(this.addCustomerUrl);
	},
	addOrder:function(){
		
		var row = this.getSelectedItem();
		if(row){
			var taskId = row.id ||'';
			var customerId = row.customerId ||'';
			var accountId = row.customer_accountId ||'';
			var mobile = row.customer_mobile ||'';
			//var important = row.customer_important ||'';
			var ps = new Array();
			ps.push('taskId='+taskId);
			ps.push('customerId='+customerId);
			ps.push('accountId='+accountId);
			ps.push('mobile='+mobile);
			var urlParameter = ps.join('&');
			var url = '/panda/crm/order/salesman/add?'+urlParameter;
			window.open(url);
		}
	},
	add:function(){
		var row = this.getSelectedItem();
		if(row){

			var customerId = row.customerId;
			var url = this.addUrl+"?fk=customerId:"+customerId;
	    	layer.open({
	    		  type: 2,
	    		  title: '新增商机',
	    		  fixed: false,
	    		  maxmin: true,
	    		  shadeClose:true,
	    		  area: ['90%','90%'],
	    		  content: url
	    	});
		}
	},
	detail:function(id){
		
		this.edit(id);
	},
	doubleClickRow : function(index, row) {
		this.edit(row.id);
	},
	edit : function(id) {
		
		$('#'+this.context.id).datagrid('unselectAll');
		$('#'+this.context.id).datagrid('selectRecord',id);
		var selectRow = this.getSelectedItem();
		var url = this.editUrl+"?taskId="+id+"&customerId="+selectRow.customerId;
    	layer.open({
    		  type: 2,
    		  title: '编辑商机',
    		  fixed: false,
    		  maxmin: true,
    		  shadeClose:true,
    		  area: ['90%','90%'],
    		  content: url
    	});
	},
	batchAllocation:function(){
		//商机批量分配
		var me = this;
		var ids = this.getSelectionIds();
		if(ids == "" || ids == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doAllot(ids);
	},
	allocation:function(id){
		//商机分配
		var me = this;
		me.doAllot(id);
	},
	doAllot : function(taskId) {
		var me = this;
		var supplierOption = getSupplierOption();
		var departmentOption = getDepartmentOption();
		var employeeOption = getEmployeeOption();
		PandaHelper.openDynamicForm({
			title:'商机分配',
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
				
				me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId],function(data) {
					me.reload();
					IMessageBox.toast('分配成功');
					layer.closeAll();
					return;
				});
			}
		});
	},
	follow : function(id) {		
		$("#" + this.context.id).datagrid('unselectAll');
		$("#" + this.context.id).datagrid('selectRecord',id);
		var me = this;
		var taskId = id;
		$('#'+this.context.id).datagrid('selectRecord',id);
		var selectedRow = $('#'+this.context.id).datagrid('getSelected');
		var customerId = selectedRow.customerId;
		var originalQualityId = selectedRow.qualityId;
		var taskFollowCtrl = new com.gongsibao.crm.web.TaskFollowCtrl();
		taskFollowCtrl.open(taskId,customerId,originalQualityId,function(index, layero){
			
			me.reload();
		});
	},
	doFollowService : function(id,getqualityId,time,amount,getNote) {
		var me = this;
		this.invokeService("follow", [id,getqualityId,time,amount,getNote],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	},
	regain : function(){
		//商机收回
		var me = this;
		var id = this.getSelectionIds();
		if(id == "" || id == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doRegain(id);
	},
	doRegain : function(id) {
		var me = this;
		PandaHelper.openDynamicForm({
			title:'收回商机',
			width:500,
			height:400,
			items:[{id:'txtNote',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:'',
	            option:{required:true,validType:['maxLength[500]']}
			}],
			explain:'商机将会释放至【公海】，进行【二次分配】',
			notice:'',
			callback:function(index, layero){
				
				var validate = $('#dynamicForm').form('validate');
				if(!validate){
					
					return;
				}
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入内容');
					return false;
				}
				me.doRollBackService(id,getNote,'regain');
			}
		});
	},
	rollback : function(id){
		//商机释放
		var me = this;
		//这里先要取消所有行，再选中1行
		$("#" + this.context.id).datagrid('unselectAll');
		$("#" + this.context.id).datagrid('selectRecord',id);
		var row = this.getSelectedItem();
		var intenCategory = row.intentionCategory;
		
		me.doRollBack(id,intenCategory);
	},
	doRollBack : function(id,intenCategory) {
		var me = this;
		PandaHelper.openDynamicForm({
			title:'释放商机',
			width:500,
			height:400,
			items:[{id:'txtNote',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:'',
	            option:{required:true,validType:['maxLength[500]']}
			}],
			explain:'商机将会释放至【公海】，进行【二次分配】',
			notice:customerQuality(intenCategory),
			callback:function(index, layero){
				
				var validate = $('#dynamicForm').form('validate');
				if(!validate){
					
					return;
				}
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入内容');
					return false;
				}
				me.doRollBackService(id,getNote,'rollback');
			}
		});
	},
	doRollBackService : function(id,getNote,functionName) {
		var me = this;
		this.invokeService(functionName, [id,getNote],function(data) {
			me.reload();
			IMessageBox.toast('操作成功');
			layer.closeAll();
			return;
		});
	},
	batchTransfer : function(){
		//商机批量转移
		var me = this;
		var id = this.getSelectionIds();
		if(id == "" || id == null ){
			IMessageBox.info('请选择记录');
			return;
	    }
		me.doTransfer(id);
	},
	transfer : function(id){
		//商机转移
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
			title:'商机转移',
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
	openMember : function(customerId,isSendSms){

		var me = this;
		IMessageBox.confirm("您确定要开通会员吗？",function(r){
			
			if(r===true){

				me.invokeService("openMember", [customerId,isSendSms],function(data) {
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
	            className:'',
	            option:{required:true,validType:['maxLength[500]']}
			}],
			callback:function(index, layero){
				
				var validate = $('#dynamicForm').form('validate');
				if(!validate){
					
					return;
				}
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
	},
	contactFormatter:function(value,row,index,typeName){
		
		if(value){
		  var ctrl = workspace.parts.byIndex(0).key;
		  return '<sapn>'+PandaHelper.dimString(value)+'</span><i class="fa fa-eye" onclick="'+ctrl+'.showPlaintext(\''+row.customerId+'\',\''+value+'\',\''+typeName+'\',this);"></i>';
		}
	},
	showPlaintext:function(customerId,value,typeName,obj){
		
		$(obj).parent().text(value);
		var serviceLocator = new org.netsharp.core.JServiceLocator();
		serviceLocator.invoke(this.context.service, "recordLookLog",[customerId,typeName]);
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
			//改变部门的查询条件
			$('#allot_department_name').combogrid('clear');
			var grid = $('#allot_department_name').combogrid('grid');
			var options = $(grid).datagrid('options');
			var filter = ' supplier_id ____ ----'+ newValue + '----';
			options.url = '\/panda\/rest\/reference?code=CRM_Supplier_Depart&filter='+ filter;
			$(grid).datagrid(options);
			//改变业务员的查询条件
	    	$('#allot_employee_name').combogrid('clear');
			var grid = $('#allot_employee_name').combogrid('grid');
			var options = $(grid).datagrid('options');
			//var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
			var filter = ' supplier_id ____ ----'+ newValue + '----';
			options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
			$(grid).datagrid(options);
			
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
			//改变业务员的查询条件
	    	$('#allot_employee_name').combogrid('clear');
			var grid = $('#allot_employee_name').combogrid('grid');
			var options = $(grid).datagrid('options');
			//var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
			var filter = ' department_id ____ ----'+ newValue + '----';
			options.url = '\/panda\/rest\/reference?code=Salesman&filter='+ filter;
			$(grid).datagrid(options);
		}};
	return departmentOption;
}

function getEmployeeOption(){
	var employeeOption = {columns : [ [ {
			field : 'supplier_name',
			title : '服务商',
			width : 100
		},{
			field : 'department_name',
			title : '部门',
			width : 100
		},{
			field : 'employee_name',
			title : '名称',
			width : 100
		},{
			field : 'receiving',
			title : '自动接受商机',
			width : 100,
			formatter : function(value,row,index){return value===false?'否':'是';}
		}] ],
		rowStyler: function(index,row){if(row.receiving ===false) {return 'color:red;';  }},
		url : '\/panda\/rest\/reference?code=Salesman&filter=',
		idField : 'employeeId',
		textField : 'employee_name',
		width : 300,
		fitColumns : true,
		panelWidth : 450,
		panelHeight : 310,
		pagination : true,
		pageSize : 10,
		mode : 'remote',
		multiple : false,
		onChange : function(newValue, oldValue) {
			/*if(oldValue!="" && newValue != oldValue){
			}*/
		}};
	
	return employeeOption;
}
/**
 * 判断客户质量ABX需要提示
 * @param intenCategory 
 * @returns {String}
 */
function customerQuality(intenCategory){
	
	if(intenCategory.indexOf("A") > -1 || intenCategory.indexOf("B") > -1 || intenCategory.indexOf("X") > -1){
		return '提示：请慎用！执行释放后该商机将不会再分配给你，如果只是需要将商机转给同事或者下属，请使用【商机转移】功能！';
	}
}