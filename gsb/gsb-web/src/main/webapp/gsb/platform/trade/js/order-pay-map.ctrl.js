System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderPayMapCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

        this.base();
        this.service = 'com.gongsibao.trade.web.OrderPayController';
        this.soOrder = null;
    },
    init: function () {

        var centerHeight = $('body').height() - 60;
        $('#center').height(centerHeight);
        var orderNo = this.queryString('no');//订单编号
        
        if (orderNo!="0"){

            $("#orderNo").val(orderNo);
            this.orderNoBlur("#orderNo");
        }

    },
    orderNoBlur: function (e) {

        var me = this;
        var orderNo = $.trim($(e).val());
        if (!System.isnull(orderNo)) {

            //调用后台验证orderNo是否存在
            this.invokeService("getOrderByNo", [orderNo], function (data) {

                if (data == null) {

                    layer.msg('订单号【' + orderNo + '】不存在或已付全款');
                    $(e).val('');
                    return;
                }

                me.soOrder = data;
                var unpaidAmount = me.getUnpaidAmount();
                unpaidAmount = System.RMB.fenToYuan(unpaidAmount);
                $('#unpaidAmount').numberbox('setValue', unpaidAmount);

            }, false);

        } else {

            //有些需要置空
        }
    },
    getUnpaidAmount: function () {

        //待支付=应付-已支付-退款-结转
        var unpaidAmount = this.soOrder.payablePrice - this.soOrder.paidPrice - this.soOrder.refundPrice - this.soOrder.carryAmount;
        return unpaidAmount;
    },
    offlineInstallmentTypeChange: function (newValue, oldValue) {

        var orderNo = $('#orderNo').val().trim();
        if (System.isnull(orderNo)) {

            layer.msg('请填写【订单号】！');
            $('#offlineInstallmentType').combobox('clear');
            return;
        }

        /*
         * 根据选择的不同自动代入不同金额
         * 如果选择的是分期，需要判断当前订单有没有申请分期
         */

        //全款、尾款、四期默认分配金额=待支付金额
        if (newValue === '-1') {

            //尾款：判断是否申请分期，获取前几期是否已付，获取最后一期金额

        } else if (newValue === '1') {

            //首款：判断是否申请分期

        } else if (newValue === '2') {

            //二期：判断是否申请分期、一期款是否已付

        } else if (newValue === '3') {

            //三期：判断是否申请分期、一期款、二期款是否已付
        } else if (newValue === '4') {

            //四期：判断是否申请分期、一期款、二期、三期款是否已付

        }

        if (newValue === '0' || newValue === '-1' || newValue === '4') {

            var unpaidAmount = this.getUnpaidAmount();
            $('#amount').numberbox('setValue', System.RMB.fenToYuan(unpaidAmount));
        }
    },
    amountChange: function (newValue, oldValue) {

        var unpaidAmount = this.getUnpaidAmount();
        var amount = System.RMB.yuanToFen(newValue);
        if (amount > unpaidAmount) {

            layer.msg('【订单分配金额】不能超过【待支付金额】');
            return;
        }

//    	else{
//    		
//    		$('#amount').numberbox('setValue',System.RMB.fenToYuan(unpaidAmount));
//    	}

        //支付类别怎么控制？hw 2018-03-21

    },
    getPayMap: function () {

        //校验未处理
        var orderNo = $('#orderNo').val().trim();
        if (System.isnull(orderNo)) {

            layer.msg('请填写【订单号】！');
            return;
        }

        var offlineInstallmentType = $('#offlineInstallmentType').combobox('getValue');
        if (System.isnull(offlineInstallmentType)) {

            layer.msg('请选择【支付类别】！');
            return;
        }

        var amount = $('#amount').numberbox('getValue');
        if (System.isnull(amount)) {

            layer.msg('请填写【分配金额】！');
            return;
        }


        var payMap = new Object();
        payMap.orderId = this.soOrder.id;
        payMap.soOrder = this.soOrder;
        payMap.offlineInstallmentType = $('#offlineInstallmentType').combobox('getValue');
        var amount = $('#amount').numberbox('getValue');
        payMap.orderPrice = System.RMB.yuanToFen(amount);
        return payMap;
    }
});
