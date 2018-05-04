System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysSalesmanListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    doQuery : function(filter,queryParams) {
        

        var urls = this.getFilters(filter);
        var url = urls.join("&");
        url = System.Url.getUrl(url);
        url = encodeURI(url);
        this.resetUrl(url,queryParams);
        // this.reload();
    },
    onAdding : function() {

        var relationItem = this.relationItem;
        if(relationItem == null){

            IMessageBox.info("请选择部门");
            return false;
        }

        if(relationItem.children.length>0){

            IMessageBox.info("请选择末节点");
            return false;
        }
        return true;
    },
    getSelectedItem : function(obj) {
        if(obj==1){//obj 1代表新增,其他的不处理

          //新增不做选择校验
        }else {

            var items = this.getSelections();

            if (items.length == 0) {

                IMessageBox.info("您没有选择记录!");
                return null;
            } else if (items.length > 1) {

                IMessageBox.info("只能选择一条记录!");
                return null;
            } else {
                return items[0];
            }
        }




    },
    add: function() {

        if (!this.onAdding()) {
            return;
        }
        var fks = [];

        if (this.context.relationRole != null && this.relationItem != null) {

            fks.push(this.context.relationRole + ":"+ this.relationItem.id);
        }

        var selectedRow = this.getSelectedItem(1);
        if (selectedRow == null) {

            var supplierId = this.queryString('supplierId');
            if(supplierId){
                fks.push("supplierId:" + supplierId);
            }
            this.doAdd("fk=" + fks.join(";"));

        } else {

            var parentId = this.getSelectedItem().id;
            if (parentId != null && parentId != "") {
                fks.push("parentId:" + parentId);
            }

            var supplierId = this.queryString('supplierId');
            if(supplierId){
                fks.push("supplierId:" + supplierId);
            }

            this.doAdd("fk=" + fks.join(";"));
        }
    },
    addExtraParams:function(urls){// 进行扩展

        var supplierId = this.queryString('supplierId');
        if(supplierId){

            urls.push("supplierId=" + supplierId);
        }
    },
    disabledFormatter:function(value,row,index){
    	
		return '<input class="easyui-switchbutton" data-options="'
		+'checked:'+(!value)
		+',onText:\'启用\',offText:\'停用\','
		+'onChange:function(checked){controllerdepartments.setDisabled(\''+row.id+'\',!checked);}">';
    },
    setDisabled:function(salesmanId,state){
    	
		var me = this;
		this.invokeService("setDisabled", [salesmanId,state], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！");
		});
    },
    receivingFormatter:function(value,row,index){

		return '<input class="easyui-switchbutton" data-options="'
		+'checked:'+value
		+',onText:\'是\',offText:\'否\','
		+'onChange:function(checked){controllerdepartments.setReceiving(\''+row.id+'\',checked);}">';
    },    
    setReceiving:function(salesmanId,state){
    	
		var me = this;
		this.invokeService("setReceiving", [salesmanId,state], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！");
		});
    },  
	onLoadSuccess:function(data){
		
		$('.easyui-switchbutton').switchbutton();
	}
});


//重新调用查询
$(function() {

    controllerdepartments.query();
});