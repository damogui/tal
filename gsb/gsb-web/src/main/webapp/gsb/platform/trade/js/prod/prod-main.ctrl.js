System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ProdMainCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.orderProdId = null;
    	this.orderProd = null;
    },
    init:function(){
    	
 		this.orderProdId = System.Url.getParameter('id');
 		
 		//1.订单明细
 		this.initOrderProd(this.orderProdId);
 		
 		//2.订单跟进
		traceCtrl = new com.gongsibao.trade.web.ProdTraceCtrl();
		traceCtrl.mainCtrl = this;
		traceCtrl.init(this.orderProdId);
		
		//3.负责人
		principalCtrl = new com.gongsibao.trade.web.ProdPrincipalCtrl();
		principalCtrl.mainCtrl = this;
		principalCtrl.init(this.orderProdId);
		
		
    },
    initOrderProd:function(orderProdId){
    	
    	var me = this;
    	this.invokeService ("getOrderProdById", [orderProdId], function(data){
    		
    		me.orderProd = data;
    		me.bindData(data);
    	});
    },
    bindData:function(data){
    	
    	//订单编号
    	var orderNo = 100000000+data.orderId;
    	$("#orderNo").text(orderNo);
    	
    	//订单明细编号
    	$("#orderProdNo").text(data.orderId);
    	
    	//公司名称
    	var companyName = data.companyIntention != null ? data.companyIntention.name :'暂无';
    	$("#companyName").text(companyName);
    	
    	//办理名称
    	$("#handleName").text(data.handleName || '暂无');
    	
    }
});