org.netsharp.we.core.fileListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.template = ' <div class="weui-cell"> '+
    					' <div class="weui-cell__bd"> '+
    					' <p>{0}</p>'+
    					' </div>'+
    					' </div> <div class="weui-form-preview__ft"></div>'+
    					'</div>';
    },
    init:function(){
    	//查询
    	this.query();
    },
    query:function(){
    	
    	var me = this;
    	var formId = this.queryString('formId');
    	var formType = this.queryString('formType');
    	//查询
    	var pars = [formId,formType];
    	this.invokeService('getByTabNameFormId', pars, function(result){
    		
    		if(result.length==0){
        		$('#nodata').show();
        		return;
    		}
    		var html = me.getTemplateHtml(result);
    		$(me.listId).append(html);
    	});
    },
    getTemplateHtml : function (rows){
    	var html = '';
    	var me = this;
    	$(rows).each(function(i,item){
    		html += me.template.format(item.name);
    	});
    	return html;
    }
});
