<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/6/27
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>医生我的出诊</title>
    <style>
        .head {
            background-color: #337ab7;
            height: 100px;
            margin-bottom: 20px;
        }

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        table {
            width: 100%;
            border-spacing: 10px;
        }

        table tr {
            text-align: center;
            margin-top: 20px;
        }

        tr th {
            background-color: #9dcce3;
            font-size: 22px;
        }
        .div_btn1{
            margin-left: 20px;
            margin-right: 20px;
            margin-top: 10px;
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
        <p >我的出诊</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li >
                <a  style="font-size: x-large;padding-bottom: 9%" href="/docRecord.do">出诊记录</a>
            </li>
            <li><a  href="/doctorHome.do" style="font-size: x-large;color: white">医生首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/doctorExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<div>
    <br>
    <form method="post">
        <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
        <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
        <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
    </form>
    <table id="table">
        <tr>
            <th>用户姓名</th>
            <th>联系方式</th>
            <th>家庭住址</th>
            <th>请求时间</th>
            <th>请求内容</th>
            <th>可选操作</th>

        </tr>
    </table>
    <hr><br>
    <div class="title3" style="text-align: center">
        <button class="div_btn" onclick="queryDoc(1)">首页</button>
        <button class="div_btn" onclick="queryDoc(2)">上一页</button>
        <span class="" id="showInfo" style="color: black"> 第 1 页 / 共 1 页 </span>
        <button class="div_btn" onclick="queryDoc(3)">下一页</button>
        <button class="div_btn" onclick="queryDoc(4)">末页</button>
    </div>
</div>


<script type="application/javascript">

    $(function () {
        queryDoc(0);
    });


    function update(id) {
        $.ajax({
            url: "/docRefuseMission.do",
            data:
                {
                    "applyId": id
                }
            ,
            type: "Post",
            success: function () {
                queryDoc(0);
                alert("处理成功")
            },
            error: function () {
                alert("error");
            }
        });
    }

    function finish(id1,id2,introduce) {
        $.ajax({
            url: "/docFinishMission.do",
            data:
                {
                    "userId": id1,
                    "applyId": id2,
                    "introduce":introduce
                }
            ,
            type: "Post",
            success: function () {
                queryDoc(0);
                alert("处理成功")
            },
            error: function () {
                alert("error");
            }
        });
    }
    function queryDoc(key) {

        var curPage = $("#curPage").val();
        var maxPage = $("#maxPage").val();
        var row4Page = $("#row4Page").val();

        switch (key) {
            case 0:
                $("#curPage").val(1);
                $("#maxPage").val(1);
                $("#row4Page").val(3);
                curPage = 1;
                maxPage = 1;
                row4Page = 3;
                break;
            case 1:
                $("#curPage").val(1);
                break;
            case 2:
                $("#curPage").val(curPage > 1 ? curPage - 1 : curPage);
                break;
            case 3:
                $("#curPage").val(curPage < maxPage ? curPage * 1 + 1 : maxPage);
                break;
            case 4:
                $("#curPage").val(maxPage);
                break;
        }
        $.ajax({
            url: "/queryDocAssignList.do",
            data:
                {

                    "curPage": $("#curPage").val(),
                    "row4Page": $("#row4Page").val(),
                    "maxPage": $("#maxPage").val()
                }
            ,
            type: "Post",
            success: function (page) {
                refreshTable(page);
            },
            error: function () {
                alert("error");
            }
        });
    }

    /**
     * 刷新表格
     * @param page
     */

    function refreshTable(page) {

        $("#curPage").val(page.curPage);
        $("#maxPage").val(page.maxPage);
        $("#row4Page").val(page.row4Page);

        $("#showInfo").html(" 第 " + page.curPage + " 页 / 共 " + page.maxPage + " 页 ");
        var List = page.objList;
        //获取表格行集合
        var trList = $("#table tr");
        //遍历删除表格行
        $.each(trList, function (index, item) {
            if (index > 0) {
                $(item).remove();
            }
        });
        //刷新表格行
        // if (!List) {
        //     return;
        // }
        List.forEach(function (data) {

            var htmlStr = "<tr style='padding: 10px'>" +
                "<td>" +data.user.userName+ "</td>" +
                "<td>" +data.user.phone + "</td>" +
                "<td>" +data.user.homeAdd + "</td>" +
                "<td>" +data.applyTime+ "</td>" +
                "<td>" + data.applyIntro + "</td>" +
                "<td><button class='div_btn1' onclick='update(" + data.applyId + ")'>暂时拒绝</button><br>" +
                "<button class='div_btn1' onclick='finish(" + data.userId +","+data.applyId+ ",\""+data.applyIntro+"\")'>完成申请</button>"+
                "</td>" +
                "</tr><br>";
            if(data.is_checked=="true"){
                $("#table").append(htmlStr);
            }

        });
    }
</script>
<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
</body>
</html>
