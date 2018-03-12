System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderReceivePerformanceDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {

    ctor: function () {
        this.base();
    },
    add:function () {

        alert("新增");

    },
     remove:function () {

         alert("删除");
     }



});