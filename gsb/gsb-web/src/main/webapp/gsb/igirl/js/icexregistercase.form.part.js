System.Declare("com.gongsibao.igirl.ic.web");
com.gongsibao.igirl.ic.web.IcExRegisterCasePart = org.netsharp.panda.commerce.FormPart.Extends({
    ctor: function () {
        this.base();
    },
    isTel: function (el) {
        var me = this;
        var customerMobile = $(el).val();
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test(customerMobile)) {
            IMessageBox.error("【手机】格式错误");
            $("#customerMobile").val("");
            return false;
        }
        me.invokeService("findByMobile",[customerMobile],function (customer) {
            if(customer!=null){
                $("#customerName").val(customer.realName).attr('readonly',true);
                //选取对应id的下拉框选项值
                var data= $("#source").combobox("getData");
                for (var i = 0; i < data.length; i++){
                    if(data[i].value==customer.customerSourceId){
                        $("#source").combobox("setValue",customer.customerSourceId);
                        break;
                    }
                }
                var dataway= $("#consultWay").combobox("getData");
                for (var i = 0; i < dataway.length; i++){
                    if(dataway[i].value==customer.consultWay){
                        $("#consultWay").combobox("setValue",customer.consultWay);
                        break;
                    }
                }
            }
        })
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
    /*加载二维码*/
    onload:function(){
        this.base();
        $("#tokenImgUrl").click(function(){
            var qrurl=$(this).attr("src");
            if(qrurl && qrurl!=""){
                var enurl=qrurl.split("=")[1];
                window.open(decodeURIComponent(enurl));
            }
        });



    },
});