System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.SoOrderListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
	detail : function(id) {
		/*this.edit(id);
		alert(11);
		return;*/
		var me = this;
		this.invokeService("querySoOrderTraceList", [id], function(data) {
			var html = me.pj(data);
        	window.top.layer.open({
      		  type: 1,
      		  title: '办理进度',
      		  fixed: false,
      		  maxmin: true,
      		  shadeClose:true,
      		  area: ['600px', '400px'],
      		  content: html
        	});
		});
	},
	/*add ：function(){
		alert(11);
	},*/
	pj:function(data){
		var pre = '<ul>';
		var html = '';
		$.each(data, function(i,item){ 
			html += "<li style='line-height:30px'><span>" + item.createTime +
			"</span><span style='padding-left: 15px;'>"+item.info+"</span>";
		});
		var end = '</ul>';
		return pre + html + end;
	}
});