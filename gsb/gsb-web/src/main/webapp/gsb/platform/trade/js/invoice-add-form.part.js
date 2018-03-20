System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.InvoiceFormPart = org.netsharp.panda.commerce.FormPart.Extends( {
    ctor: function () {
        this.base();
    },
    init:function(){
    	
    },
    add: function () {
        var me = this;
        var url = window.location.href;
        var fk = this.queryString("fk");	
        var orderId = null;
        if (fk != null && fk != "") {
        	
            var properties = fk.split(';');
            for (var i = 0; i < properties.length; i++) {
            	
                var property = properties[i];
                var pair = property.split(':');
                if(pair[0] == 'orderId'){
                	
                	orderId = pair[1];
                }
            }
        }
        //显示订单信息
        this.invokeService("querySoOrderById", [orderId], function (data) {
        	if(data!= null){
        		var soOrder = data;
        		$("#soOrderNo").text(soOrder.no);
        		$("#totalPrice").text(soOrder.totalPrice);
        		$("#paidPrice").text(soOrder.paidPrice);
        		$("#customerName").text(soOrder.customerName==null?"":soOrder.customerName);
        		$("#accountMobile").text(soOrder.accountMobile);
        		$("#createTime").text(soOrder.createTime);
        		$("#sourceType").text(soOrder.sourceType);
        		$("#payStatus").text(soOrder.payStatus);
        		$("#stageNum").text(soOrder.stageNum);
        		$("#channelOrderNo").text(soOrder.channelOrderNo);
        		$("#remark").text(soOrder.remark);
        		$("#forminvoice").append("<input type='hidden' id='orderId' value='"+soOrder.id+"' />");
        	}
        });
    },
    save:function (){
    	var me = this;
    	var isValidated = this.validate();
        if (!isValidated) {
            return;
        }
        var invoiceData = {
            	title:$("#title").val(),
            	titleType:$("#titleType").val(),
            	companyId:$("#companyId").val(),
            	typeId:$("#typeId").val(),
            	amount:Number($("#amount").val()),
            	content:$("#content").val(),
            	vatTaxNo:$("#vatTaxNo").val(),
            	receiverName:$("#receiverName").val(),
            	receiverMobilePhone:$("#receiverMobilePhone").val(),
            	receiverEmail:$("#receiverEmail").val(),
            	receiverAddress:$("#receiverAddress").val(),
            	vatAddress:$("#vatAddress").val(),
            	vatPhone:$("#vatPhone").val(),
            	vatBankName:$("#vatBankName").val(),
            	vatBankNo:$("#vatBankNo").val()
            };
           
            IMessageBox.confirm('确定提交申请吗？',function(r){
        		if(r){
        			var orderId = $("#orderId").val();
        			me.invokeService("applyInvoice", [invoiceData,orderId], function(data){
        	    		IMessageBox.info('申请成功，请等待审核!',function(s){
        	    			window.parent.layer.closeAll();
        	    		});
        	    	});
        		}
        	});

       
    },
    validate: function () {
    	 var isValidate = $("#" + this.context.formName).form('validate');
         if(isValidate){
        	 var mobile = $("#receiverMobilePhone").val();
        	 if(!/^(1[0-9])\d{9}$/.test(mobile)){
                 IMessageBox.toast('【手机号】格式错误');
                 return false;
             }
        	 var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
        	 var receiverEmail = $("#receiverEmail").val();
        	 if(!reg.test(receiverEmail)){ 
        		 IMessageBox.toast('【邮箱】格式错误');
                 return false;
        	 }
        	 return true;
         }
         return isValidate;
    },
    checkAmount: function (el){  //验证发票金额
    	var amount = $(el).val();
    	var paidPrice = $("#paidPrice").text();
    	if(paidPrice != null && amount > paidPrice){
    		 IMessageBox.toast('发票金额不能大于支付金额');
             return false;
    	}
    },
    changeInvoiceType : function (el){ //增值转票
    	var invoiceType = $(el).val();
    	if("3082" == invoiceType){
    		$('#vatAddress').textbox({disabled:false});
    		$('#vatPhone').textbox({disabled:false});
    		$('#vatBankName').textbox({disabled:false});
    		$('#vatBankNo').textbox({disabled:false});
    	}else{
    		$('#vatAddress').textbox({value:""});
    		$('#vatAddress').textbox({disabled:true});
    		$('#vatPhone').textbox({value:""});
    		$('#vatPhone').textbox({disabled:true});
    		$('#vatBankName').textbox({value:""});
    		$('#vatBankName').textbox({disabled:true});
    		$('#vatBankNo').textbox({value:""});
    		$('#vatBankNo').textbox({disabled:true});
    	}
    }
});


