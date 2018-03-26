System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.ChangeTradeMarkPart = org.netsharp.panda.commerce.FormPart.Extends( {
    ctor: function () {
        $("#certFileENPath").parent().parent().hide();
        $("#certificateType").parent().parent().hide();
        $("#appCertificateNum").parent().parent().hide();
        $("#appCertFilePath").parent().parent().hide();
        $("#appCertFileENPath").parent().parent().hide();
        $("#bgzmFileENPath").parent().parent().hide();
        $("#certFileENPath").attr("data-options","width:0,required:false");
        $("#certificateType").attr("data-options","width:0,required:false");
        $("#appCertificateNum").attr("data-options","width:0,required:false");
        $("#appCertFilePath").attr("data-options","width:0,required:false");
        $("#appCertFileENPath").attr("data-options","width:0,required:false");
        $("#bgzmFileENPath").attr("data-options","width:0,required:false");
        this.base();
    },
    changeTypeChange:function (newValue, oldValue) {
        $("#certFileENPath").parent().parent().hide();
        $("#certFilePath").parent().parent().hide();
        $("#certificateType").parent().parent().hide();
        $("#appCertificateNum").parent().parent().hide();
        $("#appCertFilePath").parent().parent().hide();
        $("#appCertFileENPath").parent().parent().hide();
        $("#certFileENPath").attr("data-options","width:0,required:false");
        $("#certFilePath").attr("data-options","width:0,required:false");
        $("#certificateType").attr("data-options","width:0,required:false");
        $("#appCertificateNum").attr("data-options","width:0,required:false");
        $("#appCertFilePath").attr("data-options","width:0,required:false");
        $("#appCertFileENPath").attr("data-options","width:0,required:false");
        var applierType = $("#applierType").val();
        var languageType = $("#languageType").val();
        if(languageType==0&&applierType==0){
            $("#certFilePath").parent().parent().show();
            $("#certFilePath").attr("data-options","width:0,required:true");
        }else if(languageType==1&&applierType==0){
            $("#certFileENPath").parent().parent().show();
            $("#certFilePath").parent().parent().show();
            $("#certFileENPath").attr("data-options","width:0,required:true");
            $("#certFilePath").attr("data-options","width:0,required:true");
        }else if(languageType==0&&applierType==1){
            $("#certFilePath").parent().parent().show();
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
            $("#certFilePath").attr("data-options","width:0,required:true");
            $("#certificateType").attr("data-options","width:0,required:true");
            $("#appCertificateNum").attr("data-options","width:0,required:true");
            $("#appCertFilePath").attr("data-options","width:0,required:true");
        }else{
            $("#certificateType").parent().parent().show();
            $("#appCertificateNum").parent().parent().show();
            $("#appCertFilePath").parent().parent().show();
            $("#appCertFileENPath").parent().parent().show();
            $("#certificateType").attr("data-options","width:0,required:true");
            $("#appCertificateNum").attr("data-options","width:0,required:true");
            $("#appCertFilePath").attr("data-options","width:0,required:true");
            $("#appCertFileENPath").attr("data-options","width:0,required:true");
        }
    },
    changeType:function (newValue,oldValue) {
        if(newValue==0){
            $("#txt_bgqmyzw").validatebox("enableValidation");
            $("#txt_bgqdzzw").validatebox("disableValidation");
            $("#txt_bgqmyzw").parent().parent().find("label").first().show();
            $("#txt_bgqdzzw").parent().parent().find("label").first().hide();
        }else if(newValue ==1){
            $("#txt_bgqmyzw").validatebox("disableValidation");
            $("#txt_bgqdzzw").validatebox("enableValidation");
            $("#txt_bgqmyzw").parent().parent().find("label").first().hide();
            $("#txt_bgqdzzw").parent().parent().find("label").first().show();
        }else{
            $("#txt_bgqmyzw").validatebox("enableValidation");
            $("#txt_bgqdzzw").validatebox("enableValidation");
            $("#txt_bgqmyzw").parent().parent().find("label").first().show();
            $("#txt_bgqdzzw").parent().parent().find("label").first().show();
        }
    },
    proveLanguageTypeChange:function (newValue,oldValue) {
        if (newValue==1){
            $("#bgzmFileENPath").parent().parent().show();
            $("#bgzmFileENPath").attr("data-options","width:0,required:true");
        }else{
            $("#bgzmFileENPath").parent().parent().hide();
            $("#bgzmFileENPath").attr("data-options","width:0,required:false");
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
            var certFilePath = $("#certFilePath").parent().children(".textbox").children(".textbox-text").val();
            var certFileENPath = $("#certFileENPath").parent().children(".textbox").children(".textbox-text").val();
            var appCertFileENPath = $("#appCertFileENPath").parent().children(".textbox").children(".textbox-text").val();
            var appCertFilePath = $("#appCertFilePath").parent().children(".textbox").children(".textbox-text").val();
            if(certFilePath.length>0){
                suffix = certFilePath.substring(certFilePath.length-3,certFilePath.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("主体资格证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            if(certFileENPath.length>0){
                suffix = certFileENPath.substring(certFileENPath.length-3,certFileENPath.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("主体资格证明原文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            if(appCertFilePath.length>0){
                suffix = appCertFilePath.substring(appCertFilePath.length-3,appCertFilePath.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("身份证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            if(appCertFileENPath.length>0){
                suffix = appCertFileENPath.substring(appCertFileENPath.length-3,appCertFileENPath.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("身份证明原文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            return true;
        }else{
            return false
        }
    }
});