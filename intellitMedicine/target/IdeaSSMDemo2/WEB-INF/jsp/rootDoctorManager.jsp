<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/2
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>医生管理</title>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>
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
        .daohang a{
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

        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* *x选择器：选择所有元素 */
        * {
            box-sizing: border-box;
        }

        .row {
            display: flex;
            height: 100%;
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
            /*padding-top: 10px;*/
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
        .medium1{
            display: flex;
            flex-direction: row;
            float: right;
        }
        #myMenu input{
            border: none;
            color: white;
            width: 100%;
            height: 50px;
            font-size: 24px;
            background-color:#337ab7;
        }
        .medium21{
            display: block;
            /*margin-top: 10px;*/
            /*margin-left: 1%;*/
            margin-bottom: 15%;
        }
        .medium21 ul{
            /*margin-top: 10px;*/
            margin-left: 13%;
        }
        .medium21 ul li{
            font-size: 24px;
            color: black;
            margin-bottom: 10px;
            padding-top:10px;
        }
        .table{
            background-color: white;
        }
        .table th{
            text-align: center;
        }
        .table td{
            vertical-align: middle;
            text-align: center;
        }
        .input-group{
            float:left !important;
        }
    </style>


</head>
<body>
<c:if test="${!empty insertMedicineState}">
    <script type="text/javascript">
        alert("${insertMedicineState}");
    </script>
</c:if>
<div class="header">
    <div class="head">
        <p >医生管理</p>
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
        <ul id="myMenu" >

            <li><input type="button" id="v1" value="医生注册审批" onclick="v1show()"></li>
            <li><input type="button" id="v2" value="管理已注册医生" onclick="v2show()"></li>
        </ul>
    </div>
    <div class="right" >
        <%--        <hr>--%>
        <div class="span12  center-block" id="div1">
            <div style="margin-top: 2%">
                <table class="table" id="docTable">
                    <tr>
                        <th>医生头像</th>
                        <th>医生名称</th>
                        <th>所属医院</th>
                        <th>所属科室</th>
                        <th>查看资质证书</th>
                        <th>管理医生</th>
                    </tr>
                </table>
                <div class="text-center">
                    <button class="btn btn-success" onclick="queryDoctor(1)">首页</button>
                    <button class="btn btn-success" onclick="queryDoctor(2)">上一页</button>
                    <span class="control-label" id="showInfo1" style="color: #000000"> 第 1 页 / 共 1 页 </span>
                    <button class="btn btn-success" onclick="queryDoctor(3)">下一页</button>
                    <button class="btn btn-success" onclick="queryDoctor(4)">末页</button>
                </div>
            </div>
        </div>
        <div class="span12  center-block" style="margin-top: 1%;margin-left: 1%;display: none" id="div2" >

            <div style="display: inline">
                <form>
                    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
                    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
                    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
                    <div class="input-group input-group-sm col-sm-3" style="margin-left:59%">
                        <span class="input-group-addon">医生名称:</span>
                        <input type="text" id="queryDoctorName" class="form-control" placeholder="请输入医生名称" name="queryDoctorName">
                    </div>
                </form>
                <button class="btn btn-sm col-sm-1 btn-success" onclick="queryDoctorByName()">查询</button>
            </div>
            <br>
            <div style="margin-top: 2%">
                <table class="table" id="userTable">
                    <tr>
                        <th>医生头像</th>
                        <th>医生名称</th>
                        <th>医生性别</th>
                        <th>所属医院</th>
                        <th>所属科室</th>
                        <th>管理医生</th>
                    </tr>
                </table>
                <div class="text-center">
                    <button class="btn btn-success" onclick="queryUser(1)">首页</button>
                    <button class="btn btn-success" onclick="queryUser(2)">上一页</button>
                    <span class="control-label" id="showInfo" style="color: #000000"> 第 1 页 / 共 1 页 </span>
                    <button class="btn btn-success" onclick="queryUser(3)">下一页</button>
                    <button class="btn btn-success" onclick="queryUser(4)">末页</button>
                </div>
            </div>

        </div>
    </div>
</div>
<div style="  width:100%; height:20px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script>
    // function right(name1,name2) {
    //     var target1,target2;
    //     target1=document.getElementById(name1);
    //     target2=document.getElementById(name2);
    //     target1.style.display="block";
    //     target2.style.display="none";
    //     $("#v2").click(function () {
    //         alert("dfasdgfasdg");
    //     });
    // }
    function v1show() {
        document.getElementById('div2').style.display="none";
        document.getElementById('div1').style.display="block";
        queryDoctor(0);
    }

    function v2show() {queryUser(0);
        document.getElementById('div1').style.display="none";
        document.getElementById('div2').style.display="block";

    }
    $(function(){
        queryDoctor(0);
    });


    function queryDoctorByName(){
        queryUser(0);
    }

    function queryDoctor(key) {
            var curPage = $("#curPage").val();
            var maxPage = $("#maxPage").val();
            var row4Page = $("#row4Page").val();
            switch (key){
                case 0:
                    $("#curPage").val(1);
                    $("#maxPage").val(1);
                    $("#row4Page").val(4);
                    curPage = 1;
                    maxPage = 1;
                    row4Page = 4;
                    break;
                case 1:
                    $("#curPage").val(1);
                    break;
                case 2:
                    $("#curPage").val(curPage>1?curPage-1:curPage);
                    break;
                case 3:
                    $("#curPage").val(curPage<maxPage?curPage*1+1:maxPage);
                    break;
                case 4:
                    $("#curPage").val(maxPage);
                    break;
            }
            // alert($("#symptom").val()) ;
            $.ajax({
                url:"rootCheckDoctor.do",
                data:{
                    curPage: $("#curPage").val(),
                    row4Page: $("#row4Page").val(),
                    maxPage: $("#maxPage").val(),
                },
                type:"Post",
                success:function(page){
                    refreshDoctorTable(page);
                },
                error:function(){
                    alert("SOMETHING WRONG ");
                }
            });
    }

    function refreshDoctorTable(page){
        $("#curPage").val(page.curPage);
        $("#maxPage").val(page.maxPage);
        $("#row4Page").val(page.row4Page);
        $("#showInfo1").html(" 第 "+page.curPage+" 页 / 共 "+page.maxPage+" 页 ");
        var userList = page.objList;
        //获取表格行集合
        var trList = $("#docTable tr");
        //遍历删除表格行
        $.each(trList,function (index,item) {
            if(index>0){
                $(item).remove();
            }
        });
        // src='"+data+"'
        //刷新表格行
        userList.forEach(function(doctor){
            var headAdd = doctor.headAdd;
            var certAdd = doctor.certAdd;
                // alert(certAdd);
            var htmlStr = "<tr>" +
                "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+doctor.doctorName+"</td>"+
                "<td>"+doctor.hosId+"</td>"+
                "<td>"+doctor.department+"</td>"+
            "<td><a href='"+certAdd+"'>查看证书</a></td>";
            htmlStr +="<td>" +
                "<button class=\"btn col-sm-5 btn-success center-block\" onclick=\"passDoctor("+doctor.doctorId+")\">     审核通过</button>" +
                "<button class=\"btn col-sm-5 btn-success center-block\" onclick=\"delDoctor("+doctor.doctorId+")\">     审核不通过</button>" +
                "</td></tr>";
            $("#docTable").append(htmlStr);
        });
    }

    /**
     * 删除指定药品
     * @param id
     */
    function passDoctor(doctorId){
        // alert("id=="+doctorId);
        $.ajax({
            url:"rootPassDoctor.do",
            data:
                {
                    doctorId:doctorId,
                },
            type:"Post",
            success:function(data){
                alert("该医生审核通过成功!");
                queryDoctor(0);
                // refreshTable(page);
            },
            error:function(){
                alert("error");
            }
        });
    }

    function queryUser(key){
        var queryDoctorName = $("#queryDoctorName").val();
        var curPage = $("#curPage").val();
        var maxPage = $("#maxPage").val();
        var row4Page = $("#row4Page").val();
        switch (key){
            case 0:
                $("#curPage").val(1);
                $("#maxPage").val(1);
                $("#row4Page").val(4);
                curPage = 1;
                maxPage = 1;
                row4Page = 4;
                break;
            case 1:
                $("#curPage").val(1);
                break;
            case 2:
                $("#curPage").val(curPage>1?curPage-1:curPage);
                break;
            case 3:
                $("#curPage").val(curPage<maxPage?curPage*1+1:maxPage);
                break;
            case 4:
                $("#curPage").val(maxPage);
                break;
        }
        // alert($("#symptom").val()) ;
        $.ajax({
            url:"rootQueryCheckedDoc.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                queryDoctorName:  $("#queryDoctorName").val(),
            },
            type:"Post",
            success:function(page){
                refreshTable(page);
            },
            error:function(){
                alert("SOMETHING WRONG ");
            }
        });
    }
    /**
     * 刷新表格
     * @param page
     */
    function refreshTable(page){
        var i = 1;
        $("#curPage").val(page.curPage);
        $("#maxPage").val(page.maxPage);
        $("#row4Page").val(page.row4Page);
        $("#showInfo").html(" 第 "+page.curPage+" 页 / 共 "+page.maxPage+" 页 ");
        var userList = page.objList;
        //获取表格行集合
        var trList = $("#userTable tr");
        //遍历删除表格行
        $.each(trList,function (index,item) {
            if(index>0){
                $(item).remove();
            }
        });
        //刷新表格行
        userList.forEach(function(doctor){
            var headAdd =doctor.headAdd;
            var htmlStr = "<tr>" +
                "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+doctor.doctorName+"</td>"+
                "<td>"+doctor.sex+"</td>"+
                "<td>"+doctor.hosId+"</td>"+
                 "<td>"+doctor.department+"</td>";
            htmlStr +="<td>" +
                "<button class=\"btn col-sm-6-8  btn-success center-block\" onclick=\"delDoctor("+doctor.doctorId+")\">     删除该医生</button>" +
                "</td></tr>";
            $("#userTable").append(htmlStr);
        });
    }


    /**
     * 删除指定药品
     * @param id
     */
    function delDoctor(doctorId){
        // alert("id=="+doctorId);
        $.ajax({
            url:"delDoctor.do",
            data:
                {
                    doctorId:doctorId,
                },
            type:"Post",
            success:function(data){
                alert("删除该医生成功!");
                queryUser(0);
                queryDoctor(0);
            },
            error:function(){
                alert("error");
            }
        });
    }


</script>
</body>
</html>
