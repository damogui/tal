System.Declare("com.gongsibao.crm.web");
com.gongsibao.crm.web.TaskCtrl = System.Object.Extends({
	ctor : function() {
		
		this.taskUrl = null;
		this.customerUrl = null;
	},
	init:function(){

		this.resize();
		this.initTaskIframe();
	},
	initTaskIframe:function(){
		
		var taskId = System.Url.getParameter('taskId');
		var url = this.taskUrl + '?id='+taskId;
		$('#iframe_task').attr('src',url);
	},
	cutCustomerIframe:function(){
		
		var customerId = System.Url.getParameter('customerId');
		var url = $('#iframe_customer').attr('src');
		if(System.isnull(url)){

			url = this.customerUrl + '?id='+customerId;
			$('#iframe_customer').attr('src',url);
		}
	},
	resize:function(){
		
		var tabsId = '#tt';
		$(tabsId).tabs('resize',{  
			plain : false,  
			boder : false,  
			width:$(window).width(),  
			height:$(window).height()
		});
		
		var me = this;
		$(tabsId).tabs({onSelect:function(title,index){
				
			if(index ==1){
				
				me.cutCustomerIframe();
			}
		}});
	}
});