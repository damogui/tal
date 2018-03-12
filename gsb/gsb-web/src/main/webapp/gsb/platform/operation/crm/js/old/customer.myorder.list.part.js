System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.CustomerMyOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({

    ctor: function () {
        this.base();
    }
});


//重新调用查询
$(function () {
    var accountId = controllersoOrderList.queryString('accountId');
    var startAddOrderDate = controllersoOrderList.queryString('startAddOrderDate');
    var endAddOrderDate = controllersoOrderList.queryString('endAddOrderDate');
    var url = controllersoOrderList.context.queryUrl;
    var options = $('#datagridsoOrderList').datagrid('options');
    if (accountId) {
        url += '&accountId=' + accountId;
    }
    if (startAddOrderDate) {
        url += '&startAddOrderDate=' + startAddOrderDate;
    }
    if (endAddOrderDate) {
        url += '&endAddOrderDate=' + endAddOrderDate;
    }
    options.url = url;
    //列表
    $('#datagridsoOrderList').datagrid(options);//进行重新加载

});