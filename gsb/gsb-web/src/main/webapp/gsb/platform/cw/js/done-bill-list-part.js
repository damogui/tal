System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.DoneBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
    	this.base();
    	  this.seeUrl = "/nav/gsb/platform/cw/audit_bill_see";
    },
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var formType = row.formTypeValue;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showBill('+formId+','+formType+');">查看</a>';
    	return opeHtml;
    },
    showBill:function (formId,formType){ //打开单据详情
    	var contentUrl = this.seeUrl +"?formId="+formId +"&formType="+formType;
    	var title = "";
    	if(formType == 1)title = "借款单";
    	if(formType == 2)title = "报销单";
    	if(formType == 3)title = "付款单";
    	layer.open({
    		id: "auditBillIframe",
            type: 2,
            title: '查看'+title,
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: contentUrl,
            btn : [ '取消' ],
            success: function (layero, index) {
           	
            }
        });
    }
});

