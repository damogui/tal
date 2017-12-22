/// <reference path="core.js"/>
/// <reference path="panda.js"/>
/// <reference path="panda_Controls.js"/>

org.netsharp.panda.QueryController = System.Object.Extends({
	

    ctor: function () {
        this.qpc = null;
        this.iscollected = false;
        this.context = null;
        this.controls = [];

    },
	reset:function(){
		
        this.collectControl();
        this.qpc = [];
        for (var i = 0; i < this.controls.length; i++) {

        	this.controls[i].clear();
        }
	},
    getQueryParameters: function () {
 
        var isValidate = $("#queryFrom").form('validate');
        if(!isValidate){
        	
        	return null;
        }

        this.collectControl();
        this.qpc = [];
        for (var i = 0; i < this.controls.length; i++) {
            var control = this.controls[i];
            var qp = control.get();
            if (qp != null) {

                this.qpc.push(qp);
            }
        }

        return this.qpc;
    },
    
    collectControl: function () {

        if (this.iscollected) {
            return;
        }

        this.iscollected = true;

        var viewmodel = this;

        $("#queryFrom [query='1']").each(function (index, item) {

            var controlType = $(item).attr("controlType");
            var control = null;
            var expression = 'control=new org.netsharp.controls.'+controlType;
            eval(expression);
            control.uiElement = item;
            control.propertyName = item.id;
            viewmodel.controls.push(control);
        });
    }
});


//Query Controls
org.netsharp.controls.TextBoxQueryItem=org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        var propertyValue = this.uiElement.value;

        if (System.isnull(propertyValue))
        {
            return null;
        }
        //去掉两端空格
        propertyValue = propertyValue.replace(/(^\s*)|(\s*$)/g,'');
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue;
        
        var operationAttribute = this.uiElement.attributes['operation'];
        if(operationAttribute){

            var operation = operationAttribute.value;
            if(operation == "Equal"){

                qp.Filter = this.propertyName + "= '" + qp.Value + "'";
            }else{

                qp.Filter = this.propertyName + " LIKE '%" + qp.Value + "%'";
            }
        }

      //  qp.Filter = this.propertyName + "=@" + this.propertyName;
        return qp;
    },
	clear: function() {
		
		this.uiElement.value = "";
	}
});

org.netsharp.controls.EncryptionBoxQueryItem=org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        var propertyValue = this.uiElement.value;

        if (System.isnull(propertyValue))
        {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue;
        qp.Filter = this.propertyName + "= '{cryp" + qp.Value + "!cryp}'";

        return qp;
    },
	clear: function() {
		this.uiElement.value = "";
	}
});

org.netsharp.controls.BoolComboBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        var propertyValue = $('#' + this.propertyName).combobox('getValues');

        if (propertyValue == null || propertyValue == undefined || propertyValue == ""||propertyValue=="-1") {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        qp.Filter = this.propertyName + " ='" + propertyValue[0] + "'";

        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).combobox('setValue','');
	}
});

org.netsharp.controls.CheckBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        //使用jquery时，打勾的值是checked,未打勾的值是undefined
        //var propertyValue = $('#' + this.uiElement.id).attr('checked');

        var propertyValue = document.getElementById(this.uiElement.id).checked;

        if(System.isnull(propertyValue)){
        	return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "Bool";
        qp.Value = propertyValue;

        qp.Filter = this.propertyName + "='" + qp.Value + "'";

        return qp;
    },
	clear: function() {
		
	}
});


org.netsharp.controls.DateBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {
        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');
        var interval = $('#' + this.uiElement.id).attr('interval');
        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');
        
        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }

        if (!System.isDateTime(propertyValue)) {

            //IMessageBox.warning("日期格式不合法!");

            //window.alert("请进行日期格式校验");

            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {
//            qp.Filter = this.propertyName + ">=@" + this.propertyName;
//            qp.Filter = this.propertyName + ">='" + qp.Value+"'";
        	
//        	if(interval === 'true'){
//        		
//        		var operation = $("#shortcut_"+this.propertyName).datebox("getValue");
//        		
//        		if(operation == "="){
//
//            		qp.Filter =  "DATE_FORMAT('"+this.propertyName+"','%Y-%m-%d')='"+qp.Value+"' "
////        			
//        		}else{
//
//            		qp.Filter = this.propertyName + operation +"'" + qp.Value + " 00:00:00' ";
//        		}
//        		
//        	}else{

                qp.Filter = this.propertyName + ">='" + qp.Value + " 00:00:00' ";
//        	}
        }
        else {
//            qp.Filter = this.propertyName + "<@" + this.propertyName;

            qp.Filter = this.propertyName + "<='" + qp.Value + " 23:59:59' ";
        }
        return qp;
    },
	clear: function() {
		$('#Start_' + this.propertyName).datebox('setValue','');
		$('#End_' + this.propertyName).datebox('setValue','');
	}
});

org.netsharp.controls.MonthBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');

        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');

        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }


        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {

        	qp.Filter = this.propertyName + ">='" + qp.Value+"-01'";
        }else {

        	var value = propertyValue+"-01";
        	var day = new Date(value);
        	var daycount = day.getDays();
        	value = propertyValue + "-" + daycount + " 23:59:59";
            qp.Filter = this.propertyName + "<'" + value+"'";
        }
        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).datebox('setValue','');
	}
});


