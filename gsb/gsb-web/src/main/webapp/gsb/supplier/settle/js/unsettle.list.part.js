System.Declare("com.gongsibao.igirl.settle.web");
com.gongsibao.igirl.settle.web.UnSettleListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    doSettle: function () {
        var me = this;
        debugger;
        var rows = me.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择未结算订单');
            return false;
        }

        var ids = new Array();
        var cost = 0;
        var charge = 0;
        rows.forEach(function (v, i) {
            ids.push(v.orderProdId);
            charge = charge + Number(v.charge);
            cost = cost + Number(v.cost);
        });
        var msg = "您选择了" + rows.length + "个订单，总额" + (cost + charge) +"元, 成本" + cost +"元, 服务费" + charge +"元, 确定要结算吗？";
        IMessageBox.confirm(msg, function (bool) {
            if(bool) {
                me.invokeService("submitSettle", [taskId, supplierId, departmentId, toUserId], function (data) {
                    //me.reload();
                    IMessageBox.toast('分配成功');
                    layer.closeAll();
                    return;
                });
            }
        })
    }
});