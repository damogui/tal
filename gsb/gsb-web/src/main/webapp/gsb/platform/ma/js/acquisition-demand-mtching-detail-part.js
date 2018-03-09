System.Declare("com.gongsibao.ma.web");
com.gongsibao.ma.web.AcquisitionDemandMatchingDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {

    ctor: function () {
        this.base();
    },
    refresh: function () {
    	
    	if(this.relationItem.entityState == EntityState.New){

        	IMessageBox.warning("请先保存再刷新匹配信息！",function(){
    		
        	});
    		return;
    	}
    	
    	var me = this;
    	var acquisitionDemandId = this.relationItem["id"];
        this.invokeService("refresh", [acquisitionDemandId], function (data) {
        	
        	//暂时先刷新整个表单，后续再实现分页
        	me.parent.byId(acquisitionDemandId);
       });
    },
    openComparisonPage:function(value,row){
    	
    	var acquisitionDemandId = row.acquisitionDemandId;
    	var sellingDemandId = row.sellingDemandId;

		return '<a href="javascript:controllermatchingDetails.doOpen(\''+acquisitionDemandId+'\', \''+sellingDemandId+'\');">查看</a>';
    },
    doOpen:function(acquisitionDemandId,sellingDemandId){
    	
    	var me = this;
    	var url = '/gsb/platform/ma/demand_comparison.html';
		url = System.Url.join(url, "acquisitionDemandId=" + acquisitionDemandId);
		url = System.Url.join(url, "sellingDemandId=" + sellingDemandId);
		
    	window.top.layer.open({
  		  type: 2,
  		  title: '匹配详情',
  		  fixed: false,
  		  maxmin: true,
  		  shadeClose:true,
  		  area: ['900px', '700px'],
  		  content: url,
  		  btn: ['生成订单', '关闭'],
  		  yes: function(index, layero){

  	        me.invokeService("createOrder", [acquisitionDemandId,sellingDemandId], function (orderId) {

  	  			window.top.layer.close(index);
  	        	layer.msg('生成成功', {time: 3000, icon:1});
  	        	var url ='/panda/ma/order/form?id='+orderId;
  	        	window.open(url);
  	        });
  		  },
  		  btn2: function(index, layero){

  		  }
    	});
    }
});
