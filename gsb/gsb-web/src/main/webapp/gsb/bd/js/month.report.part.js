System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeMonthReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    generateReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成月报成功");
			return;
		});
	}
});