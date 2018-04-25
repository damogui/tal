org.netsharp.we.core.subsidyListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.template = '<div class="weui-form-preview__bd"> '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">补助类型</label> '+
    					' <span class="weui-form-preview__value">{0}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">补贴天数</label> '+
    					' <span class="weui-form-preview__value">{1}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">补贴标准</label> '+
    					' <span class="weui-form-preview__value">{2}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">金额</label> '+
    					' <span class="weui-form-preview__value">{3}</span> '+
    					' </div>  '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">说明</label> '+
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
    	this.invokeService('getSubsidyByFormId', pars, function(result){
    		
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
    		var subsidyType = SubsidyTypeDict.byKey(item.type);
    		alert(subsidyType);
    		var standard = item.standard /100;
    		var subsidyAmount = item.subsidyAmount /100;
    		html += me.template.format(subsidyType,item.countDay,standard,subsidyAmount,item.memoto);
    	});
    	return html;
    }
});
