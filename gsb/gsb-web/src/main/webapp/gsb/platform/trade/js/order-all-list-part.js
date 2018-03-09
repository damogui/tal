/*平台的所有订单*/
com.gongsibao.trade.web.OrderAllListPart = com.gongsibao.trade.web.SalesmanAllOrderListPart.Extends({
    ctor: function () {
        this.base();
        this.originType = 1;//来源类型（0或null：业务员跳转过来的；1：平台跳转过来的）
    }
});
