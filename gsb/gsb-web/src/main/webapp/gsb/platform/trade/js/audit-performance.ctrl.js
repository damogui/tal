System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditPerformanceCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.audit.AuditPerformanceController';
    },
    initData:function(){

    }
});
