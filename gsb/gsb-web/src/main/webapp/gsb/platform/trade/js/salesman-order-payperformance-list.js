/**
 * Created by win on 2018/3/21.
 */
System.Declare("com.gongsibao.trade.web");
//订单业绩
com.gongsibao.trade.web.SalesmanOrderPerformanceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
    },
    detail: function (id) {

        var rows = this.getSelections();
        if (System.isnull(id)) {

            if (rows.length > 1) {
                IMessageBox.warning("只能选择一条记录！");
                return;
            }

            if (rows.length == 0) {
                IMessageBox.warning("请选择一条记录！");
                return;
            }

            //id = rows[0].order_id;//订单id
        }
        var url = System.Url.join("/crm/order/salesman/coperformance", "id=" + rows[0].order_id);
        url = System.Url.getUrl(url);
        if (this.context.openMode == OpenType.window) {

            var me = this;
            url = System.Url.join(url, "openType=" + OpenType.window);
            IMessageBox.open("详情", url, this.context.windowWidth, this.context.windowHeight, function () {

                me.reload();
            });

        } else if (this.context.openMode == OpenType.redirect) {
            url = System.Url.join(url, "openType=" + OpenType.redirect);
            window.location.href = url;
        } else {
            url = System.Url.join(url, "openType=" + OpenType.open);
            window.open(url);
        }
    }
});



