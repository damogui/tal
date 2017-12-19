System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    generaReports : function() {
		this.invokeService("execute", [], function() {
			IMessageBox.info('生成报表成功');
			return;
		});
	}
});