System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.PritBillTemplateCtrl = com.gongsibao.cw.web.AuditBillFormCtrl.Extends({
    ctor: function () {
    	this.base();
    },
    bindFileTable : function (loanData){ //绑定附件数据
    	var me = this;
    	var fileData = loanData.files;
    	if(fileData !=null && fileData.length > 0){
    		for(var i =0;i<fileData.length;i++){
    			var number = i+1;
    			var fileItem = fileData[i];
    			var file_html = "<tr> ";
    			file_html +="<th scope='row'>"+number+"</th>";
    			file_html +="<td> <a class=\"grid-btn\" href=\"javascript:window.open('"+fileItem.url+"');\">"+fileItem.name+"</a></td>";
    			file_html +="</tr>";
    			$("#file_data_table").append(file_html);
    		}
    	}else{
    		$("#file_data_table").append("<tr><td colspan='2' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    },
    bindTripTable : function (billData){  //行程记录
    	var me = this;
    	var tripData =   billData.tripItem;  
    	if(tripData !=null && tripData.length > 0){
    		for(var i =0;i<tripData.length;i++){
    			var number = i+1;
    			var tripItem = tripData[i];
    			var trip_html = "<tr> ";
    			trip_html +="<th scope='row'>"+number+"</th>";
    			trip_html +="<td>"+tripItem.origin+"</td>";
    			trip_html +="<td>"+tripItem.destination+"</td>";
    			trip_html +="<td>"+tripItem.startTime+"</td>";
    			trip_html +="<td>"+tripItem.endTime+"</td>";
    			trip_html +="<td>"+tripItem.memoto+"</td>";
    			trip_html +="</tr>";
    			$("#trip_data_table").append(trip_html);
    		}
    	}else{
    		$("#trip_data_table").append("<tr><td colspan='6' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    },
    bindSubsidyTable : function (billData){  //补助记录
    	var me = this;
    	var subsidyData =   billData.subsidyItem;  
    	if(subsidyData !=null && subsidyData.length > 0){
    		for(var i =0;i<subsidyData.length;i++){
    			var number = i+1;
    			var subsidyItem = subsidyData[i];
    			var subsidy_html = "<tr> ";
    			subsidy_html +="<th scope='row'>"+number+"</th>";
    			subsidy_html +="<td>"+me.subsidyType[subsidyItem.type]+"</td>";
    			subsidy_html +="<td>"+subsidyItem.countDay+"</td>";
    			subsidy_html +="<td>"+subsidyItem.subsidyAmount+"</td>";
    			subsidy_html +="<td>"+subsidyItem.standard+"</td>";
    			subsidy_html +="<td>"+subsidyItem.memoto+"</td>";
    			subsidy_html +="</tr>";
    			$("#subsidy_data_table").append(subsidy_html);
    		}
    	}else{
    		$("#subsidy_data_table").append("<tr><td colspan='6' style='text-align: center;height:35px;'>暂无数据</td></tr>");
    	}
    }

});

