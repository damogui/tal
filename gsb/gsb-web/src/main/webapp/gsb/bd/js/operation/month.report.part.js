System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeDayReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    operDayReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成运营月报成功");
			return;
		});
	}
});