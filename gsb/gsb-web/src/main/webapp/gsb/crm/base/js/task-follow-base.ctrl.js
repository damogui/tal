System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.TaskFollowCtrl = System.Object.Extends({
	ctor : function() {
		
	},
	/**
	 * 任务跟进，验证跟进时间
	 * @returns
	 */
	followTimeValida:function (code){
		//跟进时间验证
		var timeResult = true;
		var timeRequired = "XA0A1A2A3A4B1B2";
		if(timeRequired.indexOf(code)>-1){
			//必填
			timeResult = false;
		};
		return timeResult;
	},
	/**
	 * 任务跟进，验证签单金额
	 * @returns
	 */
	followAmountValida:function (code){
		//签单金额验证
		var amountResult = true;
		var amountRequired = "A0A1A2A3A4B1";
		if(amountRequired.indexOf(code)>-1){
			//必填
			amountResult = false;
		};
		return amountResult;
	},
	/**
	 * 任务跟进，验证内容
	 * @returns
	 */
	followNoteValida:function (code){
		//内容验证
		var noteResult = true;
		var noteRequired = "B1B3C1C2C3D1D2";
		if(noteRequired.indexOf(code)>-1){
			//必填
			noteResult = false;
		};
		return noteResult;
	},
	/**
	 * 任务跟进，验证意向产品
	 * @returns
	 */
	followProductValida:function (code){
		var productResult = true;
		var productRequired = "A0A1A2A3A4B1B3C1C2C3";
		if(productRequired.indexOf(code)>-1){
			//必填
			productResult = false;
		};
		return productResult;
	},
	getIntentionOption:function (taskId){
		
		var result;
		var me = this;
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
				var grid = $('#allot_intention_name').combogrid('grid');
		    	var row = grid.datagrid('getSelected');	
		    	var code = row.code;
		    	
		    	var followProduct = me.followProductValida(code);
		    	if(!followProduct){
					var serviceLocator = new org.netsharp.core.JServiceLocator();
					var service = "com.gongsibao.crm.web.TaskFollowCtrl";
					serviceLocator.invoke(service, 'hasProduct', [taskId], function(data){
						if(!data){
							layer.msg('此类质量的任务必须添加意向产品');
						}
					}, null, true);
		    	}
			}};
		
		return intentionOption;
	},
	open:function(taskId,customerId,callback){
		
		var me = this;
    	var intentionOption = this.getIntentionOption(taskId);
    	PandaHelper.openDynamicForm({
			title:'任务跟进',
			width:500,
			height:450,
			items:[{id:'allot_intention_name',
				title:'任务质量',
				type:'combogrid',
				className:'',
				option:intentionOption},
					
				{id:'nextFoolowTime',
					title:'下次跟进时间',
					type:'datebox',
					className:'',
					option:{width:300,disabled:true}
				},
					 
				{id:'amount',
					title:'估计签单金额',
					type:'numberbox',
					className:'',
					option:{width:300,disabled:true}
				},	
					 
				{id:'follow_content',
					title:'内容',
					type:'textarea',
					height:130,
					width:300,
					className:''}
			],
			explain:'',
			notice:'',
			callback:function(index, layero){

				var qualityId = $('#allot_intention_name').combogrid('getValue');
				if(System.isnull(qualityId)){
					layer.msg('此类质量的任务必须添加意向产品');
					return false;
				}
				var grid = $('#allot_intention_name').combogrid('grid');
		    	var row = grid.datagrid('getSelected');	
		    	var code = row.code;
		    	var score = row.score;//当前质量的分值
		    	
		    	var nextFoolowTime = $("#nextFoolowTime").datebox('getValue');
		    	var timeRequired = me.followTimeValida(code);
		    	if(!timeRequired && System.isnull(nextFoolowTime)){
		    		layer.msg('请添加下次跟进时间');
					return false;
		    	};
		    	

		    	var estimateAmount = $("#amount").numberbox('getValue');
		    	var amountRequired =  me.followAmountValida(code);
		    	if(!amountRequired && System.isnull(estimateAmount)){
		    		layer.msg('请添加估计签单金额');
					return false;
		    	};
		    	
		    	
				var contentRequired =  me.followNoteValida(code);
				var content = $("#follow_content").val();
				if (!contentRequired && System.isnull(content)) {
					layer.msg('请添加内容');
					return false;
				};
				
				var taskFollowObj = {
						
						taskId:taskId,
						score:score,
						customerId:customerId,
						qualityId:qualityId,
						nextFoolowTime:nextFoolowTime,
						estimateAmount:estimateAmount,
						content:content
				};
				//封装的Ajax
				var serviceLocator = new org.netsharp.core.JServiceLocator();
				var service = "com.gongsibao.crm.web.TaskFollowCtrl";
				serviceLocator.invoke(service, 'follow', [taskFollowObj], function(data){
					
					//提示跟进成功，关闭当前窗口
					layer.msg('提交成功！');
					layer.close(index);
					
					if(callback){
						
						callback(index, layero);
					}
					
				}, null, true);
			}
		});
    
	}
});

