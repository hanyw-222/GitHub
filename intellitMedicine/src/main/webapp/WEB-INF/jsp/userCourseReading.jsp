<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c2" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2019/6/26
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta chartset=UTF-8 >
    <title>课程页面</title>
    <style>
        .title {
            background-color: #337ab7;
            height: 100px;
         /*/   margin-bottom: 20px;*/
        }

        .subtitle {
            font-size: 20px;
            padding-left: 50px;
            padding-right: 50px;
            /*padding-bottom: 5px;*/
            text-align: center;
            clear: both;
        }

        .div_oneline {
            margin: 0;
            padding: 70px;
            display: inline-block;
            _display: inline;
            *display: inline;
            zoom: 1;
        }

        .div_oneline button {
            border: none;
            background-color: white;
            font-size: 20px;
        }

        .div_btn {
            font-size: 18px;
            margin-right: 30px;

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

        .header{
            height: 100px;
            line-height: 40px;
            background:#337ab7;
        }
        .head{
            height: 90px;
            float: left;
            /*margin-bottom: 20px;*/
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
        <p >医学讲堂-阅读专区</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userHome.do" style="font-size: x-large;color: white">用户首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/userExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<form method="post">
    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
</form>

<div class="subtitle">
    筛选：
    <div class="div_oneline">
        <button onclick="courseChoose(1)">营养</button>
    </div>
    <div class="div_oneline">
        <button onclick="courseChoose(2)">母婴</button>
    </div>
    <div class="div_oneline">
        <button onclick="courseChoose(3)">慢病</button>
    </div>
    <div class="div_oneline">
        <button onclick="courseChoose(4)">肿瘤</button>
    </div>
    <div class="div_oneline">
        <button onclick="courseChoose(5)">生活</button>
    </div>
    <div class="div_oneline">
        <button onclick="courseChoose(6)">两性</button>
    </div>
</div>
<hr/>
<div class="span12 center-block">
    <table id="courseTable">
        <tr>
            <th>课程名称</th>
            <th>课程内容</th>
            <th>课程链接</th>
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
    var courseType;
    $(function () {
        queryCourse(0);

    });

    function courseChoose(key) {
        queryCourse(0);
        switch (key) {
            case 1:
                courseType = "营养";
                break;
            case 2:
                courseType = "母婴";
                break;
            case 3:
                courseType = "慢病";
                break;
            case 4:
                courseType = "肿瘤";
                break;
            case 5:
                courseType = "生活";
                break;
            case 6:
                courseType = "两性";
                break;
        }
    }

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
            url: "/queryReadCoursePage.do",
            data:
                {
                    "courseType": courseType,
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
        var trList = $("#courseTable tr");
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
            var htmlStr = "<tr>" +
                "<td>" + course.courseName + "</td>" +
                "<td>" + course.courseIntro + "</td>" +
                "<td><a href='../.."+course.courseAdd+"' style='color: #31b0d5'>" +"打开链接"+"</a></td>" +
                "</tr>";

            $("#courseTable").append(htmlStr);
        });
    }
</script>

</body>
</html>
