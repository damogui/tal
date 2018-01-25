System.Declare("com.gongsibao.gardian.web");
com.gongsibao.gardian.web.DeviceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
    },
    deviceTypeChange:function (newValue, oldValue) {
        if(newValue==1||newValue==2){
            $("#memory").style("display","block");
        }else{
            $("#memory").style("display","none");
        }
    }
});