System.Declare("com.gongsibao.trade.web.interactive");
com.gongsibao.trade.web.interactive.OperationPoolListPart = com.gongsibao.trade.web.interactive.MyInChargeListPart.Extends({
    ctor: function () {
        this.base();
    },
    detail: function (id) {
        var url = '/nav/gsb/platform/trade/orderProdDetail?id=' + id;
        window.open(url);
    },
    addBatchPrincipal: function () {
        var me = this;
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var orderProdIdList = [];
        $.each(rows, function (k, v) {
            orderProdIdList.push(v.id);
        });
        var salesmanCtrl = new com.gongsibao.trade.web.SelectSalesmanCtrl();
        salesmanCtrl.open('添加负责人', false, function (salesmans) {
            var principalIds = new Array();
            var nameArr = new Array();
            $(salesmans).each(function (index, item) {
                principalIds.push(item.employeeId);
                nameArr.push(item.name);
            });
            var principalNames = nameArr.join();
            var pars = [];
            pars.push(orderProdIdList);
            pars.push(principalIds);
            pars.push(principalNames);
            var serviceLocator = new org.netsharp.core.JServiceLocator();
            serviceLocator.invoke("com.gongsibao.trade.web.OrderProdDetailController", "addBatchPrincipal", pars, function (data) {
                    me.reload();
                    IMessageBox.toast('添加成功');
                    layer.closeAll();
                }, null, false
            );
        });
    },
    queryOperation: function () {
        var queryInteractionGroup = new com.gongsibao.trade.web.QueryInteractionGroupCtrl();
        queryInteractionGroup.open('订单操作组查询', false);

    }
});