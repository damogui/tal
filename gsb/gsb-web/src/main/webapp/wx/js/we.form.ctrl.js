org.netsharp.we.core.formCtrl = org.netsharp.we.core.view.Extends({
    ctor: function () {
    	this.base();
    	this.id = this.queryString('id');
    	this.context.entity = null;
    },
    init:function(){
    	
    	if(this.id){

        	this.byId();
    	}else{
    		
    		this.newInstance();
    	}
    },
    byId:function(){
    	
    },
    newInstance:function(){
    	
    },
    validate:function(){
    	
    	//表单验证
    	
    },
    getEntity:function(entity){
    	
    },
    save:function(){
    	
    	//保存
    	
    }
});