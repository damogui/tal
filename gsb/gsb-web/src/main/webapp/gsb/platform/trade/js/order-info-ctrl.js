System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderInfoCtrl = com.gongsibao.trade.web.BaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
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
    },
    queryString: function (name) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }
        return result[1];
    },
    init:function(){
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		me.bindData(data);
    	});
    },
    bindData:function(soOrder){
    	
    	$('#no').text(soOrder.no);
    	
    	var payablePrice=soOrder.payablePrice/100;
    	$('#payablePrice').text(payablePrice.toFixed(2));
    	
    	var paidPrice=(soOrder.paidPrice/100).toFixed(2);
    	$('#paidPrice').text(paidPrice);
    	
    	$('#accountName').text(soOrder.accountName);
    	$('#accountMobile').text(soOrder.accountMobile);
    	$('#addTime').text(soOrder.addTime);
    	
    	$('#platformSource').text(this.platformSourceTypeEnum[soOrder.platformSource]);
    	$('#payStatus').text(this.payStatusTypeEnum[soOrder.payStatus]);
    	var installmentMode = soOrder.installmentMode;
    	if(installmentMode){
    		
    		var ss = installmentMode.split('|');
    		var count = ss.length;
        	$('#installmentCount').text(count+'期');
    	}else{
    		
    		$('#installmentCount').text('-');
    	}
    	
    	$('#channelOrderNo').text(soOrder.channelOrderNo||'');
    	
    	$('#remark').text(soOrder.remark||'');
    }
});
$(function(){
	var orderInfoCtrl = new com.gongsibao.trade.web.OrderInfoCtrl();
	orderInfoCtrl.init();
});
