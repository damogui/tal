/**
 * Created by win on 2018/3/22.
 */
System.Declare("com.gongsibao.trade.web");
//订单业绩审核
com.gongsibao.trade.web.AuditOrderPerformanceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.auditUrl = "/nav/gsb/platform/trade/auditPerformance";//审核的urljsp 页面


    },

    audit: function (id) {//审核




       debugger;


        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        // if (rows.length <= 0) {
        //     IMessageBox.info('请先选择订单数据');
        //     return false;
        // }
        var contentUrl = this.auditUrl + "?id=" + row.orderId;


        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单业绩审核',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['70%', '95%'],
            zIndex: 100000,
            id: "addAuditPerIframe",
            content: contentUrl,
            btn: ['保存', '取消'],
            yes: function (index, layero) {
                document.getElementById('addAuditPerIframe').firstElementChild.contentWindow.refundCtrl.save();
            },
        });


    },


});