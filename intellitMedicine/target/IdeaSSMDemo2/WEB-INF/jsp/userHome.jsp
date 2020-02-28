<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/24
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户首页</title>
    <style type="text/css">
        img{
            background-size:cover;
            width:100%;
            height:70%;
        }
        ul
        {
            list-style-type:none;
            margin:0;
            padding:0;
        }
        li
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
        .logo{
            padding-left: 50px;
            width: 375px;
            height: 120px;
            float: left;
        }
        .daohang {
            display:block;
            float: right;
            margin-top: 37px;
        }
        .header{
            height: 90px;
            line-height: 40px;
            background: #00568e;
        }
        .bar{
            float:right;
        }
        .wenzi{
            padding-top: 30px;
        }
        .block3{
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .block32{
            height: 450px;
            padding-right: 50px;
            padding-left: 50px;
            padding-top: 50px;
            background-size: cover;

        }
        .block321{
            background-size: cover;
            width: 100%;
        }
        .block4{
            background-color: #00568e;
            height: 100px;
            width: 100%;
            font-size: 24px;
            text-align: center;
        }
    </style>
</head>
<body>
<div>
    <div class="header">
        <div class="logo" style=float:left>
            <h1>智慧医养公共服务平台</h1>
        </div>
        <div class="bar">
            <ul class="daohang">
<%--                <li ><a  href="/">首 页</a><li>--%>
                <li ><a style="font-size: x-large" href="/userCenter.do">个人中心：${curLoginUser.userName}</a><li>
                <li ><a style="font-size: x-large" href="/userExit.do">退出登录</a><li>
            </ul>
        </div>
    </div>

    <div align="center">
        <img style="height: 50%;width: 100%" src="img/main.jpg">
    </div>

    <div class="wenzi">
        <a><img src="img/position.PNG" style="width: 40px;height: 40px"></a>
        <span style="font-size: 30px">医疗公共服务平台</span>
    </div>
    <hr>
    <div class="block3" style="background-color: #f3f5f5">
        <div class="block32">
            <div class="block321">
                <a href="/userOnlineAsk.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">在线咨询</p>
            </div>
        </div>
        <div class="block32">
            <div class="block321">
                <a href="/userQueryMedicine.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">药品查询</p>
            </div>
        </div>
<%--        <div class="block32">--%>
<%--            <div class="block321">--%>
<%--                <a href="/userHealthExplain.do"><img src="img/one.PNG"/></a>--%>
<%--            </div>--%>
<%--            <div style="text-align: center">--%>
<%--                <p style="font-size: 30px">健康解读</p>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>

    <div class="wenzi">
        <a><img src="img/position.PNG" style="width: 40px;height: 40px"></a>
        <span style="font-size: 30px">医养结合平台</span>
    </div>
    <hr>
    <div class="block3" style="background-color: #f3f5f5 ">
        <div class="block32">
            <div class="block321">
                <a href="/userHomeCare.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">居家养老</p>
            </div>
        </div>
        <div class="block32">
            <div class="block321">
                <a href="/userCommunityCare.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">社区养老</p>
            </div>
        </div>
        <div class="block32">
            <div class="block321">
                <a href="/userInstitutionCare.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">机构养老</p>
            </div>
        </div>
    </div>

    <div class="wenzi">
        <a><img src="img/position.PNG" style="width: 40px;height: 40px"></a>
        <span style="font-size: 30px">医学讲堂</span>
    </div>
    <hr>


    <div class="block3" style="background-color: #f3f5f5">
        <div class="block32">
            <div class="block321">
                <a href="/userCourseVideo.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">视频专区</p>
            </div>
        </div>
        <div class="block32">
            <div class="block321">
                <a href="/userCourseReading.do"><img src="img/one.PNG" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">阅读专区</p>
            </div>
        </div>
    </div>

</div>
<div style="  width:100%; height:20px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
</body>
</html>
