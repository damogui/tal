System.Declare("org.netsharp.core");
org.netsharp.core.attachmentListController = System.Object.Extends({
	ctor: function() {
		this.jServiceLocator = null;
		this.service = "org.netsharp.attachment.AttachmentListPart";
		this.service2 = "com.gongsibao.igirl.tm.web.TradeMarkListPart";
		this.entityId = "";
		this.foreignKey = 0;
	},
	getSelections : function() {

		var rows = $("#attachment_grid").datagrid('getSelections');
		return rows;
	},
	getSelectionIds : function() {

		var rows = this.getSelections();
		var ids = new Array();
		for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			ids.push(row.id);
		}

		return ids.join('_');
	},

	getSelectionCount : function() {

		return this.getSelections().length;
	},
    queryString: function (name) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }

        return result[1];
    },
	invoke : function(month, pars, callback) {
		
		if (this.jServiceLocator == null) {
			
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service, month, pars, callback);
	},
	invoke2 : function(month, pars, callback) {
		
		if (this.jServiceLocator == null) {
			
			this.jServiceLocator = new org.netsharp.core.JServiceLocator();
		}
		this.jServiceLocator.invoke(this.service2, month, pars, callback);
	},
	init:function(){

		this.initDataGrid();
		this.initUpload();
	},
	initDataGrid:function(){
		
		this.entityId = this.queryString("entityId");
		this.foreignKey = this.queryString("foreignKey");
		
		var me = this;
		var pars = [];
		pars.push(this.entityId);
		pars.push(this.foreignKey);
		this.invoke("query", pars, function(data) {

			$("#attachment_grid").datagrid({
				height:570,
				rownumbers:true,
				 columns:[[
				           {field:'name',title:'名称',width:550},
				           {field:'fileExtend',title:'扩展名',width:50,align:"right"},
				           {field:'downLoadCount',title:'下载次数',width:60,align:"center"},
				           {field:'alias',title:'操作',width:60,align:"center",formatter:function(value,rowData,rowIndex){
				        	   //return "<a href='/download?name="+rowData.name+"&path="+rowData.path+"' onclick='listController.updateDownLoadCount("+rowData.id+");'>下载</a>";
				        	   
				        	   return "<a target='_blank' href='"+rowData.path+"' onclick='listController.updateDownLoadCount("+rowData.id+");'>下载</a>";
				           }},
				           {field:'creator',title:'上传人',width:60,align:"center"},
				           {field:'createTime',title:'上传时间',width:130,align:"center"}				           
				       ]],
				data:data
			});
		});
	},
	initUpload:function(){
		var upload = new org.netsharp.controls.AttachmentUpload();
		upload.parent = this;
		upload.init();
	},
  save: function (path,fileType,fileName) {
    	var me = this;
    	    //根据文件名称，获取商标号,按照商标号获取商标,构造
    	if(me.foreignKey!=""){
	    	        var entity = {
									name:fileName,
									fileExtend:fileType,
									path:path,
									viewCount:0,
									downLoadCount:0,
									foreignKey:me.foreignKey,
									entityId:me.entityId,
									entityState:EntityState.New
								};
	    	    //按照markCode，state去更新商标状态并返回商标
	    	this.invoke2("updateMarkStateByUploadFiles",[entity,"",""],function(data){
	    	        	me.initDataGrid();
	    	
	    	    });
    	    }else{
    	       lastIndex=fileName.lastIndexOf(".");
				    	var farray=fileName.substring(0,lastIndex).split("-");
				    	    if(farray.length==3){
				    	    	var state=farray[2];
							    	var markCode=farray[0];
							    	var entity = {
															name:fileName,
															fileExtend:fileType,
															path:path,
															viewCount:0,
															downLoadCount:0,
															foreignKey:me.foreignKey,
															entityId:me.entityId,
															entityState:EntityState.New
														};
							    	    //按照markCode，state去更新商标状态并返回商标
							    	this.invoke2("updateMarkStateByUploadFiles",[entity,markCode,state],function(data){
							    	        	me.initDataGrid();
							    	
							    	    });
				    	        
				    	    }
				    
    	   
    	    }
    	    
    	
    },
    
    updateDownLoadCount:function(id){
    	
    	var me = this;
		this.invoke("updateDownLoadCount", [id], function(data) {
			me.initDataGrid();
		});
    },
    
	remove:function(){
		
		var count = this.getSelectionCount();

		if (count <= 0) {

			IMessageBox.warning("请选择要删除的记录！");

			return;
		}

		var me = this;
		IMessageBox.confirm("确认要删除选中的记录吗？", function(istrue) {

			if (istrue) {
				
				var ids = me.getSelectionIds();

				var pars = [];
				pars.push(ids);

				me.invoke("delete", pars, function(data) {
					me.initDataGrid();
				});
			}
		});
	}
});
org.netsharp.controls.OSSUpload = org.netsharp.controls.QiNiuUpload.Extends({
	ctor: function() {
		this.base();
		this.config=null;
	},
	getButtonId:function(){
		
		return "button_" + this.propertyName;
	},
	init:function(){
	  var buttonId = this.getButtonId();
		var me = this;
	  var uploader =null;
	    //获取发送的配置
	   var serviceLocator = new org.netsharp.core.JServiceLocator();
	   serviceLocator.invoke("org.netsharp.web.AliyunOssController", "getOssConfig", [],function(data) {
        me.config=data;
		    uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button :buttonId, 
				flash_swf_url : '/package/plupload/js/Moxie.swf',
				silverlight_xap_url : '/package/plupload/js/Moxie.xap',
			    url : 'http://oss.aliyuncs.com',
			    multi_selection: true,
				init: {
					PostInit: function() {

					},
					FilesAdded: function(up, files) {
						uploader.start();
					},
					beforeUpload:function(up,file){
					   //在上传每个文件前设置每个文件上传的参数
						me.setUploadParam(file,up);
					},
					UploadProgress: function(up, file) {
						IMessageBox.loading.show();
						setTimeout(function(){
						   var progress=Math.round(up.total.uploaded/up.files.length)*100;
						   $("#p").progressbar('setValue',progress);
						},10);
					
					},
					FileUploaded: function(up, file, info) {	
						IMessageBox.loading.hide();
			            if (info.status == 200)
			            {
			            	var path = up.getOption().url+'/'+ up.getOption().multipart_params.key;
							     // ("#" + me.propertyName).filebox("setText", path);
							      me.preview(path,file);
			            }
			      else
			            {
			            	IMessageBox.info(info.response);
			            } 
					},
					UploadComplete: function(up, files) {
						// Called when all files are either uploaded or failed
		                
					},
	
					Error: function(up, err) {
	
						IMessageBox.error(err.response);
					}
				}
			});
		  uploader.init();
		},null, false);

	},
	setUploadParam:function (file,up)
	{
		  var config = this.config;
			var suffix= this.getSuffix(file.name);
			var filename=  this.randomString() + suffix;
	        var newMultipartParams = {
	            'key' : config.dir + '_' + filename,
	            'policy': config.policyBase64,
	            'OSSAccessKeyId': config.accessid, 
	            'success_action_status' : '200',
	            'signature': config.signature,
	        };

	        up.setOption({
	            'url': config.host,
	            'multipart_params': newMultipartParams
	        });
	
	},
	randomString:function (len) {
	　　len = len || 32;
	　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
	　　var maxPos = chars.length;
	　　var pwd = '';
	　　for (i = 0; i < len; i++) {
	    　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	    }
	    return pwd;
	},
	getSuffix:function (filename) {
	    pos = filename.lastIndexOf('.')
	    suffix = ''
	    if (pos != -1) {
	        suffix = filename.substring(pos)
	    }
	    return suffix;
	}
});
org.netsharp.controls.AttachmentUpload = org.netsharp.controls.OSSUpload.Extends({
	ctor: function() {
		this.base();
		this.parent = null;
	},
	getButtonId:function(){
		
		return "btn_upload";
	},
	preview:function(path,file){
		
		if(System.isnull(path)){
			return;
		}
		//debugger;
		this.parent.save(path,file.type,file.name);
	}
});
