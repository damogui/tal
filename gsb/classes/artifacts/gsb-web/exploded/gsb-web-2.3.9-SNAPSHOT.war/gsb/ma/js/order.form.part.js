System.Declare("com.gongsibao.ma.web");
com.gongsibao.ma.web.MaOrderFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    hasExpenseChange:function(checked){

    	if(checked===true){
    		
    		$("#expense").numberbox('enable').numberbox('enableValidation');
    	}else{
    		
    		$("#expense").numberbox('disable').numberbox('disableValidation').numberbox('clear');
    	}
    },
    stateChange:function(newValue,oldValue){
    	//状态
    	if(newValue == 5){

    		$('#finishVoucher').filebox('enableValidation');
    		$('#finishDate').datebox('enableValidation');
    		
    	}else{
    		$('#finishVoucher').filebox('disableValidation');
    		$('#finishDate').datebox('disableValidation');
    	}
    },
    signPriceeChange:function(newValue,oldValue){

    	//签单价
    	var performance = newValue - $("#expense").numberbox('getValue');
    	$("#performance").numberbox('setValue',performance);
    },
    expenseChange:function(newValue,oldValue){
    	
    	//招待费
    	var performance = $("#signPrice").numberbox('getValue') - newValue;
    	$("#performance").numberbox('setValue',performance);
    },
    submitAudit:function(){
    	//提交审核
    	var me = this;
    	IMessageBox.confirm('确认提交审核吗？',function(r){
    		
    		var id = me.viewModel.currentItem.id.toString();
  	        me.invokeService("submitAudit", [id], function (orderId) {

  	        	IMessageBox.toast('提交成功！');
  	        	me.onload();
  	        });
    	});
    },
    leaderPass:function(){
    	
    	//组长审核通过
    	var me = this;
    	IMessageBox.confirm('确认审核通过吗？',function(r){
    		
    		var id = me.viewModel.currentItem.id.toString();
  	        me.invokeService("leaderPass", [id], function (orderId) {

  	        	IMessageBox.toast('审核成功！');
  	        	me.onload();
  	        });
    	});
    },
    leaderNoPass:function(){
    	//组长审核不通过
    	var me = this;
    	IMessageBox.confirm('确认审核不通过吗？',function(r){
    		
    		var id = me.viewModel.currentItem.id.toString();
  	        me.invokeService("leaderNoPass", [id], function (orderId) {

  	        	IMessageBox.toast('审核成功！');
  	        	me.onload();
  	        });
    	});
    },
    vpPass:function(){
    	//VP审核通过
    	var me = this;
    	IMessageBox.confirm('确认审核通过吗？',function(r){
    		
    		var id = me.viewModel.currentItem.id.toString();
  	        me.invokeService("vpPass", [id], function (orderId) {

  	        	IMessageBox.toast('审核成功！');
  	        	me.onload();
  	        });
    	});  	
    },
    vpNoPass:function(){
    	//VP审核不通过
    	var me = this;
    	IMessageBox.confirm('确认审核不通过吗？',function(r){
    		
    		var id = me.viewModel.currentItem.id.toString();
  	        me.invokeService("vpNoPass", [id], function (orderId) {

  	        	IMessageBox.toast('审核成功！');
  	        	me.onload();
  	        });
    	});    	
    },
    getsubmitAuditState:function(){

    	//设置【提交审核】启用状态
    	var entity = this.viewModel.currentItem;
    	if(entity){
    		
    		if(entity.entityState==EntityState.New){

    			return UiElementState.Hide;
    		}
    		
        	var leaderAuditState = entity.groupLeaderAuditState;
        	var vpAuditState = entity.vpAuditState;
        	if(leaderAuditState==1 || vpAuditState==1 || leaderAuditState==2 || vpAuditState==2){
        		
        		return UiElementState.Disable;
        	}
        	
        	if(leaderAuditState==3 || vpAuditState==3){
        		
        		return UiElementState.Enable;
        	}
    	}
    	
    	return UiElementState.Empty;
    },
    getleaderPassState:function(){
    	
    	//设置【组长审核通过】启用状态
    	var entity = this.viewModel.currentItem;
    	if(entity){
    		
        	var leaderAuditState = entity.groupLeaderAuditState;
        	var vpAuditState = entity.vpAuditState;
        	if(leaderAuditState==1){
        		
        		return UiElementState.Enable;
        	}
    	}
    	
    	return UiElementState.Disable;
    },
    getleaderNoPassState:function(){
    	
    	//设置【组长审核不通过】启用状态
    	return this.getleaderPassState();
    },
    getvpPassState:function(){
    	
    	//设置【VP审核通过】启用状态
    	var entity = this.viewModel.currentItem;
    	if(entity){
    		
        	var leaderAuditState = entity.groupLeaderAuditState;
        	var vpAuditState = entity.vpAuditState;
        	if(vpAuditState==1){
        		
        		return UiElementState.Enable;
        	}
    	}
    	
    	return UiElementState.Disable;
    },
    getvpNoPassState:function(){
    	
    	//设置【VP审核不通过】启用状态
    	return this.getvpPassState();
    }
});