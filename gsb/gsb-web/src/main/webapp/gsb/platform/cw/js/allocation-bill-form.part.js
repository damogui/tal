System.Declare("com.gongsibao.cw.web");
com.gongsibao.cw.web.AllocationBillFormPart =  org.netsharp.panda.commerce.FormPart.Extends({
	
	  ctor: function () {
	        this.base();
	    },
	    added: function (currentItem) {
	        $("#memoto").css({ "width": "500px" });
	    },
	    doSave: function (entity) {
	        var me = this;
	        IMessageBox.confirm('确定提交申请吗？', function (r) {
	            if (r) {
	                me.invokeService("saveAllocation", [entity], function (data) {
	                    IMessageBox.info('申请成功，请等待审核!', function (s) {
	                        window.parent.layer.closeAll();
	                        window.parent.controllerallocationList.reload();
	                    });
	                });
	            }
	        });
	    }
});



//附件
com.gongsibao.cw.web.AttachmentListPart = org.netsharp.panda.commerce.DetailPart.Extends({
	ctor: function () {
      this.base();
  },
  initUpload: function () {

      var upload = new org.netsharp.controls.LoanFileUpload();
      upload.parent = this;
      upload.init();
  },
  appendRow: function (path, file) {

      var row = new Object();
      row.name = file.name;
      row.url = path;
      row.tabName = 'cw_allocation';//要放到后台处理
      $('#' + this.context.id).datagrid('appendRow', row);
  },
  onload: function () {

      this.resize();
      this.initUpload();
  },
  urlFormatter: function (value, row, index) {

      var str = '<a class="grid-btn" href="javascript:window.open(\'' + row.url + '\');">查看</a> \
		   <a class="grid-btn" href="javascript:controllerfiles.remove(' + index + ');">删除</a>';
      return str;
  },
  remove: function (index) {

      $('#' + this.context.id).datagrid('deleteRow', index);
  }
});

org.netsharp.controls.LoanFileUpload = org.netsharp.controls.OSSUpload.Extends({
  ctor: function () {
      this.base();
      this.multi_selection = true;
      this.parent = null;
  },
  getButtonId: function () {

      return "controllerfilesuploadAdd";
  },
  preview: function (path, file) {

      if (System.isnull(path)) {
          return;
      }
      this.parent.appendRow(path, file);
  }
});


