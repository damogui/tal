System.Declare("com.gongsibao.u8.web");
com.gongsibao.u8.web.PayReceiptCheckDTOController = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    bindReceiptWeb : function() {
    	
    	var me = this;
    	var row = this.getSelectedItem();
    	if(row == null){
    		
    		IMessageBox.info('请选择记录');
    		return;
    	}
    	
    	var payId = row.id;
    	var orderNo = row.orderNo;
    	//alert(payId);
    	var content = '<br/><p style="padding-left:50px;">&nbsp;订单编号：<input  type="text" disabled="disabled"  class="easyui-validatebox nsInput"  value="'+orderNo+'" style="width:180px;"></input></p>'
        + '<p style="padding-left:50px;">&nbsp;回单号：<input id="txtReceiptNo" type="text" class="easyui-validatebox nsInput" required="true" style="width:180px;"></input></p>';
    	//window.top.layer.open({
    	layer.open({
	  		  type: 1,
	  		  title: '绑定回单编号',
	  		  fixed: false,
	  		  maxmin: false,
	  		  shadeClose:false,
	  		  area: ['500px', '300px'],
	  		  content: content,
	  		  btn: ['保存','取消'],//可以无限个按钮
	  		  btn1: function(index, layero){

	  			var receiptNo = $("#txtReceiptNo").val();
	  			alert(receiptNo);
		  			if(System.isnull(receiptNo)){		  				
		  				IMessageBox.info('请输入回单编号');
		  				return false ;
		  			}
		  			
		          me.doBindReceiptWeb(payId,receiptNo);
	  		  },
	  		 btn2: function(index, layero){

	  		 }
	  	});

	},
	doBindReceiptWeb:function(payId,receiptNo){

		this.invokeService("bindReceiptNo", [id,receiptNo], function() {
			window.alert("111112211221");
			return;
		});
	}
});