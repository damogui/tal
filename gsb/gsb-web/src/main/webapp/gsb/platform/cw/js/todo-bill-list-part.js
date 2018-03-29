System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.TodoBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
    ctor: function () {
    	this.base();
    	  this.auditUrl = '/nav/gsb/platform/cw/audit_bill_form';
    	  this.seeUrl = "/nav/gsb/platform/cw/audit_bill_form";
    },
    operationFormatter:function (value,row,index){ //操作格式化
    	var formId = row.formId;
    	var opeHtml = '<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showAudit('+formId+');">审核</a>';
    	    opeHtml +='<a class="grid-btn" href="javascript:controllerbillAuditDTOList.showSee('+formId+');">查看</a>'; 
    	return opeHtml;
    },
    showAudit:function (formId){ //打开审核页面
    	var contentUrl = this.auditUrl +"?formId="+formId;
    	layer.open({
    		id: "auditBillIframe",
            type: 2,
            title: '审核借款单',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: contentUrl,
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
           	 
            }
        });
    },
    showSee:function(){  //打开查看页面
    	layer.open({
    		id: "seeBillIframe",
            type: 2,
            title: '新建借款单',
            fixed: false,
            maxmin: true,
            shadeClose:true,
            area: ['60%','90%'],
            content: "/gsb/platform/cw/audit_bill_form.jsp",
            btn : [ '提交', '取消' ],
            success: function (layero, index) {
           	
            },
            yes:function (){
           	 document.getElementById('seeBillIframe').firstElementChild.contentWindow.controllercontract.save();
            }
        });
    }
});

