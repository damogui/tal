System.Declare("com.gongsibao.u8.web");
com.gongsibao.u8.web.ManualVoucherOrderDTOController = org.netsharp.panda.commerce.ListPart
		.Extends({

			ctor : function() {
				this.base();
			},
			addOrderVoucherFollowLogWeb : function() {

				var me = this;
				var row = this.getSelectedItem();
				if (row == null) {

					IMessageBox.info('请选择记录');
					return;
				}

				var orderId = row.id;		
				//内容
				var content = '<br/><p style="padding-left:50px;">&nbsp;内容：<textarea rows="8" cols="50"  id="txtContent" ></textarea></p>';
				
				// window.top.layer.open({
				layer.open({
					type : 1,
					title : '新增凭证跟进记录',
					fixed : false,
					maxmin : false,
					shadeClose : false,
					area : [ '500px', '300px' ],
					content : content,
					btn : [ '保存', '取消' ],// 可以无限个按钮
					yes : function(index, layero) {

						var content = $("#txtContent").val();

						if (System.isnull(content)) {
							IMessageBox.info('请输入内容');
							return false;
						}

						me.doAddOrderVoucherFollowLogWeb(orderId, content);
					},
					btn2 : function(index, layero) {

					}
				});

			},
			doAddOrderVoucherFollowLogWeb : function(orderId, content) {

				this.invokeService("addOrderVoucherFollowLog", [ orderId, content ],
						function() {
							IMessageBox.toast('添加成功');
							return;
						});
			},
			changeManualVoucherStatusFormatter:function(value,row,index){
				
				var checked = value=='已完成'?true:false;
				return '<input class="easyui-switchbutton" data-options="'
				+'checked:'+checked
				+',onText:\'已完成\',offText:\'未完成\','
				+'onChange:function(checked){ controllermanualVoucherOrderDTOList.changeManualVoucherStatus(\''+row.id+'\',checked);}">';
			},
			changeManualVoucherStatus(orderId,value){
				
				var state = value==true?1:0;
				var me = this;
				this.invokeService("changeManualVoucherStatus", [orderId,state], function(data) {

					me.reload();
					IMessageBox.toast("操作成功！");
				});
			},
			onLoadSuccess:function(data){
				
				$('.easyui-switchbutton').switchbutton();
			}
		});