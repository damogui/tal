app.controller('TmCaseCtrl',function ($scope,ajax,UrlParameter) {
	$scope.model = null;
	$scope.service = 'com.gongsibao.igirl.web.TradeMarkCasePart';
	//联系人电话
	var mobile = UrlParameter.get("mobile");
	console.log(mobile);
	ajax.invoke($scope.service,'getTradeMarkCaseModelByMobile',[mobile],function(result){
		
		$scope.model = result;
	});
	
});