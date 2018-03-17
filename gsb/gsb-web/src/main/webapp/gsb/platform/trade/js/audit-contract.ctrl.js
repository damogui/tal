System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditContractCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.audit.AuditContractController';
    },
    initStyle:function(){
    	
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});	
    },
    initData:function(){

    }
});
