/**
 * Created by win on 2018/3/21.
 */
System.Declare("com.gongsibao.trade.web");
//回款业绩跳转
com.gongsibao.trade.web.SalesmanOrderReceivedListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditPayUrl="/nav/gsb/platform/trade/auditPay";//审核回款
    },
    detail: function (id) {

        var rows = this.getSelections();
        debugger;
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


        var urlEnd = this.auditPayUrl + "?id=" + id;//回款

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



