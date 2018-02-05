org.netsharp.we.core.listCtrl = org.netsharp.we.core.view.Extends({
    ctor: function () {
    	this.base();
    	this.listContainer = '.weui_body';
    	this.listId = "#list";
    	this.paging = {};
    	this.paging.pageSize = 20;
    	this.paging.pageIndex = 1;
    	this.paging.totalCount = 0;
    	this.paging.pageCount = 0;
    	this.paging.loadingCount = 0;//已经加载的条数
    	this.loading = false;
    	this.template = '';
    },
    init:function(){
    	
    	//初始化下拉刷新
    	this.initPullRefresh();
    	
    	//查询
    	this.query();
    },
    initPullRefresh:function(){
    
      var me = this;
	  $(this.listContainer).pullToRefresh();
	  $(this.listContainer).on('pull-to-refresh', function (done) {
		
	     me.pageIndex = 1;
		 me.query();
	  });
	  
    },
    initLoadmore:function(){

    	var me = this;
    	$(document.body).infinite(100);

    	var loading = false;  //状态标记
    	$(document.body).infinite(100).on("infinite", function() {
    	
    	  if(me.loading) return;
    	  $('#loadmore').show();
    	  me.paging.pageIndex += 1;
    	  me.loading = true;
    	  me.query();
    	});

    },
    query:function(){

    },
    queryAfter:function(){
    	
    	//初始化下拉刷新
    	$(this.listContainer).pullToRefreshDone();
    	
    	//无数据时显示信息
    	if(this.paging.totalCount==0){

    		$('#nodata').show();
    		return;
    	}
    	
    	$('#nodata').hide();

    	//计算总页数
    	var remainder = this.paging.totalCount%this.paging.pageSize;
    	var count = this.paging.totalCount/this.paging.pageSize;
    	this.paging.pageCount = remainder==0?count:count+1;
    	
    	//初始化滚动加载
    	$('#loadmore').hide();
  	    this.loading = false;
    	if(this.paging.totalCount>this.paging.loadingCount){

        	this.initLoadmore();
    	}else{
    		
    		$(document.body).destroyInfinite();
    	}
    }
});