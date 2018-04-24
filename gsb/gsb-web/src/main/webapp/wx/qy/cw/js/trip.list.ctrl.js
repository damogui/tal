org.netsharp.we.core.tripListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.template = '<div class="weui-form-preview__bd"> '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">出发地</label> '+
    					' <span class="weui-form-preview__value">{0}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">目的地</label> '+
    					' <span class="weui-form-preview__value">{1}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">开始时间</label> '+
    					' <span class="weui-form-preview__value">{2}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">结束时间</label> '+
    					' <span class="weui-form-preview__value">{3}</span> '+
    					' </div>  '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">备注</label> '+
    					' <span class="weui-form-preview__value">{4}</span> '+
    					' </div>  '+
    					'<div class="weui-form-preview__ft"></div>'+
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
    	this.invokeService('getTripByFormId', pars, function(result){
    		
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
    		html += me.template.format(item.origin,item.destination,item.startTime,item.endTime,item.memoto);
    	});
    	return html;
    }
});
