System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.ProdMainCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.initializeDetailList = new System.Dictionary();
    	this.orderProdId = null;
    	this.orderProd = null;
    	this.loginUserId = null;
    },
    init:function(){
    	
    	var me = this;
    	this.getLoginUserId();
    	
 		this.orderProdId = System.Url.getParameter('id');
 		
 		//1.订单明细
 		this.initOrderProd(this.orderProdId);
 		
 		//2.订单跟进
		traceCtrl = new com.gongsibao.trade.web.ProdTraceCtrl();
		traceCtrl.mainCtrl = this;
		traceCtrl.init(this.orderProdId);
		
		//3.负责人
		principalCtrl = new com.gongsibao.trade.web.ProdPrincipalCtrl();
		principalCtrl.mainCtrl = this;
		principalCtrl.init(this.orderProdId);
		
		
    	$('#detail_tabs').tabs({    
			tabHeight:30,
		    onSelect:function(title){
		    	
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='材料信息'){
		    		
			    	var fileCtrl = new com.gongsibao.trade.web.FileCtrl();
			    	fileCtrl.mainCtrl = me;
			    	fileCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,fileCtrl);
			    	
		    	}else if(title=='订单信息'){
		    		
			    	var orderCtrl = new com.gongsibao.trade.web.OrderCtrl();
			    	orderCtrl.mainCtrl = me;
			    	orderCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,orderCtrl);

		    	}else if(title=='客户信息'){
		    		
			    	var customerCtrl = new com.gongsibao.trade.web.CustomerCtrl();
			    	customerCtrl.mainCtrl = me;
			    	customerCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,customerCtrl);
			    	
		    	}else if(title=='企业信息'){

			    	companyCtrl = new com.gongsibao.trade.web.CompanyCtrl();
			    	companyCtrl.mainCtrl = me;
			    	companyCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,companyCtrl);
			    	
		    	}else if(title=='材料预览'){

			    	filePreviewCtrl = new com.gongsibao.trade.web.FilePreviewCtrl();
			    	filePreviewCtrl.mainCtrl = me;
			    	filePreviewCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,filePreviewCtrl);
			    	
		    	}else if(title=='自动进度'){

			    	var trailCtrl = new com.gongsibao.trade.web.TrailCtrl();
			    	trailCtrl.mainCtrl = me;
			    	trailCtrl.init(me.orderProdId);
			    	me.initializeDetailList.add(title,trailCtrl);
		    	}
		    }   
		});
    },
    getLoginUserId:function(){
    	
    	var me = this;
    	this.invokeService ("getLoginUserId", [], function(data){
    		
    		me.loginUserId = data;
    	});
    },
    initOrderProd:function(orderProdId){
    	
    	var me = this;
    	this.invokeService ("getOrderProdById", [orderProdId], function(data){
    		
    		me.orderProd = data;
    		me.bindData(data);
    	});
    },
    bindData:function(data){
    	
    	//订单编号
    	var orderNo = 100000000 + data.orderId;
    	$("#orderNo").text(orderNo);
    	
    	//订单明细编号
    	$("#orderProdNo").text(data.orderId);
    	
    	//公司名称
    	var companyName = data.companyIntention != null ? data.companyIntention.companyName :'-';
    	$("#companyName").text(companyName);
    	
    	//办理名称
    	$("#handleName").text(data.handleName || '-');
    	
    	//申请号
       	$("#applyNo").text(data.applyNo || '-');
       	
       	
       	if(data.processStatus){
       		
       		$("#processStatus").text(data.processStatus.name || '-');
       	}
       	
       	//已经进行天数是怎么计算的？ hw
       	$("#processdDays").text(data.processdDays || '0');
       	$("#needDays").text(data.needDays || '0');
       	
    	$("#nodeDayCount").text('0');
       	var weekdayCount = data.processStatus != null ? data.processStatus.weekdayCount:0;
       	$("#weekdayCount").text(weekdayCount);
       	
       	if(data.handleName){
       		
       		$("#editHandleName").text(data.handleName);
       	}
       	
       	if(data.applyNo){
       		
       		$("#editApplyNo").text(data.applyNo);
       	}

       	var me = this;
       	
       	if(data.product.isHandle === true){

           	$("#editHandleName").bind('click',function(){
           		
           		me.editHandleName();
           	});
           	
       	}
       	
    	if(data.product.isApplyNo === true){

           	$("#editApplyNo").bind('click',function(){
           		
           		me.editApplyNo();
           	});
    	}
    },
    editHandleName:function(){
    	
    	var me = this;
		var builder = new System.StringBuilder();
		builder.append('	<table cellpadding="5" cellspacing="10" class="form-panel">');
		builder.append('		<tr><td><input type="text" id="name" style="width:230px;" class="easyui-validatebox nsInput"/></td>');
		builder.append('	</table>');

		layer.open({
			type : 1,
			title : '办理名称',
			fixed : false,
			maxmin : false,
			shadeClose : true,
			zIndex : 100000,
			area : [ '300px', '165px' ],
			content : builder.toString(),
			btn : [ '确定', '取消' ],
			success : function(layero, index) {

				$('#name').val(me.orderProd.handleName);
			},
			btn1 : function(index, layero) {

				var name = $('#name').val();
				if(System.isnull(name)){
					
					layer.msg('请填写办理名称');
					return;
				}
				me.invokeService("editHandleName", [me.orderProdId,name], function(data){
					
					layer.close(index);
					$('#editHandleName').text(name);
					layer.msg('编辑成功');
				});
			}
		});
    },
    editApplyNo:function(){
    	
    	var me = this;
		var builder = new System.StringBuilder();
		builder.append('	<table cellpadding="5" cellspacing="10" class="form-panel">');
		builder.append('		<tr><td><input type="text" id="name" style="width:230px;" class="easyui-validatebox nsInput"/></td>');
		builder.append('	</table>');

		layer.open({
			type : 1,
			title : '申请号',
			fixed : false,
			maxmin : false,
			shadeClose : true,
			zIndex : 100000,
			area : [ '300px', '165px' ],
			content : builder.toString(),
			btn : [ '确定', '取消' ],
			success : function(layero, index) {

				$('#name').val(me.orderProd.applyNo);
			},
			btn1 : function(index, layero) {

				var name = $('#name').val();
				if(System.isnull(name)){
					
					layer.msg('请填写申请号');
					return;
				}
				me.invokeService("editApplyNo", [me.orderProdId,name], function(data){
					
					layer.close(index);
					$('#editApplyNo').text(name);
					layer.msg('编辑成功');
				});
			}
		});
    }
});