//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.NclBatchListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
    nclBatch:function(){
        var rows=this.getSelections();
        var url = rows[0].url;
        var isInsert = rows[0].isInsert;
        if(isInsert==true){
            IMessageBox.info("当前数据源已经被导入");
        }else{
            var that = this;
            this.invokeService("nclBatchToData",[url],function (message) {
                IMessageBox.info(message);
                that.reload();
            })
        }
	}
});
