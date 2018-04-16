System.Declare("com.gongsibao.trade.web.interactive");
com.gongsibao.trade.web.interactive.OperationPoolListPart = com.gongsibao.trade.web.interactive.MyInChargeListPart.Extends({
    ctor: function () {
        this.base();
    },
    batchOrderTran: function () {
        var me = this;
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var orderIdList = [];
        $.each(rows, function (k, v) {
            orderIdList.push(v.id);
        });



    }
});