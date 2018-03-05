System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.TransferTradeMarkPart = org.netsharp.panda.commerce.FormPart.Extends( {
    ctor: function () {
        $("#zrZtEnTrName").parent().parent().hide();
        $("#srZtEnTrName").parent().parent().hide();
        $("#certType").parent().parent().hide();
        $("#certNo").parent().parent().hide();
        $("#scertType").parent().parent().hide();
        $("#scertNo").parent().parent().hide();
        $("#zrSfCnTrName").parent().parent().hide();
        $("#srSfCnTrName").parent().parent().hide();
        $("#zrSfEnTrName").parent().parent().hide();
        $("#srSfEnTrName").parent().parent().hide();
        $("#zrZtEnTrName").attr("data-options","width:0,required:false");
        $("#srZtEnTrName").attr("data-options","width:0,required:false");
        $("#certType").attr("data-options","width:0,required:false");
        $("#certNo").attr("data-options","width:0,required:false");
        $("#scertType").attr("data-options","width:0,required:false");
        $("#scertNo").attr("data-options","width:0,required:false");
        $("#zrSfCnTrName").attr("data-options","width:0,required:false");
        $("#srSfCnTrName").attr("data-options","width:0,required:false");
        $("#zrSfEnTrName").attr("data-options","width:0,required:false");
        $("#srSfEnTrName").attr("data-options","width:0,required:false");
        this.base();
    },
    typeChange:function (newValue,oldValue) {
        $("#zrZtCnTrName").parent().parent().hide();
        $("#zrZtEnTrName").parent().parent().hide();
        $("#srZtCnTrName").parent().parent().hide();
        $("#srZtEnTrName").parent().parent().hide();
        $("#certType").parent().parent().hide();
        $("#certNo").parent().parent().hide();
        $("#scertType").parent().parent().hide();
        $("#scertNo").parent().parent().hide();
        $("#zrSfCnTrName").parent().parent().hide();
        $("#srSfCnTrName").parent().parent().hide();
        $("#zrSfEnTrName").parent().parent().hide();
        $("#srSfEnTrName").parent().parent().hide();
        $("#zrZtCnTrName").attr("data-options","width:0,required:false");
        $("#srZtCnTrName").attr("data-options","width:0,required:false");
        $("#zrZtEnTrName").attr("data-options","width:0,required:false");
        $("#srZtEnTrName").attr("data-options","width:0,required:false");
        $("#certType").attr("data-options","width:0,required:false");
        $("#certNo").attr("data-options","width:0,required:false");
        $("#scertType").attr("data-options","width:0,required:false");
        $("#scertNo").attr("data-options","width:0,required:false");
        $("#zrSfCnTrName").attr("data-options","width:0,required:false");
        $("#zrSfEnTrName").attr("data-options","width:0,required:false");
        $("#srSfCnTrName").attr("data-options","width:0,required:false");
        $("#srSfEnTrName").attr("data-options","width:0,required:false");
        var zdllx = $("#zdllx").val();
        var sdjlx = $("#sdjlx").val();
        var zwjlx = $("#zwjlx").val();
        var swjlx = $("#swjlx").val();
        if(zdllx==0&&sdjlx==0&&zwjlx==0&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#srZtEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==0&&zwjlx==1&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#zrZtEnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==0&&zwjlx==1&swjlx==1){
            $("#zrZtCnTrName").parent().parent().show();
            $("#zrZtEnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#srZtEnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#srZtEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==0&&zwjlx==0&swjlx==1){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#srZtEnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#srZtEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==0&&zwjlx==0&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==0&&zwjlx==1&swjlx==0){
            $("#srZtCnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrSfEnTrName").parent().parent().show();
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
            $("#zrSfEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==0&&zwjlx==0&swjlx==1){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#srZtEnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtEnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==0&&zwjlx==1&swjlx==1){
            $("#srZtCnTrName").parent().parent().show();
            $("#srZtEnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrSfEnTrName").parent().parent().show();
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtEnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
            $("#zrSfEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==1&&zwjlx==0&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#zrSfEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==1&&zwjlx==1&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#zrZtEnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
        } else if(zdllx==0&&sdjlx==1&&zwjlx==0&swjlx==1){
            $("#zrZtCnTrName").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#srSfEnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
            $("#srSfEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==0&&sdjlx==1&&zwjlx==1&swjlx==1){
            $("#zrZtCnTrName").parent().parent().show();
            $("#zrZtEnTrName").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#srSfEnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
            $("#srSfEnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==1&&zwjlx==0&swjlx==0){
            $("#zrZtCnTrName").parent().parent().show();
            $("#srZtCnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#zrZtCnTrName").attr("data-options","width:0,required:true");
            $("#srZtCnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==1&&zwjlx==1&swjlx==0){
            $("#srZtCnTrName").parent().parent().show();
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrSfEnTrName").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#zrZtEnTrName").attr("data-options","width:0,required:true");
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
            $("#zrSfEnTrName").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
        }else if(zdllx==1&&sdjlx==1&&zwjlx==1&swjlx==1){
            $("#certType").parent().parent().show();
            $("#certNo").parent().parent().show();
            $("#scertType").parent().parent().show();
            $("#scertNo").parent().parent().show();
            $("#zrSfCnTrName").parent().parent().show();
            $("#zrSfEnTrName").parent().parent().show();
            $("#srSfCnTrName").parent().parent().show();
            $("#srSfEnTrName").parent().parent().show();
            $("#certType").attr("data-options","width:0,required:true");
            $("#certNo").attr("data-options","width:0,required:true");
            $("#scertType").attr("data-options","width:0,required:true");
            $("#scertNo").attr("data-options","width:0,required:true");
            $("#zrSfCnTrName").attr("data-options","width:0,required:true");
            $("#zrSfEnTrName").attr("data-options","width:0,required:true");
            $("#srSfCnTrName").attr("data-options","width:0,required:true");
            $("#srSfEnTrName").attr("data-options","width:0,required:true");
        }
    },
    validate:function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
            var uploadFile = $("#fileWtName").parent().children(".textbox").children(".textbox-text").val();
            var suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
            if (suffix!=="jpg"){
                IMessageBox.error("转让人委托书：文件类型必须是jpg");
                return false;
            }
            uploadFile = $("#fileWtName2").parent().children(".textbox").children(".textbox-text").val();
            suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
            if (suffix!=="jpg"){
                IMessageBox.error("受让人委托书：文件类型必须是jpg");
                return false;
            }
            uploadFile = $("#flwsName").parent().children(".textbox").children(".textbox-text").val();
            suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
            if (suffix!=="pdf"){
                IMessageBox.error("同意转让声明或商标移转证明：文件类型必须是pdf");
                return false;
            }
            uploadFile = $("#zrZtCnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("转让人主体资格证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#zrZtEnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("转让人主体资格证明文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#srZtCnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("受让人主体资格证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#srZtEnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("受让人主体资格证明文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#zrSfCnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("转让人身份证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#srSfCnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("受让人身份证明文件(中文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#zrSfEnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("转让人身份证明文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#srSfEnTrName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("受让人身份证明文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            uploadFile = $("#fileYgName").parent().children(".textbox").children(".textbox-text").val();
            if(uploadFile.length>0){
                suffix = uploadFile.substring(uploadFile.length-3,uploadFile.length);
                if (suffix!=="pdf"){
                    IMessageBox.error("受让人身份证明文件(外文)：文件类型必须是pdf");
                    return false;
                }
            }
            return true;
        }else{
            return false
        }
    }
});