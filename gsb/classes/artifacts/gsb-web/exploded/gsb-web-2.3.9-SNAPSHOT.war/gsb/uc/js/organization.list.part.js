System.Declare("com.gongsibao.uc.web");
com.gongsibao.uc.web.OrganizationListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
	doubleClickRow : function(index, row) {

		var editLength = $("a[code='edit']").length;
		var viewLength = $("a[code='view']").length;
		if(editLength>0 || viewLength>0){
			
			this.edit(row.pkid);
		}
	},
});