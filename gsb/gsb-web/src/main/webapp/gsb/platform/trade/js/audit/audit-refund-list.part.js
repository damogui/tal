System.Declare("com.gongsibao.trade.web");
//订单审核-退款审核
com.gongsibao.trade.web.AuditRefundListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.addRefundUrl = '/nav/gsb/platform/trade/auditRefund';//创建退款jsp
    },
    operateFormatter:function(value,row,index){
    	if(row.status!="待审核"){ 
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.detail(" + value + ");'>查看</a>";
    	}else{    		
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.auditRefund(" + value + ");'>审核</a>";
    	}
    	
    },
    auditRefund: function (id) {//订单审核-退款审核
    	var me = this;
    	var row = this.getSelectedItem();
    	var fefundId = row.formId; 
    	var orderId = row.fefund_orderId;
    	
        var contentUrl = this.addRefundUrl + "?fefundId=" + fefundId + "&id=" + orderId + "&auditId=" + id;
        if(row.status != '待审核'){
    		layer.msg('该审核记录的状态不是【待审核】，禁止审核！');
    		return;
    	}
        
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '退款审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['70%', '80%'],
            zIndex: 100000,
            id: "auditRefundIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditRefundIframe').firstElementChild.contentWindow.auditRefundCtrl.approved(function(){
                	me.reload();
                });
            },
            btn2: function (index, layero){
            	document.getElementById('auditRefundIframe').firstElementChild.contentWindow.auditRefundCtrl.rejected(function(){
            		me.reload();
            	});
            	return false;
            }
        });

    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var fefundId = row.formId; 
    	var orderId = row.fefund_orderId;
    	
        var contentUrl = this.addRefundUrl + "?fefundId=" + fefundId + "&id=" + orderId;
         layer.open({
             type: 2,//1是字符串 2是内容
             title: '查看退款',
             fixed: false,
             maxmin: true,
             shadeClose: true,
             area: ['70%', '80%'],
             zIndex: 100000,
             id: "auditRefundIframe",
             content: contentUrl
         });
    },
    serviceNameFormatter : function(value,row,index){
    	
    }
});



