System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.AuditStageCtrl = com.gongsibao.trade.web.AuditBaseCtrl.Extends({
    ctor: function () {
    	
    	this.base();
    	this.service = 'com.gongsibao.trade.web.audit.AuditStageController';
    },
    initData:function(){
    	var orderId = this.queryString('id');
    	//异步加载？1.获取分期信息
    	this.invokeService ("getSoOrder", [orderId], function(data){
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
    	//2.获取审批进度
    	this.invokeService ("getAuditLogList", [orderId,1047], function(data){
    		var builder = new System.StringBuilder();
    		builder.append('<tr>');
    		builder.append('<td width="10%">创建人名称</td>');
    		builder.append('<td width="10%">审核状态</td>');
    		builder.append('<td width="20%">创建时间</td>');
    		builder.append('<td width="20%">审批内容</td>');
    		builder.append('<td>说明</th>');
    		builder.append('</tr>');
    		$(data).each(function(i,item){
    			builder.append('<tr>');
        		builder.append('<td>' + item.creator + '</td>');
        		builder.append('<td>' + getStatus(item.status) + '</td>');
        		builder.append('<td>' + item.createTime + '</td>');
        		builder.append('<td>' + item.content + '</td>');
        		builder.append('<td>' + item.remark + '</td>');
        		builder.append('</tr>');
    		});
    		$("#audit_progress_grid").append(builder.toString());
    	});
    }
});

//判断审核状态
function getStatus(status){
	var statString = '待审核';
	switch (status){
	case 1051:
		statString = "待审核";
	  break;
	case 1052:
		statString = "审核中";
	  break;
	case 1053:
		statString = "驳回审核";
	  break;
	case 1054:
		statString = "审核通过";
	  break;
	case 1055:
		statString = "排队";
	  break;
	default:
		statString = "关闭";
	}
	return statString;
}
