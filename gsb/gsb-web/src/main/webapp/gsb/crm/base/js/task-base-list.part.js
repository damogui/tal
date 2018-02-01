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
		this.edit(row.id);
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
		/*var me = this;
		var url = this.followUrl +"?fk=taskId:"+id;
		IMessageBox.open("任务跟进", url, 700, 450, function() {
			me.reload();
		});
		return;*/
		var me = this;
		var intentionOption = getIntentionOption();
		PandaHelper.openDynamicForm({
			title:'任务跟进',
			width:560,
			height:480,
			items:[{id:'allot_intention_name',
				title:'任务质量',
				type:'combogrid',
				className:'',
				option:intentionOption,
				width:170},
					
				{id:'followTime',
					title:'下次跟进时间',
					type:'datebox',
					className:'',
					disabled:true},
					 
				{id:'amount',
					title:'估计签单金额',
					type:'numberbox',
					className:''},	
					 
				{id:'txtNote',
					title:'内容',
					type:'textarea',
					height:130,
					width:300,
					className:''}
			],
			explain:'',
			notice:'',
			callback:function(index, layero){
				var g = $('#allot_intention_name').combogrid('grid');
		    	var r = g.datagrid('getSelected');	
		    	var code = r.code;
		    	var time = followTimeValida(code);
		    	var amount = followAmountValida(code);
		    	
		    	if(!time && System.isnull($("#followTime").val())){
		    		IMessageBox.info('请输入下次跟进时间');
					return false;
		    	};
		    	if(!amount && System.isnull($("#amount").val())){
		    		IMessageBox.info('请输入估计签单金额');
					return false;
		    	};
				var getNote = $("#txtNote").val();
				if (System.isnull(getNote)) {
					IMessageBox.info('请输入内容');
					return false;
				};
				//me.doRollBackService(id,getNote,'regain');
			}
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
				me.invokeService("allocation", [taskId,supplierId,departmentId,toUserId],function(data) {
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
		me.doRegain(id);
	},
	doRegain : function(id) {
		var me = this;
		PandaHelper.openDynamicForm({
			title:'收回任务',
			width:500,
			height:400,
			items:[{id:'txtNote',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:''}
			],
			explain:'任务将会退回至【公海】，进行【二次分配】',
			notice:'',
			callback:function(index, layero){
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
		//任务退回
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
			title:'退回任务',
			width:500,
			height:400,
			items:[{id:'txtNote',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:''}
			],
			explain:'任务将会退回至【公海】，进行【二次分配】',
			notice:customerQuality(intenCategory),
			callback:function(index, layero){
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


function getIntentionOption(){
	var intentionOption = {columns : [ [ {
			field : 'intentionCategory',
			title : '分类',
			width : 30
		},{
			field : 'code',
			title : '编码',
			width : 30
		},{
			field : 'name',
			title : '名称',
			width : 50
		}] ],
		url : '\/panda\/rest\/reference?code=NCustomerTaskQuality',
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
	
	return intentionOption;
}

/**
 * 任务跟进，验证跟进时间
 * @returns
 */
function followTimeValida(code){
	//跟进时间验证
	var timeResult = true;
	var timeRequired = "XA0A1A2A3A4B1B2";
	if(timeRequired.indexOf(code)>-1){
		//必填
		timeResult = false;
	};
	return timeResult;
}
/**
 * 任务跟进，验证签单金额
 * @returns
 */
function followAmountValida(code){
	//签单金额验证
	var amountResult = true;
	var amountRequired = "A0A1A2A3A4B1";
	if(amountRequired.indexOf(code)>-1){
		//必填
		amountResult = false;
	};
	return amountResult;
}


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
			var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE supplier_id ____ ----'+ newValue + '----)';
			options.url = '\/panda\/rest\/reference?code=Employee&filter='+ filter;
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
			var filter = ' id IN ( SELECT employee_id FROM sp_salesman WHERE department_id ____ ----'+ newValue + '----)';
			options.url = '\/panda\/rest\/reference?code=Employee&filter='+ filter;
			$(grid).datagrid(options);
		}};
	return departmentOption;
}

function getEmployeeOption(){
	var employeeOption = {columns : [ [ {
			field : 'name',
			title : '名称',
			width : 100
		}] ],
		url : '\/panda\/rest\/reference?code=Employee&filter= id IN ( SELECT employee_id FROM sp_salesman)',
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
		return '提示：请慎用！执行退回后该任务将不会再分配给你，如果只是需要将任务转给同事或者下属，请使用【任务转移】功能！';
	}
}