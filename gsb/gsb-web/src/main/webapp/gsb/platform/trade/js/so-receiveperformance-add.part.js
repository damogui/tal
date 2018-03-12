System.Declare("com.gongsibao.trade.web");
//创建回款业绩
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends({

    ctor: function () {
        this.base();
        this.addUrl = null;
        this.editUrl = null;
        this.followUrl = null;
        this.addCustomerUrl = null;
    },
    add:function () {

        alert("添加");

    }


});

