System.Declare("com.gongsibao.taurus.web");
com.gongsibao.taurus.web.UserFormPart = org.netsharp.panda.commerce.FormPart.Extends( {

    ctor: function () {
        this.base();
    }
});

System.Declare("com.gongsibao.taurus.web");
com.gongsibao.taurus.web.WalletLogsDetailPart = org.netsharp.panda.commerce.DetailPart.Extends( {

    ctor: function () {
        this.base();
    },
    operationFormatter:function(value,row){
    	
    	if(row.type == 2){
    		
    		if(row.undone == true){

        		return '已撤销';
    		}
    		return '<a href="javascript:controllerwalletLogs.revocation(\''+value+'\');">撤销</a>';
    	}
    },
    revocation:function(logId){
    	
    	if(this.relationItem.entityState == EntityState.New){
    	
        	IMessageBox.warning("请先保存再进行操作！",function(){
    		
        	});
    		return;
    	}
    	
    	var me = this;
    	var userId = this.relationItem["id"];
    	//撤销赠送的金额
    	IMessageBox.confirm('确定要撤销吗？',function(){
    		
    		me.invokeService("revocation", [logId], function (data) {
        	
        	//暂时先刷新整个表单，后续再实现分页
        	me.parent.byId(userId);
        	IMessageBox.info("撤销成功");
          });
    		
    	});
    	
    },
    edit:function(){
    	
    },
	save:function(){
		
		var isValidated = $("#" + this.context.formName).form('validate');
        if (!isValidated) {
            return;
        }
        
        this.viewModel.context = this.context;
        var entity = this.viewModel.getEntity();
        entity.userId = this.relationItem["id"];
        entity.price = Number.parseInt(entity.price);
        entity.discountAmount = Number.parseInt(entity.discountAmount);
        this.viewModel.clear();
        var me = this;
        
        this.invokeService("recharge", [entity], function (data) {
        	
        	me.parent.byId(entity.userId);
        	IMessageBox.info("充值成功");
        	me.getDialog().dialog('close');
       });
	}
});
