/**
 * Created by win on 2018/3/21.
 */
System.Declare("com.gongsibao.trade.web");
//回款业绩跳转
com.gongsibao.trade.web.SalesmanOrderReceivedListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditPayUrl = "/nav/gsb/platform/trade/auditPayPerformance";//回款业绩审核
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
       
        var rowCurrent = this.getSelectedItem()
        //var row = this.getSelected();
        var urlEnd = this.auditPayUrl + "?id=" + rowCurrent.order_id;//传递订单id

        layer.open({
            type: 2,//1是字符串 2是内容
            title: '我的回款信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '60%'],
            zIndex: 100000,
            id: "myorderpay",
            content: urlEnd,
            // btn: ['保存', '取消'],// 可以无限个按钮
            success: function (layero, index) {


            },
            yes: function (index, layero) {

            }


        });


    }
});



