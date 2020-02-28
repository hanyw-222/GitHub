<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/2
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
    <style>
        .subtitle {
            font-size: 20px;
            padding-left: 50px;
            padding-right: 50px;
            padding-bottom: 30px;
            text-align: center;
            clear: both;
        }

        .div_btn {
            font-size: 18px;
            margin-right: 30px;

        }

        .head {
            background-color: #337ab7;
            height: 100px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-spacing: 10px;
        }

        table tr {
            font-size: 20px;
            text-align: center;
        }

        tr th {
            background-color: #9dcce3;
        }

        .medium1 {
            font-size: 22px;
            width: 100%;
            display: flex;
            align-items: center;
            justify-items: center;
            margin-top: 20px;
            margin-left: 100px;
        }

        .medium1 ul {
            display: flex;
            flex-direction: row;
            list-style-type: none;
        }

        .medium1 ul li {
            text-align: center;
        }

        .medium1 ul li a {
            margin-right: 100px;
        }

        .medium2 {
            display: flex;
            flex-direction: row;
            float: right;
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
<body onload="commSelect()">
<div class="header">
    <div class="head">
        <p >查看用户信息</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<br>
<div class="medium1">
    社区：
    <ul id="ul1">

    </ul>
</div>


<div class="medium2">

    <input type="text" name="pname" id="pname" placeholder="请输入用户名" style="font-size: 20px"/>

    <button class="div_btn1" style="size: 30px" onclick="queryCourse(0)">查询</button>
</div>
<br><br>
<hr>
<div class="bottom">

    <form method="post">
        <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
        <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
        <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
    </form>

    <table id="table1">
        <tr>
            <th>用户头像</th>
            <th>用户名</th>
            <th>所属社区</th>
            <th>详细信息</th>
        </tr>

    </table>
    <br>
    <div class="subtitle">
        <button class="div_btn" onclick="queryCourse(1)">首页</button>
        <button class="div_btn" onclick="queryCourse(2)">上一页</button>
        <span class="div_btn" id="showInfo" style="color: black"> 第 1 页 / 共 1 页 </span>
        <button class="div_btn" onclick="queryCourse(3)">下一页</button>
        <button class="div_btn" onclick="queryCourse(4)">末页</button>
    </div>
</div>
<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>

<script type="application/javascript">
    var Id
    function courseChoose(key) {
        queryCourse(0);
        Id = key;
    }

    $(function () {
        queryCourse(0);

    });

    function queryCourse(key) {
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
            url: "/queryUsersList.do",
            data:
                {
                    "communityId": Id,
                    "userName": $("#pname").val(),
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
        var courseList = page.objList;
        //获取表格行集合
        var trList = $("#table1 tr");
        //遍历删除表格行
        $.each(trList, function (index, item) {
            if (index > 0) {
                $(item).remove();
            }
        });
        //刷新表格行
        if (!courseList) {
            return;
        }
        courseList.forEach(function (course) {
            // var headAdd = course.headAdd;
            // alert(course.headAdd);
            var htmlStr = "<tr>" +
                "<td>" + course.userId + "</td>" +
                // "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>" + course.userName + "</td>" +
                "<td>" + course.community.comName + "</td>" +
                "<td>电话：" + course.phone + "</td>" +
                "</tr>";

            $("#table1").append(htmlStr);
        });
    }


    function commSelect() {

        $.ajax({
            url: "/selectCommunity.do",
            data: {},
            type: "Post",
            success: function (list) {
                var trList = $("#ul1 li");
                //遍历删除表格行
                $.each(trList, function (index, item) {
                    if (index > 0) {
                        $(item).remove();
                    }
                });
                //刷新表格行
                if (!list) {
                    return;
                }

                list.forEach(function (community) {

                    var htmlStr = "<li><button  class='div_btn' style='margin-right: 80px' onclick='courseChoose(" + community.communityId + ")'>" + community.comName + "</button></li>";

                    $("#ul1").append(htmlStr);
                });
            },
            error: function () {
                alert("error");
            }
        });
    }
</script>

</body>
</html>
