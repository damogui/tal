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
      		$("#paidPrice_hidden").val(data.paidPrice);
      		$("#refundPrice_hidden").val(data.refundPrice);
      		$("#carryAmount_hidden").val(data.carryAmount);
      		
    		$('#formOrderNo').val(data.no);
    	});
    },
    amountChange:function(newValue,oldValue){
    	var paidPrice = $("#paidPrice_hidden").val();
    	var refundPrice = $("#refundPrice_hidden").val();
    	var carryAmount = $("#carryAmount_hidden").val();
    	var getFinals = paidPrice - refundPrice - carryAmount;
    	if((getFinals - newValue) < 0){
    		$('#amount').numberbox('clear');
    		layer.msg('结转金额大于订单可结转金额，请核实');
    	}
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
    			if(data.carryStatus == 1051){
    				$('#toOrderNo').val("");
    				layer.msg('去向订单待审核中，暂不能操作！');
    			}else if(data.carryStatus == 1052){
    				$('#toOrderNo').val("");
            		layer.msg('去向订单结转中，暂不能操作！');
    			}
    		}
    	});
    },
    save:function(){
    	
    	var formOrderId = this.queryString('id');
    	var formOrderNo = $('#formOrderNo').val();
    	var toOrderId = $("#orderId_hidden").val();
    	var toOrderNo = $('#toOrderNo').val();
    	var remark = $('#carryRemark').val();
    	
    	var amount = parseFloat($('#amount').numberbox('getValue'))*100;
    	//未校验
    	if(isEmpty(toOrderNo) || isNaN(amount) || isEmpty(remark)){
    		layer.msg('请输入必填项');
    		return false;
    	}
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
//字符验证
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == "" ){
        return true;
    }else{
        return false;
    }
}