//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.tm.web");
com.gongsibao.igirl.tm.web.TradeMarkFormPart = org.netsharp.panda.commerce.FormPart.Extends({
	ctor : function() {
		this.base();
		this.filterTradeMarkState();
	},
	filterTradeMarkState:function(){//过滤状态 只显示状态0，应用于“我的跟进”
		$('#markState').combobox({
			loadFilter: function(data){
				for(var i=1;i<data.length;){
					if(data[i].value=='0'){
						i++;
					}else{
						data.splice(i,1); 
					}
				}
				return data; 
			}

		});
	}
	

});

