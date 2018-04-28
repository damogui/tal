System.Declare("com.gongsibao.crm.web");
//所有客户列表基类
com.gongsibao.crm.web.BaseCustomerListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
		this.isPlatform = 1;
		this.addUrl = null;
		this.editUrl = null;
		this.addTaskUrl=null;
	},
	add:function(){
		
		window.open(this.addUrl);
	},
	detail:function(id){
		
		this.edit(id);
	},
	edit : function(id) {

		window.open(this.editUrl+"?id="+id);
	},
	doubleClickRow : function(index, row) {

		this.edit(row.id);
	},
	addTask:function(){
		var me = this;
		var row = this.getSelectedItem();
		if(row == null){
			return;
		}
		var customerId = row.id;
		me.isHaveTask(customerId,function(ownerName){
			if(ownerName == ''){
				var url=me.addTaskUrl+'?fk=customerId:'+customerId;
		    	layer.open({
		  		  type: 2,
		  		  title: '新增商机',
		  		  fixed: false,
		  		  maxmin: true,
		  		  shadeClose:true,
		  		  area: ['98%','98%'],
		  		  content: url,
		  		  cancel: function(){ 

				  }
		  	    });
			}else{
				IMessageBox.info("您不是客户所拥有者，无法创建商机，请联系【" + ownerName + "】创建");
			}
		});
    	
	},
	isHaveTask:function(customerId,callBack){
    	var me = this;
    	if(this.isPlatform==0){
    		me.invokeService("isHaveTask", [customerId], function (ownerName) {
				return callBack(ownerName);        	
   	        });
    	}else{
    		//平台不受影响
    		return callBack("");     
    	}
    },
	openMember : function(customerId,isSendSms){

		var msg = isSendSms==true?"确定要开通会员吗？该操作会向客户发送短信":"确定要静默开通会员吗？该操作<span style='color:red;'>不会向客户发送短信</span>，客户将无法直接获取自己的账号密码";
		var me = this;
		IMessageBox.confirm(msg,function(r){
			
			if(r===true){

				me.invokeService("openMember", [customerId,isSendSms],function(data) {
					if(data){
						IMessageBox.toast('开通成功');
						me.reload();
						layer.closeAll();
					}else{
						IMessageBox.toast('开通失败,稍后再试');
					}
				});
			}
		});
	},
	contactFormatter:function(value,row,index,typeName){
		
		if(value){
		  var ctrl = workspace.parts.byIndex(0).key;
		  return '<sapn>'+PandaHelper.dimString(value)+'</span><i class="fa fa-eye" onclick="'+ctrl+'.showPlaintext(\''+row.id+'\',\''+value+'\',\''+typeName+'\',this);"></i>';
		}
	},
	showPlaintext:function(customerId,value,typeName,obj){
		
		$(obj).parent().text(value);
		var serviceLocator = new org.netsharp.core.JServiceLocator();
		serviceLocator.invoke(this.context.service, "recordLookLog",[customerId,typeName]);
	}
});
