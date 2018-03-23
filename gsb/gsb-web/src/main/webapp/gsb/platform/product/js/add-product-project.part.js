System.Declare("com.gongsibao.product.web");
//所有订单
com.gongsibao.product.web.ProductListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
        this.base();
        this.addCarryoverUrl = '/nav/gsb/platform/product/productProjectForm';//创建结转
    }, 
    enabledFormatter:function(value,row,index){
    	
		return '<input class="easyui-switchbutton" data-options="\
		checked:'+value+',onText:\'启用\',offText:\'禁用\',\
		onChange:function(checked){controllerworkflowList.updatePojectEnabled(\''+row.id+'\',checked);}">';
    },
    updatePojectEnabled:function(id,value){
		var me = this;
		this.invokeService("updatePojectEnabled", [id,value], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！   ");
		});
    },
	onLoadSuccess:function(data){
		
		$('.easyui-switchbutton').switchbutton();
	},
    addProducts: function () {
//    	var me = this;
//        var row = this.getSelectedItem();
    	var contentUrl = this.addCarryoverUrl;
    	layer.open({
            type: 2,//1是字符串 2是内容
            title: '产品录入',
            fixed: false,
            maxmin: true,
            shadeClose: false,
            area: ['70%', '90%'],
            zIndex: 100000,
            id: "addProductProjectIframe",
            content: contentUrl,
            btn: ['保存', '取消'],// 可以无限个按钮
            yes: function (index, layero) {
                document.getElementById('addProductProjectIframe').firstElementChild.contentWindow.carryoverCtrl.save();
            },
        });
    }
});



