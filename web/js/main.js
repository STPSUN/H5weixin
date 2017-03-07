$(function () {

    var order=null;
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

//注销
    $("#ID-mobile i").click(function () {
        $("#ID").text("");
    });

//微信二维码弹窗
    $("#get-ewm").click(function () {
        $("#weChat-PoP").fadeIn(300);
    })

// //立即上传
    $("#upload").click(function () {
            $("#form-PoP").fadeIn(300);
            var order=1;
    })

// //立即邀请
    $("#upload2").click(function (){
            $("#form-PoP").fadeIn(300);
            var order=2;
    });

//关闭
    $(".close").click(function () {
        $(".PoP-wrap").fadeOut(300);
        $(".error").html("");
        $("#mobile").val("");
        $("#code").val("");
    });

//信息提交
    $("#submit").live("click",function () {
        if($("#code").val()=="")
        {
            $("#error").html("请输入验证码");
        }
        else {
            $("#error").html("");
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfoMobile",
                data:{
                    "mobile":$("#mobile").val(),
                    "code":$("#code").val()
                },
                dataType:"json",
                success:function (data) {
                    if(data.success == 1 || data.success == 0)
                    {
                        if(data.code==1)//验证码正确
                        {
                            if(order=1){//立即认证按钮
                                $("#form-PoP").fadeOut();
                                $("#submitPoP").fadeIn(300);
                                $("#close").click(function () {
                                    window.location.href = "weixin?action=refresh2&pMobile=" + data.mobile;
                                    $("#ID").val(data.mobile);//获取后台传回来的手机号
                                });
                            }
                            else if(order=2)//立即邀请按钮
                            {
                                $("#form-PoP").fadeOut();
                                $("#share-tip-wrap").fadeIn(300);
                                $("#know").click(function () {
                                    window.location.href = "weixin?action=refresh2&pMobile=" + data.mobile;
                                    $("#ID").val(data.mobile);//获取后台传回来的手机号
                                });
                            }
                        }
                        else if(data.code==0)//验证码错误
                        {
                            $("#error").html("验证码错误")
                        }
                    }
                    else {
                        $("#error").html("请求失败")
                    }
                }
            })
        }
    });

//验证码按钮
    function send(me) {
        var reg = /^1[34578]\d{9}$/;
        var mobile=$("#mobile").val();
        if (mobile == "" || mobile == "null") {
            $("#error").html("手机号不能为空");
        }
        else if (!reg.test(mobile)) {
            $("#error").html("请输入正确的手机号码");
        }
        else if($("#ID").text()==$("#mobile").val())
        {
            $("#error").html("该手机号已认证");
        }
        else {
            $.ajax({
                type:"post",
                url:"weixin?action=sendCode",//发送手机号
                data:{
                      "mobile":$("#mobile").val(),
                },
                dataType:"json",
                success:function (result) {
                    if(result == 1)
                    {
                        var countdown=60;
                        function settime() {
                            if (countdown == 0) {
                                $(me).removeAttr("disabled");
                                $(me).val("获取验证码");
                                countdown = 60;
                                return;
                            } else {
                                $(me).attr("disabled", true);
                                $(me).val("重新发送(" + countdown + ")");
                                countdown--;
                            }
                            setTimeout(settime,1000);
                        }
                        settime();
                    }
                    else {
                        $(".error").html("验证码发送失败，请重新发送");
                    }
                }
            });
        }
    }
    $(".send").click(function () {
        send(this);
    })


//识别设备跳转下载APP页面
    $.ajax({
        type:"get",
        url:"weixin?action=down",//判断设备并返回参数
        success:function (link) {
            if(link==2)//安卓
            {
                $("#downAPP").attr("href","http://139.196.198.75:11024/pcbfzb/resource/androidApp/batzb.apk");
            }
            else if(link==1)//iphone
            {
                $("#downAPP").attr("href","https://www.pgyer.com/aShZ");
            }
        }
    })

    //知道了
    $("#know").click(function () {
        $(".PoP-wrap").fadeOut(300);
    });


    //底部表格，被邀请人数据
    $.ajax({
        type:"get",
        url:"weixin?action=getData",
        dataType:"json",
        success:function (json) {//json存储success和data
            if(json.success == 1){
                var data = json.userList;//data存放邀请人与被邀请人数据
                var htmls=[];
                var hasAuth=0;//计数已经认证的个数
                if(data.length>0){
                    for(var i=0;i<data.length;i++)
                    {
                        var obj=data[i];//第i个被邀请人实体：（手机号码，是否认证）

                        if(obj.awardStatus==1)//已认证
                        {
                            hasAuth++;
                            htmls.push('<li class="box-content"><span>'+ obj.mobile +'</span><span>50元</span></li>');
                        } else if (obj.awardStatus==0)//未认证
                        {
                            htmls.push('<li class="box-content" style="color: #530d0d"><span>' + obj.mobile + '</span><span>50元<i class="noAuth">未认证</i></span></li>');
                        }
                    }
                    $("#invitationList").after(htmls);
                    $("#totalMoney").html(50*hasAuth);
                    $("#totalPerson").html(hasAuth);
                }
            }
        },
    })
})