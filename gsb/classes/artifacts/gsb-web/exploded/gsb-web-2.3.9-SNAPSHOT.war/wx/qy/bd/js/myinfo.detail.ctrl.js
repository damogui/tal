org.netsharp.we.core.myInfoDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    	this.id = this.queryString('employeeId');
    	this.entity = null;
    },
    init:function(){
    	
    	this.byId();
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getEmployeeInfo', pars, function(result){
    		
    		me.entity = result;
    		me.bindData(result);
    	});
    },
    bindData:function(entity){

    	if(entity.name){

        	$('#name').text(entity.name);
    	}
    	if(entity.mobile){

        	$('#mobile').text(entity.mobile);
    	}
    	if(entity.entryDate){

    		var arr = entity.entryDate.split(/[- : \/]/),
    		date = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
        	var entryDate = date.format('yyyy年MM月dd日');
        	$('#entryDate').text(entryDate);
    	}
    	if(entity.post){

        	$('#post').text(entity.post.pathName);
    	}
    	
    },
    toList:function(){
    	
    	window.location.href = 'trackList?franchiseeId='+this.entity.franchiseeId;
    }
});