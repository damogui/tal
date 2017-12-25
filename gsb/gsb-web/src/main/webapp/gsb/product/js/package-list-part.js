System.Declare("com.gongsibao.product.web");
com.gongsibao.product.web.PackageListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    
    enabledFormatter:function(value,row,index){
    	
		return '<input class="easyui-switchbutton" data-options="\
		checked:'+value+',onText:\'启用\',offText:\'禁用\',\
		onChange:function(checked){controllerproductPackageList.updateEnabled(\''+row.id+'\',checked);}">';
    },
    updateEnabled:function(id,value){
    	
		var me = this;
		this.invokeService("updateEnabled", [id,value], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！   ");
		});
    },
	onLoadSuccess:function(data){
		
		$('.easyui-switchbutton').switchbutton();
	}
});