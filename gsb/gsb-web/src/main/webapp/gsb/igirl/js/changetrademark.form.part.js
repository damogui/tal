System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.ChangeTradeMarkPart = org.netsharp.panda.commerce.FormPart.Extends( {
    ctor: function () {
        $("#certFileENPath").parent().parent().hide();
        $("#certificateType").parent().parent().hide();
        $("#appCertificateNum").parent().parent().hide();
        $("#appCertFilePath").parent().parent().hide();
        $("#appCertFileENPath").parent().parent().hide();
        $("#bgzmFileENPath").parent().parent().hide();
        this.base();
    },
    applierTypeChange:function (newValue, oldValue) {
        $("#certFileENPath").parent().parent().hide();
        $("#certFilePath").parent().parent().hide();
        $("#certificateType").parent().parent().hide();
        $("#appCertificateNum").parent().parent().hide();
        $("#appCertFilePath").parent().parent().hide();
        $("#appCertFileENPath").parent().parent().hide();
        var languageType = $("#languageType").val();
        if(languageType==0&&newValue==0){
            $("#certFilePath").parent().parent().show();
        }else if(languageType==1&&newValue==0){
            $("#certFileENPath").parent().parent().show();
            $("#certFilePath").parent().parent().show();
        }else if(languageType==0&&newValue==1){
            $("#certFilePath").parent().parent().show();
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
        }else{
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
            $("#appCertFileENPath").parent().parent().show();
        }
    },
    languageTypeChange:function (newValue,oldValue) {
        $("#certFileENPath").parent().parent().hide();
        $("#certFilePath").parent().parent().hide();
        $("#certificateType").parent().parent().hide();
        $("#appCertificateNum").parent().parent().hide();
        $("#appCertFilePath").parent().parent().hide();
        $("#appCertFileENPath").parent().parent().hide();
        var applierType = $("#applierType").val();
        if(newValue==0&&applierType==0){
            $("#certFilePath").parent().parent().show();
        }else if(newValue==1&&applierType==0){
            $("#certFileENPath").parent().parent().show();
            $("#certFilePath").parent().parent().show();
        }else if(newValue==0&&applierType==1){
            $("#certFilePath").parent().parent().show();
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
        }else{
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
            $("#appCertFileENPath").parent().parent().show();
        }
    },
    proveLanguageTypeChange:function (newValue,oldValue) {
        if (newValue==1){
            $("#bgzmFileENPath").parent().parent().show();
        }else{
            $("#bgzmFileENPath").parent().parent().hide();
        }
    },
    nameChange:function (newValue) {
        var val=$(newValue).val();
        var reg = /^[\u4E00-\u9FA5]{2,60}$/;
        if (!reg.test(val)){
            IMessageBox.error("不能为空且长度在2-60个汉字之间");
            return false;
        }
    },
    txt_sqrdzzwChange:function (newValue) {
        var val=$(newValue).val();
        var reg=/(([北][京][市])|([天][津][市])|([河][北][省])|([山][西][省])|([内][蒙][古][自][治][区])|([辽][宁][省])|([吉][林][省])|([黑][龙][江][省])|([上][海][市])|([江][苏][省])|([浙][江][省])|([安][徽][省])|([福][建][省])|([江][西][省])|([山][东][省])|([河][南][省])|([湖][北][省])|([湖][南][省])|([广][东][省])|([广][西][壮][族][自][治][区])|([海][南][省])|([重][庆][市])|([四][川][省])|([贵][州][省])|([云][南][省])|([西][藏][自][治][区])|([陕][西][省])|([甘][肃][省])|([青][海][省])|([宁][夏][回][族][自][治][区])|([新][疆][维][吾][尔][自][治][区])|([香][港][特][别][行][政][区])|([澳][门][特][别][行][政][区])|([台][湾][省]))/;
        if(!reg.test(val)){
            IMessageBox.error("请检查申请人地址，必须出现省市县（区）三级！");
            return false;
        }
    },
    validate: function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
            var agentBookPath = $("#agentBookPath").parent().children(".textbox").children(".textbox-text").val();
            var suffix = agentBookPath.substring(agentBookPath.length-3,agentBookPath.length);
            if (suffix!=="jpg"){
                IMessageBox.error("代理委托书：文件类型必须是jpg");
                return false;
            }
            var applierType = $("#applierType").val();
            var languageType = $("#languageType").val();
            var certFilePath = $("#certFilePath").parent().children(".textbox").children(".textbox-text").val();
            var certFileENPath = $("#certFileENPath").parent().children(".textbox").children(".textbox-text").val();
            var appCertFileENPath = $("#appCertFileENPath").parent().children(".textbox").children(".textbox-text").val();
            var appCertFilePath = $("#appCertFilePath").parent().children(".textbox").children(".textbox-text").val();
            var certificateType = $("#certificateType").val();
            var appCertificateNum = $("#appCertificateNum").val();
            if(languageType==0&&applierType==0){
                if(certFilePath.length==0){
                    IMessageBox.error("主体资格证明文件(中文)：不能为空");
                    return false;
                }else{
                    suffix = certFilePath.substring(certFilePath.length-3,certFilePath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("主体资格证明文件(中文)：文件类型必须是pdf");
                        return false;
                    }
                }
            }else if(languageType==1&&applierType==0){
                if(certFilePath.length==0){
                    IMessageBox.error("主体资格证明文件(中文)：不能为空");
                    return false;
                }else{
                    suffix = certFilePath.substring(certFilePath.length-3,certFilePath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("主体资格证明文件(中文)：文件类型必须是pdf");
                        return false;
                    }
                }
                if(certFileENPath.length==0){
                    IMessageBox.error("主体资格证明原文件(外文)：不能为空");
                    return false;
                }else{
                    suffix = certFileENPath.substring(certFileENPath.length-3,certFileENPath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("主体资格证明原文件(外文)：文件类型必须是pdf");
                        return false;
                    }
                }
            }else if(languageType==0&&applierType==1){
                if(certFilePath.length==0){
                    IMessageBox.error("主体资格证明文件(中文)：不能为空");
                    return false;
                }else{
                    suffix = certFilePath.substring(certFilePath.length-3,certFilePath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("主体资格证明文件(中文)：文件类型必须是pdf");
                        return false;
                    }
                }
                if(certificateType==null){
                    IMessageBox.error("证件名称：不能为空");
                    return false;
                }
                if(appCertificateNum.length==0){
                    IMessageBox.error("证件号码：不能为空");
                    return false;
                }
                if(appCertFilePath.length==0){
                    IMessageBox.error("身份证明文件(中文)：不能为空");
                    return false;
                }else{
                    suffix = appCertFilePath.substring(appCertFilePath.length-3,appCertFilePath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("身份证明文件(中文)：文件类型必须是pdf");
                        return false;
                    }
                }
            }else{
                if(certificateType==null){
                    IMessageBox.error("证件名称：不能为空");
                    return false;
                }
                if(appCertificateNum.length==0){
                    IMessageBox.error("证件号码：不能为空");
                    return false;
                }
                if(appCertFilePath.length==0){
                    IMessageBox.error("身份证明文件(中文)：不能为空");
                    return false;
                }else{
                    suffix = appCertFilePath.substring(appCertFilePath.length-3,appCertFilePath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("身份证明文件(中文)：文件类型必须是pdf");
                        return false;
                    }
                }
                if(appCertFileENPath.length==0){
                    IMessageBox.error("身份证明原文件(外文)：不能为空");
                    return false;
                }else{
                    suffix = appCertFileENPath.substring(appCertFileENPath.length-3,appCertFileENPath.length);
                    if (suffix!=="pdf"){
                        IMessageBox.error("身份证明原文件(外文)：文件类型必须是pdf");
                        return false;
                    }
                }
            }
            return true;
        }else{
            return false
        }

    },
    updateState:function () {

    }
});