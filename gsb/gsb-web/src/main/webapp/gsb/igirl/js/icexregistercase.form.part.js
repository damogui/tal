System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExRegisterCasePart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel: function (el) {
        var tel = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(tel)) {
            IMessageBox.error("【手机】格式错误");
            $("#customerMobile").val("");
            return false;
        }
        return true;
    },
    isCom: function (el) {
        var me = this;
        var approvalName = $(el).val();
        var con = "北京";
        if (approvalName.indexOf(con) == -1) {aa4344
            IMessageBox.error("【公司名称】格式错误");
            $("#approvalName").val("");
            return false;
        }
        me.invokeService("findCom",[approvalName],function (result) {
            if(result==1){
                IMessageBox.error("【公司】已存在");
                $("#approvalName").val("");
                return false;
            }
            return true;
        })
    },
});