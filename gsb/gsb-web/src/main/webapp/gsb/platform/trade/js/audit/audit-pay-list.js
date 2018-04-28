System.Declare("com.gongsibao.trade.web");
//回款审核
com.gongsibao.trade.web.AuditPayListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.auditUrl = "/nav/gsb/platform/trade/auditPay";//回款审核的jsp


    },

    audit: function (id) {//审核
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        // if (rows.length <= 0) {
        //     IMessageBox.info('请先选择订单数据');
        //     return false;
        // }
        // row.nDepPay_orderId+"&auditId="+row.id;
        var contentUrl = this.auditUrl + "?id=" + row.pay_id + "&auditId=" + row.id;
        ;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '回款审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            zIndex: 100000,
            id: "addAuditPayIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('addAuditPayIframe').firstElementChild.contentWindow.auditPayCtrl.approved(reloadPage);
                // IMessageBox.toast("审核通过");
                // layer.close(index);
            },
            btn2: function (index, layero) {
                document.getElementById('addAuditPayIframe').firstElementChild.contentWindow.auditPayCtrl.rejected(reloadPage);
                return false;
            }
        });


    },
    show: function (id) {//查看
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        // if (rows.length <= 0) {
        //     IMessageBox.info('请先选择订单数据');
        //     return false;
        // }
        // row.nDepPay_orderId+"&auditId="+row.id;
        var contentUrl = this.auditUrl + "?id=" + row.pay_id + "&auditId=" + row.id;
        ;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '回款审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            zIndex: 100000,
            id: "addAuditPayIframe",
            content: contentUrl

        });


    },
    optionFormatter:function (value,row,index) {
        if(row.status!="待审核"){

            return  "<a class='grid-btn' href='javascript:controllerauditLogList.show("+value+");'>查看</a>";
        }else{

            return  "<a class='grid-btn' href='javascript:controllerauditLogList.audit("+value+");'>审核</a>";
        }

        
    }


});
/*重新调取下请求方法*/
function reloadPage() {
    controllerauditLogList.query();

}




