System.Declare("com.gongsibao.gardian.web");
com.gongsibao.gardian.web.DeviceFormPart = org.netsharp.panda.commerce.FormPart.Extends({

    ctor: function () {
        this.base();
    },
    deviceTypeChange:function (newValue, oldValue) {

        if(newValue==1||newValue==0){
            //$("fieldset:first").next().show();
            $("#memory").parent().parent().parent().parent().parent().show()
        }else{
            //$("fieldset:first").next().hide();
            $("#memory").parent().parent().parent().parent().parent().hide()
        }
    },
    validate:function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        var memory=$("#memory").val();
        var core=$("#core").val();
        var hdd=$("#hdd").val();
        var cloudhdd=$("#cloudhdd").val();

        if(isValidate) {
            if(IsVain(memory)||IsVain(core)||IsVain(hdd)||IsVain(cloudhdd)){
                if($("#deviceType").val()=="0"||$("#deviceType").val()=="1")
                {
                    IMessageBox.error("内存、CPU、硬盘、云盘参数必须填写。");
                    return false;
                }
                else
                {
                    return true;
                }

            }
            else
            {
                return true;
            }
        }
        else {
            return false;
        }
    }
});

function IsVain(s)
{
    if($.trim(s)==''){
        return true;
    }else{
        return false;
    }
}