System.Declare("com.gongsibao.cw.web");
//全部订单
com.gongsibao.cw.web.AllBillsListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
    operationFormatter:function (value,row,index){ //操作格式化
    	var opeHtml = '<a class="grid-btn" href="javascript:alert(\'调用U8凭证生成接口\');">生成凭证</a>';
    	return opeHtml;
    }
});