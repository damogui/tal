System.Declare("com.gongsibao.trade.web.settle");
com.gongsibao.trade.web.settle.SettleListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.detailUrl = '/nav/gsb/supplier/settle/settleDetail';
    },
    detail: function (id) {
        var contentUrl = this.detailUrl + "?id=" + id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '结算单详情',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['70%', '95%'],
            zIndex: 100000,
            id: "settleInfo",
            content: contentUrl
        });
    },
    doubleClickRow: function (index, row) {
        this.detail(row.id);
    }
});