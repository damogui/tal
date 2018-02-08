//无法签单中的工具栏
System.Declare("com.gongsibao.igirl.web");
com.gongsibao.igirl.web.TradeMarkListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	markPic:function(value,row,index){
		var html='<a href="#" onclick="controllertradeMarkList.openMarkPic('
        +row.id+')">图样</a> &nbsp;'
		         +'<a href="#" onclick="controllertradeMarkList.totmcase('
		                  +row.tradeMarkCaseId+')">案件</a>'
		return html;
	},
	openMarkPic:function(markid){
		this.invokeService("getTradeMarkPicUrl",[markid],function (url) {
			 if(url && url!=""){
				 window.open(url)
			 }else{
				 IMessageBox.warning("当前商标的图样还未上传！");
			 }
			   
		 });    
	},
	autoSubmit:function(type){
		var rows=this.getSelections();
        if (rows.length <= 0) {
            IMessageBox.info('请先选择需要修改的行');
            return false;
        }

        var ids = new Array(rows.length);
        for(var i=0;i<rows.length;i++){
            ids[i] = rows[i].id;
        }
        var that = this;
        this.invokeService("updateMarkState",[ids,type],function (message) {
            if(message.length===0){
                that.reload();
            }else{
                IMessageBox.info("案件："+message+"材料不齐，请联系客户.");
            }
        })
	},
	totmcase:function(tradeMarkCaseId){
			var formUrl = "/igirl/trademarkcase/all/form";
			if (System.isnull(formUrl)) {
				return;
			}
			var rows = this.getSelections();
			if(System.isnull(tradeMarkCaseId)){
				if (rows.length > 1) {
					IMessageBox.warning("只能选择一条记录！");
					return;
				}
				if (rows.length ==0) {
					IMessageBox.warning("请选择一条记录！");
					return;
				}			
			}
			if(tradeMarkCaseId){
				id = tradeMarkCaseId;
			}else{
				id = rows[0].tradeMarkCaseId;
			}
			
			var url = System.Url.join(formUrl, "id=" + id);
			url = System.Url.getUrl(url);
			url = System.Url.join(url, "openType="+OpenType.open);
			window.open(url);
//			if(this.context.openMode == OpenType.window){
//
//				var me = this;
//				url = System.Url.join(url, "openType="+OpenType.window);
//				IMessageBox.open("方案详情", url, this.context.windowWidth, this.context.windowHeight, function() {
//
//					me.reload();
//				});
//				
//			} else if(this.context.openMode == OpenType.redirect){
//				url = System.Url.join(url, "openType="+OpenType.redirect);
//				window.location.href = url;
//			} else {
//				url = System.Url.join(url, "openType="+OpenType.open);
//				window.open(url);
//			}
		},
		doubleClickRow : function(index, row) {
			this.totmcase(row.tradeMarkCaseId);
		},

});
