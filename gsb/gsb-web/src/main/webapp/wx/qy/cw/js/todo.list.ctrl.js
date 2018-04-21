org.netsharp.we.core.TodoListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.template = '<a class="weui-cell weui-cell_access" href="{0}"> '+
    		            '<div class="weui-cell__bd"><p>{1}</p></div><div class="weui-cell__ft">{2}</div> '+
    					'</a>';
    	this.service = 'com.gongsibao.cw.web.wx.TodoListController';
    	
    },
    query:function(){
    	
    	var me = this;
    	var employeeId = this.queryString('employeeId');
    	var searchKeyWord = $("#searchKeyWord").val().trim();
    	console.log("当前页："+this.paging.pageIndex);
    	//查询
    	var pars = [employeeId,searchKeyWord,this.paging.pageIndex,this.paging.pageSize];
    	this.invokeService('query', pars, function(result){
    		
    		me.paging.loadingCount = me.paging.loadingCount + result.rows.length;
    		me.paging.totalCount = result.total;
            //$(me.listId).setTemplateElement("template", null, { filter_data: false }).processTemplate(result.rows);
    		var html = me.getTemplateHtml(result.rows);
    		$(me.listId).append(html);
            me.queryAfter();
    	});
    },
    getTemplateHtml:function(rows){
    	var me = this;
    	var html = '';
    	var billUrl = "";
    	$(rows).each(function(i,item){
    		var formTypeText = formTypeDict.byKey(item.formType);
    		if(item.formType == 3 ){
    			billUrl = "loanDetail?id="+item.formId;
    		}
    		if(item.formType == 4 ){
    			billUrl = "expenseDetail?id="+item.formId;
    		}
    		html += me.template.format(billUrl,formTypeText,item.code);
    	});
    	return html;
    },
    filter:function(){
    	
    	var searchKeyWord = $("#searchKeyWord").val().trim();
    	if(System.isnull(searchKeyWord)){
    		
    		return;
    	}
    	
    	$(this.listId).html('');
    	this.query();
    },
    cancel:function(){
    	
    	$("#searchKeyWord").val("");
    	$(this.listId).html('');
    	this.query();
    }
});