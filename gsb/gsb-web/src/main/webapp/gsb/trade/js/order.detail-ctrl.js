System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.BaseCtrl = System.Object.Extends({
    ctor: function () {
    	
    	this.service = 'com.gongsibao.trade.web.OrderDetailController';
    },
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var me = this;
        var thisCallback = function (data) {
        	
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        serviceLocator.invoke(this.service, method, pars, thisCallback, null, isAsyn, errorCallback);
    }
});

/*
 * 订单产品明细
 */
com.gongsibao.trade.web.OrderProductDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单回款记录
 */
com.gongsibao.trade.web.OrderPaymentCollectionDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单退款明细
 */
com.gongsibao.trade.web.OrderRefundDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单改价明细
 */
com.gongsibao.trade.web.OrderChangePriceDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 优惠明细
 */
com.gongsibao.trade.web.OrderDiscountDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单合同
 */
com.gongsibao.trade.web.OrderContractCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单任务明细
 */
com.gongsibao.trade.web.OrderTaskDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});

/*
 * 订单流转日志
 */
com.gongsibao.trade.web.OrderFollowDetailCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    }
});