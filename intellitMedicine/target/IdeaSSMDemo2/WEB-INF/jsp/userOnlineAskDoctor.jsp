<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/6/29
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>一对一问诊</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <style>
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
</head>
<body>
<div class="header">
    <div class="head">
        <p >在线咨询</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userHome.do" style="font-size: x-large;color: white">用户首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/userExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<hr >
<div class="miandiv" style="padding-left: 20%;" >
    <form action="onlineAskForm.do" method="post" id="ask_form" >
        <div class="" style="padding-left: 10%;padding-right: 10%">
            <div class="" style="font-size:xx-large;padding-left: 2%">问题描述:</div>
            <div class="" style="margin-top: 10px;padding-left: 5%">
                <div class="" style="font-size: x-large;padding-top: 1%">疾病介绍<span style="color: #8c8c8c">（必填）</span></div>
                <div class="" style="margin-top: 1%">
                    <textarea style="width: 65%;height: 20%;font-size: larger" autocomplete="off" name="cons" id="new_disease_desc" class="form-desc" maxlength="500" placeholder="请详细描述患者的主要症状、发病时间、治疗经过、病情变化、想得到怎样的帮助（10-500个字）"></textarea>
                </div>

                <div class="" style="font-size:small;;padding-top: 5px">
                    <p class="hd">示例：</p>
                    <p>咨询标题：胸痛、胸闷、伴大汗怎么回事？</p>
                    <p>症状描述：10小时前突然出现胸痛、胸闷、伴大汗、疼痛向左肩背部放射。</p>
                    <p>治疗经过：含服硝酸甘油后症状持续不缓解。</p>
                    <p>检查情况：心率104次/分，心尖部可闻及舒张期奔马律，心电图v1~4导联可见病理性Q波，T波双向。</p>
                </div>

                <div class="form-upload-opreate clearfix">
                    <ul class="form-upload-list"></ul>
                        <div class="" id="upload" >
                            <img alt="默认图片" src="img/title.PNG" width="100px"  height="50px" onclick="imgOnclick()">
                            <p class="" style="color: #31b0d5">添加图片 <span>(可选填),可添加检查单，请保证图片清晰</span></p>
                        </div>
                    </div>
                </div>
                <div style="margin-left: 28%;margin-top: 10px">
                    <button type="submit" class="col-sm-2 btn btn-success" onclick="return submitCheak() ">提交</button>
                </div>

        </div>
    </form>
</div>

<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script type="text/javascript">
    function  imgOnclick() {
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
            url: "uploadExamine.do",
            type: "POST",
            data: formData,
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (data) {
                $("#headPort").val(data);
                $("#upload").html("<img src='"+data+"' width='200px' onclick='imgOnclick()'>");
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }

    function submitCheak(){
        if ($("#new_disease_desc").val() == "") {
            $("#new_disease_desc").focus();
            alert("疾病描述不不能为空！");
            return false;
        } else {
            alert("提交成功！");
            return true;
        }
    }
</script>
</body>
</html>
