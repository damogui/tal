System.Declare("com.gongsibao.trade.web");
//订单审核-订单改价审核
com.gongsibao.trade.web.AuditOrderListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.addOrderUrl = '/nav/gsb/platform/trade/auditOrder';//订单审核jsp
    },
    operateFormatter:function(value,row,index){
    	if(row.status!="待审核"){ 
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.detail(" + value + ");'>查看</a>";
    	}else{    		
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.auditOrder(" + value + ");'>审核</a>";
    	}
    	
    },
    auditOrder : function (id){
    	var me = this;
    	var row = this.getSelectedItem();
    	var orderId = row.soOrder_id;
    	var contentUrl = this.addOrderUrl + "?id=" + orderId + "&auditId=" + id;
    	if(row.status != '待审核'){
    		layer.msg('该审核记录的状态不是【待审核】，禁止审核！');
    		return;
    	}
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '查看订单',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['50%', '70%'],
            zIndex: 100000,
            id: "orderIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('orderIframe').firstElementChild.contentWindow.auditOrderCtrl.approved(function(s){
                	me.reload();
                });
            },
            btn2: function (index, layero){
            	document.getElementById('orderIframe').firstElementChild.contentWindow.auditOrderCtrl.rejected(function(s){
            		me.reload();
            	});
            	return false;
            }
        });
    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var orderId = row.soOrder_id;
    	var contentUrl = this.addOrderUrl + "?id=" + orderId;
         layer.open({
             type: 2,//1是字符串 2是内容
             title: '查看订单',
             fixed: false,
             maxmin: true,
             shadeClose: true,
             area: ['70%', '70%'],
             zIndex: 100000,
             id: "orderIframe",
             content: contentUrl
         });
    }
});