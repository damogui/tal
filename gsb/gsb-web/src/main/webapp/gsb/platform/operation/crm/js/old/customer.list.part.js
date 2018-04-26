System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.CustomerListPart = org.netsharp.panda.commerce.ListPart.Extends({

    ctor: function () {
        this.base();
    },
    vieworder: function () {
        var me = this;
        var row = this.getSelectedItem();
        var accountId = row.accountId;
        if (accountId == null || accountId == 0) {
            IMessageBox.info('该客户还不是会员，没有订单信息');
            return;
        }
        //订单开始时间
        var startAddOrderDate = $("#Start_addOrderDate").val();
        //订单结束时间
        var endAddOrderDate = $("#End_addOrderDate").val();
        //客户订单url
        var url = '/panda/crm/customer/my/order/list?accountId=' + accountId + "&startAddOrderDate=" + startAddOrderDate + "&endAddOrderDate=" + endAddOrderDate;
        layer.open({
            type: 2,
            title: '订单列表',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['98%', '98%'],
            content: url
        });
    }
});