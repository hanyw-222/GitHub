<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/24
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医生首页</title>
    <style type="text/css">
        img{
            background-size:cover;
            width:100%;
            height:70%;
        }
        .tu{
            background-size:cover;
            width:80%;
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
        .block3{
            padding-top: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f3f5f5;
            text-align:center;
        }
        .block31{
            padding-right: 50px;
            padding-left: 50px;
            padding-top: 30px;
            height:400px;
        }
        .block4{
            background-color: #00568e;
            height: 60px;
            width: 100%;
            font-size: 24px;
            text-align: center;
        }
        .wenzi{
            padding-top: 30px;
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

                <li >
                    <a  style="font-size: x-large;padding-bottom: 9%" href="/doctorCenter.do">个人中心：${curLoginDoctor.doctorName}</a>
                </li>
                <li >
                    <a  style="font-size: x-large" href="/doctorExit.do">退出登录</a>
                </li>
            </ul>
        </div>
    </div>
    <div align="center" >
        <img src="img/main.jpg" style="height: 50%;width: 100%">
    </div>
    <div class="wenzi">
        <a><img src="img/position.PNG" style="width: 40px;height: 40px"></a>
        <span style="font-size: 30px">服务平台</span>
    </div>
    <hr>
    <div class="block3" style="background-color: #f3f5f5">
        <div class="block31">
            <div class="tu">
                <a href="/doctorReplyAsk.do"><img src="img/fuwu1.jpg" /></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">我的咨询</p>
            </div>
        </div>
<%--        <div class="block31">--%>
<%--            <div class="tu">--%>
<%--                <a href="/doctorHealthExplain.do"><img src="img/fuwu1.jpg" /></a>--%>
<%--            </div>--%>
<%--            <div style="text-align: center">--%>
<%--                <p style="font-size: 30px">患者健康解读</p>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="block31">
            <div class="tu">
                <a href="/doctorOutCall.do"><img src="img/fuwu3.jpg"/></a>
            </div>
            <div style="text-align: center">
                <p style="font-size: 30px">我的出诊</p>
            </div>
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
