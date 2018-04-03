System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ProdMainCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.orderProdId = null;
    },
    init:function(){
    	
 		var orderProdId = System.Url.getParameter('id');
		traceCtrl = new com.gongsibao.trade.web.ProdTraceCtrl();
		traceCtrl.init(orderProdId);
    }
});