<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/1
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册角色选择</title>
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
            height:414px;
            margin:0 auto;
            position:relative;
        }
        .banner{
            width:796px;
            height:414px;
            padding:0px 0px 0px 0px;
            margin: 0px 0px 0px 0px;
        }
        .slides li{
            height:414px;
            width:796px;
            list-style: none;
        }
        .kuang{
            width:362px;
            height:414px;
            background:url(img/denglu-bg.png) no-repeat;
            z-index:1000;
            position:absolute;
            right:0;
            bottom:0;
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
            display: flex;
            width:348px;
            height:120px;
            font-size:20px;
            line-height:60px;
            padding-left: 110px;
        }
        .btn-login{
            float:left;
            width:200px;
            height:36px;
            line-height:38px;
            background:#0566c5;
            font-size:18px;
            color:#fff;
            margin-left: 70px;
            margin-top: 30px;
        }
        .content{
            width:1140px;
            height:414px;
            margin:0 auto;
            position:relative;
        }
        .banner{
            width:796px;
            height:414px;
            padding:0px 0px 0px 0px;
            margin: 0px 0px 0px 0px;
        }
        .slides li{
            height:414px;
            width:796px;
            list-style: none;
        }
        .kuang{
            width:362px;
            height:414px;
            background:url(img/denglu-bg.png) no-repeat;
            z-index:1000;
            position:absolute;
            right:0;
            bottom:0;
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
            display: flex;
            width:348px;
            height:120px;
            font-size:20px;
            line-height:60px;
            padding-left: 110px;
        }
        .btn-login{
            float:left;
            width:200px;
            height:36px;
            line-height:38px;
            background:#0566c5;
            font-size:18px;
            color:#fff;
            margin-left: 10px;
            margin-top: 30px;
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
        <p >注册角色选择</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userLogin.do" style="font-size: x-large;color: white">返回登录</a></li>
        </ul>
    </div>
</div>
<div class="medium">
</div>
<%--<div id = "title" style="padding-left:530px; padding-top: 100px;">
    *注册新用户
    <div id="div1">
    </div>
    <style>
        #div1{
            margin-top: 10px;
            width: 200px;
            border-bottom: 1px solid #000000;
        }
    </style>
    &lt;%&ndash;    <svg>&ndash;%&gt;
    &lt;%&ndash;        <line x1="400" y1="0" x2="600" y2="0" style="stroke:rgb(109,112,255);stroke-width:4" />&ndash;%&gt;
    &lt;%&ndash;    </svg>&ndash;%&gt;
</div>--%>
<div class="content">
    <div class="banner">
        <ul class="slides">
            <li><img src="img/timg (1).jpg" width="796" height="100%"/> </li>
        </ul>
    </div>
    <div class="kuang">
        <div class="xuanze" style="width:348px; height:300px; margin-left:7px; margin-top:7px;">
            <div style="width:348px; height:66px; background:#e1e1e1;">
                <p class="current"><img src="img/yhdl.png" style=" float:left; margin:13px 10px 0px 30px;" />角色选择</p>
            </div>
            <div class="xuanxiang">
                <form  role="form" action="/registerRole.do" id = "registerRole.do_form" method="post" novalidate="novalidate" >
                    <input type="radio" name="userRole" value="user" onclick="registerRole()">普通用户<br>
                    <input type="radio" name="userRole" value="doctorres" onclick="registerRole()">医疗人员<br>
                    <div id = "next" style="display: none">
                        <input id="choose" type="submit" value="下一步，填写注册信息" class="btn-login">
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<script>
    function registerRole() {
        document.getElementById("next").style.display="block";
    }
</script>
<div class="line"></div>
<div class="footer">
    <p>服务热线  800861270</p>
    <p>版权所有 智慧医养公共服务平台</p>
</div>

<%--<div id = "login-box "  style="align-content: center">
    <div id="login-form">
        <form  role="form" action="/registerRole.do" id = "registerRole.do_form" method="post" style="padding-left: 530px; padding-top: 30px;" novalidate="novalidate" >
            <div id="chrckRole" style="display: inline;padding-top: 15px;padding-bottom: 30px">
                <input type="radio" name="userRole" value="user" onclick="registerRole()">普通用户&nbsp;&nbsp;
                <input type="radio" name="userRole" value="doctorres" onclick="registerRole()">医疗人员<br>
            </div>
            <br>
            <div  id="next" style="display: inline;padding-left:30px;padding-top:15px;display:none">
                <input type="submit" id="choose" value="下一步，填写注册信息" >
            </div>
        </form>
    </div>
</div>
<script>
    function registerRole() {
        document.getElementById("next").style.display="block";
    }
</script>--%>
</body>
</html>

