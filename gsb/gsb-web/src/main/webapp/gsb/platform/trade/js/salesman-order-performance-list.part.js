System.Declare("com.gongsibao.trade.web");
//订单业绩
com.gongsibao.trade.web.SalesmanOrderPerformanceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditPerformanceUrl="/nav/gsb/platform/trade/auditPerformance";
    },
    detail: function (id) {

        var rows = this.getSelections();
        if (System.isnull(id)) {

            if (rows.length > 1) {
                IMessageBox.warning("只能选择一条记录！");
                return;
            }

            if (rows.length == 0) {
                IMessageBox.warning("请选择一条记录！");
                return;
            }

            //id = rows[0].order_id;//订单id
        }

        var urlEnd = this.auditPerformanceUrl + "?id=" + rows[0].order_id;

        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单业绩信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '60%'],
            zIndex: 100000,
            id: "orderperIframe",
            content: urlEnd,
           // btn: ['保存', '取消'],// 可以无限个按钮
            success: function (layero, index) {


            },
            yes: function (index, layero) {

                // layer.closeAll();
                // document.getElementById('addOrderReceivedIframe').firstElementChild.contentWindow.controllersoOrder.save();//保存
                // IMessageBox.toast('保存成功');


            }


        });

    }

});



