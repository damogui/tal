System.Declare("com.gongsibao.ma.web");
com.gongsibao.ma.web.SellingDemandFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    hasDepositChange:function(checked){
		
		if(checked===true){
			
			$("#depositAmount").numberbox('enable').numberbox('enableValidation');
		}else{
			
			$("#depositAmount").numberbox('disable').numberbox('disableValidation').numberbox('clear');
		}
	},
	hasBranchCompanyChange:function(checked){

		if(checked===true){
			
			$('#tabcenter').tabs('enableTab', '分公司');
		}else{
			$('#tabcenter').tabs('disableTab', '分公司');
			
			//清空分公司明细 controllerbrancheCompanyDetails 
			
		}
	},
	hasSubsidiaryCompanyChange:function(checked){

		if(checked===true){
			
			$('#tabcenter').tabs('enableTab', '子公司');
		}else{
			$('#tabcenter').tabs('disableTab', '子公司');
			
			//清空子公司明细  controllersubdiaryieCompanyDetails
		}
	},
    databindafter:function(){
    	
    	var url = window.location.href;
    	if(url.indexOf("channel")!=-1){
    		
    		var registDate = $('#registDate').val();
    		if(registDate){
    			
    			registDate = registDate.substring(0,4)+'年';
    			$('#registDate').val(registDate);
    		}
    	}
    },
    registDateBeginChange:function(date){
    	
    	var endDate = $("#registDateEnd").datebox('getValue');
    	if(System.isnull(endDate)){
    		
    		return;
    	}

    	var beginDate = date.format('yyyy-MM-dd');
    	var yearCount = yearDifference(beginDate,endDate);
    	$('#registYear').numberbox('setValue',yearCount);
    },
    registDateEndChange:function(date){
    	
    	var beginDate = $("#registDate").datebox('getValue');
    	if(System.isnull(beginDate)){
    		
    		return;
    	}

    	var endDate = date.format('yyyy-MM-dd');
    	var yearCount = yearDifference(beginDate,endDate);
    	$('#registYear').numberbox('setValue',yearCount);
    }
});
function yearDifference(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式  
    var dateSpan,
        tempDate,
        iDays;
    sDate1 = Date.parse(sDate1);
    sDate2 = Date.parse(sDate2);
    dateSpan = sDate2 - sDate1;
    dateSpan = Math.abs(dateSpan);
    iYears = Math.floor(dateSpan / (24 * 3600 * 1000))/365;
    return iYears
};