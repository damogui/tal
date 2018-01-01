System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.DepartmentDayReportController = org.netsharp.panda.commerce.TreegridPart.Extends( {

    ctor: function () {
        this.base();
    },
    departDayReports : function() {
		this.invokeService("execute", [], function() {
			window.alert("生成部门日报成功");
			return;
		});
	}
});