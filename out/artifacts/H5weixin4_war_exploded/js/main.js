$(function () {

    var isOperate =0;
    var ID= null;


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
            $("#Province-PoP").fadeIn(300);
        }
        else  if(isOperate==1)//重复认证提示
        {
            $("#noRepeat").css("display","block");
            setTimeout(show,1000);
            function show() {
                $("#noRepeat").fadeOut();
            }
        }else
        {
            alert("test");
        }
    })

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
        else if(isOperate==1 || isOperate ==2){//已认证
            $("#share-tip-wrap").fadeIn(300);
        }
    });


//提交按钮:3个按钮
//手机和区域
    $("#registerSubmit").live("click",function () {
        if($("#code").val()=="")
        {
            $("#error").html("请输入验证码");
        }
        else  if ($("#province1").val()=="")
        {
            $("#error").html("请选择省份");
        }
        else {
            $("#error").html("");
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfo",
                data:{
                    "mobile":$("#mobile").val(),
                    "province":$("#province1  option:selected").text(),
                    "city":$("#city1  option:selected").text(),
                    "pMobile":$("#pMobile").text(),
                    "code":$("#code").val()
                },
                dataType:"json",
                success:function (data) {
                    if(data.success == 1)//手机号未认证
                    {
                        if(data.code==1){//验证码正确
                            $("#register-PoP").fadeOut();
                            $("#submitPoP").fadeIn(300);
                            isOperate=1;//认证成功
                            ID=$("#mobile").val();
                            // window.location.href = "weixin?action=refresh&pMobile=" + data.mobile;
                            $(".close").click(function () {
                                window.location.href="weixin?action=refresh&pMobile=" + data.mobile;
                            })
                        }
                        else if(data.code==0){//验证码错误
                            $("#error").html("验证码错误")
                        }
                    }
                    else  if(data.success==0)//手机号已认证
                    {
                        $("#error").html("该手机号已认证")
                    }
                    else {
                        $("#error").html("请求失败")
                    }
                }
            })
        }
    });

//提交区域
    $("#ProvinceSubmit").live("click",function () {
        if ($("#province2").val()=="")
        {
            $(".error").html("请选择省份");
        }
        else {
            $(".error").html("");
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfoArea",
                data:{
                    "province":$("#province2  option:selected").text(),
                    "city":$("#city2  option:selected").text()
                },
                dataType:"json",
                success:function (data) {
                    if(data.success == 1)
                    {
                        $("#Province-PoP").fadeOut();
                        $("#submitPoP").fadeIn(300);
                        isOperate = 1;
                    }
                     else {$(".error").html("请求失败")}
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
            $("#error").html("");
            $.ajax({
                type:"post",
                url:"weixin?action=getUserInfoMobile",
                data:{
                    "mobile":$("#mobile2").val(),
                    "code":$("#code2").val()
                },
                dataType:"json",
                success:function (data) {
                    if(data.success == 1 || data.success == 0)
                    {
                        if(data.code== "1")//验证码正确
                        {
                            $("#invitation-PoP").fadeOut();
                            isOperate=2;//已邀请
                            ID=$("#mobile2").val();
                            $("#share-tip-wrap").fadeIn(300);
                            $("#know").click(function () {
                                window.location.href = "weixin?action=refresh2&pMobile=" + data.mobile;
                            });
                        }
                        else if(data.code== "0")//验证码错误
                        {
                            $(".error").html("验证码错误")
                        }
                    }
                    else {
                        $(".error").html("请求失败")
                    }
                }
            })
        }
    });


//验证码按钮
    //验证码按钮
    function send(me) {
        var reg = /^1[34578]\d{9}$/;
        var mobile = $(me).parent().parent().find("input:eq(0)").val();
        if (mobile == "" || mobile == "null") {
            $(".error").html("手机号不能为空");
        }
        else if (!reg.test(mobile)) {
            $(".error").html("请输入正确的手机号码");
        }
        else {
            $(".error").html("");
            $.ajax({
                type:"post",
                url:"weixin?action=sendCode",
                data:{
                    "mobile":$(me).parent().parent().find($(".mobile")).val(),
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
                if(link==2)
                {
                    $("#downAPP").attr("href","http://139.196.198.75:11024/pcbfzb/resource/androidApp/batzb.apk");
                }
                else if(link==1)
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
                // var  items=data.mobile;//items被邀请人列表
                var htmls=[];
                var hasAuth=0;//计数已经认证的个数
                if(data.length>0){
                    for(var i=0;i<data.length;i++)
                    {
                        var obj=data[i];//第i个被邀请人实体：（手机号码，是否认证）
                        // alert("obj.awardStatus:" + obj.awardStatus)
                        // html.push('<li class="box-content"><span>items.mobile</span><span>50元<i>未认证</i></span></li>');//被邀请人手机号
                        // $("#invitationList").before(html);
                        if(obj.awardStatus==1)//已认证
                        {
                            hasAuth++;
                            htmls.push('<li class="box-content"><span>'+ obj.mobile +'</span><span>50元</span></li>');
                            // alert("obj.mobile 1 :" + obj.mobile)
                        } else if (obj.awardStatus==0)//未认证
                        {
                            // alert("obj.mobile 0 :" + obj.mobile)
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

    //填写信息后跳转到新页面并绑定手机号

            if($("#refresh").html()==1)
            {
                $("#register-PoP").fadeOut();
                isOperate=1;//认证成功
                ID=$("#mobile").val();
            }else if($("#refresh2").html()==2)
            {
                $("#invitation-PoP").fadeOut();
                isOperate=2;//认证成功
                ID=$("#mobile").val();
            }
})