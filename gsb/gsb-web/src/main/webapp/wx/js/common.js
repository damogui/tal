  $(function() {
    FastClick.attach(document.body);
  });
  
  System.Declare("org.netsharp.core");
  var EntityState =
  {
      Transient: "Transient",
      New: "New",
      Persist: "Persist",
      Deleted: "Deleted"
  };
  var ResultType =
  {
      info: "info",
      warn: "warn",
      error: "error",
      loginTimeout: "loginTimeout"
  };
  org.netsharp.core.JServiceLocator = System.Object.Extends({

      ctor: function () {
          this.url = System.Url.getUrl("/panda/rest/service");
      },

      invoke: function (service, methodName, pars, callback, vid, isAsyn, errorCallback) {

          if (System.isnull(vid)) {
              vid = null;
          }

          var message = {
              service: service,
              methodName: methodName,
              vid: vid,
              parameters: []
          };

          if (pars) {
              for (var i = 0; i < pars.length; i++) {

                  var j = JSON.stringify(pars[i]);
                  var jpa = { value: encodeURIComponent(j) };
                  message.parameters.push(jpa);
              }
          }

          this.doInvoke(this.url, message, callback, isAsyn, errorCallback);
      },
      doInvoke: function (url, message, callback, isAsyn, errorCallback) {

          if (isAsyn == undefined || isAsyn == null) {
              isAsyn = true;
          }
          var me = this;
          var jstr = JSON.stringify(message);
          $.ajax({
              url: url,
              type: "Post",
              dataType: "text",
              contentType: "json;charset=utf-8",
              data: jstr,
              async: isAsyn,
              success: function (json) {

                  var result = null;
                  try {
                      result = order = JSON.parse(json);
                  }
                  catch (error2) {
                	  
                	  $.toptip('系统异常', 'error');
                      return;
                  }

                  if (result.type == ResultType.info) {

                      if (callback == undefined || callback == null) {
                          return;
                      }
                      callback(result.data);
                  }
                  else if (result.type == ResultType.warn) {

                      if (errorCallback == undefined || errorCallback == null) {
                    	  
                    	  $.toptip(result.message, 'warning');
                      } else {
                    	  
                    	  $.toptip(result.message, 'warning');
                          //IMessageBox.warning(result.message, errorCallback);
                      }
                  }
                  else if (result.type == ResultType.error) {

                      if (errorCallback == undefined || errorCallback == null) {
                    	  
                    	  $.toptip(result.message, 'error');
                          
                      } else {
                    	  
                    	  $.toptip(result.message, 'error');
                          //IMessageBox.error(result.message, errorCallback);
                      }
                      
                  } else if (result.type == ResultType.loginTimeout) {

                  	  window.top.location.href='/nav/panda-bizbase/authorization/login';
                  }

                  $.hideLoading();
              },
              error: function (p1, p2, p3) {
                  try {

                      if (p1.responseText != "") {

                    	$.toptip(p1.responseText, 'error');
                      }
                      return;
                  }
                  catch (error) {
                	  $.toptip('系统异常', 'error');
                  }
              }
          });
      }
  });
System.Declare("org.netsharp.we.core");
org.netsharp.we.core = {};

System.Declare("org.netsharp.we.core.view");
org.netsharp.we.core.view = System.Object.Extends({
    ctor: function () {
    	this.context = {};
    	this.service = null;
    },
    invokeService: function (method, pars, callback, isAsyn, errorCallback) {

    	$.showLoading();
        var me = this;
        var serviceLocator = new org.netsharp.core.JServiceLocator();
        var thisCallback = function (data) {
        	
        	$.hideLoading();
            if (!System.isnull(callback)) {
            	
                callback(data);
            }
        };
        serviceLocator.invoke(this.service, method, pars, thisCallback, this.vid, isAsyn, errorCallback);
    },
    queryString: function (name,callback) {

        var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
        if (result == null || result.length < 1) {

            return "";
        }
        return result[1];
    },
    init:function(){
    	
    	
    }
});