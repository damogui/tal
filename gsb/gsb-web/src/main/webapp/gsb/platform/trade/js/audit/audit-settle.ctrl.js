System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditSettleCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
        this.base();
        this.service = 'com.gongsibao.trade.web.audit.AuditSettleController';
    },
    init:function(){
        this.initStyle();
        this.initData();
    },
    initData: function () {//重写数据
        var id = this.queryString('id');//payid
    }
});
