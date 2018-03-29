System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.PaymentBillFormCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    init:function(){

    },
    addRows:function (){
    	var row_html = '<tr> '+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:200px;height:32px"></td>'+
						' <td style="text-align: center;" >'+
    					'<select id="cc" class="easyui-combobox"  style="width:200px;">'+
    					'<option value="1">技术部</option>'+
    					'<option value="2">财务部</option>'+
    					'<option value="3">市场部</option>'+
    					'</select>'+
						'</td>'+
						' <td style="text-align: center;"><input class="easyui-textbox" style="width:360px;height:32px"></td>'+
						' <td style="text-align: center;"><a href="#" onclick="$(this).parent().parent().remove();" data-options="iconCls:\'icon-remove\'" class="easyui-linkbutton" >删除</a></td>'+
					    '</tr>';
    	$('#cost_detail_table').append(row_html);
    	//重写加载样式
    	$.parser.parse($('#cost_detail_table'));  
    }
});

