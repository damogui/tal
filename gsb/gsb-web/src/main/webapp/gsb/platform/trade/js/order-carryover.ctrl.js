System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderCarryoverCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderCarryoverController';
    },
    init:function(){
		
		$('#detail_tabs').tabs({
			fit:true,
			tabHeight:35
		});
		
    	var orderId = this.queryString('id');
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		$('#formOrderNo').val(data.no);
    	});
    },
    amountChange:function(newValue,oldValue){
    	
    	//校验金额合法性
    	
    },
    toOrderNoBlur:function(){
    	
    	alert(1);
    },
    save:function(){
    	
    	var formOrderId = this.queryString('id');
    	var formOrderNo = $('#formOrderNo').val();
    	var toOrderId = null;
    	var toOrderNo = $('#toOrderNo').val();
    	var remark = $('#remark').val();

    	//未校验
    	
    	var orderCarryover = {
    			formOrderId:formOrderId,
    			toOrderId:toOrderId,
    			formOrderNo:formOrderNo,
    			toOrderNo:toOrderNo,
    			remark:remark
    	};
    	
    	var me = this;
    	IMessageBox.confirm('确定提交申请吗？',function(r){
    		if(r){
    			me.invokeService("applyCarryover", [orderCarryover], function(data){
    	    		IMessageBox.info('申请成功，请等待审核!',function(s){
    	    			window.parent.layer.closeAll();
    	    		});
    	    	});
    		}
    	});
    }
});
