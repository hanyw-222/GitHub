<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/6/27
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员任务指派</title>
    <style>
        .title {
            text-align: left;
            padding: 10px;
            margin: 0;
        }
        .title2 {
            text-align: center;
            padding: 10px;
            margin: 10px;

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
        <p >任务指派</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>


<form method="post">
    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
</form>


<div class="title2">
    <h2>待处理申请</h2>
</div>
<hr>

<div>
    <table width="90%" height="100px" style="table-layout:fixed;text-align:center;padding-top: 30px" id="table">
        <tr>
            <th>申请用户</th>
            <th>具体需求</th>
            <th style="width: 100px;text-align: center">请求时间</th>
            <th></th>
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
<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script type="application/javascript">

    $(function () {
        queryDoc(0);

    });


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
            url: "/rootCheckAssign.do",
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
    function newController(applyId) {


        $.ajax({
            url: "/rootReleaseMission.do",
            data:
                {
                    "applyId":applyId
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
        if (!List) {
            return;
        }
        List.forEach(function (data) {
            var check=data.is_checked;

            var htmlStr = "<tr style='padding: 10px'>" +
                "<td><h5> "+data.userId+"</h5> </td>"+
                "<td>" +data.applyIntro+"</td>" +
                "<td>"+data.applyTime+"</td>"+
                "<td><button class='div_btn' onclick='newController("+data.applyId+")'>处理</button></td>"+
                "</tr>";

            if(check!="true"){
                $("#table").append(htmlStr);
            }

        });
    }
</script>
</body>
</html>
