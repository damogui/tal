<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好未来内部OA</title>
<link  rel='stylesheet' type='text/css' href='/package/font-awesome/css/font-awesome.min.css'/>
<link rel="stylesheet" type="text/css" href="/package/animsition/animsition.css">
<link rel="stylesheet" type="text/css" href="/panda-bizbase/authorization/css/login.css">
</head>
<body class="page-login layout-full page-dark">


<div class="page height-full">
    <div class="page-content height-full">
        <div class="page-brand-info vertical-align animation-slide-left hidden-xs">
            <div class="page-brand vertical-align-middle">
            </div>
        </div>
        <div class="page-login-main animation-fade">
        	
            <div class="vertical-align" style="height:90%;">
                <div class="vertical-align-middle">
                    <div class="brand visible-xs text-center">
                        <img class="brand-img" src="/panda-bizbase/authorization/images/tal.png">
                    </div>
                    <h3 class="hidden-xs">登录</h3>
                    <p class="hidden-xs">建议使用Chrome浏览器，<a target="_blank" href="http://pan.baidu.com/s/1o7EstQy">点击下载</a></p>
                    <form class="login-form" id="loginForm">
                        <div class="form-group">
                            <label class="sr-only" for="username">用户名</label>
                            <input type="text" class="form-control" id="username" name="loginName" placeholder="请输入用户名">
                        </div>
                        <div class="form-group">
                            <label class="sr-only" for="password">密码</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                        </div>
                        <div class="form-group clearfix">
                        
                            <div class="checkbox-custom checkbox-inline checkbox-primary pull-left">
                                <input type="checkbox" id="remember" name="remember">
                                <label for="remember">自动登录</label>
                            </div>
                            <div class="pull-right">
	                            <a class="collapsed" data-toggle="collapse" href="#forgetPassword" aria-expanded="false" aria-controls="forgetPassword"> 找回密码</a>
                            </div>
                        </div>
                    </form>
                    <button type="button" onclick="loginCtrl.login();" class="btn btn-primary btn-block margin-top-30">立即登录</button>
                </div>
            </div>
            <footer class="page-copyright">
                <p>好未来 &copy; <a href="http://www.100tal.com/" target="_blank">100tal.com</a></p>
            </footer>
        </div>
    </div>
</div>
	<script type="text/javascript" src="/package/easyui/jquery.min.js"></script>
	<script src="/package/layer/layer.js"></script>
	<script type="text/javascript" src="/package/jquery/jquery.md5.js"></script>
	<script type="text/javascript" src="/panda-res/js/system.js"></script>
	<script type="text/javascript" src="/panda-res/js/panda.core.js"></script>
	<script type="text/javascript" src="/panda-bizbase/authorization/js/login.js"></script>
	<script>

		var loginCtrl = new org.netsharp.core.LoginController();
		$(function() {
			loginCtrl.init();
		});
		
	</script>

</body>

</html>
