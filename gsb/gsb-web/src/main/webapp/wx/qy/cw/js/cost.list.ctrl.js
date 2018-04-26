org.netsharp.we.core.costListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	this.template = '<div class="weui-form-preview__bd"> '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">费用归属部门</label> '+
    					' <span class="weui-form-preview__value">{0}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">费用类型</label> '+
    					' <span class="weui-form-preview__value">{1}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">发票类型</label> '+
    					' <span class="weui-form-preview__value">{2}</span> '+
    					' </div>'+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">费用金额</label> '+
    					' <span class="weui-form-preview__value">{3}</span> '+
    					' </div>  '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">费率</label> '+
    					' <span class="weui-form-preview__value">{4}</span> '+
    					' </div>  '+
    					' <div class="weui-form-preview__item"> '+
    					' <label class="weui-form-preview__label">税费金额</label> '+
    					' <span class="weui-form-preview__value">{5}</span> '+
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
    	this.invokeService('getCostByFormId', pars, function(result){
    		
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
    		var invoiceType = invoiceTypeDict.byKey(item.invoiceType);
    		var detailMoney = item.detailMoney/100;
    		var taxRate = 0;
    		if(item.invoiceType == 2){
    			taxRate = TaxRateTypeDict.byKey(item.taxRate);
    		}
    		var detailTaxation = item.detailTaxation/100;
    		html += me.template.format(item.pathName,item.costTypeName,invoiceType,detailMoney,taxRate,detailTaxation);
    	});
    	return html;
    }
});
