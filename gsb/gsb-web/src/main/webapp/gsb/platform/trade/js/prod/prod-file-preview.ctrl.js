System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.FilePreviewCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

		alert(orderProdId);
		
		//http://gsb-public.oss-cn-beijing.aliyuncs.com/hive/8234e2f5d93bdf436d3ea8d671ab7699.jpg
    }
});