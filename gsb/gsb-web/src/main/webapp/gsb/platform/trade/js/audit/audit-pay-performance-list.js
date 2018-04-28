System.Declare("com.gongsibao.trade.web");
//回款业绩审核
com.gongsibao.trade.web.AuditPayPerformanceListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();

        this.auditUrl = "/nav/gsb/platform/trade/auditPayPerformance";//回款业绩审核的jsp


    },

    audit: function (id) {//审核
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        // if (rows.length <= 0) {
        //     IMessageBox.info('请先选择订单数据');
        //     return false;
        // }
        //115763722
       
        var contentUrl = this.auditUrl + "?id=" + row.soOrder_id+"&auditId="+row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '回款业绩审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            zIndex: 100000,
            id: "auditPayPerIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditPayPerIframe').firstElementChild.contentWindow.auditPayPerformanceCtrl.approved(reloadPage);
                // IMessageBox.toast("审核成功");
                // layer.close(index);
            },
            btn2: function (index, layero){
                document.getElementById('auditPayPerIframe').firstElementChild.contentWindow.auditPayPerformanceCtrl.rejected(reloadPage);
               return false;
            }
        });


    },
    show: function (id) {//审核
        var me = this;
        var row = this.getSelectedItem();
        var rows = this.getSelections();
        // if (rows.length <= 0) {
        //     IMessageBox.info('请先选择订单数据');
        //     return false;
        // }
        //115763722

        var contentUrl = this.auditUrl + "?id=" + row.soOrder_id+"&auditId="+row.id;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '回款业绩审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['95%', '95%'],
            zIndex: 100000,
            id: "auditPayPerIframe",
            content: contentUrl,

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


