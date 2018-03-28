System.Declare("com.gongsibao.igirl.tm.web");
com.gongsibao.igirl.tm.web.TradeMarkOptListPart = org.netsharp.panda.commerce.ListPart.Extends({
	ctor : function() {
		this.base();
	},
	markPic:function(value,row,index){
		var html='<a href="#" onclick="controllertradeMarkList.openBussinessPic('+row.id+')">营业执照</a> &nbsp;'
			+'<a href="#" onclick="controllertradeMarkList.openProxyPic('+row.id+')">委托书</a> &nbsp;'
			+'<a href="#" onclick="controllertradeMarkList.openMarkPic('+row.id+')">图样</a> &nbsp;'
            +'<a href="#" onclick="controllertradeMarkList.openIDPic('+row.id+')">身份证</a> &nbsp;'
		         +'<a href="#" onclick="controllertradeMarkList.totmcase('+row.tradeMarkCaseId+')">案件</a>';
		return html;
	},

    openIDPic:function(markid){
        this.invokeService("getIDPic",[markid],function (url) {
            if(url && url!=""){
                window.open(url)
            }else{
                IMessageBox.warning("当前案子涉及自然人的身份证还未上传！");
            }

        });
    },

  openBussinessPic:function(markid){
        this.invokeService("getBussinessPicUrl",[markid],function (url) {
            if(url && url!=""){
                window.open(url)
            }else{
                IMessageBox.warning("当前案子的营业执照还未上传！");
            }

        });
    },


    openProxyPic:function(markid){
        this.invokeService("getProxyPicUrl",[markid],function (url) {
            if(url && url!=""){
                window.open(url)
            }else{
                IMessageBox.warning("当前案子的委托书还未上传！");
            }

        });
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
			var formUrl = "/igirl/my/case/form";
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
		},
		doubleClickRow : function(index, row) {
			this.totmcase(row.tradeMarkCaseId);
		},
		attachment : function() {
//			var count = this.getSelectionCount();
//			if (count <= 0) {
//				IMessageBox.info("请选择要查看附件的记录！");
//				return;
//			}
//			if (count > 1) {
//				IMessageBox.info("请选择一条记录查看附件！");
//				return;
//			}
			var ids = this.getSelectionIds();
			if(ids && ids!=""){
				var url = '/gsb/igirl/attachment/attachment-list.jsp?foreignKey={0}&entityId={1}'.format(ids,this.context.entityId);
				var me = this;
				IMessageBox.open("附件", url, 1000, 600, function() {
					me.reload();
				}, true);
			}else{
				var url = '/gsb/igirl/attachment/attachment-list.jsp?foreignKey={0}&entityId={1}'.format("",this.context.entityId);
				var me = this;
				IMessageBox.open("附件", url, 1000, 100, function() {
					me.reload();
				}, true);
			}

		}

});
