System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.SalesmanAllOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.addOrderReceivedUrl = '/panda/crm/order/salesman/coperformance';//创建订单业绩
        this.addReceivedUrl = "/panda/crm/order/salesman/creceivedperformance";//回款业绩
        this.originType = null;//来源类型（0或null：业务员跳转过来的；1：平台跳转过来的）
        this.addStagingUrl = '/nav/gsb/platform/trade/orderStage';//创建订单业绩
    },
    addOrderReceived: function () {//创建订单业绩
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }


        var urlEnd = this.addOrderReceivedUrl + "?id=" + row.id;

        // var  iframeStr='<iframe scrolling="auto" allowtransparency="true" id="addOrderReceivedIframe"   frameborder="0"  src='+urlEnd+' style="height: 462px;"></iframe>';


        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['60%', '60%'],
            zIndex: 100000,
            id: "addOrderReceivedIframe",
            content: urlEnd,
            btn: ['保存', '取消'],// 可以无限个按钮
            success: function (layero, index) {


            },
            yes: function (index, layero) {

                layer.closeAll();
                document.getElementById('addOrderReceivedIframe').firstElementChild.contentWindow.controllersoOrder.save();//保存
                IMessageBox.toast('保存成功');


            }


        });
    },
    addReceived: function (id) {//创建回款业绩

        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }


        var urlEnd = this.addReceivedUrl + "?id=" + row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['80%', '80%'],
            zIndex: 100000,
            id: "addReceivedIframe",
            content: urlEnd,

            btn: ['保存', '取消'],// 可以无限个按钮

            yes: function (index, layero) {

                layer.closeAll();
                document.getElementById('addReceivedIframe').firstElementChild.contentWindow.controllersoOrder.save();
                IMessageBox.toast('保存成功');


            },


        });
    },
    addRefund: function (id) {//创建退款

        this.edit(id);
    },
    addCarryover: function (id) {//创建结转

        this.edit(id);
    },
    addStaging: function (id) {//创建分期

    	var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var urlEnd = this.addStagingUrl + "?id=" + row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '订单信息',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['80%', '80%'],
            zIndex: 100000,
            id: "addReceivedIframe",
            content: urlEnd,
            btn: ['保存', '取消'],// 可以无限个按钮
            yes: function (index, layero) {
                layer.closeAll();
                document.getElementById('addReceivedIframe').firstElementChild.contentWindow.controllersoOrder.save();
                IMessageBox.toast('保存成功');
            },
        });
    },
    addContract: function (id) {//创建合同

        this.edit(id);
    },
    addInvoice: function (id) {//申请发票

        this.edit(id);
    },
    batchOrderTran: function () {//批量订单转移
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
        me.doOrderTran(orderIdList);
    },
    orderTran: function (id) {//订单转移
        var me = this;
        var orderIdList = [];
        orderIdList.push(id);
        me.doOrderTran(orderIdList);
    },
    doOrderTran: function (orderList) {//订单转移
        //公共控件集合
        var me = this;
        var pubControlList = new org.netsharp.controls.PubControlList();
        var zyStr = me.originType == 1 ? "分配" : '转移';
        PandaHelper.openDynamicForm({
            title: "订单" + zyStr,
            width: 450,
            height: 300,
            items: [
                pubControlList.getSupplierCombogrid(),
                pubControlList.getDepartmentCombogrid(),
                pubControlList.getEmployeeCombogrid()
            ],
            callback: function (index, layero) {

                //var supplierId = $('#supplier_name').combogrid('getValue');
                //var departmentId = $('#department_name').combogrid('getValue');
                var toUserId = $('#employee_name').combogrid('getValue');

                if (System.isnull(toUserId)) {
                    IMessageBox.info('请选择一个业务员');
                    return;
                }

                me.invokeService("orderTran", [orderList, toUserId], function (data) {
                    me.reload();
                    IMessageBox.toast(zyStr + '成功');
                    layer.closeAll();
                    return;
                });
            }
        });
    },
    begOption: function (id) {//开始操作

        this.edit(id);
    },
    detail: function (id) {
        var rows = this.getSelections();
        if (id == null && rows.length != 1) {
            IMessageBox.info('请先选择一条订单数据');
            return false;
        }
        var row = id == null ? this.getSelectedItem() : {};
        id = id == null ? row.id : id;
        var url = '/nav/gsb/platform/trade/orderDetail?id=' + id;
        window.open(url);
    },
    doubleClickRow: function (index, row) {
        this.detail(row.id);
    },
    // edit : function(id) {

    batchAllocation: function () {
        //任务批量分配
        var me = this;
        var row = this.getSelectedItem();
        var id = this.getSelectionIds();
        if (id == "" || id == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doAllot(id);
    },

    batchTransfer: function () {
        //任务批量转移
        var me = this;
        var id = this.getSelectionIds();
        if (id == "" || id == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    transfer: function (id) {
        //任务转移
        var me = this;
        var row = this.getSelectedItem();
        if (row == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    }


});



