System.Declare("com.gongsibao.report.web");
com.gongsibao.report.web.PerformanceStatisticsController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    generaResultsReports : function() {
    	
		var builder = new System.StringBuilder();
		builder.append('<div style="margin:10px;">');
		builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
		builder.append(' 		<tr><td class="title">生成日期</td><td><input id="generateDate"/></td></tr>');
		builder.append('	</table>');
		builder.append('</div>');
		var me = this;
		layer.open({
			type : 1,
			title : '生成业绩报表',
			fixed : false,
			maxmin : false,
			shadeClose : false,
			zIndex : 100000,
			area : [ '350px', '200px' ],
			content : builder.toString(),
			btn : [ '生成', '取消' ],
			success : function(layero, index) {

				$('#generateDate').datebox({required:true});
			},
			btn1 : function(index, layero) {

				var date = $('#generateDate').datebox('getValue');
				if (System.isnull(date)) {

					IMessageBox.info('请选择生成日期');
					return;
				}

				me.invokeService("execute", [date], function(data) {

					if (data === true) {

						layer.close(index);
						IMessageBox.toast("生成业绩成功！");
						me.reload();
					}
				});
			}
		});
	}
});