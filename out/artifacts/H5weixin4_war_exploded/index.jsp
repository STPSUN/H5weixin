<%--
  Created by IntelliJ IDEA.
  User: SUN
  Date: 2017/2/24
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="user-scalable=no, initial-scale=1, maximum-scale=1,width=device-width">
    <script src="js/jquery-1.8.3.min.js"></script>
    <script src="js/ProvinceSelect.js"></script>
    <script src="js/main.js"></script>
    <link rel="stylesheet" href="main.css">
    <title>活动</title>
</head>
<body>
<!--主页面begin-->
<header>
    <picture>
        <source srcset="../H5weixin4/img/icon_banner@3X.jpg" media="(min-width:1000px)" />
        <source srcset="../H5weixin4/img/icon_banner@2X.jpg" media="(min-width:640px)" />
        <img srcset="../H5weixin4/img/icon_banner@1X.jpg" alt="banner" />
    </picture>
</header>
<main>
    <div class="main-content">
        <div class="step step1">
            <div class="step-title">上传证书领红包</div>
            <div class="step-content"><span>认证有奖</span>注册并上传工程师相关证书可领取红包奖励</div>
            <div class="upload" id="upload">立即上传</div>
            <div class="step1-icon"></div>
        </div>
        <div class="step step2">
            <div class="step-title">邀请朋友领红包</div>
            <div class="step-content"><span>邀请有奖</span>邀请朋友上传证书领取红包奖励，多邀多得</div>
            <div class="upload" id="upload2">立即邀请</div>
            <div class="step2-icon"></div>
        </div>
        <div class="rule">
            <div class="rule-part  rule-part1-less" id="rule-turn">
                <div class="rule-content  clearfix ">
                    <span>1、</span>
                    <p>即日起至活动结束日期内，闪蝠众包平台邀请广大用户进行分享活动。每成功邀请一名网络工程师在闪蝠众包平台完成
                        资质认证，即可获得流量奖励。流量奖励将通过闪蝠众包微信公众号进行下发。
                    </p>
                </div>
                <div class="rule-icon"></div>
            </div>
            <div class="btn-down-wrap" id="btn-down-wrap">
                <div class="btn-down" id="btn-down">活动详情展开</div>
            </div>
            <div class="rule-hidden" id="rule-hidden">
                <div class="rule-part rule-part2">
                    <div class="rule-content  clearfix" style="padding-bottom: 0">
                        <span>2、</span>
                        <p>如果您是拥有CCNP/CCIE/HCNP/HCIE证书的工程师，在闪蝠众包APP上完成认证任务，即可获得红包奖励，
                            红包奖励通过app下发至您的账号，可直接提现。</p>
                    </div>
                    <div class="rule-content clearfix" style="padding-top: 0">
                        <span>3、</span>
                        <p>本红包活动仅限于网络工程师注册认证，证书认证仅包括：CCNP、HCNP、CCIE、HCIE。</p>
                    </div>
                </div>
                <div class="rule-part rule-part3">
                    <div class="rule-content  clearfix" style="padding-bottom: 0">
                        <span>2、</span>
                        <p>如果您是拥有CCNP/CCIE/HCNP/HCIE证书的工程师，在闪蝠众包APP上完成认证任务，即可获得红包奖励，
                            红包奖励通过app下发至您的账号，可直接提现。</p>
                    </div>
                    <div class="rule-content clearfix" style="padding-top: 0">
                        <span>3、</span>
                        <p>本红包活动仅限于网络工程师注册认证，证书认证仅包括：CCNP、HCNP、CCIE、HCIE。</p>
                    </div>
                </div>
                <div class="btn-up-wrap">
                    <div class="btn-up" id="btn-up"></div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer>
    <div class="invitation-title">我邀请的好友</div>
    <div class="invitation-list">
        <ul>
            <li class="box-header"><span>累计收益</span><span>成功邀请</span></li>
            <li class="box-content" style="color:#FEEB89"><span>100元</span><span>2人</span></li>
            <li class="box-header"><span>邀请账户</span><span>收益明细</span></li>
            <li class="box-content"><span>181****7159</span><span>50元</span></li>
            <li class="box-content"><span>181****7159</span><span>50元</span></li>
        </ul>
    </div>
