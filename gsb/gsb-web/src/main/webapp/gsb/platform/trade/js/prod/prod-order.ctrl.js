System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.OrderCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	alert(orderProdId);
    	
    	
//	    <tr>
//		        <td class="label_td"><label>订单编号：</label></td>
//		        <td class="control_td" id="order_info_order_no"></td>
//		        <td class="label_td"><label>业务员：</label></td>
//		        <td class="control_td" id="order_info_salesman"></td>
//		        <td class="label_td"><label>地区：</label></td>
//		        <td class="control_td" id="order_info_salesman"></td>
//		 </tr>
//		 <tr>
//		        <td class="label_td"><label>支付金额：</label></td>
//		        <td class="control_td" id="order_info_payablePrice"></td>
//		        <td class="label_td"><label>下单时间：</label></td>
//		        <td class="control_td" id="order_info_createTime"></td>
//		        <td class="label_td"><label>付款状态：</label></td>
//		        <td class="control_td" id="order_info_payStatus"></td>
//		 </tr>
 
    	//service_item_grid
    	
    	//other_prod_grid
    }
});