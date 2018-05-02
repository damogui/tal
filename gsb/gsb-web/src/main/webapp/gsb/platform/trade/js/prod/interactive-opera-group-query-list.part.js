System.Declare("com.gongsibao.trade.web.interactive");
com.gongsibao.trade.web.interactive.OperatGroupQueryList = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    query: function () {
        var me = this;
        var queryParams = me.queryModel.getFilterParameters();
        if (System.isnull(queryParams) || queryParams.length <= 0) {
            $('#datagridorderProdList').datagrid('loadData', {total: 0, rows: []});
        } else {
            me.base();
        }
    }
});