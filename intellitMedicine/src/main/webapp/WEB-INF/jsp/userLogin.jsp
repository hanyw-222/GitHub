
<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/6/25
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <style type="text/css">
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-image: url("img/loginBackground.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-size:cover;
            background-attachment:fixed;
        }
        .LoginDiv {
            width: 100%;
            display: flex;
            flex-direction: column;
            text-align: center;
            align-items: center;
        }

        h2 {
            font-size: 32px;
            letter-spacing: 31px;
            margin-bottom: 16px;
        }
        .form-group1{
            display: flex;
            flex-direction: row;
            font-size: 20px;
            letter-spacing: 30px;
            text-align: center;
            color: #fff;
            margin-top: 10px;
            margin-bottom: 15px;
        }
        .form-group2{
            align-items: center;
            font-size: 20px;
            text-align: center;
            color: #fff;
            margin-top: 15px;
            margin-bottom: 15px;
        }
    </style>
    <script>
        function registerRole() {
            window.location="/registerRole.do";
        }
    </script>
</head>

<body>


<div class="LoginDiv">

    <form  action="userLogin.do" method="post">
        <div class="form-group1" style="font-size: xx-large;">
            <h1>区级卫生健康委员会智慧公共服务平台</h1>
        </div>
        <br><br>
        <c:if test="${!empty msg1}">
            <div style="text-align: center;color: blue">${msg1}</div>
        </c:if>
        <div class="form-group2">
            用户名: <input id="name" type="text" name="name" placeholder="输入用户名" style="color: #000000;border:#1b1b1b1a 1px solid;">
        </div>
        <div class="form-group2">
            密&nbsp;&nbsp;&nbsp;&nbsp;码: <input id="psw" type="password" name="password" placeholder="输入密码" style="color: #000000;border:#1b1b1b1a 1px solid;">
        </div>
        <div class="form-group2">

            <div  style="padding-top: 6px">
                用户类型:&nbsp;&nbsp;&nbsp;<input id="user" type="radio" name="userRole" value="user">普通用户&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="root" type="radio" name="userRole" value="root">管理员&nbsp;&nbsp;&nbsp;&nbsp;
                <input id="doctorres" type="radio" name="userRole" value="doctorres">医生<br>
            </div>
        </div>
        <br>
        <div class="form-group2">
            <div >
                <input type="button" id="register" value="注册" style=" border:white solid 1px;background-color: rgba(33,150,243,.3);width: 150px" onclick="registerRole()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" id="login" value="登 录" style=" border:white solid 1px;background-color: rgba(33,150,243,.3);width: 150px" onclick="return submitCheak()">
            </div>
        </div>
    </form>
</div>
<script>


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
        if(!document.getElementById("user").checked&&!document.getElementById("root").checked&&!document.getElementById("doctorres").checked){
            alert("必须选择用户角色！");
            return false;
        }
        return true;
    }
</script>

</body>

</html>

