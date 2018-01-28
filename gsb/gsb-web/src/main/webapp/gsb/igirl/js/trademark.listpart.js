//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.TradeMarkListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	autoSubmit:function(type){
		var rows=this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择需要修改的行');
            return false;
        }

        var ids = new Array(rows.length);
        for(var i=0;i<rows.length;i++){
            ids[i] = rows[i].id;
        }
        var that = this;
        this.invokeService("updateMarkState",[ids,type],function (message) {
            if(message.length===0){
                that.reload();
            }else{
                IMessageBox.info("案件："+message+"材料不齐，请联系客户.");
            }
        })
	}

});
