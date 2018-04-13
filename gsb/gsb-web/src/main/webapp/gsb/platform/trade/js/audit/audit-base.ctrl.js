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
		PandaHelper.openDynamicForm({
			title:'审核通过原因',
			width:400,
			height:300,
			items:[{id:'auditRemark',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
	            className:''}
			],
			callback:function(index, layero){
				var getAuditRemark = $("#auditRemark").val();
				me.doApproved(getAuditRemark,function(s){
					callback(s);
				});
			}
		});
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
    rejected:function(callback){
    	//这里有弹出填写驳回原因的窗口，校验
    	var me = this;
		PandaHelper.openDynamicForm({
			title:'审核不通过原因',
			width:400,
			height:300,
			items:[{id:'auditRemark',
				title:'内容',
				type:'textarea',
				height:130,
				width:300,
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
    }
});
