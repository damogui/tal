System.Declare("org.netsharp.core");
org.netsharp.core.FeedbackCtrl = System.Object.Extends({
	ctor: function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.attachment.AttachmentListPart";
		this.entityId = "";
		this.foreignKey = 0;
	},
	init:function(){
		
		var upload = new org.netsharp.controls.OSSUpload();
		upload.getButtonId = function(){
			
			return "button_upload";
		},
		upload.preview = function(path,file){
			
			if(System.isnull(path)){
				
				return;
			}
			var file = '<a target="_blank" href="'+path+'">'+file.name+'</a><i title="删除" onclick="$(\'#files\').empty();" class="fa fa-remove"></i>';
			$("#files").html(file);
		}
		upload.init();
	},
	save:function(){
		
		var name = $('#name').val().trim();
		if(System.isnull(name)){
			
			layer.msg("请填写标题");
			return;
		}


		var content = $('#content').val().trim();
		if(System.isnull(content)){
			
			layer.msg("请填写反馈内容");
			return;
		}
		
		if(content.length>500){
			
			layer.msg("反馈内容长度不能超过500字");
			return;
		}
	}
});