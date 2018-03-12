System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderStagetrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderStageController';
    },
    init:function(){
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});
		
    	var orderId = this.queryString('id');
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		var stageAmount = ((data.payablePrice - data.paidPrice)/100).toFixed(2);
    		$('#stageAmount').numberbox('setValue',stageAmount);
    		$('#stageNum').combobox('setValue','2');
    		
    	});
    },
    stageNumChange:function(newValue,oldValue){
    	
    	var max = 4;
    	var num = parseInt(newValue);
    	for(var i=1;i<=num;i++){
    		
    		$('tr.stage[num="'+i+'"]').show();
    		$('#stageAmount'+i).numberbox('enable');
    	}
    	
    	for(var i=max;i>num;i--){
    		
    		$('tr.stage[num="'+i+'"]').hide();
    		$('#stageAmount'+i).numberbox('clear');
    		$('#stagePercentage'+i).numberbox('clear');
    	}
    	
    	//禁用最后一个
    	$('#stageAmount'+num).numberbox('disable');
    	
    	//还有一些联动未处理 hw
    },
    stageAmountChange:function(newValue,oldValue,numIndex){
    	
    	var stageAmountCtrlId = '#stageAmount'+numIndex;
    	var stagepercentageCtrlId = '#stagePercentage'+numIndex;
    	var totalStageAmount =  (parseFloat($('#stageAmount').numberbox('getValue'))*100);
    	var currentStageAmount = parseFloat(newValue)*100;
    	var percentage = (currentStageAmount/totalStageAmount)*100;
    	$(stagepercentageCtrlId).numberbox('setValue',percentage);
    	
    	var stageNum = parseInt($('#stageNum').combobox('getValue'));
    	if(stageNum==2 && numIndex==1){
    		
    		var surplusAmount = ((totalStageAmount-currentStageAmount)/100).toFixed(2);
    		$('#stageAmount2').numberbox('setValue',surplusAmount);
    	}else if(stageNum==3 && numIndex==2){
    		
    		var stageAmount1 = (parseFloat($('#stageAmount1').numberbox('getValue'))*100);
    		var surplusAmount = ((totalStageAmount-currentStageAmount-stageAmount1)/100).toFixed(2);
    		$('#stageAmount3').numberbox('setValue',surplusAmount);
    		
    	}else if(stageNum==4 && numIndex==3){
    		
    		var stageAmount1 = (parseFloat($('#stageAmount1').numberbox('getValue'))*100);
    		var stageAmount2 = (parseFloat($('#stageAmount2').numberbox('getValue'))*100);
    		var surplusAmount = ((totalStageAmount-currentStageAmount-stageAmount1-stageAmount2)/100).toFixed(2);
    		$('#stageAmount4').numberbox('setValue',surplusAmount);
    	}
    },
    save:function(){
    	
    	alert('保存！');
    }
});
