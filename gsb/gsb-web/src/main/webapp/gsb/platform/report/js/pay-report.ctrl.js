System.Declare("com.gongsibao.report.web");
com.gongsibao.report.web.PayReportCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.report.web.pay.PayReportController';
    	this.rows = null;
    },
    init:function(){
    	
    	this.setDefault();
    },
    setDefault:function(){
    	
    	var today = new Date().format('yyyy-MM-dd');
    	$('#start_date').datebox('setValue',today);
    	$('#end_date').datebox('setValue',today);
    	this.query();
    },
    query:function(){
    	
    	var startDate = $('#start_date').datebox('getValue');
    	if(System.isnull(startDate)){
    		
    		layer.msg('请输入统计【开始时间】');
    		return;
    	}
    	var endDate = $('#end_date').datebox('getValue');
    	if(System.isnull(endDate)){
    		
    		layer.msg('请输入统计【结束时间】');
    		return;
    	}
    	
    	if (new Date(Date.parse(startDate)) > new Date(Date.parse(endDate))){
    		
    		layer.msg('请【结束时间】不能小于【开始时间】');
    		return;
    	}
    	
    	IMessageBox.loading.show();
    	var me = this;
    	this.invokeService('query', [startDate,endDate], function(rows){

    		me.rows = rows;
    		me.initGrid(rows);
    		IMessageBox.loading.hide();
    	});
    },
    initGrid:function(rows){

    	var data = new Object();
    	data.rows = rows;
    	data.footer = this.getFooter(rows);

    	var columns = this.getColumns(rows);
    	
    	var contentHeight =  $('body').height() - 50;
    	$('#content').height(contentHeight);
		$("#datagrid").datagrid({
			idField:'id',
			emptyMsg:'当日没有回款记录',
			title:'<span style="font-weight: normal;color: red;">统计说明：【线上支付】-当日支付成功的记录，【线下支付】-为当日审核通过的记录</span>',
			rownumbers:true,
			pagination:false,
			striped:true,
			showFooter:true,
			singleSelect:true,
			height:contentHeight - 60,
			data:data,
			rowStyler: function(index,row){
				
				if (row.isSubtotal){
					return 'background-color:#f9f9f9;color:blue;';
				}else if (row.isFooter){
					return 'background-color:#f9f9f9;color:red;';
				}else if (row.type ==2){
					
					//type=2:平台服务商
					return 'color:#1E7CB5;';
				}
			},
			frozenColumns:[[{field:'name',title:'部门',width:200}]],
		    columns: columns
		});
    },
    getFooter:function(rows){

    	var me = this;
    	var footerRows = [];
    	var footer = new Object();
    	footer.isFooter = true;
    	footer.name = '合计：';
    	$(rows).each(function(index,item){
    		
    		for(var fieldName in item){

        		if(me.isCalculateColumn(fieldName)){
        			
        			eval('if (!footer.'+fieldName+') {eval("footer.'+fieldName+'=0;")}');
        			var val = parseInt(item[fieldName]);
        			eval('footer["'+fieldName+'"]+=val');
        		}
    		}
    	});
    	footerRows.push(footer);
    	return footerRows;
    },
    isCalculateColumn:function(name){
    	
    	if(name !='name' && name != 'id' && name != 'category_id' && name != 'type'){
    		
    		return true;
    	}
    	return false;
    },
    getColumns:function(rows){
    	
    	var me = this;
    	var obj = rows[0];
    	var fieldList = [];
    	for(var name in obj){//遍历对象属性名  
    		
    		if(me.isCalculateColumn(name)){
    			
    			var ss = name.split('_');
    			var date = ss[1];
    			var col = {index:date,name:name};
    			fieldList.push(col); 
    		}
    	}
    	
    	//排序
    	var compareIndex = function (item1, item2) {
    		
    		var ce = parseInt(item1.index) - parseInt(item2.index);
    		if(ce==0){
    			
    			if(item1.name.indexOf('audit') !=-1){
    				
    				return 1;
    			}
    		}
    		return ce;
    	}   
    	fieldList.sort(compareIndex);
    	
    	var columns = [];
    	var ordinaryColumns = [];
    	var getTitle = function(date){
    		
    		//0409转为  4月19日
    		var month = parseInt(date.substr(4,2));
    		var day = parseInt(date.substr(6,2));
    		return month+'月'+day+'日';
    	}
    	
    	var dictionary = new System.Dictionary();
    	$(fieldList).each(function(index,item){

			var date = item.index;
			var key = date.substring(4,date.length);
			var title = getTitle(date);
			//var mergeColumn = {title:title,colspan:2};//2018-04-22 hw 暂时不做
			var mergeColumn = {title:title};
			if(dictionary.byKey(key) == null){
				
				dictionary.add(key,mergeColumn);
			}
			//var title = item.name.indexOf('submit')>=0?'已提交':'金额';//2018-04-22 hw 暂时不做
			var col = {field:item.name,title:title,width:100,align:'right',formatter:function(value,row,index){
        		
				if(value>0){

		        	return System.RMB.fenToYuan(value);
				}else{
					
					return '-';
				}
	        }};
			ordinaryColumns.push(col);
    	});
    	
    	//普通列：排序
    	var mergeColumns = [];
	    for(var i = 0;i<dictionary.getLength();i++){
	    	
	    	var mc = dictionary.byIndex(i);
	    	mergeColumns.push(mc.value);
	    }
	    //columns.push(mergeColumns);
	    columns.push(ordinaryColumns);
    	return columns;
    },
    downloadExl:function(type){

    	if(this.rows == null){
    		
    		layer.msg('没有数据可导出');
    		return;
    	}

    	var json = [];
    	var ordinaryColumns = $("#datagrid").datagrid('getColumnFields');
    	var freezeColumns = $("#datagrid").datagrid('getColumnFields',true);
    	var columns = ordinaryColumns.concat(freezeColumns);
    	columns.push(freezeColumns);
    	columns.push(ordinaryColumns);
    	
    	var rows = $("#datagrid").datagrid('getRows');

    	var header = new Object();
    	var object = new Object();
    	var row = this.rows[0];
    	for(name in row){
    		
    		var col = getCol(columns,name);
    		if(col != null){

            	header[col.title] = '';
    		}
    	}
    	json.push(header);

    	function getCol(columns,field){
    		
        	for(var i=0;i<columns.length;i++){

        		return $("#datagrid").datagrid('getColumnOption',field);
        	}
        	return null;
    	}


             var tmpdata = json[0];
             json.unshift({});
             var keyMap = []; //获取keys
             for (var k in tmpdata) {
                 keyMap.push(k);
                 json[0][k] = k;
             }
           var tmpdata = [];//用来保存转换好的json 
                 json.map((v, i) => keyMap.map((k, j) => Object.assign({}, {
                     v: v[k],
                     position: (j > 25 ? getCharCol(j) : String.fromCharCode(65 + j)) + (i + 1)
                 }))).reduce((prev, next) => prev.concat(next)).forEach((v, i) => tmpdata[v.position] = {
                     v: v.v
                 });
                 var outputPos = Object.keys(tmpdata); //设置区域,比如表格从A1到D10
                 var tmpWB = {
                     SheetNames: ['回款统计'], //保存的表标题
                     Sheets: {
                         '回款统计': Object.assign({},
                             tmpdata, //内容
                             {
                                 '!ref': outputPos[0] + ':' + outputPos[outputPos.length - 1] //设置填充区域
                             })
                     }
                 };
                 tmpDown = new Blob([s2ab(XLSX.write(tmpWB, 
                     {bookType: (type == undefined ? 'xlsx':type),bookSST: false, type: 'binary'}//这里的数据是用来定义导出的格式类型
                     ))], {
                     type: ""
                 }); //创建二进制对象写入转换好的字节流
             var href = URL.createObjectURL(tmpDown); //创建对象超链接
             document.getElementById("hf").href = href; //绑定a标签
             document.getElementById("hf").click(); //模拟点击实现下载
             setTimeout(function() { //延时释放
                 URL.revokeObjectURL(tmpDown); //用URL.revokeObjectURL()来释放这个object URL
             }, 100);


         function s2ab(s) { //字符串转字符流
             var buf = new ArrayBuffer(s.length);
             var view = new Uint8Array(buf);
             for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
             return buf;
         }
          // 将指定的自然数转换为26进制表示。映射关系：[0-25] -> [A-Z]。
         function getCharCol(n) {
             let temCol = '',
             s = '',
             m = 0
             while (n > 0) {
                 m = n % 26 + 1
                 s = String.fromCharCode(m + 64) + s
                 n = (n - m) / 26
             }
             return s
         }
    }
});
