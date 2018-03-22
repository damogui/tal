System.Declare("com.gongsibao.trade.web");
//所有订单
com.gongsibao.trade.web.SalesmanAllOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.addOrderReceivedUrl = '/panda/crm/order/salesman/coperformance';//创建订单业绩
        //this.addReceivedUrl = "/panda/crm/order/salesman/creceivedperformance";//回款业绩

        this.addReceivedUrl = "/nav/gsb/platform/trade/orderPay";//回款
        
        this.addPayPerformanceUrl = "/nav/gsb/platform/trade/payPerformance";//回款业绩

        this.originType = null;//来源类型（0或null：业务员跳转过来的；1：平台跳转过来的）
        this.addStagingUrl = '/nav/gsb/platform/trade/orderStage';//创建分期
        this.addRefundUrl = '/nav/gsb/platform/trade/orderRefund';//创建退款
        this.addCarryoverUrl = '/nav/gsb/platform/trade/orderCarryover';//创建结转
        this.addContractUrl = '/panda/trade/order/contract/form';//创建合同
        this.addInvoiceUrl = '/panda/trade/order/invoice/form';//创建发票
    },
    addPayPerformance:function(){
    	
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var urlEnd = this.addPayPerformanceUrl + "?id=" + row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '创建回款业绩',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['80%', '80%'],
            zIndex: 100000,
            id: "addReceivedIframe",
            content: urlEnd,
            btn: ['保存', '取消'],
            yes: function (index, layero) {

            	var payPerformanceCtrl = document.getElementById('addReceivedIframe').firstElementChild.contentWindow.payPerformanceCtrl;
                var isSave = payPerformanceCtrl.save();
                if (isSave === true) {

                	layer.msg('保存成功！',function(){

                    	layer.closeAll();
                	});
                }
            }
        });
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
            title: '创建回款',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['80%', '80%'],
            zIndex: 100000,
            id: "addReceivedIframe",
            content: urlEnd,
            btn: ['保存', '取消'],
            yes: function (index, layero) {

            	var payCtrl = document.getElementById('addReceivedIframe').firstElementChild.contentWindow.payCtrl;
                var isSave = payCtrl.save();
                if (isSave === true) {

                	layer.msg('保存成功！',function(){

                    	layer.closeAll();
                	});
                }
            }
        });
    },
    addRefund: function (id) {//创建退款

        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var contentUrl = this.addRefundUrl + "?id=" + row.id;
        //验证金额 和一些退款的状态
        me.invokeService("refundCarryValidate", [row.id, 0], function (data) {
            if (data < 0) {
                layer.msg('无退款金额！');
            } else if (data == 1051) {
                layer.msg('退款待审核中，暂不能操作！');
            } else if (data == 1052) {
                layer.msg('退款中，暂不能操作！');
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '申请退款',
                    fixed: false,
                    maxmin: true,
                    shadeClose: false,
                    area: ['70%', '95%'],
                    zIndex: 100000,
                    id: "addRefundIframe",
                    content: contentUrl,
                    btn: ['保存', '取消'],// 可以无限个按钮
                    yes: function (index, layero) {
                        document.getElementById('addRefundIframe').firstElementChild.contentWindow.refundCtrl.save();
                    },
                });
            }
        });
    },
    addCarryover: function (id) {//创建结转

        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var contentUrl = this.addCarryoverUrl + "?id=" + row.id;
        me.invokeService("refundCarryValidate", [row.id, 1], function (data) {
            if (data < 0) {
                layer.msg('结转金额不足！');
            } else if (data == 1051) {
                layer.msg('结转待审核中，暂不能操作！');
            } else if (data == 1052) {
                layer.msg('结转中，暂不能操作！');
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '创建结转',
                    fixed: false,
                    maxmin: true,
                    shadeClose: false,
                    area: ['50%', '70%'],
                    zIndex: 100000,
                    id: "addCarryoverIframe",
                    content: contentUrl,
                    btn: ['保存', '取消'],// 可以无限个按钮
                    yes: function (index, layero) {
                        document.getElementById('addCarryoverIframe').firstElementChild.contentWindow.carryoverCtrl.save();
                    },
                });
            }
        });

    },
    addStaging: function (id) {//创建分期
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var contentUrl = this.addStagingUrl + "?id=" + row.id;
        //判断是否已经分期付款了（先支持1次分期）
        me.invokeService("isStaged", [row.id], function (data) {
            if (data) {
                IMessageBox.info('该订单已经分期付款');
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '申请分期',
                    fixed: false,
                    maxmin: true,
                    shadeClose: false,
                    area: ['50%', '70%'],
                    zIndex: 100000,
                    id: "addStagingIframe",
                    content: contentUrl,
                    btn: ['保存', '取消'],// 可以无限个按钮
                    yes: function (index, layero) {
                        document.getElementById('addStagingIframe').firstElementChild.contentWindow.stagetrl.save();
                    },
                });
            }
        });

    },
    addContract: function (id) {//创建合同
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var url = this.addContractUrl + '?fk=orderId:' + row.id;
        //增加订单是否创建合同
        serviceLocator.invoke("com.gongsibao.trade.web.OrderAllListPart", "checkContract", [row.id], function (data) {

            if (data) {
                IMessageBox.info('该订单已经创建合同');
            } else {

                layer.open({
                    id: "contractCreateIframe",
                    type: 2,
                    title: '合同信息',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['60%', '90%'],
                    content: url,
                    btn: ['提交', '取消'],
                    success: function (layero, index) {

                    },
                    yes: function () {
                        document.getElementById('contractCreateIframe').firstElementChild.contentWindow.controllercontract.save();
                    }
                });
            }
        }, null, false);
    },
    addInvoice: function (id) {//申请发票
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }
        var url = this.addInvoiceUrl + '?fk=orderId:' + row.id;
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        //增加订单是否创建发票
        serviceLocator.invoke("com.gongsibao.trade.web.InvoiceFormPart", "checkInvoice", [row.id], function (data) {
            if (data) {
                IMessageBox.info('该订单已经创建支票了');
            } else {
                layer.open({
                    id: "invoiceCreateIframe",
                    type: 2,
                    title: '基本信息',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['60%', '90%'],
                    content: url,
                    btn: ['提交', '取消'],
                    success: function (layero, index) {

                    },
                    yes: function () {
                        document.getElementById('invoiceCreateIframe').firstElementChild.contentWindow.controllerinvoice.save();
                    }
                });
            }
        }, null, false);

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



