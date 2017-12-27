org.netsharp.we.core.franchiseeDetailCtrl = org.netsharp.we.core.detailCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    	this.id = this.queryString('id');
    },
    init:function(){
    	
    	this.byId();
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getFranchiseeById', pars, function(result){
    		
    		me.bindData(result);
    	});
    },
    bindData:function(entity){
    	
    	$('#name').text(entity.name);
    	$('#legalPerson').text(entity.legalPerson);
    	$('#employeeCount').text(entity.employeeCount);
    	
    	if(entity.annualIncome){

        	$('#annualIncome').text(entity.annualIncome+'万');
    	}
    	var district = entity.province.name + ' / ' +  entity.city.name + ' / ' + entity.county.name;
    	$('#district').text(district);
    	$('#registerAddress').text(entity.registerAddress);
    	$('#workdAddress').text(entity.workdAddress);
    	$('#memoto').text(entity.memoto);
    	
    	var businessScope = '';
    	if(entity.scopes){
    		
    		$(entity.scopes).each(function(index,item){
    			
    			var text = businessScopeDictionary.byKey(item.scope);
    			if(text){

        			businessScope+=text+'，';
    			}
    		});
    	}
    	
    	$('#businessScope').text(businessScope);
    	
    	$('#linkmanName').text(entity.linkmanName);
    	$('#mobile').text(entity.mobile).attr("href","tel:"+entity.mobile);
    	$('#post').text(entity.post);
    	$('#weixin').text(entity.weixin);
    	$('#qq').text(entity.qq);
    	$('#tel').text(entity.tel);
    	
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
    	
    	if(entity.cooperativeMode){

    		var cooperativeMode = cooperativeModeDictionary.byKey(entity.cooperativeMode);
        	$('#cooperativeMode').text(cooperativeMode);
    	}
    	
    	if(entity.trackProgress){

    		var trackProgress = trackProgressDictionary.byKey(entity.trackProgress);
        	$('#trackProgress').text(trackProgress);
    	}
    	
    	if(entity.owner != null){

        	$('#owner').text(entity.owner.name);
    	}
    	
    	if(entity.department != null){

        	$('#department').text(entity.department.name);
    	}
    	$('#lastTrackContent').text(entity.lastTrackContent);
    	
    },
    toLinkmanList:function(){
    	
    	window.location.href = 'linkmanList?franchiseeId='+this.id;
    },
    toTrackList:function(){
    	
    	window.location.href = 'franchiseeTrackList?franchiseeId='+this.id;
    }, 
    operation:function(){
    	
    	var me = this;
	    $.actions({
	      onClose: function() {
	        console.log("close");
	      },
	      actions: [
    	        {
      	          text: "修改",
      	          onClick: function() {
      	            window.location.href='franchiseeEdit?id='+me.id;
      	          }
      	        },
	        {
	          text: "添加跟进",
	          onClick: function() {
	            window.location.href='trackAdd?franchiseeId='+me.id;
	          }
	        },
	        {
  	          text: "添加联系人",
  	          onClick: function() {

  	        	window.location.href='linkmanEdit?franchiseeId='+me.id;
  	          }
  	        }
	      ]
	    });
    }
});


