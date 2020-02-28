<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/2
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>医疗机构管理</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* *x选择器：选择所有元素 */
        * {
            box-sizing: border-box;
        }

        .row {
            display: flex;
        }

        /* 左列(菜单) */
        .left {
            flex: 15%; /*让所有弹性盒模型对象的子元素的长度为15%，且忽略它们内部的内容*/
            padding: 15px 0;
            height: 100%;
            padding-top: 20px;
            margin-left: 20px;
        }

        .left h2 {
            padding-left: 8px;
            background-color: #337ab7;
        }

        /* 右侧(内容) */
        .right {
            flex: 85%; /*让所有弹性盒模型对象的子元素的长度为85%，且忽略它们内部的内容*/
            padding-left: 15px;
            height: 100%;
            padding-top: 10px;
        }

        /* 左侧导航 */
        #myMenu {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        /*左侧导航标签*/
        #myMenu li a {
            background-color: #1784bb;
            padding: 12px;
            text-decoration: none;
            font-size: 26px;
            color: white;
            display: block
        }

        .medium1 {
            display: flex;
            flex-direction: row;
            float: right;
        }

        #myMenu input {
            border: none;
            color: white;
            width: 100%;
            height: 50px;
            font-size: 24px;
            background-color: #337ab7;
        }

        .medium21 {
            display: block;
            margin-top: 10px;
            margin-left: 15%;
        }

        .medium21 ul {
            /*margin-top: 10px;*/
            margin-left: 50px;
        }

        .medium21 ul li {
            font-size: 24px;
            color: black;
            margin-bottom: 10px;
            padding-top: 10px;
        }

        .medium22 {
            display: none;
        }

        .div_btn1 {
            font-size: 18px;
            width: 120px;
            height: 30px;
        }
        .div_btn2{
            font-size: 18px;
            /*margin-right: 30px;*/
            margin-left: 120px;
            width: 120px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-spacing: 10px;
        }

        table tr {
            text-align: center;
        }

        tr th {
            background-color: #9dcce3;
            font-size: 22px;
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
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>

</head>
<body>
<div class="header">
    <div class="head">
        <p >医疗机构管理</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>

<div class="row" style="margin-top: 20px">
    <div class="left" style="background-color:#337ab7;">
        <ul id="myMenu">
            <li><input type="button" id="v1" value="新增医院" onclick="v1show()"></li>
            <li><input type="button" id="v2" value="管理医院" onclick="v2show()"></li>
        </ul>
    </div>
    <div class="right">
        <hr>
        <div class="medium21" id="div1">
            <%-- <img src="img/medicine.jpg" style="width: 350px;height: 400px"><br>
             <input type="button" id="change" name="change" value="上传图片" style="width: 120px;margin-left: 90px;margin-top: 20px">--%>
            <form id="medicineInformation" method="post" style="border: black 1px solid;width: 500px;margin-left: 10%;margin-top: 40px">
                <ul>
                    <li>
                        医院名称：<input type="text" id="name" name="name">
                    </li>
                    <li>
                        医院科室：<input type="text" id="department" name="department">
                    </li>
                    <li>
                        医院简介：<br> <br><textarea name="area" id="area" cols="30" rows="5" style="font-size: 20px"></textarea>
                    </li>

                    <button class="div_btn2" onclick="a();">提交</button>
                </ul>

            </form>
        </div>
        <div class="medium22" id="div2">
            <form method="post">
                <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
                <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
                <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
            </form>
            <br>
            <div class="medium1">
                <form id="search" name="search">
                    <input type="text" name="pname" id="pname" placeholder="请输入医院名称" style="font-size: 20px"/>
                </form>
                <button class="div_btn1" style="size: 50px" onclick="queryDoc(0)">查询</button>
            </div>
            <br><br><br>
            <hr>
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>医院名称</th>
                    <th>医院简介</th>
                    <th>医院管理</th>
                </tr>

            </table>
            <br>
            <div class="title3" style="text-align: center">
                <button class="div_btn" onclick="queryDoc(1)">首页</button>
                <button class="div_btn" onclick="queryDoc(2)">上一页</button>
                <span class="" id="showInfo" style="color: black"> 第 1 页 / 共 1 页 </span>
                <button class="div_btn" onclick="queryDoc(3)">下一页</button>
                <button class="div_btn" onclick="queryDoc(4)">末页</button>
            </div>
        </div>
    </div>
</div>

<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script type="application/javascript">

    function v1show() {
        document.getElementById('div2').style.display = "none";
        document.getElementById('div1').style.display = "block";

    }

    function v2show() {
        document.getElementById('div1').style.display = "none";
        document.getElementById('div2').style.display = "block";
        queryDoc(0);
    }

    function queryDoc(key) {
        var curPage = $("#curPage").val();
        var maxPage = $("#maxPage").val();
        var row4Page = $("#row4Page").val();
        var pname = $("#pname").val();
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
            url: "/queryHospitalList.do",
            data:
                {
                    "pname": $("#pname").val(),
                    "curPage": $("#curPage").val(),
                    "row4Page": $("#row4Page").val(),
                    "maxPage": $("#maxPage").val()
                }
            ,
            type: "Post",
            success: function (page) {
                // alert("9999999");
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
                "<td><h5> " + data.hosId + "</h5> </td>" +
                "<td>" + data.hosName + "</td>" +
                "<td>" + data.hospitalGen + "</td>" +
                "<td><button class='div_btn' onclick='newDelete(" + data.hosId + ")'>删除医院</button></td>" +
                "</tr>";

            $("#table").append(htmlStr);
        });
    }

    function newDelete(id) {

        $.ajax({
            url: "/rootDeleteHos.do",
            data:
                {
                    "hosId": id
                }
            ,
            type: "Post",
            success: function () {
                v2show();
            },
            error: function () {
                alert("该医院不可被删除");
            }
        });
    }


    function a() {
        /* alert("插入成功");*/
        var name = $('#name').val();
        var department = $('#department').val();
        var area = $('#area').val();
        $.ajax({
            url:
                "/insertHospital.do",
            data:
                {
                    "name": name,
                    "department": department,
                    "area": area
                }
            ,
            type: "Post",
            success: function () {
                alert("插入成功")
            },
            error: function () {
                alert("error");
            }
        });
    }
</script>
</body>
</html>
