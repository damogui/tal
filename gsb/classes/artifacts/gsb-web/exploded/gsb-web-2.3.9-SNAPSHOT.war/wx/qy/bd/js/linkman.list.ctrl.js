org.netsharp.we.core.linkmanListCtrl = org.netsharp.we.core.listCtrl.Extends({
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
    	this.invokeService('getLinkmanByFranchiseeId', pars, function(result){
    		
    		if(result.length==0){

        		$('#nodata').show();
        		return;
    		}

            $(me.listId).setTemplateElement("template").processTemplate(result);
    	});
    },
    toAdd:function(){
    	
    	window.location.href = 'linkmanEdit?franchiseeId='+this.queryString('franchiseeId');
    },
    toFranchiseeDetail:function(franchiseeId){
    	
    	window.location.href = 'franchiseeDetail?id='+franchiseeId;
    },
    toEdit:function(franchiseeId){
    	
    	window.location.href = 'linkmanEdit?id='+franchiseeId;
    }
});