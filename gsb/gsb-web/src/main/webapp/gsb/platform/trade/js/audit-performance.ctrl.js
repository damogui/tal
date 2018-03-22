System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditPerformanceCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.audit.AuditPerformanceController';
    },
    init:function () {

        var id = this.queryString('id');
        alert(id);
        this.initGrid1();
        this.initGrid2();
    },
    initGrid1:function(){

    },
    initGrid2:function(){

    }
});
