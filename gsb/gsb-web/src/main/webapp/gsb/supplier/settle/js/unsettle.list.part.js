System.Declare("com.gongsibao.trade.web.settle");
com.gongsibao.trade.web.settle.UnSettleListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    doSettle: function () {
        var me = this;
        var rows = me.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择未结算订单');
            return false;
        }

        var ids = new Array();
        var cost = 0;
        var charge = 0;
        rows.forEach(function (v, i) {
            ids.push(v.id);
            charge = charge + Number(v.charge);
            cost = cost + Number(v.cost);
        });
        var msg = "您选择了" + rows.length + "个订单，总额" + (cost + charge) +"元, 成本" + cost +"元, 服务费" + charge +"元, 确定要结算吗？";
        IMessageBox.confirm(msg, function (bool) {
            if(bool) {
                me.invokeService("submitSettle", [ids.join(",")], function (data) {
                    //me.reload();
                    data = data || {};
                    var s = data.status || -1;
                    var m = data.msg || "提交失败";

                    if(s == 1) {
                        layer.msg("结算提交成功", null, function () {
                            window.location.reload();
                        });
                        layer.closeAll();
                    } else {
                        layer.msg(m);
                    }
                });
            }
        })
    }
});