System.Declare("com.gongsibao.trade.web");
//回款业绩审核
com.gongsibao.trade.web.AuditSettleListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.detailUrl = '/nav/gsb/supplier/settle/settleDetail';
        this.auditUrl = '/nav/gsb/platform/trade/auditSettle';
    },
    operateFormatter:function(value,row,index){
        if(row.status!="待审核"){
            return "<a class='grid-btn' href='javascript:controllerauditLogList.detail(" + row.formId + ");'>查看</a>";
        }else{
            return "<a class='grid-btn' href='javascript:controllerauditLogList.auditSettle(" + row.formId + ", " + row.id + ");'>审核</a>";
        }
    },
    detail:function (formId) {
        var me = this
        var contentUrl = this.detailUrl + "?id=" + formId;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '结算单详情',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['70%', '95%'],
            zIndex: 100000,
            id: "ViewSettleIframe",
            content: contentUrl
        });
    },
    auditSettle:function (formId, auditId) {
        var me = this
        var contentUrl = this.auditUrl + "?id=" + formId + "&auditId=" + auditId;
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '结算单审核',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['70%', '95%'],
            zIndex: 100000,
            id: "auditSettleIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditSettleIframe').firstElementChild.contentWindow.auditSettleCtrl.approved(function(){
                    me.reload();
                });
            },
            btn2: function (index, layero){
                document.getElementById('auditSettleIframe').firstElementChild.contentWindow.auditSettleCtrl.rejected(function(){
                    me.reload();
                });
                return false;
            }
        });
    },
});

/*重新调取下请求方法*/
function reloadPage() {
    controllerauditLogList.query();
}


