System.Declare("com.gongsibao.u8.web");
com.gongsibao.u8.web.ManualVoucherOrderDTOController = org.netsharp.panda.commerce.ListPart
		.Extends({

			ctor : function() {
				this.base();
			},
			bindReceiptWeb : function() {

				var me = this;
				var row = this.getSelectedItem();
				if (row == null) {

					IMessageBox.info('请选择记录');
					return;
				}

				var payId = row.id;
				var orderNo = row.orderNo;
				var receiptNo = row.receiptNo;

				var content = '<br/><p style="padding-left:50px;">&nbsp;订单编号：<input  type="text" disabled="disabled"  class="easyui-validatebox nsInput"  value="'
						+ orderNo
						+ '" style="width:180px;"></input></p>'
						+ '<p style="padding-left:50px;">&nbsp;回单号：<input id="txtReceiptNo" type="text" class="easyui-validatebox nsInput" value="'
						+ receiptNo
						+ '" required="true" style="width:180px;"></input></p>';
				// window.top.layer.open({
				layer.open({
					type : 1,
					title : '绑定回单编号',
					fixed : false,
					maxmin : false,
					shadeClose : false,
					area : [ '500px', '300px' ],
					content : content,
					btn : [ '保存', '取消' ],// 可以无限个按钮
					btn1 : function(index, layero) {

						var receiptNo = $("#txtReceiptNo").val();

						if (System.isnull(receiptNo)) {
							IMessageBox.info('请输入回单编号');
							return false;
						}

						me.doBindReceiptWeb(payId, receiptNo);
					},
					btn2 : function(index, layero) {

					}
				});

			},
			doBindReceiptWeb : function(payId, receiptNo) {

				this.invokeService("bindReceiptNo", [ payId, receiptNo ],
						function() {
							IMessageBox.toast('绑定成功');
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