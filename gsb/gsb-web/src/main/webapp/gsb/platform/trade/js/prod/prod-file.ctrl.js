System.Declare("com.gongsibao.trade.web");
com.gongsibao.trade.web.FileCtrl = org.netsharp.panda.core.CustomCtrl.Extends({
    ctor: function () {
    	this.base();
    	this.service = 'com.gongsibao.trade.web.OrderProdDetailController';
    	this.mainCtrl = null;
    },
    init:function(orderProdId){

    	alert(orderProdId);
    }
});

//您还需要上传7个必传文件：
//托管协议原件彩色扫描件及托管商IDC证书复印件、域名注册证书原件彩色扫描件、公司概况、公司章程工商调档原件扫描件、法人及股东身份证原件彩色扫描件、公司主要管理、技术人员身份证原件彩色扫描件和学历证书复印件、公司近期为员工所上的社保证明原件

//您已经上传了1个必传文件：

//您已经上传了1个必传文件：

//状态	材料名称	上传人	备注	上传时间	操作

//http://t1.gongsibao.com/gongsibao-order/soorderprodtracefile/download?orderProdIdStr=1nF_3Ni0