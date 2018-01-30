//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.NclBatchListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
    nclBatch:function(){
        var rows=this.getSelections();
        for(var i=0;i<rows.length;i++){
            var url = rows[i].url;
            this.invokeService("nclBatchToData",[url],function (message) {
                alert(message);
            })
        }
        /*this.invokeService("getNclBatchData",[],function (message) {
            alert(message);
        })*/
	}
});
