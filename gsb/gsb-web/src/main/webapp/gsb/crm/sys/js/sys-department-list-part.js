System.Declare("com.gongsibao.crm.web");

com.gongsibao.crm.web.SysDepartmentTreeGridPart = org.netsharp.panda.commerce.TreegridPart.Extends({
	
	ctor : function() {
		this.base();
	},
	onAdding : function() {
	
		var totalCount = $("#" + this.context.id).treegrid('getData').length;
		if (totalCount > 0) {
	
			var selectCount = this.getSelectionCount();
			if (selectCount == 0) {
	
				IMessageBox.info("请选择上级!");
				return false;
			}
		}
		return true;
	},
	add : function() {
	
		if (!this.onAdding()) {
			return;
		}
		var fks = [];
	
		if (this.context.relationRole != null && this.relationItem != null) {
			
			fks.push(this.context.relationRole + ":"+ this.relationItem.id);
		}
	
		var selectedRow = this.getSelectedItem();
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
	addExtraParams:function(urls){

		var supplierId = this.queryString('supplierId');
		if(supplierId){

			urls.push("supplierId=" + supplierId);
		}
	},
	// -----------------------
	// 整理路径
	// -----------------------
	pathCode : function(node) {
		var me = this;
		var $tree = $("#" + this.context.id);
		this.invokeService("pathCode", [], function(jMessage) {
	
			if (node == null) {
	
				$tree.treegrid('reload');
			} else {
	
				var selectedNode = $tree.tree('getSelected');
				if (selectedNode) {
	
					$tree.treegrid('reload', selectedNode.target);
				} else {
	
					$tree.treegrid('reload');
					}
				}
	
			});
		}
	});

$(function() {

	controllersupplierDepartmentList.query();
});