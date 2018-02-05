org.netsharp.we.core.trackListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    },
    init:function(){
    	
    	//查询
    	this.query();
    },
    query:function(){
    	
    	var me = this;
    	var franchiseeId = this.queryString('franchiseeId');
    	//查询
    	var pars = [franchiseeId];
    	this.invokeService('getTrackByFranchiseeId', pars, function(result){
    		
    		if(result.length==0){

        		$('#nodata').show();
        		return;
    		}

            $(me.listId).setTemplateElement("template").processTemplate(result);
    	});
    },
    toDetail:function(franchiseeId){
    	
    	window.location.href = 'trackDetail?id='+franchiseeId;
    }
});

org.netsharp.we.core.franchiseeTrackListCtrl = org.netsharp.we.core.trackListCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    query:function(){
    	
    	var me = this;
    	var employeeId = this.queryString('employeeId');
    	var pars = [employeeId];
    	this.invokeService('getTrackByOwnerId', pars, function(result){
    		
    		if(result.length==0){

        		$('#nodata').show();
        		return;
    		}

            $(me.listId).setTemplateElement("template").processTemplate(result);
    	});
    }
});

