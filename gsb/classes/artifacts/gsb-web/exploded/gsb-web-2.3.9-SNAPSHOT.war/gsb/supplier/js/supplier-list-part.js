System.Declare("com.gongsibao.supplier.web");
com.gongsibao.supplier.web.SupplierListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },

	onAdding : function() {

    	var relationItem = this.relationItem;
    	if(relationItem == null){
    		
    		IMessageBox.info("请选择服务商分类");
    		return false;
    	}
    	
    	if(relationItem.children.length>0){
    		
    		IMessageBox.info("请选择末节点");
    		return false;
    	}
    	return true;
    },
    openAccount:function(){//开户
    	
		var count = this.getSelectionCount();
		if (count <= 0) {

			IMessageBox.warning("请选择要操作的记录！");
			return;
		}

		var ids = this.getSelectionIds();
		var me = this;
		IMessageBox.confirm("确认要提交吗？", function(istrue) {

			if (istrue) {
				me.invokeService("openAccount", [ids], function(data) {

					me.reload();
					IMessageBox.toast("开户成功！");
				});
			}
		});
    },
    closeAccount:function(){//销户
    	
		var count = this.getSelectionCount();
		if (count <= 0) {

			IMessageBox.warning("请选择要操作的记录！");
			return;
		}

		var ids = this.getSelectionIds();
		var me = this;
		IMessageBox.confirm("确认要提交吗？", function(istrue) {

			if (istrue) {
				me.invokeService("closeAccount", [ids], function(data) {

					me.reload();
					IMessageBox.toast("销户成功！");
				});
			}
		});
    },
    setDepartment:function(){//部门设置
    	
    	var row = this.getSelectedItem();
    	if(row == null){
    		
    		IMessageBox.warning("请选择要操作的记录！");
    		return;
    	}
    	if(row.status == '未开户'){
    		
    		IMessageBox.warning("服务商未开户！");
    		return;
    	}
    	if(row.status == '已注销'){
    		
    		IMessageBox.warning("服务商已注销！");
    		return;
    	}
    	window.open('/panda/operation/supplier/department/list?supplierId='+row.id);
    },
    setSalesman:function(){//员工设置

    	var row = this.getSelectedItem();
    	if(row == null){
    		
    		IMessageBox.warning("请选择要操作的记录！");
    		return;
    	}
    	if(row.status == '未开户'){
    		
    		IMessageBox.warning("服务商未开户！");
    		return;
    	}
    	if(row.status == '已注销'){
    		
    		IMessageBox.warning("服务商已注销！");
    		return;
    	}
    	window.open('/panda/operation/supplier/salesman/list?supplierId='+row.id);
    }
});