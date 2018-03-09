System.Declare("com.gongsibao.ma.web");
com.gongsibao.ma.web.AcquisitionDemandListPart = org.netsharp.panda.commerce.ListPart.Extends( {

	soldOutStateFormatter:function(value,row,index){
		
		var checked = value=='上架'?true:false;
		return '<input class="easyui-switchbutton" data-options="'
		+'checked:'+checked
		+',onText:\'上架\',offText:\'下架\','
		+'onChange:function(checked){controlleracquisitionDemandList.changeSoldOutState(\''+row.id+'\',checked);}">';
	},
	changeSoldOutState(id,value){
		
		var state = value==true?1:2;
		var me = this;
		this.invokeService("changeSoldOutState", [id,state], function(data) {

			me.reload();
			IMessageBox.toast("操作成功！");
		});
	},
	onLoadSuccess:function(data){
		
		$('.easyui-switchbutton').switchbutton();
	}
});
