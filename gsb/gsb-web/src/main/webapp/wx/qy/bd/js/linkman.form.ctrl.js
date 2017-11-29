
org.netsharp.we.core.linkmanFormCtrl = org.netsharp.we.core.formCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    },
    byId:function(){
    	
    	var me = this;
    	var pars = [this.id];
    	this.invokeService('getLinkmanById', pars, function(result){
    		
    		me.context.entity = result;
    		me.bindData(result);
    	});
    },
    bindData:function(entity){
    	
    	if(entity){
    		
    		$('#name').val(entity.name);
    		$('#mobile').val(entity.mobile);
    		$('#weixin').val(entity.weixin);
    		$('#post').val(entity.post);
    		$('#main').prop('checked',entity.main);
    	}
    },
    newInstance:function(){
    	
    	var me = this;
    	this.invokeService('getNewLinkman', [], function(result){
    		
    		me.context.entity = result;
    		me.bindData(result);
    	});
    },
    getEntity:function(entity){
    	
    	var franchiseeId = this.queryString('franchiseeId');
    	if(franchiseeId){

        	entity.franchiseeId = franchiseeId;
    	}
    	entity.name = $('#name').val();
    	entity.mobile = $('#mobile').val();
    	entity.weixin = $('#weixin').val();
    	entity.post = $('#post').val();
    	entity.main = $('#main').prop('checked');
    	return entity;
    },
    validate:function(){
    	
        var name = $('#name').val();
        var mobile = $('#mobile').val();
        var weixin = $('#weixin').val();
        var post = $('#post').val();
        var main = $('#main').prop('checked');
        if(name == ""){
      	  
      	  $.toptip('请输入姓名');
      	  return false;
        }
        
        if(mobile == ""){
      	  
      	  $.toptip('请输入手机号');
      	  return false;
        }else if(!/^0?(13[0-9]|15[012356789]|18[0123456789]|14[57]|17[01367])[0-9]{8}$/.test(mobile)){
        	  
    	  $.toptip('手机号格式错误');
    	  return false;
        }
        
        if(weixin == ""){
      	  
      	  $.toptip('请输入微信号');
      	  return false;
        }
        
        if(post == ""){
      	  
      	  $.toptip('请输入职位');
      	  return false;
        }
        
        return true;
    },
    save:function(){
    	
    	var isValidate = this.validate();
    	if(!isValidate){
    		return;
    	}
    	
    	var me = this;
    	var entity = this.getEntity(this.context.entity);
    	this.invokeService('saveLinkman', [entity], function(result){
    		
    		$.toptip('提交成功', 'success');
    	    setTimeout(function() {
    	    	window.location.href = 'linkmanList?franchiseeId='+result.franchiseeId;
    	    }, 2000);
    	});

    }
});
