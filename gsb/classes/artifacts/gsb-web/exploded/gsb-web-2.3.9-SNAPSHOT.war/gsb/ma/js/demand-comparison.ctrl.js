app.controller('DemandComparisonCtrl',function ($scope,ajax,UrlParameter) {
	
	$scope.model = null;
	$scope.service = 'com.gongsibao.ma.web.DemandComparisonController';
	var sellingDemandId = UrlParameter.get("sellingDemandId");
	var acquisitionDemandId = UrlParameter.get("acquisitionDemandId");
	ajax.invoke($scope.service,'getComparisonModel',[acquisitionDemandId,sellingDemandId],function(result){
		
		$scope.model = result;
	});
	
	$scope.getComparisonResult = function(matchingRate){
		
		if(matchingRate==100){
			
			return '匹配';
		}else if(matchingRate==0){
			
			return '不匹配';
		}else{
			
			return '部分匹配';
		}
	}
});