
org.netsharp.panda.commerce.TreegridPart = org.netsharp.panda.commerce.ListPart.Extends({

			ctor : function() {
				this.base();
			},

			getSelections : function() {

				var rows = $("#" + this.context.id).treegrid('getSelections');
				return rows;
			},

			addSub : function() {

				var count = this.getSelectionCount();
				if (count > 1) {

					IMessageBox.info("只能选择一条记录!");

					return;
				} else if (count == 0) {

					IMessageBox.info("选择记录后才能新增子节点!");

					return;
				}

				var id = this.getSelectionIds();
				if (!this.onAdding()) {
					return;
				}

				var fks = [];
				if (this.context.relationRole != null
						&& this.context.relationRole != "parent_id") {
					fks.push(this.context.relationRole + ":"
							+ this.relationItem.id);
				}

				fks.push("parentId:" + id);
				this.doAdd("fk=" + fks.join(";"));
			},

			resetUrl : function(url) {

				$("#" + this.context.id).treegrid({
					url : url
				});
			},

			reload : function() {

				$("#" + this.context.id).treegrid('reload');
			},

			onClickCell:function(index, field){
				
			},
			doubleClickRow : function(row) {

				var editLength = $("a[code='edit']").length;
				if(editLength>0){
					
					this.edit(row.id);
				}
			},
			setStyle : function() {

				var height = $('body').height() - 150 - $('#queryFrom').height();
				$("#" + this.context.id).treegrid('resize', {
					height:height,
				});
			}
		});
