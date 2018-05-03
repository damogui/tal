System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderInfoCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {

        this.base();
        this.service = 'com.gongsibao.trade.web.OrderDetailController';
        this.platformSourceTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
        this.payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
    },
    init: function () {

        var me = this;
        var orderId = this.queryString('id');
        if (System.isnull(orderId)) {

            return;
        }
        this.invokeService("getSoOrder", [orderId], function (data) {

            me.bindData(data);
        });
    },
    bindData: function (soOrder) {

        $('#no').text(soOrder.no);

        var payablePrice = soOrder.payablePrice / 100;
        $('#payablePrice').text(payablePrice.toFixed(2));

        var paidPrice = (soOrder.paidPrice / 100).toFixed(2);
        $('#paidPrice').text(paidPrice);
        try {
            $('#accountName').text(System.isnull(soOrder.customer.realName) ? soOrder.accountName : soOrder.customer.realName);
        }
        catch (error) {
            console.log(error);

        }

        $('#accountMobile').text(soOrder.accountMobile);
        $('#addTime').text(soOrder.createTime || '-');

        $('#platformSource').text(this.platformSourceTypeEnum[soOrder.platformSource] || '-');
        $('#payStatus').text(this.payStatusTypeEnum[soOrder.payStatus]);

        var stageNumText = soOrder.stageNum == 1?'不分期':soOrder.stageNum + '期';
        $('#orderStageNum').text(stageNumText);

        $('#channelOrderNo').text(soOrder.channelOrderNo || '-');

        $('#remark').text(soOrder.remark || '-');
    }
});
$(function () {
    var orderInfoCtrl = new com.gongsibao.trade.web.OrderInfoCtrl();
    orderInfoCtrl.init();
});
