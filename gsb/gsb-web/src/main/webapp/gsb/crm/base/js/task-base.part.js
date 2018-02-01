
var FollowBox =
    {
        info: function (id) {
        	var intentionOption = getIntentionOption(id);
        	PandaHelper.openDynamicForm({
    			title:'任务跟进',
    			width:560,
    			height:480,
    			items:[{id:'allot_intention_name',
    				title:'任务质量',
    				type:'combogrid',
    				className:'',
    				option:intentionOption,
    				width:170},
    					
    				{id:'followTime',
    					title:'下次跟进时间',
    					type:'datebox',
    					className:'',
    					disabled:true},
    					 
    				{id:'amount',
    					title:'估计签单金额',
    					type:'numberbox',
    					className:''},	
    					 
    				{id:'txtNote',
    					title:'内容',
    					type:'textarea',
    					height:130,
    					width:300,
    					className:''}
    			],
    			explain:'',
    			notice:'',
    			callback:function(index, layero){
    				if(System.isnull($('#allot_intention_name').combogrid('getValue'))){
    					$('#allot_intention_name').next().children("input[type='text']").validatebox({
    		    		    required: true,
    		    		    missingMessage: '请输入任务质量'
    		    		});
    					return false;
    				}
    				var g = $('#allot_intention_name').combogrid('grid');
    		    	var r = g.datagrid('getSelected');	
    		    	var code = r.code;
    		    	var time = followTimeValida(code);
    		    	var amount = followAmountValida(code);
    		    	var getqualityId = r.id;
    		    	if(!time && System.isnull($("#followTime").val())){
    					$('#followTime').next().children("input[type='text']").validatebox({
    		    		    required: true,
    		    		    missingMessage: '请输入下次跟进时间'
    		    		});
    					return false;
    		    	};
    		    	if(!amount && System.isnull($("#amount").val())){
    		    		$('#amount').next().children("input[type='text']").validatebox({
    		    		    required: true,
    		    		    missingMessage: '请输入估计签单金额'
    		    		});
    					return false;
    		    	};
    				var getNote = followNoteValida(code);
    				if (!getNote && System.isnull($("#txtNote").val())) {
    					$('#txtNote').validatebox({
    		    		    required: true,
    		    		    missingMessage: '请输入内容'
    		    		});
    					return false;
    				};
    				//var ss = id +"|"+ getqualityId +"|"+ $("#followTime").val() +"|"+ $("#amount").val() +"|"+ getNote;
    				//me.doFollowService(id,getqualityId,$("#followTime").val(),$("#amount").val(),getNote);
    			}
    		});
        	
        }
    };
function getIntentionOption(id){
	var result;
	var intentionOption = {columns : [ [ {
			field : 'intentionCategory',
			title : '分类',
			width : 30
		},{
			field : 'code',
			title : '编码',
			width : 30
		},{
			field : 'name',
			title : '名称',
			width : 50
		}] ],
		url : '\/panda\/rest\/reference?code=NCustomerTaskQuality',
		idField : 'id',
		textField : 'name',
		width : 300,
		fitColumns : true,
		panelWidth : 450,
		panelHeight : 310,
		pagination : true,
		pageSize : 10,
		mode : 'remote',
		multiple : false,
		onChange : function(newValue, oldValue) {
			var g = $('#allot_intention_name').combogrid('grid');
	    	var r = g.datagrid('getSelected');	
	    	var code = r.code;
	    	
	    	result = followProductValida(code);
	    	/*if(!followProductValida(code)){
	    		alert(11);
	    		this.invokeService("hasProduct", [id],function(data) {
	    			if(!data){
	    				IMessageBox.toast('请先添加意向产品');
	    			}
	    			return;
	    		});
	    	}*/
		}};
	
	return intentionOption;
};
/**
 * 任务跟进，验证跟进时间
 * @returns
 */
function followTimeValida(code){
	//跟进时间验证
	var timeResult = true;
	var timeRequired = "XA0A1A2A3A4B1B2";
	if(timeRequired.indexOf(code)>-1){
		//必填
		timeResult = false;
	};
	return timeResult;
}
/**
 * 任务跟进，验证签单金额
 * @returns
 */
function followAmountValida(code){
	//签单金额验证
	var amountResult = true;
	var amountRequired = "A0A1A2A3A4B1";
	if(amountRequired.indexOf(code)>-1){
		//必填
		amountResult = false;
	};
	return amountResult;
}
/**
 * 任务跟进，验证内容
 * @returns
 */
function followNoteValida(code){
	//内容验证
	var noteResult = true;
	var noteRequired = "B1B3C1C2C3D1D2";
	if(noteRequired.indexOf(code)>-1){
		//必填
		noteResult = false;
	};
	return noteResult;
}
/**
 * 任务跟进，验证意向产品
 * @returns
 */
function followProductValida(code){
	var productResult = true;
	var productRequired = "A0A1A2A3A4B1B3C1C2C3";
	if(productRequired.indexOf(code)>-1){
		//必填
		productResult = false;
	};
	return productResult;
}