<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/2
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医生注册页面</title>

    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <style>
        .header{
            height: 90px;
            line-height: 6px;
            background: #00568e;
        }
        .logo{
            padding-left: 50px;
            height: 100px;
        }
        .logo p{
            color: #fff;
            text-align:left;
            font-size: 45px;
            padding-top: 38px
        }
        .medium{
            height: 6%;
            background: #fff;
        }
        .content{
            width:1140px;
            height:650px;
            margin:0 auto;
            position:relative;
        }
        .banner{
            width:596px;
            height:650px;
            padding:0px 0px 0px 0px;
            margin: 0px 0px 0px 0px;
        }
        .slides li{
            height:714px;
            width:596px;
            list-style: none;
        }
        .kuang{
            width:562px;
            height:650px;
            background: url(img/denglu-bg.png) no-repeat;
            background-size:100% 100%;
            z-index:1000;
            position:absolute;
            right:0;
            bottom:0;
        }
        .xuanze{
            width: 348px;
            height: 700px;
            margin-left: 7px;
            margin-top: 7px;
        }
        .xuanze p{
            display:block;
            float:left;
            font-size:18px;
            line-height:50px;
            height:50px;
            font-weight:normal;
            width:174px;
            background:#e1e1e1;
            cursor:pointer;
        }
        .xuanze p.current{
            background:#fbfbfb;
        }
        .xuanxiang{
            /*display: flex;*/
            width:450px;
            height:650px;
            font-size:18px;
            line-height:45px;
            padding-left: 110px;
            margin-top: 30px;
        }
        .btn-login{
            float:left;
            width:150px;
            height:30px;
            /*line-height:38px;*/
            background:#0566c5;
            font-size:16px;
            color:#fff;
            margin-left: 70px;
            margin-top: 0px;
        }
        .line{
            width:100%;
            height:55px;
            border-bottom:#d8d8da 1px solid;
        }
        .footer{
            width:100%;
            height:60px;
            padding-top:10px;
            text-align:center;
        }
        .footer p{
            color:#696969;
            line-height:25px;
        }
        .length{
            width:200px;
            height: 24px;
        }
        .col{
            display:block;
            width:70%;
            height:7%;
            float: left;
        }
        .header{
            height: 100px;
            line-height: 40px;
            background:#337ab7;
        }
        .head{
            height: 90px;
            float: left;
            margin-bottom: 20px;
        }
        .daohang {
            display:block;
            float: right;
            margin-top: 37px;
        }
        ul
        {
            list-style-type:none;
            margin:0;
            padding:0;
        }
        .daohang li
        {
            display:inline;
            padding: 0 15px;
        }
        a{
            color: #fff;
        }
        h1{
            color: #fff;
        }
        .head p{
            margin-top: 25px;
            margin-left: 30px;
            font-size: 45px;
            text-align: left;
            color: white;
        }
    </style>
    <script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
</head>
<body>
<div class="header">
    <div class="head">
        <p >医生注册</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userHome.do" style="font-size: x-large;color: white">返回登录</a></li>
        </ul>
    </div>
</div>
</div>
<div class="medium">
</div>

