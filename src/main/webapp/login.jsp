<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <title>Amaze UI Admin index Examples</title>
 <meta name="description" content="这是一个 index 页面">
 <meta name="keywords" content="index">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta name="renderer" content="webkit">
 <meta http-equiv="Cache-Control" content="no-siteapp" />
 <link rel="icon" type="image/png" href="${ctxStatic}/i/favicon.png">
 <link rel="apple-touch-icon-precomposed" href="${ctxStatic}/i/app-icon72x72@2x.png">
 <meta name="apple-mobile-web-app-title" content="Amaze UI" />
 <link rel="stylesheet" href="${ctxStatic}/css/amazeui.min.css" />
 <link rel="stylesheet" href="${ctxStatic}/css/amazeui.datatables.min.css" />
 <link rel="stylesheet" href="${ctxStatic}/css/app.css">
 <script src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>

 <script type="text/javascript">
     $(document).ready(function() {
         //验证码
         createCode();

     });

     function createCode() {
         $.post(ctxPath+"/captcha",function(data){
             var c = document.getElementById("myCanvas");
             var ctx = c.getContext("2d");
             ctx.clearRect(0,0,1000,1000);
             ctx.fillStyle = "black";
             ctx.font = "55px 'Microsoft Yahei'";
             ctx.fillText(data,0,100);
         });
     }
 </script>

</head>

<body data-type="login">
<script src="${ctxStatic}/js/theme.js"></script>
<div class="am-g tpl-g">
 <!-- 风格切换 -->
 <div class="tpl-skiner">
  <div class="tpl-skiner-toggle am-icon-cog">
  </div>
  <div class="tpl-skiner-content">
   <div class="tpl-skiner-content-title">
    选择主题
   </div>
   <div class="tpl-skiner-content-bar">
    <span class="skiner-color skiner-white" data-color="theme-white"></span>
    <span class="skiner-color skiner-black" data-color="theme-black"></span>
   </div>
  </div>
 </div>
 <div class="tpl-login">
  <div class="tpl-login-content">
   <div class="tpl-login-logo">
   </div>

   <form class="am-form tpl-form-line-form" action="${ctxPath}/login" method="post">
    <div class="am-form-group">
     <input type="text" class="tpl-form-input" id="user-name" name="username" placeholder="请输入账号">
    </div>

    <div class="am-form-group">
     <input type="password" class="tpl-form-input" id="user-name" name="password" placeholder="请输入密码">
    </div>

    <div class="am-form-inline am-form-group">
     <input type="text" name="verifyCode" id="" placeholder="验证码" maxlength="4" />
     <canvas style="width: 420px;height: 70px;" id="myCanvas" onclick="createCode()" >对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
    </div>
    <div class="am-form-group tpl-login-remember-me">
     <input id="remember-me"  name="rememberMe" type="checkbox" value="Y">
     <label for="remember-me">
      记住密码
     </label>
    </div>

    <div class="am-form-group">
     <button type="submit" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">提交</button>
    </div>
   </form>
  </div>
 </div>
</div>
<script src="${ctxStatic}/js/amazeui.min.js"></script>
<script src="${ctxStatic}/js/app.js"></script>

</body>

</html>