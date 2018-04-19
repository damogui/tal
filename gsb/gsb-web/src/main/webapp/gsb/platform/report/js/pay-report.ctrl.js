System.Declare("com.gongsibao.report.web");
com.gongsibao.report.web.PayReportCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.report.web.pay.PayReportController';
    	this.data = null;
    },
    init:function(){
    	
    	this.setDefault();
    },
    setDefault:function(){
    	
    	
    },
    query:function(){
    	
    	var startDate = $('#start_date').datebox('getValue');
    	if(System.isnull(startDate)){
    		
    		layer.msg('请选择统计【开始时间】');
    		return;
    	}
    	var endDate = $('#end_date').datebox('getValue');
    	if(System.isnull(endDate)){
    		
    		layer.msg('请选择统计【结束时间】');
    		return;
    	}
    	
    	if (new Date(Date.parse(startDate)) > new Date(Date.parse(endDate))){
    		
    		layer.msg('请选择统计【结束时间】不可小于【开始时间】');
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
			pagination:false,
			showFooter:true,
			singleSelect:true,
			height:contentHeight - 60,
			data:data,
			rowStyler: function(index,row){
				
				if (row.isSubtotal){
					return 'background-color:#f9f9f9;color:blue;';
				}else if (row.isFooter){
					return 'background-color:#f9f9f9;color:red;';
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
			var mergeColumn = {title:title,colspan:2};
			if(dictionary.byKey(key) == null){
				
				dictionary.add(key,mergeColumn);
			}
			var title = item.name.indexOf('submit')>=0?'已提交':'审核通过';
			var col = {field:item.name,title:title,width:100,align:'right',formatter:function(value,row,index){
        		
	        	return System.RMB.fenToYuan(value);
	        }};
			ordinaryColumns.push(col);
    	});
    	
    	//普通列：排序
    	
    	
    	var mergeColumns = [];
	    for(var i = 0;i<dictionary.getLength();i++){
	    	
	    	var mc = dictionary.byIndex(i);
	    	mergeColumns.push(mc.value);
	    }
	    columns.push(mergeColumns);
	    columns.push(ordinaryColumns);
    	return columns;
    },
    downloadExl:function(type){
    	
    	if(this.data == null){
    		
    		layer.msg('没有数据可导出');
    		return;
    	}
    	var json = [{ //测试数据
             "保质期临期预警(天)": "adventLifecycle",
             "商品标题": "title",
             "建议零售价": "defaultPrice",
             "高(cm)": "height",
             "商品描述": "Description",
             "保质期禁售(天)": "lockupLifecycle",
             "商品名称": "skuName",
             "商品简介": "brief",
             "宽(cm)": "width",
             "阿达": "asdz",
             "货号": "goodsNo",
             "商品条码": "skuNo",
             "商品品牌": "brand",
             "净容积(cm^3)": "netVolume",
             "是否保质期管理": "isShelfLifeMgmt",
             "是否串号管理": "isSNMgmt",
             "商品颜色": "color",
             "尺码": "size",
             "是否批次管理": "isBatchMgmt",
             "商品编号": "skuCode",
             "商品简称": "shortName",
             "毛重(g)": "grossWeight",
             "长(cm)": "length",
             "英文名称": "englishName",
             "净重(g)": "netWeight",
             "商品分类": "categoryId",
             "这里超过了": 1111.0,
             "保质期(天)": "expDate"
         }];

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
                     SheetNames: ['mySheet'], //保存的表标题
                     Sheets: {
                         'mySheet': Object.assign({},
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