System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditPayCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {

        this.base();
        this.service = 'com.gongsibao.trade.web.audit.AuditPayController';
    },
    initData: function () {//重写数据
        var id = this.queryString('id');//payid
        this.initPay(id);

    },

    initPay: function (id) {//获取付款凭证和关联订单
        var me = this;


        me.invokeService("getOrderCutPerformanceByPayId", [id], function (data) {
            $("#setOfBooksId").html(data.accountName);
            $("#u8BankId").html(data.payWay);
            $("#offlinePayerName").html(data.bankName);
            $("#offlineBankNo").html(data.bankNo);
            $("#payForOrderCount").html(data.isMoreOrder);
            $("#amount").html(data.amount);
            $("#offlineRemark").html(data.mark);//订单信息

            debugger;
            $('#pay_voucher_grid').datagrid({//付款凭证
                idField: 'id',
                emptyMsg: '暂无记录',
                striped: true,
                pagination: false,
                showFooter: true,
                singleSelect: false,
                height: 400,
                data: data.files,
                columns: [[
                    {
                        field: 'id',
                        title: '凭证名称',
                        width: 100,
                        align: 'right',
                        formatter: function (value, row, index) {

                            var str = '<a class="grid-btn" href="javascript:window.open(\''+row.url+'\');">'+row.name+'</a>';

                            return str;

                        }
                    }

                ]]
            });

            me.initGridOrder(id);//关联订单


        });


    },
    initGridOrder: function (id) {//获取付款凭证和关联订单
        var me = this;


        me.invokeService("getOrderInfosById", [id], function (data) {
            $('#order_relevance_grid').datagrid({
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
                    {field: 'orderNo', title: '订单号', width: 100, align: 'center'},
                    {field: 'orderCut', title: '订单分配金额', width: 150},

                    {field: 'payType', title: '付款类别', width: 150},


                ]]
            });

            me.initGridAudit(id);//等上一个请求完渲染第二个
        });


    },


    initGridAudit: function (orderId) {//审批进度
        var me = this;
        this.invokeService("getAuditLogList", [orderId], function (data) {
            $('#audit_progress_grid').datagrid({
                idField: 'id',
                emptyMsg: '暂无记录',
                striped: true,
                pagination: false,
                showFooter: true,
                singleSelect: true,
                height: '100%',
                data: data,
                columns: [[
                    // {field: 'id', checkbox: true},
                    {field: 'creator', title: '审批人', width: 100, align: 'center'},
                    {field: 'option', title: '操作', width: 150},
                    {field: 'remark', title: '审批记录内容', width: 150},
                    {field: 'createTime', title: '操作时间', width: 150}

                ]]
            });

        });

    }

});
