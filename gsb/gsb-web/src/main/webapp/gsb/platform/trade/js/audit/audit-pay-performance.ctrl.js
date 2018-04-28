System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditPayPerformanceCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {

        this.base();
        this.service = 'com.gongsibao.trade.web.audit.AuditPayPerformanceController';
    },
    initData: function () {
        var id = this.queryString('id');
        this.initOrderInfo(id);

    },
    initOrderInfo: function (id) {//根据订单id获取信息渲染订单业绩
        var me = this;
        me.invokeService("getOrderInfo", [id], function (data) {
            //订单信息
            $("#no").html(data.orderNo);
            $("#payablePrice").html( (data.payablePrice/100).toFixed(2));
            $("#paidPrice").html((data.paidPrice/100).toFixed(2));
            $("#accountName").html(data.accountName);
            $("#accountMobile").html(data.accountMobile);
            $("#addTime").html(data.addTime);
            $("#platformSource").html(data.platformSource);
            $("#payStatus").html(data.payStatus);
            $("#installmentCount").html(data.installmentCount);
            $("#channelOrderNo").html(data.channelOrderNo);
            $("#unAllotPayPrice").html((data.unAllotPayPrice/100).toFixed(2));
            $("#remark").html(data.remark);
            me.initGridPer(id);//等上一个请求完渲染第二个
        });


    },
    initGridPer: function (id) {//根据订单id获取信息渲染订单业绩
        var me = this;
        me.invokeService("getPayCutPerformance", [id], function (data) {


            $('#order_amount_cut').datagrid({
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
                   
                    {
                        field: 'amount',
                        title: '回款业绩分配金额',
                        width: 100,
                        align: 'right',
                        formatter: function (value, row, index) {
                            return (value / 100).toFixed(2);
                        }
                    }

                ]]
            });

            me.initAuditLog(id,1051);
        });
    }
});
