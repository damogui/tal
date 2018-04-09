System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.InvoiceFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
        //订单下单方式枚举
        this.orderPlatformSourceEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
        this.orderPayStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
        this.serviceLocator = new org.netsharp.core.JServiceLocator();
        this.isAdd = System.Url.getParameter("isAdd");
    },
    init: function () {

    },
    add: function () {
        var me = this;
        var url = window.location.href;
        //获取fk参数
        var fkParam = this.getfkParam();
        var orderId = fkParam.orderId;
        //显示订单信息
        this.invokeService("querySoOrderById", [orderId], function (data) {
            if (data != null) {
                //绑定订单信息
                me.bandOrderInfo(data);
            }
        });
    },
    databindextra: function (entity) {
        var me = this;
        me.bandOrderInfo(entity.soOrder);
        //查看时禁用整个form
        if (me.isAdd != 1) {
            me.disable();
        }
    },
    bandOrderInfo: function (soOrder) {
        var me = this;
        $("#soOrderNo").text(soOrder.no);
        $("#payablePrice").text(System.RMB.fenToYuan(soOrder.payablePrice));
        $("#toBeInvoicePrice").text(System.RMB.fenToYuan(soOrder.toBeInvoicePrice));
        $("#paidPrice").text(System.RMB.fenToYuan(soOrder.paidPrice));
        $("#customerName").text(soOrder.customerName == null ? "" : soOrder.customerName);
        $("#accountMobile").text(soOrder.accountMobile);
        $("#createTime").text(soOrder.createTime);
        $("#platformSource").text(me.orderPlatformSourceEnum[soOrder.platformSource]);
        $("#payStatus").text(me.orderPayStatusTypeEnum[soOrder.payStatus]);
        $("#stageNum").text(soOrder.stageNum);
        $("#channelOrderNo").text(soOrder.channelOrderNo);
        $("#remark").text(soOrder.remark);
    },
    doSave: function (entity) {
        var me = this;
        IMessageBox.confirm('订单只允许开一次发票，确定提交申请吗？', function (r) {
            if (r) {
                me.invokeService("applyInvoice", [entity, entity.orderId], function (data) {
                    IMessageBox.info('申请成功，请等待审核!', function (s) {
                        window.parent.layer.closeAll();
                        window.parent.location.reload();
                    });
                });
            }
        });
    },
    validate: function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if (isValidate) {
            var mobile = $("#receiverMobilePhone").val();
            if (!/^(1[0-9])\d{9}$/.test(mobile)) {
                IMessageBox.error('【手机号】格式错误');
                return false;
            }
            var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            var receiverEmail = $("#receiverEmail").val();
            if (!reg.test(receiverEmail)) {
                IMessageBox.error('【邮箱】格式错误');
                return false;
            }
            //发表金额
            var amount = $("#amount").val();
            amount = amount == null ? 0 : parseInt(amount);
            if (amount <= 0) {
                IMessageBox.error('发票金额不能小于等于0');
                return false;
            }
            var paidPrice = $("#paidPrice").text() == null ? 0 : parseInt($("#paidPrice").text());
            if (amount > paidPrice) {
                IMessageBox.error('发票金额不能大于已付金额');
                return false;
            }

            return true;
        }
        return isValidate;
    },
    changeInvoiceType: function (el) { //增值转票
        var invoiceType = $(el).val();
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        if ("3082" == invoiceType) {
            $('#vatAddress').textbox({disabled: false});
            $('#vatPhone').textbox({disabled: false});
            $('#vatBankName').textbox({disabled: false});
            $('#vatBankNo').textbox({disabled: false});
        } else {
            $('#vatAddress').textbox({value: ""});
            $('#vatAddress').textbox({disabled: true});
            $('#vatPhone').textbox({value: ""});
            $('#vatPhone').textbox({disabled: true});
            $('#vatBankName').textbox({value: ""});
            $('#vatBankName').textbox({disabled: true});
            $('#vatBankNo').textbox({value: ""});
            $('#vatBankNo').textbox({disabled: true});
        }
    },
    approved: function (auditId, callback) {//审核通过
        var me = this;
        //审批意见
        var auditRemark = $("#auditRemark").val();
        me.serviceLocator.invoke("com.gongsibao.trade.web.audit.AuditInvoiceController", "approved", [auditId, auditRemark], function (data) {
            IMessageBox.toast('操作成功！');
            window.parent.layer.closeAll();
            if (callback)
                callback(data);
        }, null, false);
    },
    rejected: function (auditId, callback) {//审核驳回
        var me = this;
        PandaHelper.openDynamicForm({
            title: '审核不通过原因',
            width: 400,
            height: 300,
            items: [{
                id: 'auditRemark',
                title: '内容',
                type: 'textarea',
                height: 130,
                width: 300,
                className: ''
            }
            ],
            callback: function (index, layero) {
                //审批意见
                var auditRemark = $("#auditRemark").val();
                if (System.isnull(auditRemark)) {
                    IMessageBox.info('请输入审核不通过原因');
                    return false;
                }
                me.serviceLocator.invoke("com.gongsibao.trade.web.audit.AuditInvoiceController", "rejected", [auditId, auditRemark], function (data) {
                    IMessageBox.toast('操作成功！');
                    window.parent.layer.closeAll();
                    if (callback)
                        callback(data);
                }, null, false);
            }
        });

    }
});

com.gongsibao.trade.web.OrderInvoiceFileDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.isAdd = System.Url.getParameter("isAdd");
    },
    initUpload: function () {

        var upload = new org.netsharp.controls.InvoiceFileUpload();
        upload.parent = this;
        upload.init();
    },
    appendRow: function (path, file) {

        var row = new Object();
        row.name = file.name;
        row.url = path;
        row.tabName = 'so_invoice';//要放到后台处理
        $('#' + this.context.id).datagrid('appendRow', row);
    },
    onload: function () {

        this.resize();
        this.initUpload();
    },
    urlFormatter: function (value, row, index) {
        var me = this;
        var strView = '<a class="grid-btn" href="javascript:window.open(\'' + row.url + '\');">查看</a>';
        var strDelete = me.isAdd != 1 ? "" : '<a class="grid-btn" href="javascript:controllerfiles.remove(' + index + ');">删除</a>';
        return strView + strDelete;
    },
    remove: function (index) {

        $('#' + this.context.id).datagrid('deleteRow', index);
    }
});


org.netsharp.controls.InvoiceFileUpload = org.netsharp.controls.OSSUpload.Extends({
    ctor: function () {
        this.base();
        this.multi_selection = true;
        this.parent = null;
    },
    getButtonId: function () {

        return "controllerfilesupload";
    },
    preview: function (path, file) {

        if (System.isnull(path)) {
            return;
        }
        this.parent.appendRow(path, file);
    }
});


