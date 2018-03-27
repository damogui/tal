System.Declare("com.gongsibao.trade.web");
//我的订单-我的分期
com.gongsibao.trade.web.SalesmanOrderInvoiceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditInvoiceUrl = '/panda/trade/audit/invoice/form';
    },
    detail: function (id) {
        var contentUrl = this.auditInvoiceUrl + "?id=" + id;
        layer.open({
            id: "invoiceCreateIframe",
            type: 2,//1是字符串 2是内容
            title: '发票信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '90%'],
            zIndex: 100000,
            content: contentUrl
        });
    }
});