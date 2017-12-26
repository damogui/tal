System.Declare("com.gongsibao.report.web");
com.gongsibao.report.web.PerformanceStatisticsController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    generaResultsReports : function() {
		this.invokeService("execute", [], function() {
			IMessageBox.info('生成业绩成功');
			return;
		});
	}
});