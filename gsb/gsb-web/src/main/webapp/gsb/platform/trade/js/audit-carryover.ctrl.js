System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditCarryoverCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    	//获取枚举
    	this.processStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.trade.dic.OrderProcessStatusType');
    	this.auditLogStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.service = 'com.gongsibao.trade.web.audit.AuditCarryoverController';
    },
    initData:function(){
    	var carryOverId = this.queryString('id');
    	var me = this;
    	//加载Tab项
    	$('#detail_tabs').tabs({ 
    		tabHeight:30,
		    onSelect:function(title){
		    	var detailCtrl = me.initializeDetailList.byKey(title);
		    	if(detailCtrl){		    		
		    		//已经初始化过的不再执行
		    		return;
		    	}
		    	if(title=='审批进度'){
		    		me.auditLogInfor(carryOverId);
		    	}
		    }
    	});
    	me.initializeDetailList.add('结转信息',this.carryoverInfo(carryOverId));
    },
    carryoverInfo: function(id){
    	//tab-获取分期信息
    	this.invokeService ("getNOrderCarryover", [id], function(data){
    		var builder = new System.StringBuilder();
    		builder.append('<tr>');
    		builder.append('<td class="label_td"><label>结转来源订单号：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ data.formOrderNo +'</label>');
    		builder.append('</td>');
    		builder.append('<td class="label_td"><label>结转去向订单号：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ data.toOrderNo +'</label>');
    		builder.append('</td>');
    		builder.append('<td class="label_td"><label>结转金额：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ (data.amount/100).toFixed(2) +'</label>');
    		builder.append('</td>');
    		builder.append('</tr>');
    		
    		builder.append('<tr>');
    		builder.append('<td class="label_td"><label>结转说明：</label></td>');
    		builder.append('<td colspan="5" class="control_td">');
    		builder.append('<label>'+ data.remark +'</label>');
    		builder.append('</td>');
    		builder.append('</tr>');
    		
    		$("#carryover_info_grid").append(builder.toString());
    	});
    },
    auditLogInfor: function(id){
    	//tab-审批进度
    	var me = this;
    	this.invokeService("getAuditLogList", [id,1052], function(data){    		
    		$('#audit_progress_grid').datagrid({
    			idField:'id',
    			emptyMsg:'暂无记录',
    			striped:true,
    			pagination:false,
    			showFooter:true,
    			singleSelect:true,
    			height:'100%',
    			data:data,
    		    columns:[[
    		        {field:'creator',title:'创建人名称',width:80,align:'center'},
    		        {field:'status',title:'审核状态',width:80,align:'center',formatter: function(value,row,index){
    		        	return me.auditLogStatusEnum[value];
    		        }},
    		        {field:'createTime',title:'创建时间',width:150,align:'center'},
    		        {field:'content',title:'审批内容',width:150,align:'center'},
    		        {field:'remark',title:'说明',width:300,align:'center'}
    		    ]]
    		});
    	});
    }
});