</footer>
<!--主页面end-->

<!--手机和区域填写弹窗begin-->
<div class="PoP-wrap" id="register-PoP">
    <div class="register-PoP">
        <i class="close" id="close"></i>
        <div class="register-content">
            <div class="register-title">— 资料填写 —</div>
            <div class="register-item phone clearfix"><i></i><input type="text" placeholder="请输入手机号" id="mobile"></div>
            <div class="register-item code clearfix"><i></i><input type="text" placeholder="请输入验证码" id="code"><span id="send" class="send">发送验证码</span></div>
            <div class="register-item clearfix select-list location-wrap">
                <i></i>
                <div class="location"><select name="P1" id="province1"></select><select name="C1" id="city1"></select></div>
            </div>
            <div class="error" id="error"></div>
        </div>
        <input type="submit" value="提交" class="submit" id="registerSubmit">
    </div>
</div>
<!--手机和区域填写弹窗end-->

<!--仅区域填写弹窗begin-->
<div class="PoP-wrap" id="Province-PoP">
    <div class="register-PoP">
        <i class="close"></i>
        <div class="register-content">
            <div class="register-title">— 请选择区域 —</div>
            <div class="register-item clearfix select-list location-wrap" style="padding-bottom: 20px">
                <i></i>
                <div class="location"><select name="P2" id="province2"></select><select name="C2" id="city2"></select></div>
            </div>
        </div>
        <input type="submit" value="提交" class="submit" id="ProvinceSubmit">
    </div>
</div>
<!--仅区域填写弹窗end-->

<!--仅手机填写弹窗begin-->
<div class="PoP-wrap" id="invitation-PoP">
    <div class="register-PoP">
        <i class="close"></i>
        <div class="register-content">
            <div class="register-title">— 信息确认 —</div>
            <div class="register-item phone clearfix"><i></i><input type="text" placeholder="请输入手机号" id="mobile2"></div>
            <div class="register-item code clearfix"><i></i><input type="text" placeholder="请输入验证码" id="code2"><span id="send2" class="send">发送验证码</span></div>
            <div class="error" id="error2"></div>
        </div>
        <input type="submit" value="提交" class="submit" id="invitationSubmit">
    </div>
</div>
<!--仅手机填写弹窗end-->

<!--资料提交成功弹窗begin-->
<div class="PoP-wrap" id="submitPoP">
    <div class="Submit-wrap">
        <i class="close"></i>
        <i class="submit-bg"></i>
        <p class="p1">资料提交成功</p>
        <p>请在APP里完成认证奖励</p>
        <a href="javascript:void(0)" id="downAPP">下载APP</a>
    </div>
</div>
<!--资料提交成功弹窗end-->

<!--分享提示弹窗begin-->
<div class="PoP-wrap" id="share-tip-wrap">
    <div class="share-tip-wrap">
        <div class="share-tip-content">
            <p>点击右上角发送</p>
            <p>给微信好友或分享</p>
            <p>到朋友圈</p>
        </div>
        <div class="know" id="know"><span>知道了</span></div>
    </div>
</div>
<!--分享提示弹窗begin-->

<!--分享成功弹窗begin-->
<div class="PoP-wrap" id="sharePoP" style="display: none">
    <div class="Submit-wrap">
        <i class="close"></i>
        <i class="submit-bg"></i>
        <p>感谢您的分享，请随时关注微信公众号，了解奖励详情。</p>
        <a href="javascript:void(0)">继续分享</a>
    </div>
</div>
<!--分享成功弹窗end-->

<!--分享到第三方弹窗begin-->
<div class="PoP-wrap" id="shareSelectPoP" >
    <div class="shareSelectPoP">
        <a class="share-list share-QQ" href="javascript:void (0)">QQ分享</a>
        <a class="share-list share-WeCat" href="javascript:void (0)">微信分享</a>
        <a class="share-list share-Email" href="javascript:void (0)">短信分享</a>
    </div>
</div>
<!--分享到第三方弹窗end-->

</body>
</html>