System.Declare("com.gongsibao.franchisee.web");
com.gongsibao.franchisee.web.FranchiseeListPart = org.netsharp.panda.commerce.ListPart.Extends( {

    ctor: function () {
        this.base();
    },
    allot:function(){
    	
    	var selectedCount = this.getSelectionCount();
    	if(selectedCount == 0){
    		
    		IMessageBox.info('请选择记录');
    		return;
    	}
    	
    	//是否存在【已分配】
    	var isHasAllocated = false;
    	var selectedRows = this.getSelections();
		for ( var i = 0; i < selectedRows.length; i++) {
			var allotStatus = selectedRows[i].allotStatus;
			if(allotStatus == '已分配'){
				
				isHasAllocated = true;
			}
		}
		
		var me = this;
		if(isHasAllocated===true){
			
			IMessageBox.confirm('所选记录中存在状态为【已分配】，是否继续？',function(isOk){
				
				if(isOk===true){
					
					me.doAllot();
				}
			});
		}else{
			
			me.doAllot();
		}
		
    },
    doAllot:function(){

    	var builder = new System.StringBuilder();
    	builder.append('<div style="margin:10px;">');
    	builder.append('	<table cellpadding="5" cellspacing="10" class="query-panel">');
    	builder.append(' 		<tr><td class="title">部门</td><td><input id="allot_department_name"/></td></tr>');
    	builder.append(' 		<tr><td class="title">业务员</td><td><input id="allot_employee_name"/></td></tr>');
    	builder.append('	</table>');
    	builder.append('</div>');
        
    	var me = this;
    	layer.open({
	  		  type: 1,
	  		  title: '分配客户',
	  		  fixed: false,
	  		  maxmin: false,
	  		  shadeClose:false,
	  		  zIndex:100000,
	  		  area: ['400px', '250px'],
	  		  content: builder.toString(),
	  		  btn: ['提交','取消'],
	  		  success: function(layero, index){
	  		    
		  			  var options = {
		  					  columns: [[{field:'pathName',title:'名称',width:150}]],
		  					  url:'\/panda\/rest\/reference?code=Organization-Department&filter=organizationType____3',
		  					  idField:'id',
		  					  textField:'pathName',
		  					  width:250,
		  					  fitColumns:true,
		  					  panelWidth:450,
		  					  panelHeight:310,
							  pagination:true,
							  pageSize:10,
							  mode:'remote',
							  multiple:false};
		  			  $('#allot_department_name').combogrid(options);
		  			  
		  			  //覆盖部门下拉参照配置
		  			  options.columns=[[{field:'name',title:'姓名',width:150}]];
		  			  options.url='\/panda\/rest\/reference?code=Employee&filter=';
		  			  options.textField='name';
		  			  $('#allot_employee_name').combogrid(options);
	  			  
	  		  },
	  		  btn1: function(index, layero){

			  	    var departmentId = $('#allot_department_name').combogrid('getValue');
			  	    if(System.isnull(departmentId)){
			  	    	
			  	    	IMessageBox.info('请选择部门');
			  	    	return;
			  	    }
			  	    
		  	    	var ownerId= $('#allot_employee_name').combogrid('getValue');
		  	    	var ids = me.getSelectionIds();
		  	    	me.invokeService("allot", [ids,departmentId,ownerId], function(data) {
	
		  	    		if(data===true){

			  	    		layer.close(index);
			  	    		IMessageBox.toast("分配成功！");
		  	    		}
		  			});
	  		  }
	  	});
    }
});