org.netsharp.controls.EnumBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {
    	
        var propertyValue = $('#' + this.propertyName).combobox('getValues');
        if (propertyValue == null || propertyValue == undefined || propertyValue == "" || propertyValue == "-1") {
        	
            return null;
        }
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        var filter = "";
        $(propertyValue).each(function(i,item){
        	filter+="'"+item+"',";
        });
        qp.Filter = this.propertyName.replace("_",".") + " in (" + filter.substring(0,filter.length-1) + ")";
        return qp;
    },
	clear: function() {
		$('#' + this.propertyName).combobox('setValue','');
	}
});

org.netsharp.controls.JavaEnumBoxQueryItem=org.netsharp.controls.EnumBoxQueryItem.Extends({

	clear: function() {
		$('#' + this.propertyName).combobox('setValue','-1');
	}
});



org.netsharp.controls.ReferenceBoxQueryItem=org.netsharp.controls.Control.Extends({
    get : function () {

        var propertyValue = $('#' + this.propertyName).combogrid('getValue');

        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }

        var foreignkey = $(this.uiElement).attr("foreignkey");
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + foreignkey;
        qp.DbType = "Guid";
        qp.Value = propertyValue;
        var multiple =  $('#' + this.propertyName).combogrid('options').multiple;
        if(multiple == true){
        	//多选
        	var values = $('#' + this.propertyName).combogrid('getValues');
        	var ids = "";
        	$(values).each(function(i,item){
        		
        		if(i==(values.length-1)){

        			ids += item;
        		}else{
        			ids += item+",";
        		}
        	});
        	qp.Value = ids
            qp.Filter = foreignkey + " in (" + ids + ")";
        }else{
        	//单选

            qp.Filter = foreignkey + "='" + qp.Value + "'";
        }

        return qp;
    },
	clear: function() {
		$('#' + this.propertyName).combogrid('setValue','');
	}
});

org.netsharp.controls.NumberBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get: function () {

        var propertyValue = $("#" + this.propertyName).val();
        var propertyName = $("#" + this.propertyName).attr("propertyName");
        var interval = $('#' + this.propertyName).attr('interval');

        if (System.isnull(propertyValue)) 
        {
            return null;
        }

        var qp = new org.netsharp.core.QueryParameter();
        qp.DbType = "String";
        qp.Value = propertyValue;

        if (this.propertyName.indexOf("Start_") == 0) {
        	
        	if(interval){
        		
        		qp.Filter = propertyName + '=' + propertyValue+" ";
        	}else{

        		qp.Filter = propertyName + ">=" + propertyValue+" ";
        	}
           
        }
        else
        {
            qp.Filter = propertyName + "<=" + propertyValue+" ";
        }

        return qp;
    },
	clear: function() {
		$('#' + this.propertyName).numberbox('setValue','');
	}
});

org.netsharp.controls.CustomerControl= org.netsharp.controls.Control.Extends({
	ctor: function () {
        this.base();
    },
    init:function(){
    	
    },
    get:function(){
    	
    },
	clear: function() {
		
	}
});


org.netsharp.controls.MonthDateBoxQueryItem = org.netsharp.controls.Control.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {

        this.propertyName = $('#' + this.uiElement.id).attr('propertyName');

        var propertyValue = $('#' + this.uiElement.id).datebox('getValue');

        if (propertyValue == null || propertyValue == undefined || propertyValue == "") {
            return null;
        }


        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "DateTime";
        qp.Value = propertyValue;

        if (this.uiElement.id.indexOf("Start_") == 0) {

        	qp.Filter = this.propertyName + ">='" + qp.Value+"-01'";
        }else {

        	var value = propertyValue+"-01";
        	var day = new Date(value);
        	var daycount = day.getDays();
        	value = propertyValue + "-" + daycount + " 23:59:59";
            qp.Filter = this.propertyName + "<'" + value+"'";
        }
        return qp;
    },
	clear: function() {
		
		$('#' + this.propertyName).datebox('setValue','');
	}
});

org.netsharp.controls.MonthBoxQueryItem = org.netsharp.controls.EnumBoxQueryItem.Extends({
    ctor: function () {
        this.base();
    },
    get : function () {
    	
        var propertyValue = $('#' + this.propertyName).combobox('getValues');
        if (propertyValue == null || propertyValue == undefined || propertyValue == "" || propertyValue == "-1") {
        	
            return null;
        }
        var qp = new org.netsharp.core.QueryParameter();
        qp.ParameterName = "@" + this.propertyName;
        qp.DbType = "String";
        qp.Value = propertyValue[0];

        qp.Filter = this.propertyName.replace("_",".") + " =" + propertyValue;
        return qp;
    }
});

org.netsharp.controls.YearBoxQueryItem = org.netsharp.controls.MonthBoxQueryItem.Extends({
    ctor: function () {
        this.base();
    }
});

org.netsharp.core.QueryParameter = System.Object.Extends({
	
    ctor: function () {
    	
        this.type = null;
        this.name = null;
        this.value = null;
    }
});
