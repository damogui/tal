System.Declare("org.netsharp.core");
org.netsharp.core.FeedbackCtrl = System.Object.Extends({
	ctor: function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.scrum.web.SupportFormPart";
		this.filePath = null;
	},
	invokeService : function(month, pars, callback) {
		
		if (this.jServiceLocator == null) {
			
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	init:function(){
		
		var me = this;
		var upload = new org.netsharp.controls.OSSUpload();
		upload.getButtonId = function(){
			
			return "button_upload";
		},
		upload.preview = function(path,file){
			
			if(System.isnull(path)){
				
				return;
			}
			me.filePath = path;
			var file = '<a target="_blank" href="'+path+'">'+file.name+'</a><i title="删除" onclick="$(\'#files\').empty();ctrl.filePath = null;" class="fa fa-remove"></i>';
			$("#files").html(file);
		}
		upload.init();
	},
	save:function(){
		
		var name = $('#name').val().trim();
		if(System.isnull(name)){
			
			layer.msg("请填写标题");
			return false;
		}
		
		if(name.length>100){
			
			layer.msg("标题长度不能超过100字");
			return false;
		}

		var content = $('#content').val().trim();
		if(System.isnull(content)){
			
			layer.msg("请填写反馈内容");
			return false;
		}
		
		if(content.length>500){
			
			layer.msg("反馈内容长度不能超过1000字");
			return false;
		}
		
		var me = this;
		this.invokeService("newInstance", [null], function (support) {
			  
			support.name = name;
			support.content = content;
			support.type = 5;
			support.ownerId = 1660;//系统升级后要重新设置值
			support.owner = {id:1660,name:'韩伟'};
			support.senderId = 1703;
			support.sender = {id:1703,name:'徐芳波'};
			support.estimateHours=0;
			support.filePath = me.filePath;
			me.doSave(support);
		});
	},
	doSave:function(support){
		
		this.invokeService("save", [support], function (support) {
			  
			layer.msg("提交成功，我们会尽快处理。");
			
			setTimeout(function(){

				window.top.layer.closeAll();
			},1000);
		});
	}
});