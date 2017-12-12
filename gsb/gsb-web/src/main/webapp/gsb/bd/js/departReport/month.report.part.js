System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.DepartmentMonthReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    departMonthReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成部门月报成功");
			return;
		});
	}
});