System.Declare("com.gongsibao.u8.web");
com.gongsibao.u8.web.ManualVoucherOrderDTOController = org.netsharp.panda.commerce.ListPart.Extends({

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
				// 内容
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
				var me = this;
				this.invokeService("addOrderVoucherFollowLog", [ orderId, content ],
						function() {
							me.reload();
							IMessageBox.toast('添加成功');
							layer.closeAll();
							return;
						});
			},
			viewVoucherFollowLogWeb : function() {
				var me = this;
				var row = this.getSelectedItem();
				if (row == null) {
					IMessageBox.info('请选择记录');
					return;
				}
				var orderId = row.id;
				this.invokeService("getOrderVoucherFollowLogByUserId", [orderId], function(data) {
					var body = "";
					$.each(data,function(k,v){
						body+="<tr><td>"+v.content+"</td><td>"+v.creator+"</td><td>"+v.createTime+"</td></tr>";
					});							
					body = System.isnull(body)?"<tr style='color:grey;'><td style='text-align:center'  colspan=3>暂无记录</td></tr>":body;		
					var content =controllermanualVoucherOrderDTOList.getVoucherFollowTableStyle()
											+ '<table class="gridtable">'
											+'<tr><th>内容</th><th>跟进人</th><th>跟进时间</th></tr>'
											+body
										   +'</table>';
					layer.open({
						type : 1,
						title : '凭证跟进记录',
						fixed : false,
						maxmin : false,
						shadeClose : false,
						area : [ '600px', '300px' ],
						content : content
					});
				});	
			},
			getVoucherFollowTableStyle:function(){
				var style='<style type="text/css">'
					+'table.gridtable {'
					+'font-family: verdana,arial,sans-serif;'
					+'font-size:11px;'
					+'color:#333333;'
					+'border-width: 1px;'
					+'border-color: #666666;'
					+'border-collapse: collapse;'
					+'margin-left: 5px;'
					+'width:550px;'
					+'}'
					+'table.gridtable th {'
					+'border-width: 1px;'
					+'padding: 8px;'
					+'border-style: solid;'
					+'border-color: #666666;'
					+'background-color: #dedede;'
					+'}'
					+'table.gridtable td {'
					+'border-width: 1px;'
					+'padding: 8px;'
					+'border-style: solid;'
					+'border-color: #666666;'
					+'background-color: #ffffff;'
					+'}'
					+'</style>';
				return style;
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