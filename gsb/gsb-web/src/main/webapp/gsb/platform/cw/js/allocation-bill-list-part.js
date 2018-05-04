System.Declare("com.gongsibao.cw.web");
//调拨单
com.gongsibao.cw.web.AllocationBillListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor: function () {
	   this.base();
	},
	applyAllocation:function (){ //申请调拨单
		 layer.open({
    		 id: "allocationBillIframe",
             type: 2,
             title: '新建付款单',
             fixed: false,
             maxmin: true,
             shadeClose:true,
             area: ['50%','70%'],
             content: "/panda/cw/bill/allocation/form",
             btn : [ '提交', '取消' ],
             success: function (layero, index) {
            	
             },
             yes:function (){
            	 document.getElementById('allocationBillIframe').firstElementChild.contentWindow.controllerallocation.save();
             }
         });
	}
});