System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeYearReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    generateReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成年报成功");
			return;
		});
	}
});