<div class="content">
    <div class="banner">
        <ul class="slides">
            <li><img src="img/timg (1).jpg" width="596px" height="650px"/> </li>
        </ul>
    </div>
    <div class="kuang">
        <div class="xuanze" style="width:348px; height:300px; margin-left:7px; margin-top:7px;">
            <div style="width:543px; height:60px; background:#e1e1e1;">
                <p class="current"><img src="img/yhdl.png" style=" float:left; margin:13px 10px 0px 30px;" />医疗人员注册</p>
            </div>
            <div class="xuanxiang">
                <form action="doctorRegister.do" method="post">


                    <div class="col">
                        <label>用&nbsp;&nbsp;户&nbsp;名:</label>
                        <input type="text" id="name" name="name" placeholder="输入用户名" class="length">
                        <label for="name" id="accountCheakStart"></label>
                    </div>




                    <div class="col">
                        <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
                        <input type="password" id="psw" name="password" placeholder="输入密码" class="length">
                        <label for="name" id="pswCheck1"></label>
                    </div>



                    <div class="col">
                        <label>确认密码:</label>
                        <input type="password" id="psw2" name="passwordRep" placeholder="输入密码" class="length">
                        <label for="name" id="pswCheck2"></label>
                    </div>




                    <div class="col">
                        <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
                        <input type="radio" name="sex" value="male" onclick="registerRole()">男&nbsp;&nbsp;
                        <input type="radio" name="sex" value="female" onclick="registerRole()">女<br>
                    </div>


                    <div class="col">
                        <label>所属机构:</label>
                        <select name="institution">
                            <option value="区人民医院">区人民医院</option>
                            <option value="妇幼保健院">妇幼保健院</option>
                            <option value="中医院">中医院</option>
                            <option value="村卫生室">村卫生室</option>
                        </select>
                    </div>


                    <div class="col">
                        <label>所属科室:</label>
                        <select name="office">
                            <option value="内科">内科</option>
                            <option value="外科">外科</option>
                            <option value="中医科">儿科</option>
                            <option value="妇科">妇科</option>
                        </select>
                    </div>



                    <div class="col">
                        <label>联系电话:</label>
                        <input type="text" id="phone" name="phone" placeholder="输入电话" class="length">
                    </div>


                    <div class="col">
                        <label>身&nbsp;&nbsp;份&nbsp;证:</label>
                        <input type="text" id="IdCard" name="IdCard" placeholder="输入身份证" class="length">
                    </div>


                    <div class="col" style="height: 17%">
                        <div style="float: left">
                            <label>证书上传:</label>
                        </div>
                        <div id="upload" style="float: left">
                            <img alt="默认图片" src="img/title.PNG" style="height: 50%;width: 60%"  onclick="imgClick()">
                        </div>
                    </div>


                    <div>
                        <input type="submit" value="注册" class="btn-login" onclick="return submitCheak()">
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
<div class="line"></div>
<div class="footer">
    <p>服务热线  88888888</p>
    <p>版权所有 智慧医养公共服务平台</p>
</div>
<script>
    function imgClick(){
        //动态创建文件选择框
        var fileObj = document.createElement("input");
        fileObj.type="file";
        //文件选择框的onchange事件
        fileObj.onchange = function(){
            HeadPortUpload(fileObj);
        }
        //激活文件选择框
        fileObj.click();
    }
    //ajax的文件上传
    function HeadPortUpload(fileObj){
        //alert(fileObj.files.length+"\n"+fileObj.files[0].name);
        var formData = new FormData();
        formData.append("file", fileObj.files[0]);
        $.ajax({
            url: "paper.do",
            type: "POST",
            data: formData,
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (data) {
                $("#headPort").val(data);
                $("#upload").html("<img src='"+data+"' style=\"height: 50%;width: 60%\"  onclick='imgClick()'>");
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }
    var msg = "${msg}";
    //帐号输入框失去焦点时进行帐号重复验证
    $("#name").blur(function () {
        var account = this.value;
        if (account != undefined && account != null && account != "") {
            //ajax做后台验证
            $.get("doctorNameCheck.do", {"account": account}, function (data) {
                if (data == "ok") {
                    $("#accountCheakStart").html('<i class="fa fa-check" aria-hidden="true" style="color:#09802b;font-size: 22px;"></i>');
                } else {
                    $("#accountCheakStart").html('<i class="fa fa-close" aria-hidden="true" style="color:#ff3128;font-size: 22px;"></i>');
                }
            });
        } else {
            alert("name="+$("#name").val());
            $("#accountCheakStart").html("");
        }
    });
    $("#psw").blur(function () {
        $("#pswCheck1").html('<i class="fa fa-check" aria-hidden="true" style="color:#09802b;font-size: 22px;"></i>');
    });
    $("#psw2").blur(function () {
        var psw2mima = $("#psw2").val();
        var psw1mima = $("#psw").val();
        if(psw1mima != psw2mima){
            $("#pswCheck1").html('<i class="fa fa-close" aria-hidden="true" style="color:#ff3128;font-size: 22px;"></i>');
            $("#pswCheck2").html('<i class="fa fa-close" aria-hidden="true" style="color:#ff3128;font-size: 22px;"></i>');
            alert("俩次密码不相同,请重新输入！");
            return false;
        } else {
            $("#pswCheck2").html('<i class="fa fa-check" aria-hidden="true" style="color:#09802b;font-size: 22px;"></i>');
            $("#pswCheck1").html('<i class="fa fa-check" aria-hidden="true" style="color:#09802b;font-size: 22px;"></i>');
        }

    });
    //提交时做校验
    function submitCheak() {
        if ($("#name").val() == "") {
            $("#name").focus();
            alert("账号不能为空！");
            return false;
        }
        if ($("#psw").val() == "") {
            $("#psw").focus();
            alert("密码不能为空！");
            return false;
        }
        alert("注册成功，审核成功后才可登录，审核时间为一天！");
        return true;
    }

</script>
</body>
</html>
