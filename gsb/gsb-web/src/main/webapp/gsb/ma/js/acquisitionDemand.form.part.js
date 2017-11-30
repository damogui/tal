System.Declare("com.gongsibao.ma.web");
com.gongsibao.ma.web.AcquisitionDemandFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    hasDepositChange:function(checked){
		
		if(checked===true){
			
			$("#depositAmount").numberbox('enable').numberbox('enableValidation');
		}else{
			
			$("#depositAmount").numberbox('disable').numberbox('disableValidation').numberbox('clear');
		}
	}
});