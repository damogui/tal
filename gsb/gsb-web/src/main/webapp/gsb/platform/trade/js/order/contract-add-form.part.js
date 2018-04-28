System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ContractFormPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
        //订单下单方式枚举
        this.orderPlatformSourceEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
        this.serviceLocator = new org.netsharp.core.JServiceLocator();
        this.isAdd = System.Url.getParameter("isAdd");
    },
    init: function () {

    },
    add: function () {
        var me = this;
        var url = window.location.href;
        var fkParam = this.getfkParam();
        var orderId = fkParam.orderId;
        this.invokeService("newInstance", [orderId], function (jmessage) {
            me.currentItem = jmessage;
            me.viewModel.currentItem = me.currentItem;
            me.currentItem.entityState = EntityState.New;
            me.currentItem.companyName = jmessage.soOrder.companyIntention == null ? "" : jmessage.soOrder.companyIntention.companyName;
            //新建时合同总额等于订单应付额
            me.currentItem.realAmount = jmessage.soOrder.payablePrice;
            if (me.currentItem == null) {
                me.viewModel.clear();
            } else {
                me.databind();
                me.bindOrderInfo(jmessage);
            }
        });
    },
    bindOrderInfo: function (jmessage) {
        var me = this;
        //region 订单信息的赋值
        var payablePrice = System.RMB.fenToYuan(jmessage.soOrder.payablePrice);
        //订单应付金额
        $("#soOrder_payablePrice").text(payablePrice);
        //合同来源
        $("#soOrder_platformSource").text(me.orderPlatformSourceEnum[jmessage.soOrder.platformSource]);
        //合同总额
        //$("#realAmount").numberbox('setValue', payablePrice);
        // endregion
    },
    databindextra: function (entity) {
        var me = this;
        me.bindOrderInfo(entity);
        //查看时禁用整个form
        if (me.isAdd != 1) {
            me.disable();
        }
    },
    addExtraProp: function (entity) {
        entity.sginingUserId = entity.soOrder.ownerId == null ? 0 : entity.soOrder.ownerId;
        entity.customerId = entity.soOrder.customerId == null ? 0 : entity.soOrder.customerId;
    },
    onSaved: function (jmessage) {
        var me = this;
        if (jmessage != null) {
            IMessageBox.toast("保存成功！");
            this.databind();
            window.parent.layer.closeAll();
            if (me.saveCallBack) {
                me.saveCallBack();
            }
        } else {
            IMessageBox.error("保存失败！");
        }
    },
    contractTypeChange: function (el) {  //合同类型
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        if ($(el).val() == 0) {
            $('#idNumber').textbox({value: ""});
            $('#idNumber').textbox({disabled: true});
        } else {
            $('#idNumber').textbox({disabled: false});
        }
    },
    customerTypeChange: function (el) {  //客户类型
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        if ($(el).val() == 1) {
            $('#businessLicenseNo').textbox({value: ""});
            $('#businessLicenseNo').textbox({disabled: true});
        } else {
            $('#businessLicenseNo').textbox({disabled: false});
        }
    },
    hasDataFeeChange: function (el) {  //有/无材料费
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        var me = this;
        if ($(el).val() == 0) {
            $('#dataFee').textbox({value: ""});
            $('#dataFee').textbox({disabled: true});
            $('#dataFeeCountTypeId').combobox({disabled: true});
            me.updateRealAmount(0);
        } else {
            $('#dataFee').textbox({disabled: false});
            $('#dataFeeCountTypeId').combobox({disabled: false});
            //合同金额
            var entity = me.getCurrentItem();
            var payablePrice = parseInt(System.RMB.fenToYuan(entity.soOrder.payablePrice));
            $("#realAmount").numberbox('setValue', payablePrice);
        }
    },
    dataFeeChange: function (el) {
        //材料撰写费
        var me = this;
        var dataFee = $("#dataFee").numberbox('getValue');
        dataFee = System.isnull(dataFee) ? 0 : parseInt(dataFee);
        me.updateRealAmount(dataFee);
    },
    updateRealAmount: function (dataFee) {
        //合同金额
        var me = this;
        var entity = me.getCurrentItem();
        var payablePrice = parseInt(System.RMB.fenToYuan(entity.soOrder.payablePrice));
        $("#realAmount").numberbox('setValue', payablePrice + dataFee);
    },
    hasLiquidatedDamagesChange: function (el) { //有/无违约金
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        if ($(el).val() == 0) {
            $('#liquidatedDamages').textbox({value: ""});
            $('#liquidatedDamages').textbox({disabled: true});
        } else {
            $('#liquidatedDamages').textbox({disabled: false});
        }
    },
    hasBreachChange: function (el) { //有/无违约事项
        var me = this;
        if (me.isAdd != 1) {
            return;
        }
        if ($(el).val() == 0) {
            $('#breachInfo').textbox({value: ""});
            $('#breachInfo').textbox({disabled: true});
        } else {
            $('#breachInfo').textbox({disabled: false});
        }
    },
    validate: function () {
        var me = this;
        //当前实体
        var entity = me.viewModel.getEntity();
        var isValidate = $("#" + this.context.formName).form('validate');

        if (System.isnull(entity.sginingTime)) {
            IMessageBox.error("【签约日期】不能为空");
            return false;
        }

        if (isValidate) {
            //合同类型，选为电子时，身份证号显示，为必填
            if (entity.electronics == 1) {
                if (System.isnull(entity.idNumber)) {
                    IMessageBox.error("【身份证】不能为空");
                    return false;
                }
                if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(entity.idNumber)) {
                    IMessageBox.error("【身份证】格式错误");
                    return false;
                }
            }
            //客户类型，选为企业时，营业执照号显示，为必填
            if (entity.contractType == 2) {
                if (System.isnull(entity.businessLicenseNo)) {
                    IMessageBox.error("【营业执照号】不能为空");
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    ,
    approved: function (auditId, callback) {
        var me = this;
        //审批意见
        var auditRemark = $("#auditRemark").val();
        me.serviceLocator.invoke("com.gongsibao.trade.web.audit.AuditContractController", "approved", [auditId, auditRemark], function (data) {
            IMessageBox.toast('操作成功！');
            window.parent.layer.closeAll();
            //$('#datagridauditLogList').datagrid('reload');
            if (callback)
                callback(data);
        }, null, false);
    }
    ,
    rejected: function (auditId, callback) {
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
                me.serviceLocator.invoke("com.gongsibao.trade.web.audit.AuditContractController", "rejected", [auditId, auditRemark], function (data) {
                    IMessageBox.toast('操作成功！');
                    window.parent.layer.closeAll();
                    if (callback)
                        callback(data);
                }, null, false);
            }
        });

    }
});


com.gongsibao.trade.web.OrderContractFileDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.isAdd = System.Url.getParameter("isAdd");
    },
    initUpload: function () {

        var upload = new org.netsharp.controls.ContractFileUpload();
        upload.parent = this;
        upload.init();
    },
    appendRow: function (path, file) {

        var row = new Object();
        row.name = file.name;
        row.url = path;
        row.tabName = 'so_contract';//要放到后台处理
        $('#' + this.context.id).datagrid('appendRow', row);
    },
    onload: function () {
        var me = this;
        this.resize();
        this.initUpload();
        if (me.isAdd != 1)
            $("#controllerfilesupload").hide();
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


org.netsharp.controls.ContractFileUpload = org.netsharp.controls.OSSUpload.Extends({
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

