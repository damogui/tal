System.Declare("com.gongsibao.trade.web");
//订单审核-退款审核
com.gongsibao.trade.web.AuditRefundListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditRefundUrl = '/nav/gsb/platform/trade/auditRefund';//退款审核jsp
    },
    auditRefund: function (id) {//订单审核-退款审核 
        var contentUrl = this.auditRefundUrl + "?id=" + id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '退款审核',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['50%', '70%'],
            zIndex: 100000,
            id: "auditRefundIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditRefundIframe').firstElementChild.contentWindow.auditRefundCtrl.approved();
            },
            btn2: function (){
            	document.getElementById('auditRefundIframe').firstElementChild.contentWindow.auditRefundCtrl.rejected();
            }
        });

    },
    detail : function(id){
    	var contentUrl = this.auditRefundUrl + "?id=" + id;
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



