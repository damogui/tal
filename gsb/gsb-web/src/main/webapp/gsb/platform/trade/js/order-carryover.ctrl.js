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
    	var me = this;
    	var orderNo = $('#toOrderNo').val();
    	me.invokeService("getSoOrderByNo", [orderNo], function(data){
    		if(data == null){
    			$('#toOrderNo').val("");
    			layer.msg('去向订单号输入有误，请重新输入');
    		}else{
    			$("#orderId_hidden").val(data.id);
    			if(data.carryStatus == 3031){
    				layer.msg('去向订单待审核中，暂不能操作！');
    				return false;
    			}else if(data == 3032){
            		layer.msg('去向订单结转中，暂不能操作！');
            		return false;
    			}
    		}
    	});
    },
    save:function(){
    	
    	var formOrderId = this.queryString('id');
    	var formOrderNo = $('#formOrderNo').val();
    	var toOrderId = $("#orderId_hidden").val();
    	var toOrderNo = $('#toOrderNo').val();
    	var remark = $('#remark').val();
    	
    	var amount = parseFloat($('#amount').numberbox('getValue'))*100;
    	
    	alert(amount + "|" + $("#remark").html() + "|" + toOrderId);
    	//未校验
    	
    	var orderCarryover = {
    			formOrderId:formOrderId,
    			toOrderId:toOrderId,
    			formOrderNo:formOrderNo,
    			toOrderNo:toOrderNo,
    			amount:amount,
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
