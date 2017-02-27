$(function () {
    var isOperate =0;


//首页规则内容
    $("#btn-down").click(function () {
        $("#rule-turn").removeClass("rule-part1-less");
        $("#rule-turn").addClass("rule-part1-more");
        $("#btn-down-wrap").css("display","none");
        $("#rule-hidden").slideDown(600);
    });
    $("#btn-up").click(function () {
        $("#rule-turn").removeClass("rule-part1-more");
        $("#rule-turn").addClass("rule-part1-less");
        $("#rule-hidden").css("display","none");
        $("#btn-down-wrap").css("display","block");
    });

//省市选择
    new PCAS("P1","C1");
    new PCAS("P2","C2","请选择省份");

// //立即上传
    $("#upload").click(function () {
        if(isOperate==0)//未邀请
        {
            $("#register-PoP").fadeIn(300);
        }
        else if(isOperate==2){//已邀请
            $("#province-pop").fadeIn(300);
        }
        else  if(isOperate==1)
        {
            alert("请不要重复认证");
        }
    })
//     $("#upload").click(function () {
//         $.ajax({
//             type:"get",
//             url:"url",//请求是否已经邀请并返回参数
//             success:function(isInvitation)
//             {
//                 if(isInvitation==0)
//                 {
//                     $("#register-PoP").fadeIn(300);
//                 }
//                 else if(isInvitation==0)
//                 {
//                     $("#province-pop").fadeIn(300);
//                 }
//             }
//         })
//     });
//
    $(".close").click(function () {
        $(".PoP-wrap").fadeOut(300);
        $(".error").html("");
    });

// //立即邀请
    $("#upload2").click(function (){
        if(isOperate==0)//未认证
        {
            $("#invitation-PoP").fadeIn(300);
        }
        else if(isOperate==1){//已认证
            $("#share-tip-wrap").fadeIn(300);
        }
    });
//     $("#upload2").click(function () {
//         $.ajax({
//             type:"get",
//             url:"url",//判断是否已经认证并返回参数
//             success:function(isAuthentication)
//             {
//                 if(isAuthentication)
//                 {
//                     $("#share-tip-wrap").fadeIn(300);
//                 }
//                 else if(isAuthentication==0)
//                 {
//                     $("#invitation-PoP").fadeIn(300);
//                 }
//             }
//         })
//     });


    $("#know").click(function () {
        $(".PoP-wrap").fadeOut(300);
    });


//提交按钮:3个按钮
//手机和区域
    $("#registerSubmit").live("click",function () {
        if($("#code").val()=="")
        {
            $("#error").html("请输入验证码");
        }
        else {
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfo",
                data:{
                    "mobile":$("#mobile").val(),
                    "province":$("#province1  option:selected").text(),
                    "city":$("#city1  option:selected").text()
                },
                dataType:"json",
                success:function (success) {
                    if(success == 1)
                    {
                        $("#register-PoP").fadeOut();
                        $("#submitPoP").fadeIn(300);
                        isOperate=1;//认证成功
                    }
                    else  if(success==0)
                    {
                        $("#error").html("该手机号已认证")
                    }
                    else {$("#error").html("请求失败")}
                }
            })
        }
    });
//区域
    $("#ProvinceSubmit").live("click",function () {
        if($("#code").val()=="")
        {
            $("#error").html("请输入验证码");
        }
        else {
            $.ajax({
                type:"post",
                url:"url",
                data:{
                    "city":$("#city2  option:selected").text()
                },
                dataType:"json",
                success:function (success) {
                    if(success)
                    {
                        $("#register-PoP").fadeOut();
                        $("#submitPoP").fadeIn(300);
                    }
                    else {$("#error").html("请求失败")}
                }
            })
        }
    });
//手机
    $("#invitationSubmit").live("click",function () {
        if($("#code2").val()=="")
        {
            $("#error2").html("请输入验证码");
        }
        else {
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfoMobile",
                data:{
                    "mobile":$("#mobile2").val(),
                },
                dataType:"json",
                success:function (success) {
                    if(success == 1)
                    {
                        $("#register-PoP").fadeOut();
                        $("#submitPoP").fadeIn(300);
                        isOperate=2;//已邀请
                    }
                    else  if(success==0)
                    {
                        $("#error").html("该手机号已认证")
                    }
                    else {$("#error").html("请求失败")}
                }
            })
        }
    });

// //验证码按钮
//     $(".send").click(function () {
//         var reg=/^1[3|4|5|8][0-9]\d{4,8}$/;
//         var mobile = $(".mobile").val();
//         if(mobile==""||mobile=="null")
//         {
//             $(".error").html("手机号不能为空");
//         }
//         else if(!reg.test(mobile))
//         {
//             $(".error").html("请输入正确的手机号码");
//         }
//         else {
//             $(".error").html("");
//         }
//     })


//下载APP页面跳转
        $.ajax({
            type:"get",
            url:"weixin?action=down",//判断设备并返回参数
            success:function (link) {
                if(link == 2)
                {
                    $("#downAPP").attr("href","http://139.196.198.75:11024/pcbfzb/resource/androidApp/batzb.apk");
                }
                else if(link==1)
                {
                    $("#downAPP").attr("href","https://www.pgyer.com/aShZ");
                }
            }
        })

})