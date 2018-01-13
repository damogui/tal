System.Declare("com.gongsibao.supplier.web");

com.gongsibao.supplier.web.SupplierCategoryTreegridPart = org.netsharp.panda.commerce.TreegridPart.Extends({

	add : function() {

		if (!this.onAdding()) {
			return;
		}
		var fks = [];

	    if (this.context.relationRole != null && this.relationItem!=null) {
			fks.push(this.context.relationRole + ":" + this.relationItem.id);
		}

		var parentId =  this.getSelectedItem().id;
		if (parentId != null && parentId != "") {
			fks.push("parentId:" + parentId);
		}

		this.doAdd("fk=" + fks.join(";"));
	},

	// -----------------------
	// 整理路径
	// -----------------------
	pathCode : function(node) {
		var me = this;
		var $tree = $("#" + this.context.id);
		this.invokeService("pathCode", [], function(jMessage) {
			
			if(node==null){
				
				$tree.treegrid('reload');
			}else{

				var selectedNode = $tree.tree('getSelected');
				if(selectedNode){
					
					$tree.treegrid('reload',selectedNode.target);
				}else{
					
					$tree.treegrid('reload');
				}
			}

		});
	}
});