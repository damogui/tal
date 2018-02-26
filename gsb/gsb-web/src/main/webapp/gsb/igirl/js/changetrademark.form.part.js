System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.ChangeTradeMarkPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    /*
    validate: function () {
        var isValidate = $("#" + this.context.formName).form('validate');
        if(isValidate){
            var ywPhone = $("#ywPhone").val();
            var idencode=$("#identityCode").val();
            var applierAddress=$("#applierAddress").val();
            if(!System.isnull(idencode) && !/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(idencode)){
                IMessageBox.error("【身份证】格式错误");
                return false;
            }
            var x=/(([省])|([北][京][市])|([上][海][市])|([天][津][市])|([重][庆][市])|([内][蒙][古])|([广][西])|([宁][夏])|([新][疆])|([西][藏])|([内][蒙][古][自][治][区])|([新][疆][维][吾][尔][自][治][区])|([广][西][壮][族][自][治][区])|([宁][夏][回][族][自][治][区])|([西][藏][自][治][区]))/g.test(applierAddress);
            //var x=/([广][西])/g.test(applierAddress);
            if(!x){
                IMessageBox.error("请检查申请人地址，必须出现省市县（区）三级！");
                return false;
            }
            return true;
        }else{
            return false
        }

    }*/
    applierTypeChange:function (newValue, oldValue) {
        /*if(newValue==1){
            $("#")
        }*/
        alert(newValue);
    },
    languageTypeChange:function (newValue,oldValue) {
        var applierType = $("#applierType").val();
        if(newValue==0){
            $("#certFileENPath").parent().parent().hide();
        }else {
            $("#certFileENPath").parent().parent().show();
        }
    }
});
///"省"|"上海市"|"天津市"|"重庆市"|"内蒙古"|"广西"|"宁夏"|"新疆"|"西藏"|"内蒙古自治区"|"新疆维吾尔自治区"|" 广西壮族自治区"|"宁夏回族自治区"|"西藏自治区"/g