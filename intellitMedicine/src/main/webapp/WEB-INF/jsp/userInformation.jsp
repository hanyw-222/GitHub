<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/26
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人基本信息</title>
    <style>
        body{
            display: flex;
            flex-direction: row;
        }
        .left{
            flex:15%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .right{
            flex: 85%;
            display: flex;
            flex-direction: column;
        }
        .right ul li{
            font-size: 20px;
            color: black;
            margin-top: 5px;
        }
        .block{
            display: flex;
            flex-direction: row;
        }
    </style>
</head>
<body>
<div class="left">
    <img src="img/yuanHui.png" alt="加载失败" style="height: 200px;width: 150px;margin-bottom: 20px">
    <input type="button" id="photo" value="修改头像">
</div>
<div class="right">
    <ul>
        <li>
            姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" id="name" name="name">
        </li>
        <li>
            性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input type="text" id="sex" name="sex">
        </li>
        <li>
            年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄：<input type="text" id="age" name="age">
        </li>
        <li>
            出生年月：<input type="text" id="birthday" name="birthday">
        </li>
        <li>
            联系电话：<input type="text" id="phone" name="phone">
        </li>
        <li>
            家庭住址：<input type="text" id="address" name="address">
        </li>
        <li>
            所属社区：<input type="text" id="communicate" name="communicate">
        </li>
    </ul>
    <div class="block">
        <input type="submit" id="submit" value="保存" style="margin-left: 30px;margin-right: 30px">
        <input type="button" id="change" value="修改" style="margin-left: 30px;margin-right: 30px">
    </div>

</div>
<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
</body>
</html>
