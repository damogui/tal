System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.FilePreviewCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    	this.orderProdId = null;
    },
    init:function(orderProdId){

    	this.orderProdId = orderProdId;
    	var me = this;
    	this.invokeService ("queryPreviewOrderProdTraceFiles", [orderProdId], function(list){
    		
    		me.bindData(list);
    	});
    },
    bindData:function(list){
    	
    	var me = this;
    	var builder = new System.StringBuilder();
    	$(list).each(function(i,item){

    		var url = item.file.url;
    		var isImage = me.isImage(url);
    		url = isImage==true?url:'http://gsb-public.oss-cn-beijing.aliyuncs.com/hive/8234e2f5d93bdf436d3ea8d671ab7699.jpg';
        	builder.append('<div class="file-preview-item">');
        	builder.append('	<div style="position:relative;">');
        	builder.append('		<div class="btn_top" class="btn btn-primary" onclick="filePreviewCtrl.topTraceFile('+item.id+')" >置顶</div>');
        	builder.append('		<a target="_blank" href="'+ item.file.url +'">');
        	builder.append('			<img src="'+url+'">');
        	builder.append('		</a>');
        	builder.append('	</div>');
        	builder.append('	<p>'+item.creatorId+'  '+item.createTime+'</p>');
        	builder.append('	<p>材料名称：'+item.prodWorkflowFileName+'</p>');
        	builder.append('	<p>备注：'+item.remark+'</p>');
        	builder.append('</div>');
    	});
    	$('#file_preview_panel').html(builder.toString());
    },
    isImage:function(imageUrl){

	   var suffixIndex = imageUrl.lastIndexOf(".");  
	   var suffix = imageUrl.substring(suffixIndex+1).toUpperCase(); 
	   
	   if(suffix!="BMP" && suffix!="JPG" && suffix!="JPEG" && suffix!="PNG" && suffix!="GIF"){
		   
	     return false;
	   } 
	   return true;
    },
    topTraceFile:function(traceFileId){
    	
    	var me = this;
    	this.invokeService ("topTraceFile", [me.orderProdId,traceFileId], function(list){
    		
    		me.init(me.orderProdId);
    	});
    }
});