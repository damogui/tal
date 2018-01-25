System.Declare("com.gongsibao.gardian.web");
com.gongsibao.gardian.web.DeviceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
    },
    deviceTypeChange:function (newValue, oldValue) {

        if(newValue==1||newValue==0){
            $("fieldset:first").next().show();
        }else{
            $("fieldset:first").next().hide();
        }
    }

});