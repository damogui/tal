System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.TaskFollowCtrl = System.Object.Extends({
	ctor : function() {
		
		this.type = 'list';
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
			required:true,
			fitColumns : true,
			panelWidth : 450,
			panelHeight : 310,
			pagination : true,
			pageSize : 20,
			mode : 'remote',
			multiple : false,
			onChange : function(newValue, oldValue) {
				
				var grid = $('#allot_intention_name').combogrid('grid');
		    	var row = grid.datagrid('getSelected');
		    	if(row == null){
		    		
		    		return;
		    	}
		    	
		    	var nextFoolowDateRequired = row.nextFoolowDateRequired;
		    	var returnedAmountRequired = row.returnedAmountRequired;
		    	var signingAmountRequired = row.signingAmountRequired;
		    	var contentRequired = row.contentRequired;
		    	var productRequired = row.productRequired;
		    	var districtRequired = row.districtRequired;
		    	
		    	if(nextFoolowDateRequired === true){
		    		
		    		$('#nextFoolowTime').datebox('enableValidation');
		    	}else{
		    		$('#nextFoolowTime').datebox('disableValidation').datebox('clear');
		    	}
		    	
		    	if(returnedAmountRequired === true){
		    		
		    		$('#returnedAmount').numberbox('enableValidation').numberbox('enable');
		    	}else{
		    		$('#returnedAmount').numberbox('disableValidation').numberbox('disable').numberbox('clear');
		    	}
		    	
		    	if(signingAmountRequired === true){
		    		
		    		$('#signingAmount').numberbox('enableValidation').numberbox('enable');
		    	}else{
		    		$('#signingAmount').numberbox('disableValidation').numberbox('disable').numberbox('clear');
		    	}
		    	
		    	if(contentRequired === true){
		    		
		    		$('#follow_content').validatebox('enableValidation');
		    	}else{
		    		$('#follow_content').validatebox('disableValidation');
		    		$('#follow_content').val('');
		    	}

		    	//校验意向产品
		    	var productRequired = row.productRequired;
		    	if(productRequired ===true){
		    		
		    		var isHasProduct = me.verifyHasProduct(taskId);
		    		if(!isHasProduct){

		    			if(me.type == 'list'){

			    			layer.confirm('此类质量的商机必须添加意向产品,是否立即添加？', {
			    				  btn: ['立即添加', '取消']
			    				}, function(index, layero){
			    				  
			    					window.open('/panda/crm/salesman/task/edit?id='+taskId);
			    					layer.close(index);
			    				});
		    			}else{
		    				
			    			layer.confirm('此类质量的商机必须添加意向产品,是否立即添加？', {
			    				  btn: ['立即添加', '取消']
			    				}, function(index, layero){
			    				  
				    				layer.closeAll();
			    			});
		    			}
		    			$('#allot_intention_name').combogrid('clear');
			    		return false;
		    		}
		    	}
		    	
		    	//校验意向地区
		    	var districtRequired = row.districtRequired;
		    	if(districtRequired ===true){
		    		
		    		var ishasDistrict = me.verifyHasDistrict(taskId);
		    		if(!ishasDistrict){

		    			if(me.type == 'list'){
		    				
			    			layer.confirm('此类质量的商机必须添加意向地区,是否立即添加？', {
			    				  btn: ['立即添加', '取消']
			    				}, function(index, layero){
			    				  
			    					window.open('/panda/crm/salesman/task/edit?id='+taskId);
			    					layer.close(index);
			    			});
		    			}else{
		    				
			    			layer.confirm('此类质量的商机必须添加意向地区,是否立即添加？', {
			    				  btn: ['立即添加', '取消']
			    				}, function(index, layero){
			    				  
			    					layer.closeAll();
			    			});
		    			}

		    			$('#allot_intention_name').combogrid('clear');
			    		return false;
		    		}
		    	}
		    	
		    	//查询估计签单金额、 估计回款金额 
		    	if(returnedAmountRequired === true && signingAmountRequired === true){

			    	var serviceLocator = new org.netsharp.core.JServiceLocator();
			 		me.invokeService('getSigningAmount',[taskId],function(data){
						
						if(data && data>0){
							
							$('#signingAmount').numberbox('setValue',data);
							$('#returnedAmount').numberbox('setValue',data);
						}
					});
		    	}
				
		    	return true;
			}};
		
		return intentionOption;
	},
	verifyHasProduct:function(taskId){
		
		var isHasProduct = true;
		this.invokeService('hasProduct',[taskId],function(data){
			
			isHasProduct = data;
		},false);
		
		return isHasProduct;
	},
	
	verifyHasDistrict:function(taskId){
		
		var ishasDistrict = true;
		this.invokeService('hasDistrict',[taskId],function(data){
			
			ishasDistrict = data;
		},false);
		return ishasDistrict;
	},
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

        var me = this;
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var thisCallback = function (data) {
        	
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        var service = "com.gongsibao.crm.web.TaskFollowCtrl";
        serviceLocator.invoke(service, method, pars, thisCallback, null, isAsyn, errorCallback);
    },
    
	open:function(taskId,customerId,originalQualityId,callback){
		var me = this;
    	var intentionOption = this.getIntentionOption(taskId);
    	PandaHelper.openDynamicForm({
			title:'商机跟进',
			width:500,
			height:480,
			items:[{id:'allot_intention_name',
				title:'商机质量',
				type:'combogrid',
				className:'',
				option:intentionOption},
					
				{id:'nextFoolowTime',
					title:'下次跟进时间',
					type:'datebox',
					className:'',
					option:{width:300,required:true,editable:false}
				},
				
				{id:'signingAmount',
					title:'估计签单金额',
					type:'numberbox',
					style:'text-align:right;',
					className:'',
					option:{width:300,required:true,disabled:true}
				},	
				
				{id:'returnedAmount',
					title:'估计回款金额',
					type:'numberbox',
					style:'text-align:right;',
					className:'',
					option:{width:300,required:true,disabled:true}
				},	
					 
				{id:'follow_content',
					title:'跟进内容',
					type:'textarea',
					height:100,
					width:300,
					className:'easyui-validatebox',
					option:{required:true,validType:['maxLength[500]']}
				}
			],
			callback:function(index, layero){
				var validate = $('#dynamicForm').form('validate');
				if(!validate){
					
					return;
				}
				
				
				var qualityId = $('#allot_intention_name').combogrid('getValue');
				
				var grid = $('#allot_intention_name').combogrid('grid');
		    	var row = grid.datagrid('getSelected');
		    	var score = row.score;//当前质量的分值
		    	
		    	var nextFoolowTime = $("#nextFoolowTime").datebox('getValue');
		    	
		    	var returnedAmount = $("#returnedAmount").numberbox('getValue');
		    	
		    	var signingAmount = $("#signingAmount").numberbox('getValue');
		    	
				var content = $("#follow_content").val();
				debugger;
				var taskFollowObj = {
						
						taskId:taskId,
						score:score,
						customerId:customerId,
						qualityId:qualityId,
						nextFoolowTime:nextFoolowTime,
						signingAmount:signingAmount,
						returnedAmount:returnedAmount,
						content:content
				};
				
				var serviceLocator = new org.netsharp.core.JServiceLocator();
				var service = "com.gongsibao.crm.web.TaskFollowCtrl";
				serviceLocator.invoke(service, 'follow', [taskFollowObj,originalQualityId], function(data){
					
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