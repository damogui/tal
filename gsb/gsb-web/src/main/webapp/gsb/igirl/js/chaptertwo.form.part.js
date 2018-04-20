//刻章公司的工具栏
System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.ChapterTwoPart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor : function() {
        this.base();
    },

    //验证手机号格式是否正确
    PhoneVerify: function (tel) {
        var val1=$(tel).val();
        var myreg =/^1[34578]\d{9}$/;
        if (!myreg.test(val1)) {
            IMessageBox.error("手机号码格式错误");

            return false;
        }
    },

    //验证邮箱格式是否正确
    EmailVerify: function (email) {
        var val1=$(email).val();
        var myreg =/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        if (!myreg.test(val1)) {
            IMessageBox.error("邮箱格式错误");
            return false;
        }
    },

});