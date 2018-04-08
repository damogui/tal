System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.TrailCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	alert(orderProdId);
    }
});