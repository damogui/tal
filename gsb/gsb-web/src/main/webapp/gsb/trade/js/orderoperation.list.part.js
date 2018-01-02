System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderOperationController = org.netsharp.panda.commerce.ListPart
		.Extends({

			ctor : function() {
				this.base();
			},
			batchTransferWeb : function(id) {
				var me = this;
				
				layer.open({
					type : 1,
					title : '批量转移',
					fixed : false,
					maxmin : false,
					shadeClose : false,
					area : [ '500px', '300px' ],
					content : '业务员：<select name="carlist">'
						  +'<option value ="小明">小明</option>'
						  +'<option value ="小芳">小芳</option>'
						  +'<option value="小军">小军</option>'
						  +'<option value="小花">小花</option>'
						  +'</select>',
					btn : [ '保存', '取消' ],// 可以无限个按钮
					yes : function(index, layero) {
						/*var content = $("#txtContent").val();
						if (System.isnull(content)) {
							IMessageBox.info('请输入内容');
							return false;
						}
						me.doAddOrderVoucherFollowLogWeb(orderId, content);*/
					},
					btn2 : function(index, layero) {
					}
				});
				
				/*this.invokeService("querySoOrderTraceList", [ id ], function(
						data) {
					var html = me.pj(data);
					window.top.layer.open({
						type : 1,
						title : '办理进度',
						fixed : false,
						maxmin : true,
						shadeClose : true,
						area : [ '600px', '400px' ],
						content : html
					});
				});*/
			}
		});