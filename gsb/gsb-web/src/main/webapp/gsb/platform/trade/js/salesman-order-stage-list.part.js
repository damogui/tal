System.Declare("com.gongsibao.trade.web");
//我的订单-我的分期
com.gongsibao.trade.web.SalesmanStagingListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditStageUrl = '/nav/gsb/platform/trade/auditStage';//分期审核jsp
    },
    detail : function(id){
    	var contentUrl = this.auditStageUrl + "?id=" + id;
    	  layer.open({
              type: 2,//1是字符串 2是内容
              title: '查看',
              fixed: false,
              maxmin: true,
              shadeClose: false,
              area: ['50%', '70%'],
              zIndex: 100000,
              content: contentUrl
          });
    },
    serviceNameFormatter : function(value,row,index){
    	
    }
});



