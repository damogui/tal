System.Declare("com.gongsibao.trade.web.audit");
com.gongsibao.trade.web.audit.AuditStageCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.audit.AuditStageController';
    },
    initData:function(){

    },
    auditStage : function(id){
    	alert(123);
    }
});
