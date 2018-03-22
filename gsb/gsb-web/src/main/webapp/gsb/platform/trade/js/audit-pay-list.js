System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.AuditPayListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.auditUrl = "/nav/gsb/platform/trade/auditPay";//回款审核的jsp


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
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('addAuditPerIframe').firstElementChild.contentWindow.auditPerformanceCtrl .approved();
            },
            btn2: function (){
                document.getElementById('addAuditPerIframe').firstElementChild.contentWindow.auditPerformanceCtrl .rejected();
            }
        });


    }


});



