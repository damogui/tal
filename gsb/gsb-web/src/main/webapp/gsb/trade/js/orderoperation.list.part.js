System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderOperationController = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    batchTransferWeb : function(id) {
		/*var me = this;
		this.invokeService("querySoOrderTraceList", [id], function(data) {
			var html = me.pj(data);
        	window.top.layer.open({
      		  type: 1,
      		  title: '办理进度',
      		  fixed: false,
      		  maxmin: true,
      		  shadeClose:true,
      		  area: ['600px', '400px'],
      		  content: html
        	});
		});*/
	}
});