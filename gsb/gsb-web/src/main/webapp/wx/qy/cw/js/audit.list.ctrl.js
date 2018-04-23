org.netsharp.we.core.auditListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.template = '<div class="weui-form-preview__bd"> '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">审批人</label> '+
    					' <span class="weui-form-preview__value">{0}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">审批状态</label> '+
    					' <span class="weui-form-preview__value">{1}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">审批意见</label> '+
    					' <span class="weui-form-preview__value">{2}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">审批时间</label> '+
    					' <span class="weui-form-preview__value">{3}</span> '+
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
    	this.invokeService('getAuditByFormId', pars, function(result){
    		
    		if(result.length==0){
        		$('#nodata').show();
        		return;
    		}
    		var html = me.getTemplateHtml(result);
    		$(me.listId).append(html);
            //$(me.listId).setTemplateElement("template").processTemplate(result);
    	});
    },
    getTemplateHtml : function (rows){
    	var html = '';
    	var me = this;
    	$(rows).each(function(i,item){
    		var statusText = auditStatusDict.byKey(item.status);
    		var memoto = System.isnull(item.memoto)?"":item.memoto ;
    		var updateTime = System.isnull(item.updateTime)?"":item.updateTime;
    		html += me.template.format(item.employee.name,statusText,memoto,updateTime);
    	});
    	return html;
    }
});
