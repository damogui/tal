org.netsharp.we.core.franchiseeListCtrl = org.netsharp.we.core.listCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.template = '<a class="weui-cell weui-cell_access" href="franchiseeDetail?id={0}"><div class="weui-cell__bd"><p>{1}</p></div><div class="weui-cell__ft">{2}</div></a>';
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
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
    	$(rows).each(function(i,item){
    		
    		html += me.template.format(item.id,item.name,item.trackProgress);
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