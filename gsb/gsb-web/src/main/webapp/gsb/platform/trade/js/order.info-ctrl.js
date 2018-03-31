System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderInfoCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderDetailController';
    	this.platformSourceTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
    	this.payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
    },
    init:function(){
    	
    	var me = this;
    	var orderId = this.queryString('id');
    	if(System.isnull(orderId)){
    		
    		return;
    	}
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
    	$('#addTime').text(soOrder.addTime||'-');
    	
    	$('#platformSource').text(this.platformSourceTypeEnum[soOrder.platformSource]||'-');
    	$('#payStatus').text(this.payStatusTypeEnum[soOrder.payStatus]);
    	var installmentMode = soOrder.installmentMode;
    	if(installmentMode){
    		
    		var ss = installmentMode.split('|');
    		var count = ss.length;
        	$('#installmentCount').text(count+'期');
    	}else{
    		
    		$('#installmentCount').text('-');
    	}
    	
    	$('#channelOrderNo').text(soOrder.channelOrderNo||'-');
    	
    	$('#remark').text(soOrder.remark||'-');
    }
});
$(function(){
	var orderInfoCtrl = new com.gongsibao.trade.web.OrderInfoCtrl();
	orderInfoCtrl.init();
});
