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