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
    <title>社区机构管理</title>

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
            /*padding: 15px 0;*/
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
            margin-left: 20%;
            margin-right: 20%;
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

        .div_btn1 {
            font-size: 18px;
            width: 120px;
            height: 30px;
            margin-left: 28%;
            margin-bottom: 5%;
        }

        .div_btn2{
            font-size: 18px;
            /*margin-right: 30px;*/
            /*margin-left: 60px;*/
            width: 120px;
            margin-bottom: 20px;
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
        <p >社区管理平台</p>
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
            <li><input type="button" id="v1" value="新增社区" onclick="v1show()"></li>
            <li><input type="button" id="v2" value="管理社区" onclick="v2show()"></li>
        </ul>
    </div>
    <div class="right">
        <hr>

        <div class="medium21" id="div1">
            <form id="medicineInformation" style="border: black 1px solid;width: 500px;margin-top: 3%;margin-left: 10%" method="post">
                <br>
                <ul>
                    <li>
                        社区名称：<input type="text" id="name" name="name">
                    </li>
                    <li>
                        社区简介：<br><br><textarea name="area" id="area" cols="30" rows="5" style="font-size: 20px"
                                           placeholder="请依次输入联系方式、位置信息、社区简介，形如“联系方式,位置信息,社区简介”"></textarea>
                    </li>
                    <button class="div_btn1" onclick="b();">提交</button><br>
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
                <form id="search" name="search" method="post">
                    <input type="text" name="pname" id="pname" placeholder="请输入社区名称" style="font-size: 20px"/>
                </form>
                <button class="div_btn2"  onclick="queryDoc(0)">查询</button>
            </div>
            <br><br><br>
            <hr>
            <table id="table">
                <tr>
                    <th>ID</th>
                    <th>社区名称</th>
                    <th>社区简介</th>
                    <th>社区管理</th>
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
            url: "/queryCommunList.do",
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

            var str=data.comInfor;
            var array=str.split("，");
            if(array[0]==null)array[0]="";
            if(array[1]==null)array[1]="";
            if(array[2]==null)array[2]="";


            var htmlStr = "<tr style='padding: 10px'>" +
                "<td><h5> " + data.communityId + "</h5> </td>" +
                "<td>" + data.comName + "</td>" +
                "<td><label style='text-align: center;width: 40px;height:20px '>电话："+array[0]+"</label><br/>" +
                "<label style='text-align: center;width: 40px;height:20px '>位置：" + array[1] + "</label><br/>" +
                "<label style='text-align: center;width: 40px;height:20px '>简介：" + array[2] + "</label></td>" +
                "<td><button class='div_btn' onclick='newDelete(" + data.communityId + ")'>删除社区</button></td>" +
                "</tr>";

            $("#table").append(htmlStr);
        });
    }

    function newDelete(id) {

        $.ajax({
            url: "/rootDeleteCommun.do",
            data:
                {
                    "communityId": id
                }
            ,
            type: "Post",
            success: function () {
                v2show();
            },
            error: function () {
                alert("error");
            }
        });
    }

    function b() {

        var name = $('#name').val();
        // var department=$('#department').val();
        var area = $('#area').val();
        $.ajax({
            url:
                "/insertCommun.do",
            data:
                {
                    "name": name,
                    "area": area
                }
            ,
            type: "Post",
            success: function () {
                alert("插入成功");
                v1show();

            },
            error: function () {
                alert("error");
            }
        });
    }
</script>
</body>
</html>
