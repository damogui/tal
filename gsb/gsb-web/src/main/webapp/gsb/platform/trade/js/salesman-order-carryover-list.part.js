System.Declare("com.gongsibao.trade.web");
//我的订单-我的结转业绩
com.gongsibao.trade.web.SalesmanOrderCarryoverListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.addCarryOverUrl = '/nav/gsb/platform/trade/auditCarryover';//结转审核jsp
    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var orderId = row.formOrderId;
    	var contentUrl = this.addCarryOverUrl + "?carryoverId=" + id + "&id=" + orderId;
         layer.open({
             type: 2,//1是字符串 2是内容
             title: '查看结转',
             fixed: false,
             maxmin: true,
             shadeClose: true,
             area: ['95%', '95%'],
             zIndex: 100000,
             id: "carryOverIframe",
             content: contentUrl
         });
    }
});



