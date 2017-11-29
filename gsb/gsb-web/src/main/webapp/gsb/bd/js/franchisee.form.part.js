System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    },
    follow:function(){
    	
    	//切换到【沟通日志】
    	$("#tabcenter").tabs('select',2);
//    	
//    	//将【内容】设置为可写
//    	$('#content').prop('disabled',false);
//    	
//    	//跟进
//    	controllerfollows.add();
    }
});

