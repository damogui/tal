System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.DepartmentYearReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    departYearReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成部门年报成功");
			return;
		});
	}
});