System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditPerformanceCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {

        this.base();
        this.service = 'com.gongsibao.trade.web.audit.AuditPerformanceController';
    },
    initData: function () {
        var id = this.queryString('id');
        this.initGridPer(id);

    },
    initGridPer: function (id) {//根据订单id获取信息渲染订单业绩
        var me = this;
        me.invokeService("getOrderCutPerformance", [id], function (data) {


            $('#order_performance_grid').datagrid({
                idField: 'id',
                emptyMsg: '暂无记录',
                striped: true,
                pagination: false,
                showFooter: true,
                singleSelect: false,
                height: 400,
                data: data,
                columns: [[
                    // {field: 'id', checkbox: true},
                    {field: 'suppliername', title: '服务商', width: 180, align: 'center'},
                    {field: 'departmentname', title: '部门', width: 150},

                    {field: 'salesmanname', title: '业务员', width: 150},
                    //{field: 'amount', title: '订单业绩分配金额', width: 150}

                    {
                        field: 'amount',
                        title: '订单业绩分配金额',
                        width: 100,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return (value / 100).toFixed(2);
                        }
                    }

                ]]
            });

            me.initAuditLog(id,1050);//等上一个请求完渲染第二个
        });
    }
});
