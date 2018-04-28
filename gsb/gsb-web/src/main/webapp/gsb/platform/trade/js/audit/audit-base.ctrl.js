System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditBaseCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    init:function(){

    	this.initStyle();
    	this.initData();
    },
    initStyle:function(){
    	
		var centerHeight = $('body').height() - 200;
		$('#center').height(centerHeight);
    },
    initData:function(){
    	
    	//子类重写
    },
    approved:function(callback){
    	//这里有弹出填写驳回原因的窗口，校验
    	var me = this;
		if(this.auditType == 'refund'){
			PandaHelper.openDynamicForm({
				title:'审核通过原因',
				width:520,
				height:350,
				items:[{id:'settlementMethod',
					title:'结算方式',
					type:'combobox',
					className:'',
					option:{
						valueField:'value',
						textField:'text',
						value: '1',
						url:'/panda/rest/enum?name=com.gongsibao.entity.trade.dic.RefundSettlementMethodType'}
					},{id:'auditRemark',
					title:'内容',
					type:'textarea',
					height:150,
					width:350,
		            className:''}
		            
				],
				callback:function(index, layero){
					var getAuditRemark = $("#auditRemark").val();
					var settlementMethod = $("#settlementMethod").combobox('getValue');
					debugger;
					me.doApprovedRefund(getAuditRemark,settlementMethod,function(s){
						callback(s);
					});
				}
			});
    	}else{
    		PandaHelper.openDynamicForm({
				title:'审核通过原因',
				width:520,
				height:330,
				items:[{id:'auditRemark',
					title:'内容',
					type:'textarea',
					height:150,
					width:350,
		            className:''}
				],
				callback:function(index, layero){
					var getAuditRemark = $("#auditRemark").val();
					me.doApproved(getAuditRemark,function(s){
						callback(s);
					});
				}
			});
    	}
    	//弹出确认提交窗
    },
    doApproved:function(remark,callback){    	
     	var me = this;
    	var auditLogId = this.queryString('auditId');
    	if(System.isnull(auditLogId)){    		
    		return;
    	}    	
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */    	
    	this.invokeService ("approved", [auditLogId,remark], function(data){
    		
    		//后续处理
    		IMessageBox.info('操作成功！',function(s){
    			window.parent.layer.closeAll();
    			callback(data);
    		});
    	});
    },
    doApprovedRefund:function(remark,settlementMethod,callback){    	
     	var me = this;
    	var auditLogId = this.queryString('auditId');
    	if(System.isnull(auditLogId)){    		
    		return;
    	}    	
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */    	
    	this.invokeService ("approvedRefund", [auditLogId,remark,settlementMethod], function(data){
    		
    		//后续处理
    		IMessageBox.info('操作成功！',function(s){
    			window.parent.layer.closeAll();
    			callback(data);
    		});
    	});
    },
    rejected:function(callback){
    	//这里有弹出填写驳回原因的窗口，校验
    	var me = this;
    	if(this.auditType == 'refund'){
    		PandaHelper.openDynamicForm({
    			title:'审核不通过原因',
    			width:520,
    			height:350,
    			items:[{id:'settlementMethod',
					title:'结算方式',
					type:'combobox',
					className:'',
					option:{
						valueField:'value',
						textField:'text',
						value: '1', 
						url:'/panda/rest/enum?name=com.gongsibao.entity.trade.dic.RefundSettlementMethodType'}
					},{id:'auditRemark',
    				title:'内容',
    				type:'textarea',
    				height:150,
    				width:350,
    	            className:''}
    			],    			
    			callback:function(index, layero){
    				var getAuditRemark = $("#auditRemark").val();
    				var settlementMethod = $("#settlementMethod").combobox('getValue');
    				if (System.isnull(getAuditRemark)) {
    					layer.msg('请输入审核不通过原因');
    					return false;
    				}
    				me.doRejectedRefund(getAuditRemark,settlementMethod,function(s){
    					callback(s);
    				});
    			}
    		});
    	}else{
    		PandaHelper.openDynamicForm({
    			title:'审核不通过原因',
    			width:520,
    			height:330,
    			items:[{id:'auditRemark',
    				title:'内容',
    				type:'textarea',
    				height:150,
    				width:350,
    	            className:''}
    			],
    			callback:function(index, layero){
    				var getAuditRemark = $("#auditRemark").val();
    				if (System.isnull(getAuditRemark)) {
    					layer.msg('请输入审核不通过原因');
    					return false;
    				}
    				me.doRejected(getAuditRemark,function(s){
    					callback(s);
    				});
    			}
    		});
    	}
    },
    doRejected:function(remark,callback){
    	var me = this;
    	var auditLogId = this.queryString('auditId');
    	if(System.isnull(auditLogId)){
    		return;
    	}
    	
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */
    	this.invokeService ("rejected", [auditLogId,remark], function(data){
    		
    		//后续处理
    		IMessageBox.info('操作成功！',function(s){
    			window.parent.layer.closeAll();
    			callback(s);
    		});
    	});
    },
    doRejectedRefund:function(remark,settlementMethod,callback){
    	var me = this;
    	var auditLogId = this.queryString('auditId');
    	if(System.isnull(auditLogId)){
    		return;
    	}
    	
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */
    	this.invokeService ("rejectedRefund", [auditLogId,remark,settlementMethod], function(data){
    		
    		//后续处理
    		IMessageBox.info('操作成功！',function(s){
    			window.parent.layer.closeAll();
    			callback(s);
    		});
    	});
    },
    initauditLog: function(formId){

    	var me = this;
    	this.invokeService("getAuditLogList", [formId,1046], function(data){
    		$('#audit_progress_grid').datagrid({
    			idField:'id',
    			emptyMsg:'暂无记录',
    			striped:false,
    			pagination:false,
    			showFooter:true,
    			singleSelect:true,
    			height:'100%',
    			data:data,
				onLoadSuccess: function(data){
					var mark=1;
					for (var i=1; i <data.rows.length; i++) {
						if (data.rows[i]['level'] == data.rows[i-1]['level']) {
							mark += 1;                                            
							$(this).datagrid('mergeCells',{ 
								index: i+1-mark,
								field: 'level',
								rowspan:mark
							}); 
						}else{
							mark=1;
						}
					}
				},
    		    columns:[[
    		        {field:'level',title:'顺序',width:80,align:'center',formatter: function(value,row,index){
    		        	return value+1;
    		        }},
    		        {field:'creatorId',title:'审核人',width:80,align:'center',formatter: function(value,row,index){
    		        	
    		        	if(row.employee){

        		        	return row.employee.name;
    		        	}
    		        }},
    		        {field:'status',title:'审核状态',width:80,align:'center',formatter: function(value,row,index){
    		        	return me.auditLogStatusEnum[value];
    		        },styler: function(value,row,index){

    	   				if(value == 1052){
        					
        					//审核中
            				return 'color:#25C6FC;';
            				
        				}else if(value == 1053){
        					
        					//驳回审核
        					return 'color:#E03636;';
        					
        				}else if(value == 1054){
        					
        					//审核通过
        					return 'color:#009966;';
        					
        				}else if(value == 1055){
        					
        					//审核排队
        					return 'color:#003399;';
        					
        				}
    				}},
    		        {field:'createTime',title:'创建时间',width:150,align:'center'},
    		        {field:'content',title:'审批内容',width:150,align:'left'},
    		        {field:'remark',title:'说明',width:300,align:'center'}
    		    ]]
    		});
    	});
    }
});