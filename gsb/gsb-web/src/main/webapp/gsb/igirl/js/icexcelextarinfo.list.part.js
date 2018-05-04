System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExcelExtraInfoListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor : function() {
        this.base();
    },
    choiceAddress:function () {
        var me = this;
        var rows=me.getSelections();
        if (rows.length == 0) {
            IMessageBox.info("您没有选择记录!");
            return;
        } else if (rows.length > 1) {
            IMessageBox.info("只能选择一条记录!");
            return;
        }
        var id = rows[0].id;
        me.invokeService("choiceAddress",[id],function (result) {
            if(result.length>0){
                IMessageBox.error(result);
            }else{
                me.reload();
                layer.closeAll();
                return;
            }
        })
    },
    updateState:function () {
        var me = this;
        var rows=me.getSelections();
        if (rows.length == 0) {
            IMessageBox.info("您没有选择记录!");
            return;
        } else if (rows.length > 1) {
            IMessageBox.info("只能选择一条记录!");
            return;
        }
        var id = rows[0].id;
        me.invokeService("updateState",[id],function (result) {
            if(result.length>0){
                IMessageBox.error(result);
            }else{
                me.reload();
                layer.closeAll();
                return;
            }
        })
    }
});

