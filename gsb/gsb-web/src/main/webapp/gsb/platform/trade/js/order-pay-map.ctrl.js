System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderPayMapCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
    },
    init:function(){

		var centerHeight = $('body').height() - 60;
		$('#center').height(centerHeight);

		this.payDepDetailCtrl = new com.gongsibao.trade.web.PayDepDetailCtrl();
		this.payDepDetailCtrl.init();
    },
    getDepPay:function(){

    	return {};
    }
});

com.gongsibao.trade.web.PayDepDetailCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
	
    ctor: function () {

    	this.base();
    	this.$gridId = '#pay_dep_grid';
    	this.service = 'com.gongsibao.trade.web.OrderPayController';
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
			},'-',{
				iconCls: 'fa fa-remove',
				text:'删除',
				handler: function(){

					me.remove();
				}
			}],
		    columns:[[
		        {field:'supplierId',title:'服务商',width:200,align:'right',formatter:function(value,row,index){
	        		
		        	if(row.supplier){
		        		
		        		return row.supplier.name;
		        	}
		        }},
		        {field:'departmentId',title:'部门',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	if(row.department){
		        		
		        		return row.department.name;
		        	}
		        }},
		        {field:'employeeId',title:'业务员',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	if(row.employee){
		        		
		        		return row.employee.name;
		        	}
		        }},
		        {field:'amount',title:'分配金额',width:100,align:'right',formatter:function(value,row,index){
	        		
		        	return System.RMB.FenToYun(value);
		        }}
		    ]]
		});
	},
	add:function(){
		
		
		alert('上传文件！');
	},
	remove:function(){
		
		
		alert('删除文件！');
	},
	getDepPay:function(){
		
		
	}
});
