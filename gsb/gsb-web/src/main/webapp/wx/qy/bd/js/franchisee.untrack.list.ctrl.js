org.netsharp.we.core.unTrackFranchiseeListCtrl = org.netsharp.we.core.franchiseeListCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    query:function(){
    	
    	var me = this;
    	var employeeId = this.queryString('employeeId');
    	var searchKeyWord = this.queryString('searchKeyWord');
    	var pars = [employeeId,searchKeyWord,this.paging.pageIndex,this.paging.pageSize];
    	this.invokeService('queryAwaitTrack', pars, function(result){
    		
    		me.paging.loadingCount = me.paging.loadingCount + result.rows.length;
    		me.paging.totalCount = result.total;
    		var html = me.getTemplateHtml(result.rows);
    		$(me.listId).append(html);
            me.queryAfter();
    	});
    }
});

