System.Declare("com.gongsibao.trade.web");

/***
 * 
 * 回款主控制器
 */
com.gongsibao.trade.web.PayPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.PayPerformanceController';
    	this.platformSourceTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPlatformSourceType');
    	this.payStatusTypeEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderPayStatusType');
    	this.soOrder = null;
    },
    init:function(){

    	var me = this;
    	var orderId = this.queryString('id');
    	if(System.isnull(orderId)){
    		
    		return;
    	}
    	this.invokeService ("getSoOrder", [orderId], function(data){
    		
    		me.bindData(data);
    	});
    	
    	this.payPerformanceCtrl = new com.gongsibao.trade.web.OrderPayPerformanceCtrl();
    	this.payPerformanceCtrl.init();
    	
    	this.payPerformanceCtrl.parentCtrl = this;
    },
    bindData:function(soOrder){
    	
    	this.soOrder = soOrder;
    	
    	$('#no').text(soOrder.no);
    	
    	var payablePrice = System.RMB.fenToYuan(soOrder.payablePrice);
    	$('#payablePrice').text(payablePrice);
    	
    	var paidPrice = System.RMB.fenToYuan(soOrder.paidPrice);
    	$('#paidPrice').text(paidPrice);
    	
    	$('#accountName').text(soOrder.accountName);
    	$('#accountMobile').text(soOrder.accountMobile);
    	$('#addTime').text(soOrder.createTime);
    	
    	$('#platformSource').text(this.platformSourceTypeEnum[soOrder.platformSource]);
    	$('#payStatus').text(this.payStatusTypeEnum[soOrder.payStatus]);
    	
        var stageNumText = soOrder.stageNum == 1?'不分期':soOrder.stageNum + '期';
        $('#stageNum').text(stageNumText);
    	
    	$('#channelOrderNo').text(soOrder.channelOrderNo||'');
    	
    	var unAllotPayPrice = System.RMB.fenToYuan(soOrder.unAllotPayPrice);
    	$('#unAllotPayPrice').text(unAllotPayPrice);
    	
    	$('#remark').text(soOrder.remark||'');
    },
    save:function(){
    	
    	var me = this;
    	if(this.soOrder.unAllotPayPrice<=0){
    		
    		layer.msg('【未划分回款业绩额】为0，不能分配！');
    		return false;
    	}

    	var depPayList = this.payPerformanceCtrl.getDepPays();
    	if(depPayList.length==0){
    		
    		layer.msg('分配记录不能为空！');
    		return false;
    	}
    	
		var allotTotalAmount = 0;
		$(depPayList).each(function(i,item){
			
			allotTotalAmount+=item.amount;
			
			//设置orderId
			item.orderId = me.soOrder.id;
		});
		
		if(allotTotalAmount != this.soOrder.unAllotPayPrice){
			
			layer.msg('本次需把未划分回款业绩额全部分配！');
			return;
		}
		
		var dto = new Object();
		dto.depPayList = depPayList;
		//使用同步提交
		var isSave = false;
		this.invokeService("applyPayPerformance",[dto],function(data){
			
			isSave = data;
			
		},false);
		return isSave;
		
    }
});


/***
 * 
 * 回款业绩明细控制器
 */
com.gongsibao.trade.web.OrderPayPerformanceCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
	
    ctor: function () {

    	this.base();
    	this.$gridId = '#order_pay_performance_grid';
    	this.service = 'com.gongsibao.trade.web.PayPerformanceController';
    	this.parentCtrl = null;
    },
    init:function(){

    	this.initGrid();
    },
	initGrid:function(){
	    
		var me = this;
		$(this.$gridId).datagrid({
			idField:'id',
			emptyMsg:'暂无记录',
			title:'回款业绩分配',
			striped:true,
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:'100%',
			toolbar: [{
				iconCls: 'fa fa-plus',
				text:'新增',
				handler: function(){

					me.add();
				}
			}],
		    columns:[[
		        {field:'id',title:'操作',width:80,align:'center',formatter:function(value,row,index){
		        	
		        	var str = '<a class="grid-btn" href="javascript:payPerformanceCtrl.payPerformanceCtrl.remove('+index+');">删除</a>';
		        	return str;
		        }},
		        {field:'supplierId',title:'服务商',width:200,formatter:function(value,row,index){
	        		
		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }},
		        {field:'departmentId',title:'部门',width:100,formatter:function(value,row,index){
	        		
		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'salesmanId',title:'业务员',width:100,align:'center',formatter:function(value,row,index){
	        		
		        	if(row.salesman){
		        		
		        		return row.salesman.name;
		        	}
		        }},
		        {field:'amount',title:'分配金额',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	return System.RMB.fenToYuan(value);
		        }}
		    ]]
		});
	},
	add:function(){
		
    	if(this.parentCtrl.soOrder.unAllotPayPrice<=0){
    		
    		layer.msg('【未划分回款业绩额】为0，不能分配！');
    		return false;
    	}
    	
		var me = this;
		var orderAllotCtrl = new com.gongsibao.trade.web.OrderAllotCtrl();
		orderAllotCtrl.show('回款业绩分配',function(obj){
			
			//这里要校验最大分配金额不能超过【订单分配金额】-【已分配给员工金额之和】
			
			me.appendRow(obj);
		});
	},
	remove:function(index){
		
		$(this.$gridId).datagrid('deleteRow',index);
	},
	appendRow:function(obj){
		
		$(this.$gridId).datagrid('appendRow',obj);
	},
	getDepPays:function(){
		
		return $(this.$gridId).datagrid('getRows');
	}
});
