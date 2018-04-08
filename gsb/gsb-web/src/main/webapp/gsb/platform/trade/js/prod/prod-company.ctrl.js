System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.CompanyCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	var orderProd = this.mainCtrl.orderProd;
    	var companyName = orderProd.companyIntention != null ? orderProd.companyIntention.companyName :'-';
    	$("#relevance_companyName").text(companyName);
    	
    	if(orderProd.companyIntention != null){

    		this.showBtn();
    	}else{
    		
    		this.hideBtn();
    	}
    },
    showBtn:function(){
    	
		$('#btn_add_relevance_company').hide();
		$('#btn_edit_company').show();
		$('#btn_cancel_relevance_company').show();
    },
    hideBtn:function(){
    	

		$('#btn_add_relevance_company').show();
		$('#btn_edit_company').hide();
		$('#btn_cancel_relevance_company').hide();
    },
    addRelevanceCompany:function(){
    	
    	var me = this;
    	//添加关联公司
    	var companyCtrl = new com.gongsibao.trade.web.SelectCompanyCtrl();
    	companyCtrl.open('添加关联公司',false,function(companys){
    		
    		if(companys.size == 0){
    			
    			return;
    		}
    		var company = companys[0];
    		me.invokeService ("addRelevanceCompany", [me.mainCtrl.orderProd.id,company.id], function(data){
        		
    			me.mainCtrl.orderProd.companyIntention = company;
    			me.mainCtrl.orderProd.companyId= company.id;
    			me.mainCtrl.bindData(me.mainCtrl.orderProd);
    			me.init();
        	});
    		
    	});
    },
    editCompany:function(){
    	
    	//编辑公司(未实现)
    	IMessageBox.info("开发中...");
    },
    cancelRelevanceCompany:function(){

    	//取消关联
    	var me = this;
    	IMessageBox.confirm("您确定要取消该订单明细（产品）的操作公司吗?",function(r){
    		
    		if(r){

        		me.invokeService ("cancelRelevanceCompany", [me.mainCtrl.orderProd.id], function(data){
            		
        			me.mainCtrl.orderProd.companyIntention = null;
        			me.mainCtrl.orderProd.companyId= 0;
        			me.mainCtrl.bindData(me.mainCtrl.orderProd);
        			me.init();
            	});
    		}
    	});
    }
});