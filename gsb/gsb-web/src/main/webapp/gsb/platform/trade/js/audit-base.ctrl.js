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
    	
		var centerHeight = $('body').height() - 240;
		$('#center').height(centerHeight);	
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});	
    },
    initData:function(){
    	
    	//子类重写
    },
    approved:function(){
    	alert("tongguo")
    	//审核通过
    	
    	//弹出确认提交窗
    	this.doApproved();
    },
    doApproved:function(){
    	
     	var me = this;
    	var auditLogId = this.queryString('id');
    	if(System.isnull(auditLogId)){
    		
    		return;
    	}
    	
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */
    	this.invokeService ("approved", [auditLogId], function(data){
    		
    		//后续处理
    		
    	});
    },
    rejected:function(){
    	alert("butongguo")
    	//驳回
    	
    	//这里有弹出填写驳回原因的窗口，校验
    	this.doRejected();

    },
    doRejected:function(){
    	
    	var me = this;
    	var auditLogId = this.queryString('id');
    	if(System.isnull(auditLogId)){
    		
    		return;
    	}
    	
    	var remark = $('#remark').val();
    	/*
    	 * 具体有哪些参数，目前未知，主要是要看列表上能传入什么参数
    	 * 1.orderId
    	 * 2.....
    	 */
    	this.invokeService ("rejected", [auditLogId], function(data){
    		
    		//后续处理
    		
    	});
    }
});
