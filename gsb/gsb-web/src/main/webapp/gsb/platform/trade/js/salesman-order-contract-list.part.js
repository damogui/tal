System.Declare("com.gongsibao.trade.web");
//我的订单-我的分期
com.gongsibao.trade.web.SalesmanOrderContractListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditContractUrl = '/panda/trade/audit/contract/form';
    },
    detail: function (id) {
        var contentUrl = this.auditContractUrl + "?id=" + id + "";
        layer.open({
            id: "contractCreateIframe",
            type: 2,//1是字符串 2是内容
            title: '合同信息',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            content: contentUrl
        });
    }
});



