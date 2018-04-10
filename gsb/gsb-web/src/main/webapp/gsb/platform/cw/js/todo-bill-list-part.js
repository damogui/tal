System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.TodoBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
    	this.base();
    	  this.auditUrl = '/nav/gsb/platform/cw/audit_bill_form';
    	  this.seeUrl = "/nav/gsb/platform/cw/audit_bill_form";
    },
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var formType = row.formTypeValue;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showAudit('+formId+','+formType+');">审批</a>';
    	return opeHtml;
    },
    showAudit:function (formId,formType){ //打开审核页面
    	var contentUrl = this.auditUrl +"?formId="+formId +"&formType="+formType;
    	var title = "";
    	if(formType == 1)title = "借款单";
    	if(formType == 2)title = "报销单";
    	if(formType == 3)title = "付款单";
    	layer.open({
    		id: "auditBillIframe",
            type: 2,
            title: '审批'+title,
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: contentUrl,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
            	document.getElementById('auditBillIframe').firstElementChild.contentWindow.auditBillFormCtrl.saveAudit();
            }
        });
    }
});

