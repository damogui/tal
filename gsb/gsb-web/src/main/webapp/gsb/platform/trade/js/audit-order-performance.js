/**
 * Created by win on 2018/3/22.
 */
System.Declare("com.gongsibao.trade.web");
//订单业绩审核
com.gongsibao.trade.web.AuditOrderPerformanceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        // this.addOrderReceivedUrl = '/panda/crm/order/salesman/coperformance';//创建订单业绩
        // this.addReceivedUrl = "/panda/crm/order/salesman/creceivedperformance";//回款业绩


    },

    audit: function () {
        debugger;
        alert("审核");

    }


});