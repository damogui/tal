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
    addPayPerformance: function () {

        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        var urlEnd = this.addPayPerformanceUrl + "?id=" + row.id;

        /*创建回款业绩是不是已经存在待审核*/
        me.invokeService("checkCanOrderPayPer", [parseInt(row.id)], function (data) {
            if (data > 0) {
                layer.msg("此订单回款业绩待审核");
                return;
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '创建回款业绩',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    zIndex: 100000,
                    id: "addReceivedIframe",
                    content: urlEnd,
                    btn: ['保存', '取消'],
                    yes: function (index, layero) {

                        var payPerformanceCtrl = document.getElementById('addReceivedIframe').firstElementChild.contentWindow.payPerformanceCtrl;
                        var isSave = payPerformanceCtrl.save();
                        if (isSave) {

                            IMessageBox.toast('保存成功');
                            layer.closeAll();
                            //刷新
                            reloadPage();
                        }
                    }
                });

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
        if (row.paidPrice <= 0) {
            IMessageBox.toast('必须有付款才能创建订单业绩', 2);
            return false;
        }
        var urlEnd = this.addOrderReceivedUrl + "?id=" + row.id + "&ownerId=" + row.ownerId + "&supplierId=" + row.supplierId + "&departmentId=" + row.departmentId;
        /*创建订单业绩是不是已经存在存在的话不能创建*/
        me.invokeService("checkCanOrderPer", [parseInt(row.id)], function (data) {
            if (data > 0) {
                layer.msg("订单业绩已经创建");
                return;
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '订单业绩信息',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    zIndex: 100000,
                    id: "addOrderReceivedIframe",
                    content: urlEnd,
                    btn: ['保存', '取消'],// 可以无限个按钮
                    success: function (layero, index) {

                    },
                    yes: function (index, layero) {


                        var boolResult = document.getElementById('addOrderReceivedIframe').firstElementChild.contentWindow.controllerdepReceivable.savebase();//保存
                        if (boolResult) {
                            IMessageBox.toast('保存成功');
                            layer.closeAll();
                            //刷新
                            reloadPage();

                        }


                    }


                });


            }
        });


    },
    addReceived: function (id) {//创建回款

        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择订单数据');
            return false;
        }

        /*校验是不是存在订单的改价审核和回款审核，存在不弹窗*/
        me.invokeService("checkCanPay", [parseFloat(row.id)], function (data) {
            if (data > 0) {
                layer.msg("订单改价、已存在回款审核没通过、回款已完成不允许创建回款");
                return;
            } else {
                var urlEnd = me.addReceivedUrl + "?no=" + row.no;
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '创建回款',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    zIndex: 100000,
                    id: "addReceivedIframe",
                    content: urlEnd,
                    btn: ['保存', '取消'],
                    yes: function (index, layero) {

                        var payCtrl = document.getElementById('addReceivedIframe').firstElementChild.contentWindow.payCtrl;
                        var isSave = payCtrl.save();
                        if (isSave) {

                            IMessageBox.toast('保存成功');
                            layer.closeAll();
                            //刷新
                            reloadPage();
                        }
                    }
                });
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
        me.invokeService("refundValidate", [row.id], function (data) {
            if (data < 0) {
                layer.msg('该订单无可退款金额，请知悉');
            } else if (data != 0 && data != 1054) {
                layer.msg('有笔退款或结转目前审核中，请审核通过后，再创建');
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '申请退款',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
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
        me.invokeService("carryValidate", [row.id], function (data) {
            if (data < 0) {
                layer.msg('该订单无可结转金额，请知悉');
            } else if (data != 0 && data != 1054) {
                layer.msg('有笔退款或结转目前审核中，请审核通过后，再创建');
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '创建结转',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
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
        //判断是否改价订单（审核中，不允许分期）。0-审核通过、未审核；1-驳回；2-审核中
        me.invokeService("isChangePriceOrde", [row.id], function (data) {
            if (data == 1) {
                layer.msg('订单改价审核未通过，请核实');
                return false;
            } else if (data == 2) {
                layer.msg('订单改价还未审核通过，请审核通过后再创建');
                return false;
            } else {
                me.validIsStaged(row.id);
            }
        });
    },
    validIsStaged: function (orderId) {
        //判断是否已经分期付款了（先支持1次分期）
        var me = this;
        var contentUrl = this.addStagingUrl + "?id=" + orderId;
        me.invokeService("ValidateStaged", [orderId], function (data) {
            if (data == 0) {
                layer.msg('该订单已申请分期，并审核通过，请知悉');
                return false;
            } else if (data == 1) {
                layer.msg('该订单已申请分期，目前待审核，请知悉');
                return false;
            } else {
                layer.open({
                    type: 2,//1是字符串 2是内容
                    title: '申请分期',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
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
        var url = this.addContractUrl + '?fk=orderId:' + row.id + "&isAdd=1";
        //增加订单是否创建合同
        serviceLocator.invoke("com.gongsibao.trade.web.OrderAllListPart", "checkContract", [row.id], function (data) {
            if (data == -1) {
                IMessageBox.info('该订单已经创建合同，禁止提交合同');
                return;
            }
            if (data == -2) {
                IMessageBox.info('当该订单的改价状态不是审核通过，禁止提交合同');
                return;
            }

            layer.open({
                id: "contractCreateIframe",
                type: 2,
                title: '合同信息',
                fixed: false,
                maxmin: true,
                shadeClose: true,
                area: ['90%', '90%'],
                content: url,
                btn: ['提交', '取消'],
                success: function (layero, index) {

                },
                yes: function () {
                    document.getElementById('contractCreateIframe').firstElementChild.contentWindow.controllercontract.save();
                }
            });
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
        var url = this.addInvoiceUrl + '?fk=orderId:' + row.id + "&isAdd=1";
        ;
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        //增加订单是否创建发票
        serviceLocator.invoke("com.gongsibao.trade.web.InvoiceFormPart", "checkInvoice", [row.id], function (data) {
                if (data == -1) {
                    IMessageBox.info('该订单已经创建发票了');
                    return;
                }
                if (data == -2) {
                    IMessageBox.info('该订单的可开发票额为零，禁止申请发票');
                    return;
                }
                layer.open({
                    id: "invoiceCreateIframe",
                    type: 2,
                    title: '申请发票',
                    fixed: false,
                    maxmin: true,
                    shadeClose: true,
                    area: ['90%', '90%'],
                    content: url,
                    btn: ['提交', '取消'],
                    success: function (layero, index) {

                    },
                    yes: function () {
                        document.getElementById('invoiceCreateIframe').firstElementChild.contentWindow.controllerinvoice.save();
                    }
                });
            }
            ,
            null, false
        );

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
    batchAllocation: function () {
        //商机批量分配
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
        //商机批量转移
        var me = this;
        var id = this.getSelectionIds();
        if (id == "" || id == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    transfer: function (id) {
        //商机转移
        var me = this;
        var row = this.getSelectedItem();
        if (row == null) {
            IMessageBox.info('请选择记录');
            return;
        }
        me.doTransfer(id);
    },
    contactFormatter: function (value, row, index, typeName) {

        if (value) {
            var ctrl = workspace.parts.byIndex(0).key;
            return '<sapn>' + PandaHelper.dimString(value) + '</span><i class="fa fa-eye" onclick="' + ctrl + '.showPlaintext(\'' + row.customerId + '\',\'' + value + '\',\'' + typeName + '\',this);"></i>';
        }
    }, showPlaintext: function (customerId, value, typeName, obj) {
        $(obj).parent().text(value);
        //var serviceLocator = new org.netsharp.core.JServiceLocator();
        //serviceLocator.invoke(this.context.service, "recordLookLog",[customerId,typeName]);
    }

});


/*重新调取下请求方法*/
function reloadPage() {
    controllersoOrderList.query();

}




