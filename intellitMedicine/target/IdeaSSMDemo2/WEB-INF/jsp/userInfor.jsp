<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/26
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>个人基本信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <style>
        body{
            display: flex;
            flex-direction: row;
            padding-top: 70px;
            padding-left: 7%;
        }
        .left{
            /*flex:5%;*/
            margin-left:30%;
            /*display: flex;*/
            /*flex-direction: column;*/
            /*align-items: center;*/
            /*font-size: 24px;*/
        }
        .right{
            margin-left: 22%;
            /*flex: 95%;*/
            /*display: flex;*/
            /*flex-direction: column;*/
            /*padding-left: 100px;*/
        }
        .right ul li{
            font-size: 24px;
            color: black;
            margin-bottom: 10px;
            padding-top:10px;
        }
        .block{
            /*display: flex;*/
            /*flex-direction: row;*/
            padding-top:2%;
            /*margin-right: 35%;*/
            padding-left: 15%;
        }

    </style>
</head>
<body>

<div class="right">
    <form class="form-horizontal" action="updateUserInfor.do" method="post">
        <div class="left" id="upload" style="margin-bottom: 2%">
            <img  src="${user.headAdd}"  onclick="imgClick()" style="height: 40%;width:50%;">
        </div>
    <ul>
        <li>
            <input value="${user.headAdd}" type="text" id="headAdd" name="headAdd" style="display: none" >
            <input value="${user.userId}" type="text" id="userId" name="userId" style="display: none">
            姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input  value="${user.userName}" type="text" id="userName" name="userName">
        </li>
        <li>
            性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：<input  value="${user.sex}" type="text" id="sex" name="sex">
        </li>
        <li>
            生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;日：<input  value="<fmt:formatDate value="${user.date}" pattern="yyyy-MM-dd"/>" type="text" id="date" name="date">
        </li>
<%--        <li>--%>
<%--            出生年月：<input value="${curLoginUser.date}" type="text" id="birthday" name="birthday">--%>
<%--        </li>--%>
        <li>
            联系电话：<input value="${user.phone}" type="text" id="phone" name="phone">
        </li>
        <li>
            家庭住址：<input value="${user.homeAdd}" type="text" id="homeAdd" name="homeAdd">
        </li>
    </ul>
    <div class="block">
        <input type="submit" id="change" value="修改" style="width: 150px; height: 30px;font-size: 20px;margin-left: 20%">
    </div>
</div>
</form>

<script>
    /**
     * 时间格式化方法
     * @param fmt
     * @returns {*}
     * @constructor
     */
    Date.prototype.Format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }

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
            url: "userHeadImg.do",
            type: "POST",
            data: formData,
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (data) {
              // /  alert(data)
                $("headAdd").val(data)
                // $("#headPort").val(data);
                $("#upload").html("<img style=\"height: 40%;width:50%;margin-bottom: 20px\" src='"+data+"' width='200px' onclick='imgClick()'>");
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }



</script>
</body>
</html>
