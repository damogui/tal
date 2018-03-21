System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ContractFormPart = org.netsharp.panda.commerce.FormPart.Extends({

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
        var fk = this.queryString("fk");
        var orderId = null;
        if (fk != null && fk != "") {

            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {

                var property = properties[i];
                var pair = property.split(':');
                if (pair[0] == 'orderId') {

                    orderId = pair[1];
                }
            }
        }

        this.invokeService("newInstance", [orderId], function (jmessage) {

            me.currentItem = jmessage;

            var fk = me.queryString("fk");
            if (fk != null && fk != "") {

                var properties = fk.split(';');
                for (var i = 0; i < properties.length; i++) {

                    var property = properties[i];
                    var pair = property.split(':');
                    var expression = "me.currentItem." + pair[0] + "='" + pair[1] + "';";
                    eval(expression);
                }
            }


            me.viewModel.currentItem = me.currentItem;
            me.currentItem.entityState = EntityState.New;
            //公司名称
            me.currentItem.companyName = jmessage.soOrder.companyIntention == null ? "" : jmessage.soOrder.companyIntention.companyName;
            me.currentItem.platformSource = me.orderPlatformSourceEnum[jmessage.soOrder.platformSource];
            me.added(me.currentItem);
            if (me.currentItem == null) {
                me.viewModel.clear();
            } else {
                me.databind();
                //绑定订单信息
                me.bindOrderInfo(jmessage);
            }
        });
    },
    bindOrderInfo: function (jmessage) {

        var me = this;

        //region 订单信息的赋值
        //订单编号
        $("#soOrder_no").text(jmessage.soOrder.no);
        //订单渠道号
        $("#soOrder_channelOrderNo").text(jmessage.soOrder.channelOrderNo);
        //订单应付金额
        $("#soOrder_payablePrice").text((jmessage.soOrder.payablePrice / 100).toFixed(2));
        //合同来源
        $("#soOrder_platformSource").text(me.orderPlatformSourceEnum[jmessage.soOrder.platformSource]);
        //客户姓名
        $("#soOrder_customer_realName").text(jmessage.soOrder.customer == null ? "" : jmessage.soOrder.customer.realName);
        //联系人手机
        $("#soOrder_accountMobile").text(jmessage.soOrder.accountMobile);
        //邮箱
        $("#soOrder_customer_email").text(jmessage.soOrder.customer == null ? "" : jmessage.soOrder.customer.email);
        //签单业务员
        $("#soOrder_owner_name").text(jmessage.soOrder.owner == null ? "" : jmessage.soOrder.owner.name);
        //所在部门
        $("#soOrder_department_name").text(jmessage.soOrder.department == null ? "" : jmessage.soOrder.department.name);
        // endregion
    },
    addExtraProp: function (entity) {
        entity.sginingUserId = entity.soOrder.ownerId == null ? 0 : entity.soOrder.ownerId;
        entity.customerId = entity.soOrder.customerId == null ? 0 : entity.soOrder.customerId;
    },
    contractTypeChange: function (el) {  //合同类型
        if ($(el).val() == 0) {
            $('#idNumber').textbox({value: ""});
            $('#idNumber').textbox({disabled: true});
        } else {
            $('#idNumber').textbox({disabled: false});
        }
    },
    customerTypeChange: function (el) {  //客户类型
        if ($(el).val() == 1) {
            $('#licenseNo').textbox({value: ""});
            $('#licenseNo').textbox({disabled: true});
        } else {
            $('#licenseNo').textbox({disabled: false});
        }
    },
    hasDataFeeChange: function (el) {  //有/无材料费
        if ($(el).val() == 0) {
            $('#dataFee').textbox({value: ""});
            $('#dataFee').textbox({disabled: true});
            $('#dataFeeCountTypeId').combobox({disabled: true});
        } else {
            $('#dataFee').textbox({disabled: false});
            $('#dataFeeCountTypeId').combobox({disabled: false});
        }
    },
    hasLiquidatedDamagesChange: function (el) { //有/无违约金
        if ($(el).val() == 0) {
            $('#liquidatedDamages').textbox({value: ""});
            $('#liquidatedDamages').textbox({disabled: true});
        } else {
            $('#liquidatedDamages').textbox({disabled: false});
        }
    },
    hasBreachChange: function (el) { //有/无违约事项
        if ($(el).val() == 0) {
            $('#breachInfo').textbox({value: ""});
            $('#breachInfo').textbox({disabled: true});
        } else {
            $('#breachInfo').textbox({disabled: false});
        }
    },
    validate: function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if (isValidate) {
            var idNumber = $("#idNumber").val();
            if (!System.isnull(idNumber) && !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idNumber)) {
                IMessageBox.error("【身份证】格式错误");
                return false;
            }
            return true;
        }
        return false;
    }

});


com.gongsibao.trade.web.OrderProdItemDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({
    ctor: function () {
        this.base();
    },
    doubleClickRow: function () {
        //覆盖
    },
    serviceNameFormatter: function (value, row, index) {

        var items = row.items;
        if (items.length == 1) {

            return items[0].serviceName;
        } else {

            var str = '';
            $(items).each(function (i, item) {

                str += '<p>' + item.serviceName + '</p>';
            });
            return str;
        }
    },
    unitNameFormatter: function (value, row, index) {

        var items = row.items;
        if (items.length == 1) {

            return items[0].unitName;
        } else {

            var str = '';
            $(items).each(function (i, item) {

                str += '<p>' + item.unitName + '</p>';
            });
            return str;
        }
    }
});

