System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.CustomerCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	var orderId = this.mainCtrl.orderProd.orderId;
    	var me = this;
    	this.invokeService ("getCustomerByOrderId", [orderId], function(data){
    		
    		me.bindData(data);
    	});
    },
    bindData:function(data){
    	
    	if(data){
    		
    		$("#customer_name").text(data.realName||'-');
    		$("#customer_mobile").text(data.mobile||'-');
    		$("#customer_weixin").text(data.weixin||'-');
    		$("#customer_telephone").text(data.telephone||'-');
    		$("#customer_qq").text(data.qq||'-');
    		$("#customer_email").text(data.email||'-');
    		$("#customer_address").text(data.addr||'-');
    	}
    }
});