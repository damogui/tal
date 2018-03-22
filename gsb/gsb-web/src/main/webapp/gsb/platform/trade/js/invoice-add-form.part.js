System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.InvoiceFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
        //订单下单方式枚举
        this.orderPlatformSourceEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
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
    bandOrderInfo: function (soOrder) {
        var me = this;
        $("#soOrderNo").text(soOrder.no);
        $("#payablePrice").text((soOrder.payablePrice / 100).toFixed(2));
        $("#paidPrice").text((soOrder.paidPrice / 100).toFixed(2));
        $("#customerName").text(soOrder.customerName == null ? "" : soOrder.customerName);
        $("#accountMobile").text(soOrder.accountMobile);
        $("#createTime").text(soOrder.createTime);
        $("#platformSource").text(me.orderPlatformSourceEnum[soOrder.platformSource]);
        $("#payStatus").text(soOrder.payStatus);
        $("#stageNum").text(soOrder.stageNum);
        $("#channelOrderNo").text(soOrder.channelOrderNo);
        $("#remark").text(soOrder.remark);
    },
    doSave: function (entity) {
        var me = this;
        IMessageBox.confirm('确定提交申请吗？', function (r) {
            if (r) {
                me.invokeService("applyInvoice", [entity, entity.orderId], function (data) {
                    IMessageBox.info('申请成功，请等待审核!', function (s) {
                        window.parent.layer.closeAll();
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
            return true;
        }
        return isValidate;
    },
    checkAmount: function (el) {//验证发票金额
        var amount = $(el).val();
        var payablePrice = $("#payablePrice").text();
        if (payablePrice != null && amount > payablePrice) {
            IMessageBox.error('发票金额不能大于支付金额');
            return false;
        }
    },
    changeInvoiceType: function (el) { //增值转票
        var invoiceType = $(el).val();
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
    }
});


