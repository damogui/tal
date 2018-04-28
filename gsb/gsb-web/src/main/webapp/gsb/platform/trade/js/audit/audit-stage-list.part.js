System.Declare("com.gongsibao.trade.web");
//订单审核-分期审核
com.gongsibao.trade.web.AuditStagingListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
        this.base();
        this.auditStageUrl = '/nav/gsb/platform/trade/auditStage';//分期审核jsp
    },
    operateFormatter:function(value,row,index){
    	if(row.status!="待审核"){ 
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.detail(" + value + ");'>查看</a>";
    	}else{    		
    		return "<a class='grid-btn' href='javascript:controllerauditLogList.auditStage(" + value + ");'>审核</a>";
    	}
    	
    },
    auditStage: function (id) {//订单审核-分期审核
    	var me = this;
    	var row = this.getSelectedItem();
    	var stageId = row.formId; 
    	var orderId = row.soOrder_id;
    	
    	if(row.status != '待审核'){
    		layer.msg('该审核记录的状态不是【待审核】，禁止审核！');
    		return;
    	}
        var contentUrl = this.auditStageUrl + "?stageId=" + stageId + "&id=" + orderId + "&auditId=" + id;
        
        layer.open({
            type: 2,//1是字符串 2是内容
            title: '分期审核',
            fixed: false,
            maxmin: true,
            shadeClose: true,
            area: ['98%', '98%'],
            zIndex: 1000,
            id: "auditStageIframe",
            content: contentUrl,
            btn: ['审核通过', '审核不通过'],// 可以无限个按钮
            btn1: function (index, layero) {
                document.getElementById('auditStageIframe').firstElementChild.contentWindow.auditStageCtrl.approved(function(){
                	me.reload();
                });
            },
            btn2: function (index, layero){
            	document.getElementById('auditStageIframe').firstElementChild.contentWindow.auditStageCtrl.rejected(function(){
            		me.reload();
            	});
            	return false;
            }
        });

    },
    detail : function(id){
    	var row = this.getSelectedItem();
    	var stageId = row.formId; 
    	var orderId = row.soOrder_id;
        var contentUrl = this.auditStageUrl + "?stageId=" + stageId + "&id=" + orderId;
    	  layer.open({
              type: 2,//1是字符串 2是内容
              title: '查看',
              fixed: false,
              maxmin: true,
              shadeClose: true,
              area: ['98%', '98%'],
              zIndex: 100000,
              content: contentUrl
          });
    },
    serviceNameFormatter : function(value,row,index){
    	
    }
});



