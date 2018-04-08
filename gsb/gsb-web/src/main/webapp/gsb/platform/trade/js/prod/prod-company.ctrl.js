System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.CompanyCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	var orderProd = this.mainCtrl.orderProd;
    	var companyName = orderProd.companyIntention != null ? orderProd.companyIntention.name :'-';
    	$("#relevance_companyName").text(companyName);
    	
    	if(orderProd.companyIntention != null){
    		
    		$('#btn_add_relevance_company').hide();
    		$('#btn_edit_company').show();
    		$('#btn_cancel_relevance_company').show();
    		
    	}else{
    		
    		$('#btn_add_relevance_company').show();
    		$('#btn_edit_company').hide();
    		$('#btn_cancel_relevance_company').hide();
    	}
    },
    addRelevanceCompany:function(){
    	
    	//添加关联公司
    	
    },
    editCompany:function(){
    	
    	//编辑公司
    	
    },
    cancelRelevanceCompany:function(){
    	
    	//取消关联
    }
});