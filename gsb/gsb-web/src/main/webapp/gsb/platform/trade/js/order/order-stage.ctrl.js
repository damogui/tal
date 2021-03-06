System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderStageCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderStageController';
    },
    init:function(){
		
		$('#stage_panel').panel('resize',{
			height: $('body').height()-190
		});
    	var orderId = this.queryString('id');
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		//var stageAmount = ((data.payablePrice - data.paidPrice)/100).toFixed(2);
    		var stageAmount = (data.payablePrice/100).toFixed(2);
    		$('#stageAmount').numberbox('setValue',stageAmount);
    		$('#stageNum').combobox('setValue',2);//初始化的时候不赋值
    	});
    },
    stageNumChange:function(newValue,oldValue){
        $('#stageNum').combobox('setValue',newValue);//初始化的时候不赋值
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
    	
    	// 禁用最后一个
    	$('#stageAmount'+num).numberbox('disable');
    	
    	// 还有一些联动未处理 hw
    },
    stageAmountChange:function(newValue,oldValue,numIndex){

    	var stageNum = parseInt($('#stageNum').combobox('getValue'));
    	var totalStageAmount =  (parseFloat($('#stageAmount').numberbox('getValue'))*100);
    	//验证输入分期金额是否合法
    	var getAllAmout = 0;
    	for(var i=1;i<=stageNum -1 ;i++){
    		getAllAmout += parseFloat($('#stageAmount'+i).val())*100;
    		if(getAllAmout == totalStageAmount){
    			$('#stageAmount'+numIndex).numberbox('clear');
        		layer.msg('最后一期无金额，请核实分期期数');
        		return false;
    		}else if(getAllAmout > totalStageAmount){
    			$('#stageAmount'+numIndex).numberbox('clear');
        		layer.msg('分期总额不等于订单应付金额，请核实');
        		return false;
    		}
    	}
    	var currentStageAmount = parseFloat(newValue)*100;
    	var stageAmountCtrlId = '#stageAmount'+numIndex;
    	var stagepercentageCtrlId = '#stagePercentage'+numIndex;
    	
    	
    	var percentage = (currentStageAmount/totalStageAmount)*100;
    	$(stagepercentageCtrlId).numberbox('setValue',percentage);    	
    	
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

    	var stageList = [];
    	var orderId = this.queryString('id');
    	var num = parseInt($('#stageNum').combobox('getValue'));
    	
    	for(var i=1;i<=num;i++){
    		
    		var amount = parseFloat($('#stageAmount'+i).numberbox('getValue'))*100;
    		if(isNaN(amount)){
    			
    			IMessageBox.toast('请填写分期金额!',2);
    			return;
    		}
    		var percentage = $('#stagePercentage'+i).numberbox('getValue');
    		var stage = {
    				orderId:orderId,
    				instalmentIndex:i,
    				percentage:percentage,
    					amount:amount
    		};
    		stageList.push(stage);
    	}
    	var soOrder = {id:orderId,
		    			staged:true,
		    			stageNum:num,
		    			stages:stageList};
    	
    	var me = this;
    	IMessageBox.confirm('确定提交申请吗？',function(r){
    		if(r){
    			me.invokeService("applyStage", [soOrder], function(data){
    	    		IMessageBox.info('申请成功，请等待审核!',function(s){
    	    			window.parent.layer.closeAll();
    	    		});
    	    	});
    		}
    	});
    }
});
