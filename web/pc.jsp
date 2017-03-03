<%--
  Created by IntelliJ IDEA.
  User: SUN
  Date: 2017/2/26
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请扫描二维码在手机打开</title>
    <script src="js/jquery-1.8.3.min.js"></script>
    <style>
        *{padding: 0;margin: 0;border: none;}
        .wrap{background: #393939;width: 960px;margin: auto;position: relative}
        .wrap-content{width:300px;text-align: center;position: absolute;margin:auto;top:50%;left:50%;margin-left:-150px;margin-top: -200px;}
        .pic-title{color: #fff;font-size: 15px;height: 30px;line-height: 30px;}
        .wrap-content img{width: 100%;height: 100%;margin: 15px 0;}
        .pic-bottom{height: 45px;line-height: 45px;color: #fff;background:#292929;text-align: center;border-radius: 10px;
            width:95%;margin: auto}
    </style>
</head>
<body>
<div class="wrap" id="wrap">
    <div class="wrap-content">
        <div class="pic-title">手机登录</div>
        <img src="../H5weixin4/img/ewm.jpg" alt="二维码">
        <div class="pic-bottom">请使用手机扫描二维码参加该活动</div>
    </div>
</div>
<script>
    //获取浏览器高度
    var height1=document.documentElement.clientHeight
    $("#wrap").css("height",height1);
</script>
</body>
</html>
