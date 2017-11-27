org.netsharp.we.core.trackDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    	this.id = this.queryString('id');
    	this.entity = null;
    },
    init:function(){
    	
    	this.byId();
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getTrackById', pars, function(result){
    		
    		me.entity = result;
    		me.bindData(result);
    	});
    },
    bindData:function(entity){

    	if(entity.nextTrackDate){

    		var arr = entity.nextTrackDate.split(/[- : \/]/),
    		date = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]);
        	var nextTrackDate = date.format('yyyy-MM-dd');
        	$('#nextTrackDate').text(nextTrackDate);
    	}
    	
    	if(entity.intentionDegree){

    		var intentionDegree = intentionDegreeDictionary.byKey(entity.intentionDegree);
        	$('#intentionDegree').text(intentionDegree);
    	}
    	
    	if(entity.trackProgress){

    		var trackProgress = trackProgressDictionary.byKey(entity.trackProgress);
        	$('#trackProgress').text(trackProgress);
    	}
    	
    	if(entity.expectedSign){

    		var expectedSign = expectedSignDictionary.byKey(entity.expectedSign);
        	$('#expectedSign').text(expectedSign);
    	}
    	

    	$('#content').text(entity.content);
    	
    },
    toList:function(){
    	
    	window.location.href = 'trackList?franchiseeId='+this.entity.franchiseeId;
    }
});