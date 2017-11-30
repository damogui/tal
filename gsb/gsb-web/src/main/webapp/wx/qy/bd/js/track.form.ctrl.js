
org.netsharp.we.core.trackFormCtrl = org.netsharp.we.core.formCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.franchisee.web.FranchiseeController';
    	
    },
    init:function(){
    	
//        $("#nextTrackDate").calendar({
//        	dateFormat:'yyyy-mm-dd',
//            onChange: function (p, values, displayValues) {
//              console.log(values, displayValues);
//            }
//        });

        $("#nextTrackDate").datetimePicker({title:'请选择下次跟进时间'});
        
        $("#trackProgress").select({
      	  title: "选择进度",
      	  items: [{title: "未拜访",value: "1"},
  	    	     {title: "电话拜访",value: "2"},
  	    	     {title: "陌拜",value: "3"},
  	    	     {title: "洽谈中",value: "4"},
  	    	     {title: "已合作",value: "5"},
  	    	     {title: "已中止",value: "6"},
  	    	     {title: "已合作中止",value: "7"}
      	    ]
        });
        
        $("#intentionDegree").select({
      	  title: "选择意向度",
      	  items: [{title: "高",value: "1"},
  	    	     {title: "中",value: "2"},
  	    	     {title: "低",value: "3"}]
        });
        
        $("#expectedSign").select({
        	  title: "选择预计签单时间",
        	  items: [{title: "两天内",value: "1"},
    	    	     {title: "一周内",value: "2"},
    	    	     {title: "一月内",value: "3"},
    	    	     {title: "三月内",value: "4"},
    	    	     {title: "三月以上",value: "5"}]
          });

        this.newInstance();
    },

    newInstance:function(){
    	
    	var me = this;
    	this.invokeService('getNewTrack', [], function(result){
    		
    		me.context.entity = result;
    	});
    },
    getEntity:function(entity){
    	
    	var franchiseeId = this.queryString('franchiseeId');
    	if(franchiseeId){

        	entity.franchiseeId = franchiseeId;
    	}
    	
    	entity.intentionDegree = $('#intentionDegree').attr('data-values');
    	entity.trackProgress = $('#trackProgress').attr('data-values');
    	entity.expectedSign = $('#expectedSign').attr('data-values');
    	entity.nextTrackDate = $('#nextTrackDate').val();
    	entity.content = $('#content').val();
    	return entity;
    },
    validate:function(){
    	
        var intentionDegree = $('#intentionDegree').attr('data-values');
        var trackProgress = $('#trackProgress').attr('data-values');
        var expectedSign = $('#expectedSign').attr('data-values');
        var nextTrackDate = $('#nextTrackDate').val();
        var content = $('#content').val();
        
        if(System.isnull(intentionDegree)){
      	  
      	  $.toptip('请选择意向度');
      	  return false;
        }
        
        if(System.isnull(trackProgress)){
      	  
      	  $.toptip('请选择进度');
      	  return false;
        }
        
        if(System.isnull(expectedSign)){
        	  
    	  $.toptip('请选择预计签单时间');
    	  return false;
        }
        
        if(System.isnull(nextTrackDate)){
      	  
      	  $.toptip('请选择下次跟进时间');
      	  return false;
        }
        
        if(content == ""){
      	  
      	  $.toptip('请输入跟进内容');
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
    	this.invokeService('saveTrack', [entity], function(result){
    		
    		$.toptip('提交成功', 'success');
    	    setTimeout(function() {
    	    	window.location.href = 'franchiseeTrackList?franchiseeId='+result.franchiseeId;
    	    },1000);
    	});
    }
});

