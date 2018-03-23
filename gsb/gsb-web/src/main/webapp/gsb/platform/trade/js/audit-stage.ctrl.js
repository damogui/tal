System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditStageCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.initializeDetailList = new System.Dictionary();
    	//获取枚举
    	this.auditLogStatusEnum = PandaHelper.Enum.get('com.gongsibao.entity.bd.dic.AuditLogStatusType');
    	this.service = 'com.gongsibao.trade.web.audit.AuditStageController';
    },
    initData:function(){
    	var orderId = this.queryString('id');
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
		    		me.auditLogInfor(orderId);
		    	}
		    }
    	});
    	me.initializeDetailList.add('分期信息',this.installInfo(orderId));
    },
    installInfo: function(id){
    	//tab-获取分期信息
    	this.invokeService ("getSoOrder", [id], function(data){
    		var stageAmount = ((data.payablePrice - data.paidPrice)/100).toFixed(2);
    		var builder = new System.StringBuilder();
    		builder.append('<tr>');
    		builder.append('<td class="label_td"><label>分期期数：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ data.stageNum +'</label>');
    		builder.append('</td>');
    		builder.append('<td class="label_td"><label>分期金额：</label></td>');
    		builder.append('<td class="control_td">');
    		builder.append('<label>'+ stageAmount +'</label>');
    		builder.append('</td>');
    		builder.append('</tr>');
    		
    		$(data.stages).each(function(i,item){
    			builder.append('<tr>');
        		builder.append('<td class="label_td"><label>'+item.instalmentIndex+'期付款：</label></td>');
        		builder.append('<td class="control_td">');
        		builder.append('<label>'+ (item.amount/100).toFixed(2) +'</label>');
        		builder.append('</td>');
        		builder.append('<td class="label_td"><label>付款比例：</label></td>');
        		builder.append('<td class="control_td">');
        		builder.append('<label>'+ item.percentage +'</label>');
        		builder.append('</td>');
        		builder.append('</tr>');
        	});
    		$("#stage_info_grid").append(builder.toString());
    	});
    },
    auditLogInfor: function(id){
    	//tab-审批进度
    	var me = this;
    	this.invokeService("getAuditLogList", [id,1047], function(data){    		
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